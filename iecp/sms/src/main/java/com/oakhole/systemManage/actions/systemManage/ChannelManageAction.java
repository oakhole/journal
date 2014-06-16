package com.oakhole.systemManage.actions.systemManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.systemManage.dao.BadwordsManageDao;
import com.oakhole.systemManage.dao.ChannelGroupManageDao;
import com.oakhole.systemManage.dao.ChannelManageDao;
import com.oakhole.systemManage.dao.InterfaceManageDao;
import com.oakhole.systemManage.model.BadWords;
import com.oakhole.systemManage.model.Channel;
import com.oakhole.systemManage.model.ChannelGroup;
import com.oakhole.systemManage.model.Interface;

@SuppressWarnings("serial")
public class ChannelManageAction extends BaseAction {

	@Autowired
	private ChannelManageDao channelManageDao;

	@Autowired
	private InterfaceManageDao interfaceManageDao;

	@Autowired
	private ChannelGroupManageDao channelGroupManageDao;

	@Autowired
	private BadwordsManageDao badwordsManageDao;

	/**
	 * 增加新通道
	 * 
	 * @return
	 */
	public String addChannel() {

		String sql = "select * from interface";
		PageEntity<Interface> page = this.interfaceManageDao.findAllPaginator(
				"findAllInterfaceTotalCount", "findAllInterface", sql, "asc");
		request.setAttribute("allInterface", page.getResult());

		String flag = request.getParameter("flag");

		if ("0".equals(flag)) {
			return "addChannel";
		} else {
			String channelId = request.getParameter("channelId");
			Channel channel = this.channelManageDao.findObjectById(
					"findChannelById", Long.valueOf(channelId));
			Interface inter = this.interfaceManageDao.findObjectById(
					"findInterfaceById", channel.getInterfaceId());
			request.setAttribute("channel", channel);
			request.setAttribute("inter", inter);
			return "updateChannel";
		}
	}

	/**
	 * 编辑通道
	 * 
	 * @return
	 */
	public String editChannel() {
		return null;
	}

	/**
	 * 删除通道
	 * 
	 * @return
	 */
	public String delChannel() {

		String channelId = request.getParameter("channelId");
		this.channelManageDao.delObject("delChannel", Long.valueOf(channelId));
		this.badwordsManageDao.delObject("deleteBadwords",
				Long.valueOf(channelId));
		return findAllChannel();

	}

