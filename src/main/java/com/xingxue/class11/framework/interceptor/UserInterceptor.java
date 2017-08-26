package com.xingxue.class11.framework.interceptor;

import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.service.CartService;
import com.xingxue.class11.util.CookieUtil;
import com.xingxue.class11.util.SpringApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户拦截器,处理页面显示的用户名\登陆状态信息
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //从cookie中取出用户信息
        WebUser webUser = CookieUtil.getLoginUser(request);
        //重置cookie的时效期限
        if (null != webUser) {
            CookieUtil.setLoginUser(response, webUser);
        }

        if (null != modelAndView && null != webUser && StringUtils.isNotEmpty(webUser.getUsername())) {
            Long cartSize = SpringApplicationContext.getBean(CartService.class).getCartSize(Long.parseLong(webUser.getId()));
            modelAndView.addObject("cartSize", cartSize);
            modelAndView.addObject("username", webUser.getUsername());
        }

        //如果是重定向请求需要清除model
        //重定向后如果不清除model数据，会将参数显示在url后面
        if (null != modelAndView && modelAndView.getViewName().startsWith("redirect:")) {
            modelAndView.getModel().clear();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}