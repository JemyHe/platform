package com.xingxue.class11.controller;

/**
 * Created by Administrator on 2017/6/30.
 */

import com.xingxue.class11.constants.ExceptionContants;
import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.User;
import com.xingxue.class11.exception.BaseException;
import com.xingxue.class11.exception.UserException;
import com.xingxue.class11.framework.entity.Result;
import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.service.AddressService;
import com.xingxue.class11.service.UserService;
import com.xingxue.class11.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  一.请求对应的所有方法
 *  1.跳转到登录页
 *  2.跳转到注册页
 *  3.具体执行登录操作
 *  4.具体执行注册操作
 *  5.根据用户名查询是否已存在
 *
 *  二.拦截器 （filter）
 *  登录操作----》需要拦截器
 *  spring提供的拦截器
 *  怎么用
 *  1.配置需要拦截的请求路径
 *  2.配置拦截到什么位置
 *
 *  IP地址查询---》需要拦截器 ---》所有请求
 *
 *  三.登录后的用户信息存储在什么位置？
 *      session
 *      cookie 时效性更长
 *      cookie在本项目中四个使用场景
 *      1.执行登录操作成功后---》存储cookie
 *      2.登录拦截器中，如果该用户没有登录（从cookie中取对应用户），则跳转到登录页
 *      3.注册成功后----》存储cookie
 *      4.注销登录操作----》删除cookie
 *
 *      工具类：cookieUtil
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;

    @Autowired private AddressService addressService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String prelogin(){
        return "user/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/regist" , method = RequestMethod.GET)
    public String preregist(){
        return "user/regist";
    }

    /**
     * 执行登录操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/doLogin" , method = RequestMethod.POST)
    public String login(HttpServletResponse response,User user){
        try {
            User loginUser = userService.login(user);
            if(loginUser!=null){
                WebUser webUser = new WebUser(loginUser.getId().toString(),loginUser.getName());
                CookieUtil.setLoginUser(response,webUser);
                return "redirect:/";
            }else{
                //没有登录成功，则返回登录页面
                return "redirect:/user/login";
            }
        } catch (UserException e) {
            logger.error(e.getMessage());
            return "redirect:/user/login";
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return "redirect:/user/login";
        }

    }

    /**
     * 注册操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletResponse response,User user){
        try {
            User register = userService.register(user);
            if(register!=null){
                WebUser webUser = new WebUser(register.getId().toString(),register.getName());
                CookieUtil.setLoginUser(response,webUser);
                return "redirect:/";
            }else{
                //注册失败跳转到注册页
                return "redirect:/user/regist";
            }
        } catch (UserException e) {
            logger.error(e.getMessage());
            return "redirect:/user/regist";
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return "redirect:/user/regist";
        }

    }

    /**
     *  restful风格---》url的设计风格
     *  模块/参数/动作
     *  用户的详情页---》user/{userid}/detail
     *  用户关注的商品---> user/{userid}/{username}/goods
     *
     * 根据用户名查询是否已存在
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkName/{name}",method = RequestMethod.POST)
    public Result<String> checkName(@PathVariable(value = "name") String name){
        try {
            User byUsername = userService.getByUsername(name);
            if(byUsername != null){
                return new Result(false, ExceptionContants.USER_NAME_EXSIST);
            }
            return  new Result(true,ExceptionContants.USER_REIGIST);
        } catch (UserException e) {
            logger.error(e.getMessage());
            return new Result(false, ExceptionContants.INNER_ERROR);
        } catch (BaseException e) {
            logger.error(e.getMessage());
            return new Result(false, ExceptionContants.INNER_ERROR);
        }
    }


    /**
     * 保存收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "address/save",method = RequestMethod.POST)
    public String saveAddress(Address address, HttpServletRequest request){

        try {
            WebUser webUser = CookieUtil.getLoginUser(request);
            address.setUserId(Long.valueOf(webUser.getId()));
            addressService.save(address);
            return "redirect:/";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:/cart";
        }

    }

    /**
     * 设置默认地址
     * @param addId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "address/default",method = RequestMethod.POST)
    public Result<String> defaultAddress(Integer addId,HttpServletRequest request){
        Result<String> result = new Result<>();
        try {
            WebUser webUser = CookieUtil.getLoginUser(request);
            addressService.updateType(addId,Long.valueOf(webUser.getId()));
            result.setSuccess(true);
            result.setData("成功");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setSuccess(false);
            result.setError(e.getMessage());
            return result;
        }
    }

    /**
     * 更新收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "address/update",method = RequestMethod.POST)
    public Result<String> updateAddress(Address address){
        return null;
    }

    /**
     * 删除收货地址
     * @param addId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "address/delete",method = RequestMethod.POST)
    public Result<String> deleteAddress(Integer addId){
        Result<String> result = new Result<>();
        try {
            addressService.deleteAddress(addId);
            result.setSuccess(true);
            result.setError("成功");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setSuccess(false);
            result.setError(e.getMessage());
            return result;
        }
    }


}
