<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
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
	String user=(String)session.getAttribute("user");
	if(user==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		List pagelist3=cb.getCom("select * from zc ",8);
%>
<BODY >
<TABLE  cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top > 
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
     <tr class="head"> 
      <td align="center">购车编号</td>
      <td  align="center">汽车名称</td>
      <td  align="center">租金</td>
      <td  align="center">取车时间</td>
      <td  align="center">取车地点</td>
      <td  align="center">还车时间</td>
      <td  align="center">还车地点</td>
      <td  align="center">费用信息</td>
      <td  align="center">租车会员</td>
      <td  align="center">状态</td> 
      <td  align="center">删除</td>
    </tr>
    <%
    		if(!pagelist3.isEmpty()){
				for(int i=0;i<pagelist3.size();i++){
					List pagelist2 =(ArrayList)pagelist3.get(i);
			%> 
	<tr  class="trA" onMouseOver="this.className='trB'" onMouseOut="this.className='trA'"> 
      <td  align="center" style="border-bottom:1px dotted #ccc;"><%=i+1 %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getString("select  cm from qc where id='"+pagelist2.get(1).toString()+"'") %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=cb.getString("select  zj from qc where id='"+pagelist2.get(1).toString()+"'") %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(2).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(3).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(4).toString() %></td>
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(5).toString() %></td> 
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;"><%=pagelist2.get(6).toString() %></td>        
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
	  <% float f=98;String str="普通会员，享受9.8折优惠";
	  	int num=cb.getCount("select jf from member where username='"+pagelist2.get(6).toString()+"'");
	  	if(num<5000){str="普通会员，享受9.8折优惠";f=98;}
	  	else if(num>=5000&&num<12000){str="银卡会员，享受9.8折优惠";f=98; }
		else if(num>=12000){str="金卡会员，享受9.5折优惠";f=95; }
		else if(num>=20000){str="白金卡会员，享受9折优惠";f=90;} 
	  %>
	  
	  <%=str+"当前费用："+(Float.parseFloat(cb.getString("select  zj from qc where id='"+pagelist2.get(1).toString()+"'"))/100)*f+"元" %>
	  </td>
	  <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">

<a href="<%=basePath %>ComServlet?method=shP&id=<%=pagelist2.get(0).toString()%>"><%=pagelist2.get(7).toString() %></a></td>  
     
      <td align="center" bgcolor="#FFFFFF"  style="border-bottom:1px dotted #ccc;">
      <a href="<%=basePath %>ComServlet?method=AdelP&id=<%=pagelist2.get(0).toString()%>">删除</a></td>
    </tr>
	
<%
	}} 
%>  
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
