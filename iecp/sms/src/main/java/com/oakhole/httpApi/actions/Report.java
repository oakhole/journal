package com.oakhole.httpApi.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.util.dbUtils.DBConnMysql;

@SuppressWarnings("serial")
public class Report extends BaseAction {

	private String mobile;
	private String msgid;
	private String status;

	@Override
	public String execute() throws Exception {

		String url = "jdbc:mysql://localhost:3306/sms_plugin";

		String sql = "insert into chuanglan_mt(mobile,status,sequenceId) values(?,?,?)";

		Connection conn = DBConnMysql.getConnection(url, "root", "root");
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile);
			pstmt.setString(2, status);
			pstmt.setString(3, msgid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}

		this.response.getWriter().println("success");

		return null;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
