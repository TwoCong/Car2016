<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page language="java" import="com.fredck.FCKeditor.*" %> 
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=path %>FCKeditor/fckeditor.js"></script> 
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
 
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
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		 String js=cb.getString("select js from js ");
%>
<BODY onload="document.form1.infoContent.value=document.form1.content.value" >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
    <form name="form1" action="<%=basePath %>ComServlet?method=qyJS" method="post" >
 
    <center><h1>关于我们</h1></center>
  
 &nbsp;&nbsp;我们的理念:“诚信为本、实力为先，全心全意为客户”，我们公司秉承客户至上、服务至上的经营理念，以卓越的it服务品质、专业的技术服务实力、技术精湛的客户服务团队，保障客户在信息时代的高速路上驰骋，又以稳固、发展、忠诚、高效、团结与创新的精神，尊重人才注重技术，使客户在享受信息科技发展最新成果的同时不断获得最大的收益。
<center><h3>选择我们四大理由</h3></center>
<h4>节省开支</h4>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对中小型企业，计算机数量不多，如雇专职工程师需要支付相应的工资、福利和社保等费用，而维护工作量又不大，因而支出会显得较高。而采用我们公司的服务，可以大大降低因系统维护而造成的相关费用，节约企业开支。
<h4>经验丰富</h4> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;我们为企业提供功能强大服务，包括建立系统设备档案、系统维护记录、系统维护记录分析等服务项目。我们的工程师不仅经验丰富，而且随时能获得公司强大的技术支持，保障用户系统正常运行。
<h4>技术专业</h4> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;我们为企业提供功能强大服务，包括建立系统设备档案、系统维护记录、系统维护记录分析等服务项目。我们的工程师不仅经验丰富，而且随时能获得公司强大的技术支持，保障用户系统正常运行。


</form>
   </TD>
	</TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
