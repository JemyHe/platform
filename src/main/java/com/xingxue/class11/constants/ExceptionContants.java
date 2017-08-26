package com.xingxue.class11.constants;

/**
 * Created by Administrator on 2017/6/30.
 */
public class ExceptionContants {

    /*********************************通用***************************/

    public static final String USER_DATA_ERROR = "参数不合法";

    public static final String INNER_ERROR = "内部错误，查看日志";


    /*********************************用户模块***************************/

    public static final String USER_NOT_EXIST = "用户不存在";

    public static final String USER_PASSWORD_WRONG = "用户密码错误";

    public static final String USER_NAME_EXSIST = "用户名已存在";

    public static final String USER_REIGIST = "用户名可以注册";


    /*********************************商品模块***************************/

    public static final String DEAL_NOT_ENOUGH = "库存不足";

    public static final String DEAL_OUT_SEAL = "商品已售罄";

    public static final String DEAL_OUT_MAXBUY = "超出最大购买量";

    public static final String ADD_CART = "添加成功";

    /********************************订单模块****************************/

    public static final String TOTAL_PRICE_EMPTY = "总价不能为空";

    public static final String ORDER_EMPTY = "订单不能为空";

    public static final String ORDER_DETAIL_EMPTY = "订单详细信息不能为空";



}
