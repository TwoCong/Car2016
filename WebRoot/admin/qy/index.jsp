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
<HTML><HEAD><TITLE>��̨������</TITLE>
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
 
    <center><h1>��������</h1></center>
  
 &nbsp;&nbsp;���ǵ�����:������Ϊ����ʵ��Ϊ�ȣ�ȫ��ȫ��Ϊ�ͻ��������ǹ�˾���пͻ����ϡ��������ϵľ�Ӫ�����׿Խ��it����Ʒ�ʡ�רҵ�ļ�������ʵ����������տ�Ŀͻ������Ŷӣ����Ͽͻ�����Ϣʱ���ĸ���·�ϳ۳ң������ȹ̡���չ���ҳϡ���Ч���Ž��봴�µľ��������˲�ע�ؼ�����ʹ�ͻ���������Ϣ�Ƽ���չ���³ɹ���ͬʱ���ϻ���������档
<center><h3>ѡ�������Ĵ�����</h3></center>
<h4>��ʡ��֧</h4>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����С����ҵ��������������࣬���רְ����ʦ��Ҫ֧����Ӧ�Ĺ��ʡ��������籣�ȷ��ã���ά���������ֲ������֧�����Եýϸߡ����������ǹ�˾�ķ��񣬿��Դ�󽵵���ϵͳά������ɵ���ط��ã���Լ��ҵ��֧��
<h4>����ḻ</h4> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;����Ϊ��ҵ�ṩ����ǿ����񣬰�������ϵͳ�豸������ϵͳά����¼��ϵͳά����¼�����ȷ�����Ŀ�����ǵĹ���ʦ��������ḻ��������ʱ�ܻ�ù�˾ǿ��ļ���֧�֣������û�ϵͳ�������С�
<h4>����רҵ</h4> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;����Ϊ��ҵ�ṩ����ǿ����񣬰�������ϵͳ�豸������ϵͳά����¼��ϵͳά����¼�����ȷ�����Ŀ�����ǵĹ���ʦ��������ḻ��������ʱ�ܻ�ù�˾ǿ��ļ���֧�֣������û�ϵͳ�������С�


</form>
   </TD>
	</TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