	/**
	 * 存入数据库
	 * 
	 * @return
	 */
	public String saveChannel() {

		Channel channel = null;

		String flag = request.getParameter("flag");

		String channelId = request.getParameter("channelId");
		String name = request.getParameter("name");
		String channelType = request.getParameter("channelType");
		String operators = request.getParameter("operators");
		String price = request.getParameter("price");
		String balance = request.getParameter("balance");
		String perSmsWords = request.getParameter("perSmsWords");
		String maxSmsWords = request.getParameter("maxSmsWords");
		String longsms = request.getParameter("longsms");
		String callback = request.getParameter("callback");
		String perpack = request.getParameter("perpack");
		String dayFlow = request.getParameter("dayFlow");
		String description = request.getParameter("description");

		// 通道接口配置
		String interfaceId = request.getParameter("interfaceId");
		String interfaceParameters = request
				.getParameter("interfaceParameters");

		if ("".equals(longsms) || longsms == null) {
			longsms = "0";
		}
		if ("".equals(callback) || callback == null) {
			callback = "0";
		}

		// flag为0时：新增通道，反之：更新当前正在编辑通道设置
		if ("0".equals(flag)) {
			channel = new Channel();
			channel.setName(name);
			channel.setOperators(Integer.valueOf(operators));
			channel.setChannelType(Integer.valueOf(channelType));
			channel.setDescription(description);
			channel.setMaxSmsWords(Integer.valueOf(maxSmsWords));
			channel.setPerpack(Integer.valueOf(perpack));
			channel.setPerSmsWords(Integer.valueOf(perSmsWords));
			channel.setPrice(Float.valueOf(price));
			channel.setDayFlow(0); // 日流量限制不开
			channel.setStatus(2); // 默认通道为正常
			channel.setBalance(Integer.valueOf(balance));
			channel.setCallback(Integer.valueOf(callback));
			channel.setLongSms(Integer.valueOf(longsms));
			channel.setAbled(true); // 默认启用

			// 新增接口配置
			channel.setInterfaceId(Integer.valueOf(interfaceId));
			channel.setInterfaceParameters(interfaceParameters);

			this.channelManageDao.insertChannel(channel);

		} else {
			channel = this.channelManageDao.findObjectById("findChannelById",
					Long.valueOf(channelId));
			channel.setName(name);
			channel.setOperators(Integer.valueOf(operators));
			channel.setChannelType(Integer.valueOf(channelType));
			channel.setDescription(description);
			channel.setLongSms(Integer.valueOf(longsms));
			channel.setMaxSmsWords(Integer.valueOf(maxSmsWords));
			channel.setPerpack(Integer.valueOf(perpack));
			channel.setDayFlow(Integer.valueOf(dayFlow));
			channel.setPerSmsWords(Integer.valueOf(perSmsWords));
			channel.setPrice(Float.valueOf(price));
			channel.setBalance(Integer.valueOf(balance));
			channel.setCallback(Integer.valueOf(callback));

			// 新增接口配置
			channel.setInterfaceId(Integer.valueOf(interfaceId));
			channel.setInterfaceParameters(interfaceParameters);

			this.channelManageDao.updateObject("updateChannel", channel);
		}

		return this.findAllChannel();
	}

	/**
	 * 查询所有通道
	 * 
	 * @return
	 */
	public String findAllChannel() {
		String sql = "select * from channel";
		PageEntity<Channel> page = this.channelManageDao.findAllPaginator(
				"findAllChannelTotalCount", "findAllChannel", sql, "asc");
		request.setAttribute("allChannel", page.getResult());
		return "allChannel";
	}

	/**
	 * 控制通道是否被启用
	 * 
	 * @return
	 */
	public String channelControl() {

		String channelId = request.getParameter("channelId");
		String status = request.getParameter("status");

		Channel channel = this.channelManageDao.findObjectById(
				"findChannelById", Long.parseLong(channelId));

		if ("0".equals(status)) {
			channel.setAbled(false);
			channel.setStatus(0);
			pause(channelId);
		}

		if ("1".equals(status)) {
			channel.setAbled(true);
			channel.setStatus(1);
		}

		if ("2".equals(status)) {
			channel.setAbled(true);
			channel.setStatus(2);
		}

		if ("3".equals(status)) {
			channel.setAbled(false);
			channel.setStatus(3);
			pause(channelId);
		}

		this.channelManageDao.updateObject("updateChannel", channel);

		return findAllChannel();
	}

	/**
	 * 添加通道组 flag=0 添加 flag=1 更新
	 * 
	 * @return
	 */
	public String addChannelGroup() {

		String flag = request.getParameter("flag");

		String channelGroupId = request.getParameter("channelGroupId");

		String sql_yd = "select * from channel where operators=0 or operators=1";
		String sql_lt = "select * from channel where operators=0 or operators=2";
		String sql_dx = "select * from channel where operators=0 or operators=3";

		List<Channel> yidongChannels = this.channelManageDao.findAllPaginator(
				"findAllChannelTotalCount", "findAllChannel", sql_yd, "asc")
				.getResult();

		List<Channel> liantongChannels = this.channelManageDao
				.findAllPaginator("findAllChannelTotalCount", "findAllChannel",
						sql_lt, "asc").getResult();

		List<Channel> dianxinChannels = this.channelManageDao.findAllPaginator(
				"findAllChannelTotalCount", "findAllChannel", sql_dx, "asc")
				.getResult();

		request.setAttribute("yidongChannels", yidongChannels);
		request.setAttribute("liantongChannels", liantongChannels);
		request.setAttribute("dianxinChannels", dianxinChannels);

		if ("0".equals(flag)) {
			return "addChannelGroup";
		} else {
			ChannelGroup cg = this.channelGroupManageDao.findObjectById(
					"findChannelGroupById", Long.valueOf(channelGroupId));
			request.setAttribute("channelGroup", cg);
			return "updateChannelGroup";
		}
	}

