package com.xingxue.class11.util;

import com.xingxue.class11.framework.entity.WebUser;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;


/**
 * 1.cookie key-value
 * 2.用户登录 设计cookie的键值  ui ----- 用户信息的字符串
 * 3.cookie key，value； 生效时间：30分钟 1小时 5分钟  ms 60*60*60；生效路径："/"
 * 4.登录成功之后---》添加cookie销毁cookie
 * 	  注销用户-----》
 * 	  根据键查询用户cookie
 *
 *
 * cookie?不安全？怎么办？如果黑客攻击？
 *
 * 1.cookie里是不存密码，id这种东西；用户名
 * 2.session中， cookie是存在客户端的，session里存在服务器端
 * 3.验证码，异地登录，输错三次密码，
 * 4.委托第三方登录----》qq登录，微信登录，微博登录
 * 		接受登录是否成功的标识---成功{username:xxxx}
 * 5.http://  https://
 * 6.防止sql注入-----#{}??      ${} !!
 * 			String sql = ""+"";
 *
 *
 *
 *
 * Cookie工具类
 * 1.时效期限  -----》 秒
 * 2.路径 cookie的有效路径 ---》 / --->所有路径
 * 						   ---》 /cart --->所有cart开头的请求 可以取到该cookie
 * cookie的组成--》键值对
 *  key --- 》 ui
 *  value --->  username（User实体类中的） ---->  WebUser -----》前台看到
 *
 */
public class CookieUtil {

	/**
	 * 默认Cookie过期时间（单位：秒）三十分钟
	 */
	public static final int MAX_AGE = 60 * 30;

	/**
	 * 默认Cookie过期时间（单位：秒） 一周
	 */
	public static final int MAX_AGE_WEEK = 60 * 24 * 60 * 7;

	
	/**
	 * 用户登陆信息Cookie名字
	 */
	public static final String USER_INFO = "ui";

	/**
	 * 向Cookie中写入用户信息
	 * @param response
	 * @param user
     */
	public static void setLoginUser(HttpServletResponse response, WebUser user) {
		if (null == response || null == user) {
			return;
		}
		String userId = user.getId();
		String username = user.getUsername();
		try {
			username = URLEncoder.encode(user.getUsername(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//为什么不把实体直接作为cookie的值，而是拼接一个字符串作为cookie的值
		//对象占用的资源大，不适合直接存为value
		StringBuilder cookieValue = new StringBuilder();
		// 12131231|maobo  str.split("|")
		cookieValue.append(userId).append("|").append(username);
		addCookie(response,USER_INFO,cookieValue.toString());
	}

	/**
	 * 从Cookie中取出用户信息
	 * @param request
	 * @return
	 */
	public static WebUser getLoginUser(HttpServletRequest request) {

		if (null == request) {
			return null;
		}
		//从cookie里取出用户信息(三个字段)
		String value = getCookieValue(USER_INFO, request);
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		//12123123|maobo
		String[] array = value.split("\\|");
		//User（数据库查询出的实体）-----> 前台展示----->多余查一次数据库，数据很冗余，达不到前台的展示效果
		//WebUser(username) ------->前台展示
		WebUser user = new WebUser();
		user.setId(array[0]);
		try {
			user.setUsername(URLDecoder.decode(array[1], "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			user.setUsername(array[1]);
		}
		return user;
	}

	/**
	 * 添加一个cookie
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		//所有路径下有效
		cookie.setPath("/");
		//生命周期
		cookie.setMaxAge(MAX_AGE);
		response.addCookie(cookie);
	}

	/**
	 * 通过键值取出cookie的值
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getCookieValue(String name, HttpServletRequest request) {

		if (null == request || StringUtils.isEmpty(name)) {
			return null;
		}
		//取出所有cookie
		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (Objects.equals(cookie.getName(), name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 删除Cookie
	 * @param response HttpServletResponse
	 * @param name Cookie名
	 * @param path Cookie Path
     */
	public static void removeCookie(HttpServletResponse response, String name, String path) {
		if (null == response || StringUtils.isEmpty(name) || StringUtils.isEmpty(path)) {
			return;
		}
		Cookie cookie = new Cookie(name, "");
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}