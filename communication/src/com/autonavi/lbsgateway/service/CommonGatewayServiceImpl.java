package com.autonavi.lbsgateway.service;

import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.autonavi.directl.Log;
import com.autonavi.directl.TerminalFactory;
import com.autonavi.directl.Tools;
import com.autonavi.directl.base.TerminalInfo;
 
import com.autonavi.directl.idirectl.Alarm;
import com.autonavi.directl.idirectl.BaseDictate;
import com.autonavi.directl.idirectl.Locator;
import com.autonavi.directl.idirectl.Terminal;
import com.autonavi.directl.idirectl.TerminalControl;
import com.autonavi.directl.idirectl.TerminalSetting;
import com.autonavi.lbsgateway.GPRSThread;
import com.autonavi.lbsgateway.GPRSThreadList;
import com.autonavi.lbsgateway.bean.TerminalUDPAddress;

//Generated by MyEclipse

public class CommonGatewayServiceImpl implements ICommonGatewayService {

	/**
	 * 判断终端连接是否存在
	 * 
	 * @param simCard
	 * @return
	 */
	public boolean isExit(String simCard) {
		return GPRSThreadList.getInstance().isExist(simCard);
	}

	

	/**
	 * 向字节数据指令指定终端发送信息(UDP)
	 * 
	 * @param simCard
	 *            String
	 * @param cmd
	 *            String
	 * @return int :0已发送 3不在线 4指令无效
	 */
	public synchronized int sendDataToTerminal(String simCard, byte[] cmd) {
		// 发送指令
		if (simCard == null || simCard.trim().equals("")) {
			Log.getInstance().outLog("SIM卡号为空！");
			return -1;
		}
		TerminalUDPAddress gprs = com.autonavi.lbsgateway.GPRSThreadList
				.getInstance().getGpsThreadBySim(simCard);

		if (gprs != null && cmd != null && cmd.length > 0) {
			// 车辆在线并且指令有效的情况
			try {

				// gprs.sendUDPByteData(cmd);
				ByteBuffer bf = ByteBuffer.allocate(cmd.length);
				// bf.clear();
				bf.put(cmd);
				bf.flip();

				SocketAddress clientAddress = gprs.getSocketAddress();
				DatagramChannel channel = gprs.getDatagramChannel();
				// channel.connect(clientAddress);
				// channel.write(ByteBuffer.wrap(cmd));

				int lent = channel.send(bf, clientAddress); // 发送到客户端
				Log.getInstance().outLog(
						gprs.getDeviceSN() + "下发指令报文到:" + clientAddress
								+ " 内容为：" + Tools.bytesToHexString(cmd));

			} catch (Exception ex) {
				Log.getInstance().errorLog("终端下发指令异常：" + ex.getMessage(), ex);
			}

		} else { 
			//从TCP下发
			return  sendDataToTcpTerminal( simCard,  cmd);
			
		}
		return 0;
	}
	
	/**
	 * 向字节数据指令指定终端发送信息
	 * 
	 * @param simCard
	 *            String
	 * @param cmd
	 *            String
	 * @return int :0已发送 3不在线 4指令无效
	 */
	public synchronized int sendDataToTcpTerminal(String simCard, byte[] cmd) {
		// 发送指令
		 
		com.autonavi.lbsgateway.GprsTcpThread gprs = com.autonavi.lbsgateway.GPRSThreadList
				.getInstance().getGpsTcpThreadBySim(simCard);
		// String ip = gprs.localAddress;
		if (gprs != null && cmd != null && cmd.length > 0) {
			// 车辆在线并且指令有效的情况
 				
				try {
					gprs.sendByteArrayData(cmd);
					Log.getInstance().outLog(
							/** ip+ */
							"终端"
									+ simCard
									+ "下发命令行："
							 
									+ Tools.bytesToHexString(cmd));
				} catch (Exception ex) {
					Log.getInstance().errorLog("终端下发指令异常：" + ex.getMessage(),
							ex);
				}
		 
		} else {
			// 指令无效的情况
			if (gprs == null) {
				// 车台不在线
//				Log.getInstance().outLog("终端" + simCard + "不在线或信号弱或不在服务器"/** + ip */
//				+ "上");
				return 3;
			} else if (cmd != null && cmd.length != 0) {
				// 车台不支持该功能或者没有找到指定的协议编码类
			//	Log.getInstance().outLog("车台不支持该功能或者没有找到指定的协议编码类");
				return 4;
			}
		}
		return 0;
	}

	// 判断终端是否在线
	public boolean isOnLine(String simCard) {

		// 发送指令
		if (simCard == null || simCard.trim().equals("")) {
			Log.getInstance().outLog("SIM卡号为空,不在线！");
			return false;
		}
		com.autonavi.lbsgateway.GPRSThread gprs = null;

		if (gprs == null) {
			Log.getInstance().outLog(simCard + "不在线！");
			return false;
		} else {
			return true;
		}
	}

	// 获取所有在线的终端
	public String[] getAllOnlineTerminals() {

		GPRSThreadList map = com.autonavi.lbsgateway.GPRSThreadList
				.getInstance();

		String[] sims = new String[map.size()];

		StringBuffer sb = new StringBuffer();

		Iterator set = map.keySet().iterator();

		int offset = 0;

		while (set.hasNext()) {

			TerminalInfo info = new TerminalInfo();

			String simcard =  (String) set.next();
			
			info.setGpsSn(simcard);
			
			sims[offset++] = simcard;
		}

		return sims;
	}
	
//	 获取所有在线的终端
	public ArrayList getAllOnlineTerms() {
		
		ArrayList list = new ArrayList();

		GPRSThreadList map = com.autonavi.lbsgateway.GPRSThreadList
				.getInstance();

		String[] sims = new String[map.size()];

		StringBuffer sb = new StringBuffer();

		Iterator set = map.keySet().iterator();

		int offset = 0;

		while (set.hasNext()) {

			TerminalInfo info = new TerminalInfo();

			String simcard =  (String) set.next();
			
			info.setGpsSn(simcard);
			list.add(info);

			sims[offset++] = simcard;
		}

		return list;
	}

}