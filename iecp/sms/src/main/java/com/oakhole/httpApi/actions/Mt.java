package com.oakhole.httpApi.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.common.util.fileUtils.PhoneFileUtils;
import com.oakhole.financialManage.model.Financial;
import com.oakhole.smsManage.dao.SmsManageDao;
import com.oakhole.smsManage.dao.SmsSendManageDao;
import com.oakhole.smsManage.model.SmsSend;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.dao.BadwordsManageDao;
import com.oakhole.systemManage.dao.ChannelGroupManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.ChannelGroup;

@SuppressWarnings("serial")
public class Mt extends BaseAction {

	@Autowired
	private SmsSendManageDao smsSendManageDao;

	@Autowired
	private ChannelGroupManageDao channelGroupManageDao;

	@Autowired
	private AppUserManageDao appUserManageDao;

	@Autowired
	private SmsManageDao smsManageDao;

	@Autowired
	private BadwordsManageDao badwordsManageDao;

	private String account;
	private String password;
	private String mobile;
	private String content;

	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {

		AppUser currentAppUser = this.appUserManageDao
				.findAppUserByAccount(account);

		long channelGroupId = currentAppUser.getChannelGroupId();
		ChannelGroup channelGroup = this.channelGroupManageDao.findObjectById(
				"findChannelGroupById", channelGroupId);

		if (account == null) {
			this.response.getWriter().println("101,account isn't nullable");
		}

		if (password == null) {
			this.response.getWriter().println("102,password isn't nullable");
		}

		if (mobile == null) {
			this.response.getWriter().println("103,phoneNumber isn't nullable");
		}

		if (content == null) {
			this.response.getWriter().println("104,content isn't nullable");
			return null;
		}

		if (account == null || currentAppUser == null) {
			this.response.getWriter().println("1,incorrect account");
			return null;
		}

		if (!currentAppUser.getPassword().equals(password)) {
			this.response.getWriter().println("2,incorrect password");
			return null;
		}

		String[] badwords = getBadwords(channelGroup, content).split(",");

		if ("fail".equals(badwords[0])) {
			this.response.getWriter().println("3," + badwords[1]);
			return null;
		}

		Set<String> correctMobile = checkErr(mobile);
		if (correctMobile.size() == 0
				|| correctMobile.size() > this.getCurrrentSystemSetting()
						.getSendLimitUp()) {
			this.response.getWriter().println(
					"4,incorrect send phoneNumber count");
			return null;
		}
		int totalwords = content.length();
		int contentChargeCount = 0;

		if (totalwords <= 70) {
			contentChargeCount = 1;
		} else {
			contentChargeCount = (totalwords - 70) / (70 - 7) + 2;
		}
		int sendPhoneCount = correctMobile.size();
		int chargeCount = sendPhoneCount * contentChargeCount;
		int currentAppUserBalance = currentAppUser.getBalance();
		if (currentAppUserBalance < chargeCount) {
			this.response.getWriter().println("5,channel balance not enough");
			return null;
		}

		// session不存在则重新创建
		if (session.getAttribute("GlobalConstants.GLOBALAPPUSER") == null) {
			session.setAttribute(GlobalConstants.GLOBALAPPUSER, currentAppUser);
		}

		// 满足以上条件,才可进行推送
		SmsSend smsSend = new SmsSend();
		smsSend.setAccount(currentAppUser.getAccount());
		smsSend.setAppUserId(currentAppUser.getId());
		smsSend.setChannelGroupId(currentAppUser.getChannelGroupId());
		smsSend.setContent(content);
		smsSend.setLinkman(currentAppUser.getLinkman());
		smsSend.setIsVarSms(0); // 普通短信
		smsSend.setPriority(GlobalConstants.getPriority(sendPhoneCount));
		if (currentAppUser.getIsAudit() == 0) { // 当前用户是否需要审核
			smsSend.setAuditStatus(1);
		} else {
			if (currentAppUser.getAuditCondition() <= sendPhoneCount) {
				smsSend.setAuditStatus(0);
			} else {
				smsSend.setAuditStatus(1);
			}
		}
		smsSend.setSendStatus(0); // 初始发送状态为待发送
		smsSend.setSendTime(new Date());
		smsSend.setSendPhoneCount(sendPhoneCount);
		smsSend.setChargeCount(chargeCount);
		smsSend.setTotalwords(totalwords);
		smsSend.setSendType(1); // 接口提交

		// 存放短信的缓存文件,以当前日期为目录存放
		String prefixPath = request.getRealPath("/") + "/upload";
		String suffixPath = "";

		suffixPath = System.currentTimeMillis() + ".txt";

		File sendFile = PhoneFileUtils.getCurrentDateFile(prefixPath,
				suffixPath);
		String sendFilePath = sendFile.getPath().substring(
				sendFile.getPath().indexOf(File.separator + "upload"));
		smsSend.setSendFilePath(sendFilePath);
		PhoneFileUtils.write2Txt(correctMobile, sendFile);

		// 存放短信的表
		String smsTable = "sms"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date());
		if (this.smsManageDao.existsSmsTable(smsTable) == 0) {
			this.smsManageDao.createNewSmsTable(smsTable);
		}
		smsSend.setSmsTable(smsTable);
		long sendId = this.smsSendManageDao.insertSmsSend(smsSend);
		this.writeLog(currentAppUser.getAccount() + "提交了" + chargeCount + "条短信");
		// 进行扣费
		currentAppUser.setBalance(currentAppUser.getBalance() - chargeCount);
		this.appUserManageDao.updateObject("updateAppUser", currentAppUser);