	/**
	 * 持久化操作通道组
	 * 
	 * @return
	 */
	public String saveChannelGroup() {

		String flag = request.getParameter("flag");

		String channelGroupId = request.getParameter("channelGroupId");
		String name = request.getParameter("channelGroupName");
		String yidongChannelId = request.getParameter("yidongChannelId");
		String liantongChannelId = request.getParameter("liantongChannelId");
		String dianxinChannelId = request.getParameter("dianxinChannelId");
		String memo = request.getParameter("memo");

		if ("0".equals(flag)) {
			ChannelGroup cg = new ChannelGroup();
			cg.setName(name);
			cg.setYidongChannelId(Long.valueOf(yidongChannelId));
			cg.setLiantongChannelId(Long.valueOf(liantongChannelId));
			cg.setDianxinChannelId(Long.valueOf(dianxinChannelId));
			cg.setMemo(memo);
			long newChannelGroupId = this.channelGroupManageDao
					.insertChannelGroup(cg);

			// 屏蔽词管理
			BadWords badWords = new BadWords();
			badWords.setBadwords("");
			badWords.setChannelGroupId(newChannelGroupId);
			badWords.setChannelGroupName(name);

			this.badwordsManageDao.addObject("addBadwords", badWords);

		} else {
			ChannelGroup cg = this.channelGroupManageDao.findObjectById(
					"findChannelGroupById", Long.valueOf(channelGroupId));
			cg.setYidongChannelId(Long.valueOf(yidongChannelId));
			cg.setLiantongChannelId(Long.valueOf(liantongChannelId));
			cg.setDianxinChannelId(Long.valueOf(dianxinChannelId));
			cg.setName(name);
			cg.setMemo(memo);
			this.channelGroupManageDao.updateObject("updateChannelGroup", cg);
		}

		return this.findAllChannelGroup();
	}

	/**
	 * 删除通道组
	 * 
	 * @return
	 */
	public String deleteChannelGroup() {

		String channelGroupId = request.getParameter("channelGroupId");

		int count = this.channelGroupManageDao.delObject("deleteChannelGroup",
				Long.valueOf(channelGroupId));
		if (count > 0) {
			this.responsePrint("success");
		} else {
			this.responsePrint("failed");
		}

		return this.findAllChannelGroup();
	}

	/**
	 * 查询所有通道组
	 * 
	 * @return
	 */
	public String findAllChannelGroup() {

		String sql = "select * from channel_group";

		PageEntity<ChannelGroup> page = this.channelGroupManageDao
				.findAllPaginator("findAllChannelGroupTotalCount",
						"findAllChannelGroup", sql, "asc");

		request.setAttribute("page", page);

		return "allChannelGroup";
	}

	public void setBadwordsManageDao(BadwordsManageDao badwordsManageDao) {
		this.badwordsManageDao = badwordsManageDao;
	}

	public void setInterfaceManageDao(InterfaceManageDao interfaceManageDao) {
		this.interfaceManageDao = interfaceManageDao;
	}

	public void setChannelManageDao(ChannelManageDao channelManageDao) {
		this.channelManageDao = channelManageDao;
	}

	public void setChannelGroupManageDao(
			ChannelGroupManageDao channelGroupManageDao) {
		this.channelGroupManageDao = channelGroupManageDao;
	}

}
