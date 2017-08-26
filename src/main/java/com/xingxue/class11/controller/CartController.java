package com.xingxue.class11.controller;

import com.alibaba.fastjson.JSON;
import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.Cart;
import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.DealException;
import com.xingxue.class11.exception.OrderException;
import com.xingxue.class11.framework.dto.CartDTO;
import com.xingxue.class11.framework.entity.BaseEntity;
import com.xingxue.class11.framework.entity.Result;
import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.service.AddressService;
import com.xingxue.class11.service.CartService;
import com.xingxue.class11.service.DealService;
import com.xingxue.class11.service.UserService;
import com.xingxue.class11.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 购物车
 */
@Controller
public class CartController  {

    private Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired private CartService cartService;

    @Autowired private DealService dealService;

    @Autowired private AddressService addressService;

    /*@Autowired private OrderService orderService;

    @Autowired private UserService userService;
    */

    /**
     * 加入购物车
     * @param skuId
     * @param count
     * @param request
     * @return
     */
    @RequestMapping(value = "/cart/default/{skuId}/{count}",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addToCart(@PathVariable Long skuId, @PathVariable Integer count, HttpServletRequest request) {
        try {
            String userId = CookieUtil.getLoginUser(request).getId();
            cartService.addDeal(skuId, Long.parseLong(userId), count);
            return new Result(true,ExceptionContants.ADD_CART);
        } catch (DealException e) {
            logger.error("Add deal to cart error, message : {}", e.getMessage());
            return new Result(false, e.getMessage());
        } catch (BaseException e) {
            logger.error("Add deal to cart error, message : {}", e.getMessage());
            return new Result(false,ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 进入购物车
     * 一个cart数据----》一个商品信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model, HttpServletRequest request) {
        try {
            WebUser webUser = CookieUtil.getLoginUser(request);//当前登录用户
            if (null != webUser) {
                Long userId = Long.parseLong(webUser.getId());
                List<Cart> carts = cartService.getCartByUserId(userId);
                if (!CollectionUtils.isEmpty(carts)) {//购物车非空
                    List<Long> dealIds = new ArrayList<Long>();
                    for(Cart cart:carts){
                        dealIds.add(cart.getDealId());
                    }
                    List<Deal> deals = dealService.getDealsForCart(dealIds);
                    List<CartDTO> cartDTOs = new ArrayList<>();//CartDTO用于页面显示
                    //需要的数据结构---》一个cart对应一个deal
                    //list:遍历cart，取出dealid，遍历deals,如果dealid和遍历到的实体id相同
                    //双层for循环
                    /*for(Cart cart:carts){
                        for(Deal deal:deals){
                            if(cart.getDealId() == deal.getId()){
                                //构建dto
                            }
                        }
                    }*/
                    //Map----->key,value ---->dealId,deal
                    Map<Long, Deal> dealMap = BaseEntity.getEntityMap(deals);
                    for (Cart cart : carts) {
                        cartDTOs.add(new CartDTO(cart, dealMap.get(cart.getDealId())));
                    }
                    model.addAttribute("carts", cartDTOs);
                }
                return "/cart/cart";
            }
            return "redirect:/user/login";
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return "redirect:/user/login";
        } catch (DealException e){
            logger.error(e.getMessage());
            return "redirect:/user/login";
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return "redirect:/user/login";
        }
    }

    /**
     * 购物车数量加一或减一
     * @param cartId
     * @param type
     * @return
     */
    @RequestMapping(value = "/cart/{cartId}/{type}", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> count(@PathVariable Long cartId, @PathVariable Integer type) {
        try {
            if (null != type && 1 == type) {
                cartService.increaseCartDealCount(cartId);
            } else if (null != type && 0 == type) {
                cartService.decreaseCartDealCount(cartId);
            }
            return new Result(true,ExceptionContants.ADD_CART);
        } catch (DealException e){
            logger.error(e.getMessage());
            return new Result(false,ExceptionContants.INNER_ERROR);
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return new Result(false,ExceptionContants.INNER_ERROR);
        }
    }

    /**
     * 进入结算页面
     * @param cartIds
     * @param totalPrice
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/settlement", method = RequestMethod.POST)
    public String settlement(String cartIds, Integer totalPrice, Model model, HttpServletRequest request) {


        try {
            if(totalPrice<=0){
                throw new OrderException(ExceptionContants.TOTAL_PRICE_EMPTY);
            }
            String[] split = cartIds.split(",");
            List<Long> ids = new ArrayList<>();
            for(String cartId:split){
                ids.add(Long.valueOf(cartId));
            }
            //拿到购物车数据
            List<Cart> carts = cartService.getCartsByIds(ids);
            List<Long> skuIds = new ArrayList<>();
            for(Cart cart:carts){
                skuIds.add(cart.getDealSkuId());
            }
            //拿到商品数据
            List<Deal> deals = dealService.getBySkuIds(skuIds);
            Map<Long, Deal> skuIdMap = new HashMap<>();
            for(Deal deal:deals){
                skuIdMap.put(deal.getSkuId(),deal);
            }
            //组装前台需要的数据格式
            List<CartDTO> cartDTOs = new ArrayList<>();//CartDTO用于页面显示
            for (Cart cart : carts) {
                cartDTOs.add(new CartDTO(cart, skuIdMap.get(cart.getDealSkuId())));
            }
            model.addAttribute("carts", cartDTOs);
            model.addAttribute("cartsJson",JSON.toJSONString(cartDTOs));

            //拿到用户的地址信息
            WebUser webUser = CookieUtil.getLoginUser(request);
            List<Address> addresses = addressService.getByUserId(Long.valueOf(webUser.getId()));
            model.addAttribute("addresses", addresses);
            model.addAttribute("totalPrice", totalPrice);

            return "/order/settlement";
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return "redirect:/cart";
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return "redirect:/cart";
        } catch (Exception e){
            logger.error(e.getMessage());
            return "redirect:/cart";
        }
    }

    /**
     * 选择商品直接结算,立即购买
     * @param skuId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/settlement/{skuId}")
    public String settlement(@PathVariable Long skuId, HttpServletRequest request, Model model) {
        try {
            WebUser webUser = CookieUtil.getLoginUser(request);
            Deal deal = dealService.getBySkuId(skuId);
            Cart cart = new Cart();
            cart.setCount(1);
            cart.setDealId(deal.getId());
            cart.setDealSkuId(skuId);
            cart.setUserId(Long.valueOf(webUser.getId()));
            CartDTO cartDTO = new CartDTO(cart, deal);
            model.addAttribute("carts", cartDTO);

            List<Address> addresses = addressService.getByUserId(Long.valueOf(webUser.getId()));
            model.addAttribute("addresses", addresses);
            return "/order/settlement";
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            return "redirect:/cart";
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return "redirect:/cart";
        }
    }




}