		Financial financial = new Financial();
		financial.setAccount(currentAppUser.getAccount());
		financial.setActAccount(currentAppUser.getAccount());
		financial.setActCount(0 - chargeCount);
		financial.setActTime(new Date());
		financial.setActType(0); // 短信
		financial.setActMethod(1); // 短信扣费
		this.addFinancial(financial);

		this.response.getWriter().println("0," + sendId);

		return null;
	}

	public String getBadwords(ChannelGroup channelGroup, String content) {
		String result = "success,没有发现屏蔽词";
		StringBuffer sb = new StringBuffer("");
		String allBadwords = this.badwordsManageDao
				.findBadwordsByChannelGroupId(0);
		if (allBadwords != null && !"".equals(allBadwords)) {
			for (String words : allBadwords.split(",")) {
				if (content.contains(words)) {
					sb.append(words + "\t");
				}
			}
		}
		String currentBadwords = this.badwordsManageDao
				.findBadwordsByChannelGroupId(channelGroup.getId());
		if (currentBadwords != null && !"".equals(currentBadwords)) {
			for (String words : currentBadwords.split(",")) {
				if (content.contains(words)) {
					sb.append(words + "\t");
				}
			}
		}
		if (!"".equals(sb.toString())) {
			result = "fail,发现屏蔽词: " + sb.toString();
		}
		return result;
	}

	/**
	 * 滤除错误号码和非手机号码以及重复号码
	 */
	public Set<String> checkErr(String mobile) {

		Set<String> correctMobile = new HashSet<String>();
		for (String tmp : mobile.split(",")) {
			if (tmp.matches("\\d{11}") && tmp.startsWith("1")) {
				correctMobile.add(tmp);
			}
		}

		return correctMobile;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
		this.appUserManageDao = appUserManageDao;
	}

	public void setSmsSendManageDao(SmsSendManageDao smsSendManageDao) {
		this.smsSendManageDao = smsSendManageDao;
	}

	public void setSmsManageDao(SmsManageDao smsManageDao) {
		this.smsManageDao = smsManageDao;
	}

	public void setBadwordsManageDao(BadwordsManageDao badwordsManageDao) {
		this.badwordsManageDao = badwordsManageDao;
	}

	public void setChannelGroupManageDao(
			ChannelGroupManageDao channelGroupManageDao) {
		this.channelGroupManageDao = channelGroupManageDao;
	}

}
