package com.oakhole.systemManage.actions.systemManage;

import org.springframework.beans.factory.annotation.Autowired;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.systemManage.dao.BadwordsManageDao;
import com.oakhole.systemManage.model.BadWords;

@SuppressWarnings("serial")
public class BadwordsManageAction extends BaseAction {

	@Autowired
	private BadwordsManageDao badwordsManageDao;

	/**
	 * 添加或更新badwords
	 * 
	 * @return
	 */
	public String addBadwords() {

		String flag = request.getParameter("flag");

		String badwordsId = request.getParameter("badwordsId");

		if ("0".equals(flag) || badwordsId == null || "".equals(badwordsId)) {
			return "addBadwords";
		} else {
			BadWords badWords = this.badwordsManageDao.findObjectById(
					"findBadwordsById", Long.valueOf(badwordsId));
			request.setAttribute("badwords", badWords);
			return "updateBadwords";
		}
	}

	/**
	 * 持久化屏蔽词
	 * 
	 * @return
	 */
	public String saveBadwords() {

		String flag = request.getParameter("flag");
		String badwordsId = request.getParameter("badwordsId");

		String badwords = request.getParameter("badwords");
		String channelGroupId = request.getParameter("channelGroupId");
		String channelGroupName = request.getParameter("channelGroupName");

		if ("".equals(flag) || badwordsId == null || "".equals(badwordsId)) {
			BadWords badWords = new BadWords();
			badWords.setBadwords(badwords);
			badWords.setChannelGroupId(Long.valueOf(channelGroupId));
			badWords.setChannelGroupName(channelGroupName);
			this.badwordsManageDao.addObject("addBadwords", badWords);
			this.writeLog("通道" + channelGroupName + "添加屏蔽词");
		} else {
			BadWords badWords = this.badwordsManageDao.findObjectById(
					"findBadwordsById", Long.valueOf(badwordsId));
			badWords.setBadwords(badwords);
			badWords.setChannelGroupId(Long.valueOf(channelGroupId));
			badWords.setChannelGroupName(channelGroupName);
			this.badwordsManageDao.updateObject("updateBadwords", badWords);
			this.writeLog("通道" + channelGroupName + "更新了屏蔽词");
		}

		return this.findAllBadwords();
	}

	/**
	 * 查询所有通道屏蔽词
	 * 
	 * @return
	 */
	public String findAllBadwords() {

		PageEntity<BadWords> page = this.badwordsManageDao.findAllPaginator(
				"findAllBadwordsTotalCount", "findAllBadwords",
				"select * from badwords", "asc");

		request.setAttribute("allBadwords", page.getResult());
		request.setAttribute("totalCount", page.getTotalCount());

		return "allBadwords";
	}

	public void setBadwordsManageDao(BadwordsManageDao badwordsManageDao) {
		this.badwordsManageDao = badwordsManageDao;
	}

}
