package com.oakhole.common.util.constants;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 设置线程池所需参数
 * 
 * @author oakhole
 * 
 */
public class SocketConstants {

	// 服务开
	public static final boolean ON = true;
	// 服务关
	public static final boolean OFF = false;

	// 存放所有request提交数据
	public static Queue<String> requestDataQueue = new ConcurrentLinkedQueue<String>();
}
