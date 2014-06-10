package com.oakhole.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oakhole.db.connection.DBConnMySql;
import com.oakhole.packet.cmpp.CMPPCommandID;
import com.oakhole.packet.cmpp.CMPP_DELIVER;
import com.oakhole.packet.cmpp.CMPP_SUBMIT;

public class DBHelper {

	private static Log logger = LogFactory.getLog(DBHelper.class);

	public synchronized static boolean existsIp(String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(ip) from cu where ip = ?";
		try {
			conn = DBConnMySql.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public synchronized static boolean updateDeliverList(
			List<CMPP_DELIVER> deliverList) {

		if (deliverList.size() <= 0) {
			return true;
		}

		List<CMPP_DELIVER> mtList = new ArrayList<CMPP_DELIVER>();
		List<CMPP_DELIVER> moList = new ArrayList<CMPP_DELIVER>();

		for (CMPP_DELIVER deliver : deliverList) {
			if (deliver.getRegistered_Delivery() == 0) {
				moList.add(deliver);
			} else {
				mtList.add(deliver);
			}
		}
		return updateMT(mtList) && updateMO(moList);
	}

	private static boolean updateMT(List<CMPP_DELIVER> deliverList) {

		if (deliverList.size() <= 0) {
			return true;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = DBConnMySql.getConnection();
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	private static boolean updateMO(List<CMPP_DELIVER> deliverList) {

		if (deliverList.size() <= 0) {
			return true;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = DBConnMySql.getConnection();
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public synchronized static void insertSubmitList(
			List<CMPP_SUBMIT> submitList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into sms_mt(sp_id,sp_code,src_msg_id,mobile,msg_content) values(?,?,?,?,?)";

		try {
			conn = DBConnMySql.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (CMPP_SUBMIT submit : submitList) {
				pstmt.setString(1, submit.getMsg_src());
				pstmt.setString(2, submit.getSrc_Id());
				pstmt.setLong(3, submit.getMsg_Id());
				pstmt.setString(4, submit.getDest_terminal_Id()[0]);
				pstmt.setString(5, new String(submit.getMsg_Content()));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
			pstmt.clearBatch();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static List<CMPP_DELIVER> queryDeliverList(int len) {
		List<CMPP_DELIVER> result = new ArrayList<CMPP_DELIVER>();
		List<CMPP_DELIVER> statusList = queryStatusList(len);
		List<CMPP_DELIVER> moList = queryMOList(len);

		Collections.copy(result, statusList);
		Collections.copy(result, moList);

		return result;
	}

	private static List<CMPP_DELIVER> queryStatusList(int len) {
		List<CMPP_DELIVER> result = new ArrayList<CMPP_DELIVER>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select src_msg_id, from sms_mt where readable = 0 limit ?";
		try {
			conn = DBConnMySql.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			CMPP_DELIVER deliver = null;
			while (rs.next()) {
				deliver = new CMPP_DELIVER();

				result.add(deliver);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private static List<CMPP_DELIVER> queryMOList(int len) {
		List<CMPP_DELIVER> result = new ArrayList<CMPP_DELIVER>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select msg_id,sp_code,msg_content from sms_mo where readable = 0 limit ?";
		try {
			conn = DBConnMySql.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, len);
			rs = pstmt.executeQuery();
			CMPP_DELIVER deliver = null;
			while (rs.next()) {
				deliver = new CMPP_DELIVER();
				deliver.setMsg_Id(rs.getLong(1));
				deliver.setDest_terminal_Id(rs.getString(2));
				deliver.setMsg_Content(rs.getString(3).getBytes());
				result.add(deliver);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static void main(String[] args) {
		CMPP_SUBMIT submit = new CMPP_SUBMIT();

		submit.setCommand_Id(CMPPCommandID.SUBMIT);
		submit.setSequence_Id(1);

		submit.setMsg_Id(1L);
		submit.setPk_total((byte) 1);
		submit.setPk_number((byte) 1);
		submit.setRegistered_Delivery((byte) 1);
		submit.setMsg_level((byte) 1);
		submit.setService_Id("1");
		submit.setFee_UserType((byte) 0);
		submit.setFee_terminal_Id(null);
		submit.setFee_terminal_type((byte) 1);
		submit.setTP_pId((byte) 0);
		submit.setTP_udhi((byte) 0);
		submit.setMsg_Fmt((byte) 0);
		submit.setMsg_src("999999");
		submit.setFeeType("01");
		submit.setFeeCode("000000");
		submit.setValId_Time("");
		submit.setAt_Time("");
		submit.setSrc_Id("1");
		submit.setDestUsr_tl((byte) 1);
		submit.setDest_terminal_Id(new String[] { "13800000000" });
		submit.setDest_terminal_type((byte) 0);
		submit.setMsg_Content("Hello ºº×Ö".getBytes());
		submit.setMsg_Length((byte) submit.getMsg_Content().length);
		submit.setLinkID("012345678901234567890");

		List<CMPP_SUBMIT> submitList = new ArrayList<CMPP_SUBMIT>();
		submitList.add(submit);
		DBHelper.insertSubmitList(submitList);
	}

}
