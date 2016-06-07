<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="/WEB-INF/layouts/taglibs.jsp"%>

<div class="main wd">
    <div class="path b_size">
        <ul>
            <li class="home" style="z-index:100;"><a href="${ctx}/toolbox">工具箱</a></li>
        </ul>
    </div>
    <div class="toolbox">
        <ul class="nav nav-statcked">
            <li>
                <a href="${ctx}/user" class="item">
                    <span class="t_title">用户管理</span>
                    <p class="t_info">
                        用户组织的管理，以及相应权限的设定。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/channel" class="item">
                    <span class="t_title">通道管理</span>
                    <p class="t_info">
                        对通道组和通道进行设置，可测试是否畅通与查询。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/advice" class="item">
                    <span class="t_title">公告管理</span>
                    <p class="t_info">
                        输入公告内容，发表后，可在首页查看。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/toolbox/blacklist" class="item">
                    <span class="t_title">黑名单</span>
                    <p class="t_info">
                        用户所禁止发送的号码段或指定号码，若在发送列表中出现则必然不发送。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/toolbox/whitelist" class="item">
                    <span class="t_title">白名单</span>
                    <p class="t_info">
                        用户所允许的号码段或指定号码，若在发送列表中出现则必然发送。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li>
                <a href="${ctx}/toolbox/package" class="item">
                    <span class="t_title">分包管理</span>
                    <p class="t_info">
                        将发送列表进行拆包设定，例如500号码一个包进行推送。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
            <li class="last_child">
                <a href="${ctx}/statistics" class="item">
                    <span class="t_title">报表管理</span>
                    <p class="t_info">
                        对发送情况进行统计，针对每天，每周，每月的实际发送，提交量，回执量进行反馈。
                    </p>
                    <span class="t_arrow"></span>
                </a>
            </li>
        </ul>
    </div>
</div>
