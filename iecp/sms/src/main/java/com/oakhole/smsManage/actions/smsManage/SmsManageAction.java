package com.oakhole.smsManage.actions.smsManage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.smsManage.dao.SmsManageDao;
import com.oakhole.smsManage.dao.SmsSendManageDao;
import com.oakhole.smsManage.model.Sms;
import com.oakhole.smsManage.model.SmsSend;

@SuppressWarnings("serial")
public class SmsManageAction extends BaseAction {

	@Autowired
	private SmsManageDao smsManageDao;

	@Autowired
	private SmsSendManageDao smsSendManageDao;

	/**
	 * 获取所有详细信息
	 * 
	 * @return
	 */
	public String findAllSms() {

		String sendId = request.getParameter("sendId");

		String status = request.getParameter("status");

		String currentPage = request.getParameter("currentPage");

		String content = request.getParameter("content");

		String mobile = request.getParameter("mobile");

		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}

		String sql = "";

		if (sendId != null && !"".equals(sendId)) {
			request.setAttribute("sendId", sendId);
			SmsSend smsSend = this.smsSendManageDao.findObjectById(
					"findSmsSendById", Long.valueOf(sendId));
			sql = "select * from " + smsSend.getSmsTable() + " where sendId="
					+ smsSend.getId();
			int sendSuccessCount = this.smsManageDao.getSendSuccessCount(
					smsSend.getId(), smsSend.getSmsTable());
			int sendFailureCount = this.smsManageDao.getSendFailureCount(
					smsSend.getId(), smsSend.getSmsTable());
			int sendUnknownCount = smsSend.getSendPhoneCount()
					- sendSuccessCount - sendFailureCount;
			smsSend.setSendSuccess(sendSuccessCount);
			smsSend.setSendFailure(sendFailureCount);
			smsSend.setSendUnknown(sendUnknownCount);
			request.setAttribute("smsSend", smsSend);
		}

		if (status != null && !"".equals(status)) {
			if ("0".equals(status)) {
				sql += " and (status='' or status is null)";
			} else if ("1".equals(status)) {
				sql += " and status='DELIVRD'";
			} else if ("2".equals(status)) {
				sql += " and status<>'DELIVRD'";
			}
			request.setAttribute("status", status);
		}

		if (content != null && !"".equals(content)) {
			sql += " and content like '%" + content + "%'";
			request.setAttribute("content", content);
		}

		if (mobile != null && !"".equals(mobile)) {
			sql += " and mobile='" + mobile + "'";
			request.setAttribute("mobile", mobile);
		}

		PageEntity<Sms> page = this.smsManageDao.findAllPaginator(
				"findAllSmsTotalCount", "findAllSms", sql,
				Integer.valueOf(currentPage));
		request.setAttribute("page", page);

		return "allSms";
	}

	public String changeCut() {

		String smsTable = request.getParameter("smsTable");
		String sendId = request.getParameter("sendId");
		String flag = request.getParameter("flag");

		this.smsManageDao.updateStatus(smsTable, Long.valueOf(sendId),
				Integer.valueOf(flag));

		this.responsePrint("success");

		return null;
	}

	public String exportStatus() {

		String sendId = request.getParameter("sendId");

		String status = request.getParameter("status");

		String sql = "";
		SmsSend smsSend = null;

		if (sendId != null && !"".equals(sendId)) {
			smsSend = this.smsSendManageDao.findObjectById("findSmsSendById",
					Long.valueOf(sendId));
			sql = "select * from " + smsSend.getSmsTable() + " where sendId="
					+ smsSend.getId();
		}

		if (status != null && !"".equals(status)) {
			if ("0".equals(status)) {
				sql += " and (status='' or status is null)";
			} else if ("1".equals(status)) {
				sql += " and status='DELIVRD'";
			} else if ("2".equals(status)) {
				sql += " and status<>'DELIVRD'";
			}
		}

		PageEntity<Sms> page = this.smsManageDao.findAllPaginator(
				"findAllSmsTotalCount", "findAllSms", sql, "asc");

		response.reset();
		response.setContentType("text/plain;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ System.currentTimeMillis() + ".csv");
		PrintWriter pw = null;
		File tmpFile = new File("tmpReport");

		InputStream is = null;
		OutputStream os = null;

		byte[] buff = new byte[1024];

		try {
			tmpFile.createNewFile();
			pw = new PrintWriter(tmpFile);
			String tmp = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pw.print("提交编号,帐号,手机号,发送内容,回执状态,回执时间\r\n");
			for (Sms sms : page.getResult()) {
				String huizhi = sms.getStatus() == null ? "" : sms.getStatus();
				String statusTime = sms.getStatusTime() == null ? "" : sdf
						.format(sms.getStatusTime());
				tmp = smsSend.getId() + "," + smsSend.getAccount() + ","
						+ sms.getMobile() + "," + sms.getContent() + ","
						+ huizhi + "," + statusTime + "\r\n";
				pw.print(tmp);
				pw.flush();
			}
			pw.close();
			is = new FileInputStream(tmpFile);
			os = response.getOutputStream();
			while (is.read(buff) != -1) {
				os.write(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tmpFile.exists()) {
				tmpFile.delete();
			}
		}

		return null;
	}

	public void setSmsSendManageDao(SmsSendManageDao smsSendManageDao) {
		this.smsSendManageDao = smsSendManageDao;
	}

	public void setSmsManageDao(SmsManageDao smsManageDao) {
		this.smsManageDao = smsManageDao;
	}
}
