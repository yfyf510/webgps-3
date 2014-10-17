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
		alert("��ѯ���ڸ�ʽ����");
		return;
	}
	startdate=year+"-"+month+"-"+day;
	var endYear=document.LoginLogForm.strEndYearId.value;
	var endMonth = document.LoginLogForm.strEndMonthId.value;
	var endDay = document.LoginLogForm.strEndDayId.value;
	enddate=endYear+"-"+endMonth+"-"+endDay;
	
	//�ж�����
	var start_date = new Date(year,(month-1),day);
	var end_date = new Date(endYear,(endMonth-1),endDay);
	if(end_date<start_date){
		alert("����ʱ�������ڻ���ڿ�ʼʱ�䣡");
		return;
	}
	
	document.LoginLogForm.strStartDate.value= startdate;
	document.LoginLogForm.strEndDate.value= enddate;
	document.LoginLogForm.isImportExcel.value= isImportExcel;
	document.LoginLogForm.submit();
}



</SCRIPT>
<script>

//ѡ��ҳ��
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
<title>��½��־</title>
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
	    <td class="baobiao_title">��½��־</td>
	  </tr>
	   
	</table>
	<table width=750 border=0 cellpadding=0 cellspacing=0 align=center>
		<tr>
			<td width="90%">ѡ��ʱ��: <html:select property="strStartYearId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="yearList" label="strYear"
					value="id" />
			</html:select> ��
			<html:select property="strStartMonthId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="monthList" label="strMonth" value="id" />
			</html:select> �� 
			<html:select property="strStartDayId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="dayList" label="strDay" value="id" />
			</html:select> �� <input type=button name=Submit class="button" value='��ѯ'
				onclick="_postForm('false');"></td>  
		</tr> 
		<tr id="endtimeTR" style="display:">
			<td width="90%" >����ʱ��: <html:select property="strEndYearId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="yearList" label="strYear"
					value="id" />
			</html:select> �� <html:select property="strEndMonthId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="monthList" label="strMonth"
					value="id" />  
			</html:select> �� <html:select property="strEndDayId">
				<html:option value="">--��ѡ��--</html:option>
				<html:optionsCollection property="dayList" label="strDay" value="id" />
			</html:select> �� </td>
		</tr>
		</table>
<table width=700 border=0 cellspacing=3 cellpadding=0 align=center>
	<tr>
		<td align=right><c:if test="${loginList.totalPages>0}">��${loginList.pageNo}ҳ</c:if> ��${loginList.totalPages}ҳ
		<c:if test="${loginList.hasPre}">
			<a href="javascript:void(0);" onclick="selectPageNo('1')">��ҳ</a>
			<a href="javascript:void(0);" onclick="selectPageNo('${loginList.prePage}')">��ҳ</a>
		</c:if>
		<c:if test="${loginList.hasNext}">
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.nextPage}')">��ҳ</a>
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.totalPages}')">βҳ</a>
		</c:if>
		<c:if test="${loginList.totalPages>0}">
		�鿴�� 
		<select name=selpage1 onchange='selectPageNo(selpage1.value);'>
			<c:forEach var="i" begin="1" end="${loginList.totalPages}" step="1">
				<option value="${i}" 
				<c:if test="${i==loginList.pageNo}"> selected </c:if>
				>${i}</option>
			</c:forEach>
		</select>
		 ҳ 
		 </c:if>
		</td>
	</tr>
</table>
<table width="750" border=1 align=center cellpadding=1 cellspacing=0 bordercolorlight="#bfbfbf" bordercolordark="#FFFFFF" bgcolor="#f3f3f3">
	<tr class="list">
		<td>������</td>
		<td>��������</td>
		<td>����ʱ��</td>
		<td>���ʵ�ַ</td>
		<td>�������</td>
	</tr>

	<c:forEach var="tLoginLog" items="${loginList.result}"
		varStatus="status">
	    <tr onMouseOver="javascript:this.bgColor='#ffffff';" onMouseOut="javascript:this.bgColor='#f3f3f3';">
			<td >${tLoginLog.userName}&nbsp;</td>
			<td>${tLoginLog.loginDesc}&nbsp;</td>
			<td>${tLoginLog.loginTime}&nbsp;</td>
			<td>${tLoginLog.accessIp}&nbsp;</td>
			
			<td>${tLoginLog.result==1?"�ɹ�":"ʧ��"}&nbsp;</td>
		</tr>
	</c:forEach>
</table> 
<table width=700 border=0 cellspacing=3 cellpadding=0 align=center>
	<tr>
		<td><input type="button" value="����EXCEL" onClick="_postForm('true')"></td>
		<td align=right><c:if test="${loginList.totalPages>0}">��${loginList.pageNo}ҳ</c:if> ��${loginList.totalPages}ҳ
		<c:if test="${loginList.hasPre}">
			<a href="javascript:void(0);" onclick="selectPageNo('1')">��ҳ</a>
			<a href="javascript:void(0);" onclick="selectPageNo('${loginList.prePage}')">��ҳ</a>
		</c:if>
		<c:if test="${loginList.hasNext}">
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.nextPage}')">��ҳ</a>
		<a href="javascript:void(0);" onclick="selectPageNo('${loginList.totalPages}')">βҳ</a>
		</c:if>
		<c:if test="${loginList.totalPages>0}">
		�鿴�� 
		<select name="selpage2" onchange='selectPageNo(selpage2.value);'>
			<c:forEach var="i" begin="1" end="${loginList.totalPages}" step="1">
				<option value="${i}" 
				<c:if test="${i==loginList.pageNo}">selected</c:if>
				>${i}</option>
			</c:forEach>
		</select>
		 ҳ 
		 </c:if>
		</td>
	</tr>
</table>


</html:form>
</body>