package com.oakhole.systemManage.actions.systemManage;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.RandomValidateCode;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.systemManage.dao.AdviceManageDao;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.model.Advice;
import com.oakhole.systemManage.model.AppUser;

/**
 * 系统登陆登出管理
 * 
 * @author oakhole
 * 
 */
@SuppressWarnings("serial")
@Results({ @Result(name = "index", location = "/index.jsp", type = "redirect"),
		@Result(name = "login", location = "/login.jsp", type = "redirect") })
public class LoginManageAction extends BaseAction {

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private AdviceManageDao adviceManageDao;

	/**
	 * 登陆
	 * 
	 * @return errmsg{success:dispatch; faied:not found this account}
	 */
	public String login() {

		AppUser appUser = null;
		String account = "";
		String password = "";
		String errmsg = "";
		boolean flag = false;

		// 判断验证码是否为空和正确
		if (request.getParameter("code") == null
				|| "".equals(request.getParameter("code"))) {
			errmsg = "验证码不能为空";
			flag = false;
		} else {

			String srcCode = (String) session
					.getAttribute(RandomValidateCode.RANDOMCODEKEY);
			String objCode = request.getParameter("code").toUpperCase();
			if (srcCode.equals(objCode)) {

				account = request.getParameter("account");
				password = request.getParameter("password");

				// 验证用户名和密码
				if ("".equals(account) || "".equals(password)) {
					errmsg = "用户名或密码不能为空";
					flag = false;
				} else {
					appUser = this.appUserManageDao
							.findAppUserByAccount(account);
					if (appUser == null) {
						errmsg = "没有此用户";
						flag = false;
					} else {
						if (password.equals(appUser.getPassword())) {
							flag = true;
						} else {
							errmsg = "密码错误";
							flag = false;
						}
					}
				}
			} else {
				errmsg = "验证码错误";
				flag = false;
			}
		}

		if (flag) {
			session.setAttribute(GlobalConstants.GLOBALAPPUSER, appUser);
			this.log.info(account + "登陆成功,登陆ip为：" + request.getRemoteAddr());
			this.writeLog("平台登陆成功");
			/*
			 * try { response.sendRedirect("../"); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */
			String advice_content = getFirstAdvice();
			session.setAttribute("advice_content", advice_content);
			return "index";
		} else {
			session.setAttribute("loginErrMsg", errmsg);
			/*
			 * try { response.sendRedirect("../login.jsp"); } catch (Exception
			 * e) { e.printStackTrace(); }
			 */

			return "login";
		}
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String logout() {

		session.setAttribute(GlobalConstants.GLOBALAPPUSER, null);
		session.invalidate();
		/*
		 * try { response.sendRedirect("../login.jsp"); } catch (IOException e)
		 * { e.printStackTrace(); }
		 */
		return "index";

	}

	public String getFirstAdvice() {
		String sql = "select * from advice where 1=1";

		PageEntity<Advice> page = this.adviceManageDao.findAllPaginator(
				"findAllAdviceTotalCount", "findAllAdvice", sql, "desc");

		Advice advice = page.getResult().get(0);

		String advice_content = advice.getContent();

		return advice_content;
	}

	/**
	 * 验证码生成
	 * 
	 * @return
	 */
	public String validateCode() {

		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandcode(request, response);// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

	public void setAdviceManageDao(AdviceManageDao adviceManageDao) {
		this.adviceManageDao = adviceManageDao;
	}
}
