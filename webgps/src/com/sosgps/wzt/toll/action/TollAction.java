package com.sosgps.wzt.toll.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.sos.helper.SpringHelper;
import org.sos.web.action.DispatchWebActionSupport;

import com.sosgps.wzt.excel.ExcelWorkBook;
import com.sosgps.wzt.log.LogFactory;
import com.sosgps.wzt.log.common.LogConstants;
import com.sosgps.wzt.manage.util.Page;
import com.sosgps.wzt.orm.TOiling;
import com.sosgps.wzt.orm.TOptLog;
import com.sosgps.wzt.orm.TToll;
import com.sosgps.wzt.system.common.UserInfo;
import com.sosgps.wzt.toll.service.impl.TollServiceImpl;
import com.sosgps.wzt.util.CharTools;
import com.sosgps.wzt.util.DateUtility;

public class TollAction extends DispatchWebActionSupport {
	private TollServiceImpl tollServiceImpl = (TollServiceImpl) SpringHelper.getBean("TollServiceImpl");

	public ActionForward listToll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 从request中获取参数
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String st = request.getParameter("startTime");// 开始时间，格式yyyy-MM-dd HH:mm:ss
		String et = request.getParameter("endTime");// 结束时间，格式yyyy-MM-dd HH:mm:ss
		Date startDate = DateUtility.strToDateTime(st);
		Date endDate = DateUtility.strToDateTime(et);
		String searchValue = request.getParameter("searchValue");
		searchValue = CharTools.killNullString(searchValue);
		searchValue = java.net.URLDecoder.decode(searchValue, "UTF-8");
		searchValue = CharTools.killNullString(searchValue);
		// 从session中获取企业代码
		UserInfo userInfo = this.getCurrentUser(request);
		String entCode = userInfo.getEmpCode();
		Long userId = userInfo.getUserId();
		// 是否导出excel
		String expExcel = request.getParameter("expExcel");// true为导出
		expExcel = CharTools.javaScriptEscape(expExcel);
		if (expExcel.equalsIgnoreCase("true")) {
			Page<Object[]> list =tollServiceImpl.listToll(entCode,userId, 1, 65536, startDate, endDate, searchValue);
			ExcelWorkBook excelWorkBook = new ExcelWorkBook(response);
			// header
			excelWorkBook.addHeader("名称", 15);
			excelWorkBook.addHeader("车牌号", 15);
			excelWorkBook.addHeader("手机号", 15);
			excelWorkBook.addHeader("所属组", 15);
			excelWorkBook.addHeader("缴费日期", 15);
			excelWorkBook.addHeader("缴费地点", 15);
			excelWorkBook.addHeader("缴费金额", 15);
			excelWorkBook.addHeader("经手人", 15);
			excelWorkBook.addHeader("备注", 15);
			int row = 0;
			for (Object[] objects : list.getResult()) {
				//String deviceId = (String) objects[0];
	    		String termName = (String)objects[1];
	    		String vehicleNumber = (String)objects[2];
	    		String simcard = (String)objects[3];
	    		String groupName = (String)objects[4];
	    		TToll toll = (TToll)objects[5];
				int col = 0;
				row += 1;
				excelWorkBook.addCell(col++, row, CharTools.javaScriptEscape(termName));				//终端名称
				excelWorkBook.addCell(col++, row, CharTools.javaScriptEscape(vehicleNumber));       	//车牌号
				excelWorkBook.addCell(col++, row, CharTools.javaScriptEscape(simcard));					//手机号
				excelWorkBook.addCell(col++, row, CharTools.javaScriptEscape(groupName));				//所属组
				excelWorkBook.addCell(col++, row,DateUtility.dateToStr(toll.getPayDate()));				//缴费日期
				excelWorkBook.addCell(col++, row,CharTools.javaScriptEscape(toll.getPayPlace()));		//缴费地点
				excelWorkBook.addCell(col++, row, CharTools.killNullLong2String(toll.getExpenses(),"0"));//缴费金额
				excelWorkBook.addCell(col++, row,CharTools.javaScriptEscape(toll.getHandler()));		//经手人
				excelWorkBook.addCell(col++, row,CharTools.javaScriptEscape(toll.getDemo()));			//备注
			}
			excelWorkBook.write();
			return null;
		}
		
		
		
