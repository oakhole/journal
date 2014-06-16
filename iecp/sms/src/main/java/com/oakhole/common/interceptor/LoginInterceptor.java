package com.oakhole.common.interceptor;

import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.httpApi.actions.*;
import com.oakhole.systemManage.actions.systemManage.LoginManageAction;
import com.oakhole.systemManage.model.AppUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义登陆拦截器
 * 
 */
@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 判断用户是否已经登录，如果没有登录，则为非法请求，进行拦截
	 */
	public String intercept(ActionInvocation arg0) throws Exception {
		// 判断是否请求为登录界面(login),如果是则不拦截

		if (LoginManageAction.class == arg0.getAction().getClass()
				|| Mt.class == arg0.getAction().getClass()
				|| Mo.class == arg0.getAction().getClass()
				|| Getreport.class == arg0.getAction().getClass()
				|| Getbalance.class == arg0.getAction().getClass()
				|| Report.class == arg0.getAction().getClass()
				|| Receive.class == arg0.getAction().getClass())
			return arg0.invoke();

		// 如果是请求其他页面，进行拦截
		AppUser appUser = (AppUser) arg0.getInvocationContext().getSession()
				.get(GlobalConstants.GLOBALAPPUSER);
		if (null == appUser)
			return Action.LOGIN;

		return arg0.invoke();
	}

}