<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<jsp:useBean id="sysb" scope="page" class="com.bean.SystemBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>会员管理中心</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=basePath %>member/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %>member/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %>member/images/Common.js"></SCRIPT>
<STYLE type=text/css>BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
</STYLE>
<META content="MSHTML 6.00.2900.3243" name=GENERATOR></HEAD>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String member=(String)session.getAttribute("member");
	String type=(String)session.getAttribute("type");
	if(member==null||type==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
	 //无、银卡会员9.8折、金卡会员9.5折、白金卡会员9折.
%>

<BODY scroll=no marginheight="0" marginwidth="0">
<br><br><br><br><br><p align=center><font color=red><strong>
<%String str="普通会员，享受9.8折优惠";int num=cb.getCount("select jf from member where username='"+member+"'");if(num>=5000)str="银卡会员，享受9.8折优惠"; 
else if(num>=12000)str="金卡会员，享受9.5折优惠"; 
else if(num>=20000)str="白金卡会员，享受9折优惠"; 
%>
      欢迎您，尊敬的会员：<%=member %> 您目前的积分：<%=num %> 您的身份：<%=str %></strong></font>
	</BODY>
<%} %>
</HTML>
