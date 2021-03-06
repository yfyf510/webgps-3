<%@ page contentType="text/html;charset=gb2312"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<SCRIPT LANGUAGE="JavaScript"> 

function _postForm(isImportExcel){
	var querytype="";
	var startdate="";
	var enddate="";
	var year="";
	var month="";
	var day = "";

	year=document.LoginLogForm.strStartYearId.value;
	month = document.LoginLogForm.strStartMonthId.value;
	day = document.LoginLogForm.strStartDayId.value;

	if( year =="" || month == "" || day=="" ){
		alert("查询日期格式错误");
		return;
	}
	startdate=year+"-"+month+"-"+day;
	var endYear=document.LoginLogForm.strEndYearId.value;
	var endMonth = document.LoginLogForm.strEndMonthId.value;
	var endDay = document.LoginLogForm.strEndDayId.value;
	enddate=endYear+"-"+endMonth+"-"+endDay;
	
	//判断日期
	var start_date = new Date(year,(month-1),day);
	var end_date = new Date(endYear,(endMonth-1),endDay);
	if(end_date<start_date){
		alert("结束时间必须大于或等于开始时间！");
		return;
	}
	
	document.LoginLogForm.strStartDate.value= startdate;
	document.LoginLogForm.strEndDate.value= enddate;
	document.LoginLogForm.isImportExcel.value= isImportExcel;
	document.LoginLogForm.submit();
}



</SCRIPT>
<script>

//选择页数
function selectPageNo(pageNo){
	document.forms[0].pageNo.value=pageNo;
	document.forms[0].pageSize.value=${loginList.pageSize}; 
	_postForm('false');
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel='stylesheet' href='../css/sap.css' type='text/css'>
<title>登陆日志</title>
</head>

<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0>
<html:form action="/loginlog.do" method="Post">
	<html:hidden property="method" value="displayList" />
	<html:hidden property="strStartDate" value="" />
	<html:hidden property="isImportExcel" value="" />
	<html:hidden property="strEndDate" />
	<input type="hidden" name="pageNo" value="${loginList.pageNo}">
	<input type="hidden" name="pageSize" value="30">
	<table width="100%" border=0 cellspacing=0 cellpadding=0 align=center height=30>
      <tr class="top">
	    <td align=right>&nbsp;</td>
      </tr>
    </table>
	<table width=100% border=0 cellspacing=0 cellpadding=2 align=center>
	  <tr> 
	    <td class="baobiao_title">登陆日志</td>
	  </tr>
	   
	</table>
	<table width=750 border=0 cellpadding=0 cellspacing=0 align=center>
		<tr>
			<td width="90%">选择时间: <html:select property="strStartYearId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="yearList" label="strYear"
					value="id" />
			</html:select> 年
			<html:select property="strStartMonthId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="monthList" label="strMonth" value="id" />
			</html:select> 月 
			<html:select property="strStartDayId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="dayList" label="strDay" value="id" />
			</html:select> 日 <input type=button name=Submit class="button" value='查询'
				onclick="_postForm('false');"></td>  
		</tr> 
		<tr id="endtimeTR" style="display:">
			<td width="90%" >结束时间: <html:select property="strEndYearId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="yearList" label="strYear"
					value="id" />
			</html:select> 年 <html:select property="strEndMonthId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="monthList" label="strMonth"
					value="id" />  
			</html:select> 月 <html:select property="strEndDayId">
				<html:option value="">--请选择--</html:option>
				<html:optionsCollection property="dayList" label="strDay" value="id" />
			</html:select> 日 </td>
		</tr>
		</table>
<table width=700 border=0 cellspacing=3 cellpadding=0 align=center>
	<tr>
		<td align=right><c:if test="${loginList.totalPages>0}">第${loginList.pageNo}页</c:if> 共${loginList.totalPages}页
		<c:if test="${loginList.hasPre}">
			<a href="javascript:void(0);" onclick="selectPageNo('1')">首页</a>
			<a href="javascript:void(0);" onclick="selectPageNo('${loginList.prePage}')">上页</a>
		</c:if>
		<c:if test="${loginList.hasNext}">
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.nextPage}')">下页</a>
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.totalPages}')">尾页</a>
		</c:if>
		<c:if test="${loginList.totalPages>0}">
		查看第 
		<select name=selpage1 onchange='selectPageNo(selpage1.value);'>
			<c:forEach var="i" begin="1" end="${loginList.totalPages}" step="1">
				<option value="${i}" 
				<c:if test="${i==loginList.pageNo}"> selected </c:if>
				>${i}</option>
			</c:forEach>
		</select>
		 页 
		 </c:if>
		</td>
	</tr>
</table>
<table width="750" border=1 align=center cellpadding=1 cellspacing=0 bordercolorlight="#bfbfbf" bordercolordark="#FFFFFF" bgcolor="#f3f3f3">
	<tr class="list">
		<td>操作人</td>
		<td>操作描述</td>
		<td>操作时间</td>
		<td>访问地址</td>
		<td>操作结果</td>
	</tr>

	<c:forEach var="tLoginLog" items="${loginList.result}"
		varStatus="status">
	    <tr onMouseOver="javascript:this.bgColor='#ffffff';" onMouseOut="javascript:this.bgColor='#f3f3f3';">
			<td >${tLoginLog.userName}&nbsp;</td>
			<td>${tLoginLog.loginDesc}&nbsp;</td>
			<td>${tLoginLog.loginTime}&nbsp;</td>
			<td>${tLoginLog.accessIp}&nbsp;</td>
			
			<td>${tLoginLog.result==1?"成功":"失败"}&nbsp;</td>
		</tr>
	</c:forEach>
</table> 
<table width=700 border=0 cellspacing=3 cellpadding=0 align=center>
	<tr>
		<td><input type="button" value="导出EXCEL" onClick="_postForm('true')"></td>
		<td align=right><c:if test="${loginList.totalPages>0}">第${loginList.pageNo}页</c:if> 共${loginList.totalPages}页
		<c:if test="${loginList.hasPre}">
			<a href="javascript:void(0);" onclick="selectPageNo('1')">首页</a>
			<a href="javascript:void(0);" onclick="selectPageNo('${loginList.prePage}')">上页</a>
		</c:if>
		<c:if test="${loginList.hasNext}">
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.nextPage}')">下页</a>
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.totalPages}')">尾页</a>
		</c:if>
		<c:if test="${loginList.totalPages>0}">
		查看第 
		<select name="selpage2" onchange='selectPageNo(selpage2.value);'>
			<c:forEach var="i" begin="1" end="${loginList.totalPages}" step="1">
				<option value="${i}" 
				<c:if test="${i==loginList.pageNo}">selected</c:if>
				>${i}</option>
			</c:forEach>
		</select>
		 页 
		 </c:if>
		</td>
	</tr>
</table>


</html:form>
</body>