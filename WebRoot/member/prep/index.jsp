<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>��̨������</TITLE>	1
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<script type="text/javascript">
function del()
{
	pageform.submit();
}
</script>
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
	if(member==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		List pagelist3=cb.getCom("select * from zc where member='"+member+"'",8);
%>
<BODY >
<TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top > 
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
     <tr class="head"> 
      <td width="10%" align="center">���</td>
      <td  align="center">��������</td>
      <td  align="center">���</td>
      <td  align="center">ȡ��ʱ��</td>
      <td  align="center">ȡ���ص�</td>
      <td  align="center">����ʱ��</td>
      <td  align="center">�����ص�</td>
      <td  align="center">������Ϣ</td>
      <td  align="center">״̬</td>
      <td  align="center">��ѯ/�޸�</td>
      <td  align="center">ɾ��</td>
    </tr>
    <%
    		if(!pagelist3.isEmpty()){
				for(int i=0;i<pagelist3.size();i++){
					List pagelist2 =(ArrayList)pagelist3.get(i);
			%> 
	<tr  class="trA" onMouseOver="this.className='trB'" onMouseOut="this.className='trA'"> 
      <td width='10%' align="center" style="border-bottom:1px dotted #ccc;"><%=i+1 %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getString("select  cm from qc where id='"+pagelist2.get(1).toString()+"'") %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getString("select  zj from qc where id='"+pagelist2.get(1).toString()+"'") %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(2).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(3).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(4).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(5).toString() %></td>      
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
	  <% float f=98;String str="��ͨ��Ա������9.8���Ż�";
	  	int num=cb.getCount("select jf from member where username='"+member+"'");
	  	if(num<5000){str="��ͨ��Ա������9.8���Ż�";f=98;}
	  	else if(num>=5000&&num<12000){str="������Ա������9.8���Ż�";f=98; }
		else if(num>=12000){str="�𿨻�Ա������9.5���Ż�";f=95; }
		else if(num>=20000){str="�׽𿨻�Ա������9���Ż�";f=90;} 
	  %>
	  
	  <%=str+"��ǰ���ã�"+(Float.parseFloat(cb.getString("select  zj from qc where id='"+pagelist2.get(1).toString()+"'"))/100)*f+"Ԫ" %>
	  </td>
	  <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(7).toString() %></td>  
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
      <%if(pagelist2.get(7).toString().equals("δ���")){ %>
      <a href="<%=basePath %>member/prep/up.jsp?id=<%=pagelist2.get(0).toString()%>">��ѯ/�޸�</a><%} %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
      <%if(pagelist2.get(7).toString().equals("δ���")){ %>
      <a href="<%=basePath %>ComServlet?method=delP&id=<%=pagelist2.get(0).toString()%>">ɾ��</a><%} %></td>
    </tr>
	
<%
	}} 
%>  
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
