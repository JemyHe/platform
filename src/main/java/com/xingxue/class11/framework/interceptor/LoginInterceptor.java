package com.xingxue.class11.framework.interceptor;

import com.xingxue.class11.framework.entity.WebUser;
import com.xingxue.class11.util.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 请求结束后执行
	 * @param request
     * @param response
     * @param obj
     * @param ex
     * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
	}

	/**
	 * 请求中
	 * @param request
	 * @param response
	 * @param obj
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * 在拦截之前执行
	 * 返回true 继续执行该请求
	 * 返回false 请求终止
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		//判断cookie中是否有登录信息
		WebUser webUser = CookieUtil.getLoginUser(request);
		if (null == webUser) {
			//没有登录过，跳转到登录页面
			//http https + //: + localhost : 8080
			String basePath = request.getScheme() + "//:" + request.getServerName() + ":" + request.getServerPort();
			response.sendRedirect(basePath + "/user/login");
			return false;
		}
		return true;
	}

}