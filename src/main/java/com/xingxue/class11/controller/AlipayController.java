package com.xingxue.class11.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xingxue.class11.constants.AlipayConfig;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.framework.dto.CartDTO;
import com.xingxue.class11.framework.entity.Pair;
import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.service.AddressService;
import com.xingxue.class11.service.CartService;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.service.OrderService;
import com.xingxue.class11.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 支付宝支付
 */
@Controller
public class AlipayController{

    @Autowired private CartService cartService;

    @Autowired private DealService dealService;

    @Autowired private OrderService orderService;

    @Autowired private AddressService addressService;

    @RequestMapping(value="/alipay", method = RequestMethod.POST)
    public void alipay(HttpServletRequest request, HttpServletResponse response, String carts, Long addressId,
                       Integer payType, Integer totalPrice) throws Exception {

        WebUser webUser = CookieUtil.getLoginUser(request);

        //保存订单
        List<CartDTO> cartDTOS = JSON.parseArray(carts, CartDTO.class);
        List<Pair<Cart,Deal>> cartDTOs = new ArrayList<>();
        for(CartDTO cart:cartDTOS){
            cartDTOs.add(new Pair<>(cart.getCart(),cart.getDeal()));
        }
        Address address = addressService.getById(addressId);
        Long orderId = orderService.order(Long.valueOf(webUser.getId()), cartDTOs, address, totalPrice, payType);

        //调用支付宝
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderId.toString();
        //付款金额，必填
        String total_amount = totalPrice.toString();
        //订单名称，必填
        String subject = orderId.toString()+":"+cartDTOS.get(0).getDeal().getDealTitle();
        //商品描述，可空
        String body = "感谢使用支付宝";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ 0.01 +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(result);
    }

    @RequestMapping(value="/alipay/return")
    public String alipayReturn(HttpServletRequest request, Model model) throws Exception {

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name =  iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //注意：退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //TODO 超过退款期限应保存该记录
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //注意:付款完成后，支付宝系统发送该交易状态通知
                //TODO 应先根据订单号去查询，是否存在
                orderService.payed(Long.parseLong(out_trade_no));
            }

            return "/order/success";

        }else {//验证失败

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
            return "redirect:/";
        }
    }

}
