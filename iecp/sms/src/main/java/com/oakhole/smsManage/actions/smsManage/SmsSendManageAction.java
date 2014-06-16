package com.oakhole.smsManage.actions.smsManage;

import com.oakhole.common.action.BaseAction;
import com.oakhole.common.model.PageEntity;
import com.oakhole.common.util.constants.GlobalConstants;
import com.oakhole.common.util.fileUtils.PhoneFileUtils;
import com.oakhole.financialManage.model.Financial;
import com.oakhole.smsManage.dao.SmsManageDao;
import com.oakhole.smsManage.dao.SmsSendManageDao;
import com.oakhole.smsManage.model.SmsSend;
import com.oakhole.systemManage.dao.AppUserManageDao;
import com.oakhole.systemManage.dao.BadwordsManageDao;
import com.oakhole.systemManage.dao.ChannelGroupManageDao;
import com.oakhole.systemManage.dao.ChannelManageDao;
import com.oakhole.systemManage.model.AppUser;
import com.oakhole.systemManage.model.Channel;
import com.oakhole.systemManage.model.ChannelGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class SmsSendManageAction extends BaseAction {

    @Autowired
    private SmsSendManageDao smsSendManageDao;

    @Autowired
    private ChannelManageDao channelManageDao;

    @Autowired
    private ChannelGroupManageDao channelGroupManageDao;

    @Autowired
    private AppUserManageDao appUserManageDao;

    @Autowired
    private SmsManageDao smsManageDao;

    @Autowired
    private BadwordsManageDao badwordsManageDao;

    // 文件上传的参数设置
    private File upload;
    private String uploadFileName;

    private String content;
    private String planTime;
    private String mobile;
    private String type;

    /**
     * 导入txt号码文件
     *
     * @return
     */
    public String importFromTxt() {
        return "importFromTxt";
    }

    /**
     * 导入excel文件
     *
     * @return
     */
    public String importFromExcel() {
        return "importFromExcel";
    }

    /**
     * 检测屏蔽词
     *
     * @return
     */
    public String checkBadwords() {

        String content = request.getParameter("content");
        String result = getBadwords(content);
        this.responsePrint(result);
        return null;
    }

    public String getBadwords(String content) {
        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);
        String result = "success,没有发现屏蔽词";
        long channelGroupId = currentAppUser.getChannelGroupId();
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
                .findBadwordsByChannelGroupId(Long.valueOf(channelGroupId));
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
     * 根据smsSendId删除smsSend
     *
     * @return
     */
    public String deleteSmsSend() {

        String sendStatus = request.getParameter("sendStatus");
        if (sendStatus == null || "".equals(sendStatus)) {
            sendStatus = "0";
        }

        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);

        String smsSendIds = request.getParameter("smsSendIds");

        this.smsSendManageDao.delObject("deleteSmsSend",
                Long.valueOf(smsSendIds));

        String sql = "select * from sms_send s where sendStatus="
                + Integer.parseInt(sendStatus);

        if (currentAppUser.getUserType() == 2) {
            sql += " and appUserId=" + currentAppUser.getId();
        }

        if (currentAppUser.getUserType() == 1) {
            sql += " and appUserId in (select id from appUser a where a.parentId="
                    + currentAppUser.getId()
                    + " or a.id= "
                    + currentAppUser.getId() + ")";
        }

        PageEntity<SmsSend> page = this.smsSendManageDao.findAllPaginator(
                "findAllSmsSendTotalCount", "findAllSmsSend", sql, 1);
        request.setAttribute("page", page);
        request.setAttribute("currentPage", page.getCurrentPage());

        switch (Integer.parseInt(sendStatus)) {
            case 0:
                return "wait";
            case 1:
                return "sending";
            case 2:
                return "finished";
            case 3:
                return "against";
        }
        return null;
    }

    /**
     * 根据smsSendId更改smsSend状态,由等待列表跳转
     *
     * @return
     */
    public String changeSendStatus() {

        String sendStatus = request.getParameter("sendStatus");
        if (sendStatus == null || "".equals(sendStatus)) {
            sendStatus = "0";
        }

        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);

        String smsSendIds = request.getParameter("smsSendIds");

        SmsSend smsSend = this.smsSendManageDao.findObjectById(
                "findSmsSendById", Long.valueOf(smsSendIds));

        smsSend.setSendStatus(Integer.parseInt(sendStatus));

        this.smsSendManageDao.updateObject("updateSmsSend", smsSend);

        String sql = "select * from sms_send s where sendStatus="
                + Integer.parseInt(sendStatus);

        if (currentAppUser.getUserType() == 2) {
            sql += " and appUserId=" + currentAppUser.getId();
        }

        if (currentAppUser.getUserType() == 1) {
            sql += " and appUserId in (select id from appUser a where a.parentId="
                    + currentAppUser.getId()
                    + " or a.id= "
                    + currentAppUser.getId() + ")";
        }

        PageEntity<SmsSend> page = this.smsSendManageDao.findAllPaginator(
                "findAllSmsSendTotalCount", "findAllSmsSend", sql, 1);
        request.setAttribute("page", page);
        request.setAttribute("currentPage", page.getCurrentPage());

        switch (Integer.parseInt(sendStatus)) {
            case 0:
                return "wait";
            case 1:
                return "sending";
            case 2:
                //todo:divide package ready for sending
                return "finished";
            case 3:
                return "against";
        }
        return null;
    }

    /**
     * 失败重发
     *
     * @return
     */
    public String resend() {

        long sendId = Long.valueOf(request.getParameter("sendId"));

        SmsSend smsSend = smsSendManageDao.findObjectById("findSmsSendById",
                sendId);
        smsSend.setSendStatus(0);
        smsSendManageDao.updateObject("updateSmsSend", smsSend);

        return "against";
    }

    /**
     * 请求跳转到短信发送页面
     *
     * @return
     */
    public String sendSms() {
        String isVar = request.getParameter("isVar");
        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);
        long channelGroupId = currentAppUser.getChannelGroupId();
        ChannelGroup channelGroup = this.channelGroupManageDao.findObjectById(
                "findChannelGroupById", channelGroupId);
        Channel yidongChannel = null;
        if (channelGroup.getYidongChannelId() != 0) {
            yidongChannel = this.channelManageDao.findObjectById(
                    "findChannelById", channelGroup.getYidongChannelId());
        } else {
            yidongChannel = new Channel();
            yidongChannel.setStatus(0);
        }
        Channel liantongChannel = null;
        if (channelGroup.getLiantongChannelId() != 0) {
            liantongChannel = this.channelManageDao.findObjectById(
                    "findChannelById", channelGroup.getLiantongChannelId());
        } else {
            liantongChannel = new Channel();
            liantongChannel.setStatus(0);
        }
        Channel dianxinChannel = null;
        if (channelGroup.getDianxinChannelId() != 0) {
            dianxinChannel = this.channelManageDao.findObjectById(
                    "findChannelById", channelGroup.getDianxinChannelId());
        } else {
            dianxinChannel = new Channel();
            dianxinChannel.setStatus(0);
        }
        request.setAttribute("yidongChannel", yidongChannel);
        request.setAttribute("liantongChannel", liantongChannel);
        request.setAttribute("dianxinChannel", dianxinChannel);
        request.setAttribute("channel", channelGroup);
        if ("1".equals(isVar)) {
            return "sendVarSms";
        }
        return "sendSms";
    }

    /**
     * 发送变量短信
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public String varSmsSave() {

        String result = "";

        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);
        int sendType = 0;

        String planTime = request.getParameter("planTime");

        String badwords = getBadwords(content);
        if (badwords.startsWith("fail")) {
            result = "4," + badwords.split(",")[1];
            request.setAttribute("sendErr", result);
            return "sendVarSms";
        }

        if (uploadFileName == null) {
            result = "101,上传文件为空";
            request.setAttribute("sendErr", result);
            return "sendVarSms";
        }

        if (!uploadFileName.endsWith(".csv")
                && !uploadFileName.endsWith(".xls")) {
            result = "102,上传文件格式错误";
            request.setAttribute("sendErr", result);
            return "sendVarSms";
        }

        Set<String> correctMobile = PhoneFileUtils
                .getAllPhonesFromExcel(upload);
        if (correctMobile.size() == 0) {
            result = "5,有效号码为0";
            request.setAttribute("sendErr", result);
            return "sendVarSms";
        }

        int totalwords = content.length();
        int contentChargeCount = 0;
        if (totalwords <= 70) {
            contentChargeCount = 1;
        } else {
            contentChargeCount = (totalwords - 70) / (70 - 5) + 2;
        }
        int sendPhoneCount = correctMobile.size();
        int priority = GlobalConstants.getPriority(sendPhoneCount);
        int chargeCount = sendPhoneCount * contentChargeCount;
        int currentAppUserBalance = currentAppUser.getBalance();
        if (currentAppUserBalance < chargeCount) {
            result = "7,通道余额不足";
            request.setAttribute("sendErr", result);
            return "sendVarSms";
        }
        // 满足以上条件,才可进行推送
        SmsSend smsSend = new SmsSend();
        smsSend.setAccount(currentAppUser.getAccount());
        smsSend.setAppUserId(currentAppUser.getId());
        smsSend.setChannelGroupId(currentAppUser.getChannelGroupId());
        smsSend.setContent(content);
        smsSend.setLinkman(currentAppUser.getLinkman());
        smsSend.setIsVarSms(1); // 变量短信
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
        smsSend.setPriority(priority);// 设置短信优先级
        smsSend.setSendTime(new Date());
        smsSend.setSendPhoneCount(sendPhoneCount);
        smsSend.setChargeCount(chargeCount);
        smsSend.setTotalwords(totalwords);
        smsSend.setSendType(sendType);
        if (planTime != null && !"".equals(planTime)) {
            try {
                smsSend.setPlanTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(planTime));
            } catch (Exception e) {
                result = "100,日期格式错误";
                request.setAttribute("sendErr", result);
                return "sendVarSms";
            }
        }

        // 存放短信的缓存文件,以当前日期为目录存放
        String prefixPath = request.getRealPath("/") + "/upload";
        String suffixPath = System.currentTimeMillis() + ".csv";

        File sendFile = PhoneFileUtils.getCurrentDateFile(prefixPath,
                suffixPath);
        String sendFilePath = sendFile.getPath().substring(
                sendFile.getPath().indexOf(File.separator + "upload"));
        smsSend.setSendFilePath(sendFilePath);
        PhoneFileUtils.write2Csv(upload, sendFile);

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

        // 返回值

        result = "0," + sendId;
        request.setAttribute("sendErr", result);
        return "sendVarSms";
    }

    /**
     * 发送普通短信
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public String normalSmsSave() {

        String result = "";

        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);
        int sendType = 0;

        String badwords = getBadwords(content);
        if (badwords.startsWith("fail")) {
            result = "4," + badwords.split(",")[1];
            request.setAttribute("result", result);
            return "sendSms";
        }

        Set<String> correctMobile = null;

        // 判断是否为导入提交
        if (type != null && !"".equals(type) && "1".equals(type)) {

            if (uploadFileName == null) {
                result = "101,上传文件为空";
                request.setAttribute("sendErr", result);
                return "sendVarSms";
            }

            if (!uploadFileName.endsWith(".csv")
                    && !uploadFileName.endsWith(".xls")
                    && !uploadFileName.endsWith(".txt")) {
                result = "102,上传文件格式错误";
                request.setAttribute("sendErr", result);
                return "sendVarSms";
            }

            if (uploadFileName.endsWith(".txt")) {
                correctMobile = PhoneFileUtils.getAllPhonesFromTxt(upload);
            } else {
                correctMobile = PhoneFileUtils.getAllPhonesFromExcel(upload);
            }
        } else {
            mobile = mobile.replace("\r\n", "\n");
            correctMobile = checkErr(mobile);
        }

        if (correctMobile.size() == 0) {
            result = "5,有效号码为0";
            request.setAttribute("result", result);
            return "sendSms";
        }
        int totalwords = content.length();
        int contentChargeCount = 0;
        if (totalwords <= 70) {
            contentChargeCount = 1;
        } else {
            contentChargeCount = (totalwords - 70) / (70 - 5) + 2;
        }
        int sendPhoneCount = correctMobile.size();
        int priority = GlobalConstants.getPriority(sendPhoneCount);
        int chargeCount = sendPhoneCount * contentChargeCount;
        int currentAppUserBalance = currentAppUser.getBalance();
        if (currentAppUserBalance < chargeCount) {
            result = "7,通道余额不足";
            request.setAttribute("result", result);
            return "sendSms";
        }

        // 满足以上条件,才可进行推送
        SmsSend smsSend = new SmsSend();
        smsSend.setAccount(currentAppUser.getAccount());
        smsSend.setAppUserId(currentAppUser.getId());
        smsSend.setChannelGroupId(currentAppUser.getChannelGroupId());
        smsSend.setContent(content);
        smsSend.setLinkman(currentAppUser.getLinkman());
        smsSend.setIsVarSms(0); // 普通短信
        smsSend.setPriority(priority);// 设置短信的优先级
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
        smsSend.setSendType(sendType);
        if (planTime != null && !"".equals(planTime)) {
            try {
                smsSend.setPlanTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(planTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 存放短信的缓存文件,以当前日期为目录存放
        String prefixPath = request.getRealPath("/") + "/upload";
        String suffixPath = System.currentTimeMillis() + ".txt";

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
        this.smsSendManageDao.insertSmsSend(smsSend);
        result = "0," + chargeCount;
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

        request.setAttribute("result", result);

        /**
         * TODO : temp update
         *
         * 若为终端用户则跳转到发送列表
         */
        if (currentAppUser.getUserType() == 3) {
            return this.findAllSmsSend();
        }

        return "sendSms";
    }

    /**
     * 滤除错误号码和非手机号码以及重复号码
     */
    public Set<String> checkErr(String mobile) {

        Set<String> correctMobile = new HashSet<String>();
        for (String tmp : mobile.split("\n")) {
            if (tmp.matches("\\d{11}") && tmp.startsWith("1")) {
                correctMobile.add(tmp);
            }
        }

        return correctMobile;
    }

    /**
     * forward to sms-send-manage-updateSmsSend.jsp
     *
     * @return
     */
    public String alterSmsSend() {

        String sendId = request.getParameter("smsSendId");
        SmsSend smsSend = this.smsSendManageDao.findObjectById(
                "findSmsSendById", Long.valueOf(sendId));
        request.setAttribute("smsSend", smsSend);
        return "updateSmsSend";
    }

    /**
     * 更改smsSend信息
     *
     * @return
     */
    public String updateSmsSend() {

        String sendId = request.getParameter("sendId");
        String sendPhoneCount = request.getParameter("sendPhoneCount");
        String content = request.getParameter("msgContent");
        String sendTime = request.getParameter("sendTime");

        SmsSend smsSend = this.smsSendManageDao.findObjectById(
                "findSmsSendById", Long.valueOf(sendId));

        smsSend.setChargeCount(Integer.valueOf(sendPhoneCount)
                * (smsSend.getChargeCount() / smsSend.getSendPhoneCount()));
        smsSend.setSendPhoneCount(Integer.valueOf(sendPhoneCount));
        smsSend.setContent(content);
        try {
            smsSend.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(sendTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // TODO : 更改smsTable 中的短信内容
        this.smsSendManageDao.updateObject("updateSmsSend", smsSend);

        return findAllSmsSend();
    }

    /**
     * 查询所有发送记录 sendStatus 0:待发送 1:正在发送 2:已完成 3:失败驳回
     *
     * @return
     */
    public String findAllSmsSend() {

        String sendStatus = request.getParameter("sendStatus");
        if (sendStatus == null || "".equals(sendStatus)) {
            sendStatus = "0";
        }

        AppUser currentAppUser = (AppUser) session
                .getAttribute(GlobalConstants.GLOBALAPPUSER);

        String taskId = request.getParameter("taskId");
        String account = request.getParameter("account");
        String content = request.getParameter("content") == null ? "" : request
                .getParameter("content");
        String startTime = request.getParameter("starttime") == null ? ""
                : request.getParameter("starttime");
        String endTime = request.getParameter("endtime") == null ? "" : request
                .getParameter("endtime");
        int currentPage = Integer
                .parseInt((request.getParameter("currentPage") == null
                        || "".equals(request.getParameter("currentPage")) ? "1"
                        : request.getParameter("currentPage")));

        // TODO temp update
        // 终端用户发送之后自动跳转到发送列表，因此sendStatus不作跳转依据
        String sql = "select * from sms_send s where content like '%" + content
                + "%'";

        if (currentAppUser.getUserType() != 3) {
            sql += " and sendStatus = " + Integer.valueOf(sendStatus);
        }

        if (currentAppUser.getUserType() == 2
                || currentAppUser.getUserType() == 3) {
            sql += " and appUserId=" + currentAppUser.getId();
        }

        if (currentAppUser.getUserType() == 1) {
            sql += " and appUserId in (select id from appUser a where a.parentId="
                    + currentAppUser.getId()
                    + " or a.id= "
                    + currentAppUser.getId() + ")";
        }

        if (taskId != null && taskId != "") {
            sql += " and id=" + Integer.parseInt(taskId);
        }

        if (startTime != null && !"".equals(startTime)) {
            sql += " and sendTime >= '" + startTime + " 00:00:00'";
        }

        if (endTime != null && !"".equals(endTime)) {
            sql += " and sendTime <= '" + endTime + " 23:59:59'";
        }

        if ("".equals(startTime) && "".equals(endTime)) {
            sql += " and sendTime >= '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                    + " 00:00:00'";
        }

        if (!"".equals(account) && account != null) {
            sql += " and account='" + account + "'";
        }

        PageEntity<SmsSend> page = this.smsSendManageDao.findAllPaginator(
                "findAllSmsSendTotalCount", "findAllSmsSend", sql, currentPage);
        request.setAttribute("page", page);
        request.setAttribute("currentPage", page.getCurrentPage());
        request.setAttribute("taskId", taskId);
        request.setAttribute("account", account);
        request.setAttribute("content", content);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        request.setAttribute("employee",
                this.appUserManageDao.findAppUserByAccount(account));

        // TODO temp update
        if (currentAppUser.getUserType() == 3) {
            return "finished";
        }

        switch (Integer.parseInt(sendStatus)) {
            case 0:
                return "wait";
            case 1:
                return "sending";
            case 2:
                return "finished";
            case 3:
                return "against";
        }
        return null;
    }

    /**
     * 通过审核,驳回审核
     *
     * @return
     */
    public String passAudit() {
        String auditStatus = request.getParameter("auditStatus");
        String sendId = request.getParameter("sendId");
        SmsSend smsSend = this.smsSendManageDao.findObjectById(
                "findSmsSendById", Long.valueOf(sendId));
        if ("3".equals(auditStatus)) {
            smsSend.setSendStatus(3);
        }
        smsSend.setAuditStatus(Integer.valueOf(auditStatus));
        this.smsSendManageDao.updateObject("updateSmsSend", smsSend);
        return this.findAllSmsSend();
    }

    /**
     * 下载号码
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public String downLoadPhoneNumber() {

        String sendFilePath = request.getParameter("sendFilePath");

        File sendFile = new File(request.getRealPath("/") + sendFilePath);
        if (!sendFile.exists()) {
            this.responsePrint("很抱歉,您下载的文件不存在!如有疑问,请联系客服.");
        } else {
            byte[] buff = new byte[1024];
            response.reset();
            if (sendFilePath.endsWith(".csv")) {
                response.setContentType("text/plain;charset=UTF-8");
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + System.currentTimeMillis()
                                + ".csv"
                );
            } else if (sendFilePath.endsWith(".txt")) {
                response.setContentType("text/plain;charset=UTF-8");
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + System.currentTimeMillis()
                                + ".txt"
                );
            } else {
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + System.currentTimeMillis()
                                + ".xls"
                );
            }
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(sendFile);
                os = response.getOutputStream();
                while (is.read(buff) != -1) {
                    os.write(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    os.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setSmsSendManageDao(SmsSendManageDao smsSendManageDao) {
        this.smsSendManageDao = smsSendManageDao;
    }

    public void setAppUserManageDao(AppUserManageDao appUserManageDao) {
        this.appUserManageDao = appUserManageDao;
    }

    public void setSmsManageDao(SmsManageDao smsManageDao) {
        this.smsManageDao = smsManageDao;
    }

    public void setBadwordsManageDao(BadwordsManageDao badwordsManageDao) {
        this.badwordsManageDao = badwordsManageDao;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChannelManageDao(ChannelManageDao channelManageDao) {
        this.channelManageDao = channelManageDao;
    }

    public void setChannelGroupManageDao(
            ChannelGroupManageDao channelGroupManageDao) {
        this.channelGroupManageDao = channelGroupManageDao;
    }
}
