package com.autonavi.directl.device.dopod;

import com.autonavi.directl.Log;
import com.autonavi.directl.idirectl.LocatorAdaptor;
import com.autonavi.directl.idirectl.TerminalParam;

 

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company: Beijing Mapabc Co., Ltd.
 * </p>
 * 
 * @author �ն˲�Ʒ�� ��ɽ��Ŀ��
 * @version 1.0
 */
public class DopodLocator extends LocatorAdaptor {
	public DopodLocator( TerminalParam tparam) {
		this.terminalParam = tparam;
	}

	/**
	 * ���ζ�λ SDGPS,�豸��,4
	 * 
	 * @return String
	 */
	public String singleLocator() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SDGPS,");
		buffer.append(this.terminalParam.getSeriesNo());
		buffer.append(",4");
		buffer.append("\r\n");

		return buffer.toString();
	}

	/**
	 * ��ʱ�����ش�ָ����������Ϣ SDGPS,�豸��,5/9,ʱ��,����
	 * 
	 * @param t
	 *            String
	 * @param count
	 *            String
	 * @return String
	 */
	public String multiLocator(String t, String count) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SDGPS,");
		int cnt = Integer.parseInt(count);
		buffer.append(this.terminalParam.getSeriesNo());
		if (t != null && t.trim().length() > 0) {
			if (cnt > 1) {
				buffer.append(",5,");
				buffer.append(t);
				buffer.append(",");
				buffer.append(count);
			} else {
				buffer.append(",9,");
				buffer.append(t);
			}
		}
		buffer.append("\r\n");
		 Log.getInstance().outLog("����Ƶ��ָ�"+buffer.toString());
	
		return buffer.toString();

	}
}