		int startint = Integer.parseInt(start);
		int limitint = Integer.parseInt(limit);
		int page = startint / limitint + 1;
	
		Page<Object[]> list = tollServiceImpl.listToll(entCode, userId, page, limitint, startDate, endDate, searchValue);
		StringBuffer jsonSb = new StringBuffer();
	    String total = "";
		if (list != null && list.getResult()!=null) {
			total = "{total:"+list.getTotalCount()+",data:[";
	    	Iterator<Object[]> i = list.getResult().iterator();
	    	while(i.hasNext()){
	    		Object[] userObj = (Object[])i.next();
	    		Long id = (Long)userObj[0];
	    		String termName = (String)userObj[1];
	    		String vehicleNumber = (String)userObj[2];
	    		String simcard = (String)userObj[3];
	    		String groupName = (String)userObj[4];
	    		TToll tToll = (TToll)userObj[5];
	    		jsonSb.append("{");
	    		jsonSb.append("id:'"+id+"',");
	    		jsonSb.append("deviceId:'"+CharTools.javaScriptEscape(tToll.getDeviceId())+"',");
	    		jsonSb.append("termName:'"+CharTools.javaScriptEscape(termName)+"',");
	    		jsonSb.append("vehicleNumber:'"+CharTools.javaScriptEscape(vehicleNumber)+"',");
	    		jsonSb.append("simcard:'"+CharTools.javaScriptEscape(simcard)+"',");
	    		jsonSb.append("groupName:'"+CharTools.javaScriptEscape(groupName)+"',");
	    		jsonSb.append("payDate:'"+DateUtility.dateToStr(tToll.getPayDate())+"',");
	    		jsonSb.append("payPlace:'"+CharTools.javaScriptEscape(tToll.getPayPlace())+"',");
	    		jsonSb.append("expenses:'"+CharTools.killNullLong2String(tToll.getExpenses(),"0")+"',");
	    		jsonSb.append("handler:'"+CharTools.javaScriptEscape(tToll.getHandler())+"',");
	    		jsonSb.append("demo:'"+CharTools.javaScriptEscape(tToll.getDemo())+"'");
	    		jsonSb.append("},");
	    	}
	    	if(jsonSb.length()>0){
	    		jsonSb.deleteCharAt(jsonSb.length()-1);
	    	}
	    	jsonSb.append("]}");
	    }
	    response.setContentType("text/json; charset=utf-8");
	    response.getWriter().write(total+jsonSb.toString());
		return mapping.findForward(null);
	}
	
	public ActionForward addToll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = this.getCurrentUser(request);
		if (userInfo == null) {
			response.getWriter().write("{result:\"9\"}");// 未登录
			return mapping.findForward(null);
		}
		String empCode = userInfo.getEmpCode();
		Long userId = userInfo.getUserId();
		String deviceId = request.getParameter("deviceId");
		String payDate = request.getParameter("payDate");
		String payPlace = request.getParameter("payPlace");
		String expenses = request.getParameter("expenses");
		String handler = request.getParameter("handler");
		String demo = request.getParameter("demo");
		if(deviceId == null || deviceId.length() <= 0){
			response.getWriter().write("{result:\"9\"}");// 未登录
			return mapping.findForward(null);
		}
		payPlace = CharTools.killNullString(payPlace);
		payPlace = java.net.URLDecoder.decode(payPlace, "utf-8");
		expenses = CharTools.killNullString(expenses);
		
		handler = CharTools.killNullString(handler);
		handler = java.net.URLDecoder.decode(handler, "utf-8");
		handler = CharTools.killNullString(handler);
		
		demo = CharTools.killNullString(demo);
		demo = java.net.URLDecoder.decode(demo, "utf-8");
		demo = CharTools.killNullString(demo);
		
		TToll tToll = new TToll();
		tToll.setDeviceId(deviceId);
		tToll.setEmpCode(empCode);
		tToll.setUserId(userId);
		tToll.setCreateDate(new Date());
		tToll.setChangeDate(new Date());
		tToll.setPayDate(DateUtility.strToDate(payDate));
		tToll.setPayPlace(payPlace);
		tToll.setExpenses(CharTools.str2Long(expenses,(long)0));
		tToll.setHandler(handler);
		tToll.setDemo(demo);
		tollServiceImpl.save(tToll);
		// 日志实例
		TOptLog tOptLog = new TOptLog();
		// 日志记录
		tOptLog.setEmpCode(userInfo.getEmpCode());
		tOptLog.setUserName(userInfo.getUserAccount());
		tOptLog.setAccessIp(userInfo.getIp());
		tOptLog.setUserId(userInfo.getUserId());
		tOptLog.setFunFType(LogConstants.Toll_SET);
		tOptLog.setFunCType(LogConstants.Toll_SET_ADD);
		tOptLog.setResult(1L);
		tOptLog.setOptDesc("过路过桥记录添加成功! ");
		LogFactory.newLogInstance("optLogger").info(tOptLog);
		response.getWriter().write("{result:\"1\"}");//成功
		return mapping.findForward(null);
	}
	
	public ActionForward updateToll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = this.getCurrentUser(request);
		String empCode = userInfo.getEmpCode();
		Long userId = userInfo.getUserId();
		String id = request.getParameter("id");
		String deviceId = request.getParameter("deviceId");
		String payDate = request.getParameter("payDate");
		String payPlace = request.getParameter("payPlace");
		String expenses = request.getParameter("expenses");
		String handler = request.getParameter("handler");
		String demo = request.getParameter("demo");
		if(deviceId == null || deviceId.length() <= 0){
			response.getWriter().write("{result:\"9\"}");// 未登录
			return mapping.findForward(null);
		}
		payPlace = CharTools.javaScriptEscape(payPlace);
		payPlace = java.net.URLDecoder.decode(payPlace, "utf-8");
		expenses = CharTools.killNullString(expenses);
		
		handler = CharTools.javaScriptEscape(handler);
		handler = java.net.URLDecoder.decode(handler, "utf-8");
		demo = CharTools.killNullString(demo);
		demo = java.net.URLDecoder.decode(demo, "utf-8");
		demo = CharTools.killNullString(demo);
		TToll tToll = new TToll();
		tToll.setId(Long.parseLong(id));
		tToll.setDeviceId(deviceId);
		tToll.setEmpCode(empCode);
		tToll.setUserId(userId);
		tToll.setCreateDate(new Date());
		tToll.setChangeDate(new Date());
		tToll.setPayDate(DateUtility.strToDate(payDate));
		tToll.setPayPlace(payPlace);
		tToll.setExpenses(CharTools.str2Long(expenses,(long)0));
		tToll.setHandler(handler);
		tToll.setDemo(demo);
		tollServiceImpl.update(tToll);
		// 日志实例
		TOptLog tOptLog = new TOptLog();
		// 日志记录
		tOptLog.setEmpCode(userInfo.getEmpCode());
		tOptLog.setUserName(userInfo.getUserAccount());
		tOptLog.setAccessIp(userInfo.getIp());
		tOptLog.setUserId(userInfo.getUserId());
		tOptLog.setFunFType(LogConstants.Toll_SET);
		tOptLog.setFunCType(LogConstants.Toll_SET_UPDATE);
		tOptLog.setResult(1L);
		tOptLog.setOptDesc("过路过桥记录修改成功! ");
		LogFactory.newLogInstance("optLogger").info(tOptLog);
		response.getWriter().write("{result:\"1\"}");//成功
		return mapping.findForward(null);
	}
	
	public ActionForward delToll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userInfo = this.getCurrentUser(request);
		String ids = request.getParameter("ids");
		tollServiceImpl.deleteAll(ids);
		// 日志实例
		TOptLog tOptLog = new TOptLog();
		// 日志记录
		tOptLog.setEmpCode(userInfo.getEmpCode());
		tOptLog.setUserName(userInfo.getUserAccount());
		tOptLog.setAccessIp(userInfo.getIp());
		tOptLog.setUserId(userInfo.getUserId());
		tOptLog.setFunFType(LogConstants.Toll_SET);
		tOptLog.setFunCType(LogConstants.Toll_SET_DELETE);
		tOptLog.setResult(1L);
		tOptLog.setOptDesc("过路过桥记录删除成功! ");
		LogFactory.newLogInstance("optLogger").info(tOptLog);
		response.getWriter().write("{result:\"1\"}");//成功
		return mapping.findForward(null);
	}
}
