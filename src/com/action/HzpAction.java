package com.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.HzpBean;
import com.bean.NewsBean;
import com.util.Constant;

public class HzpAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HzpAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		HttpSession session=request.getSession();
		HzpBean tb=new HzpBean();
		String method=request.getParameter("method").trim();
		///////////////////////////////////////////////////////////////////////商品
		if(method.equals("deltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
		}
		else if(method.equals("hotdeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
		}
		else if(method.equals("tejiadeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/tejia.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/tejia.jsp").forward(request, response);
			}
		}
		else if(method.equals("tuijiandeltrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.delTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/tuijian.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/tuijian.jsp").forward(request, response);
			}
		}
		else if(method.equals("hotTrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.hotTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response);
			}
		}
		else if(method.equals("nohotTrave")){
			String id=request.getParameter("id").trim();
			int flag = tb.nohotTrave(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/hot.jsp").forward(request, response);
			}
		}
		else if(method.equals("addType")){
			String type=request.getParameter("type").trim();
			int flag=tb.addType(type);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			else if(flag==Constant.DEFAULT_ERROR){
				request.setAttribute("message", "该分类已存在！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
		}
		else if(method.equals("delType")){
			String id=request.getParameter("id").trim();
			int flag=tb.delType(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
			
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/hzp/type.jsp").forward(request, response);
			}
		}
		//////////////////////////////////////////////////////////////////购物车
		else if(method.equals("addprep")){
			String sid=request.getParameter("sid").trim();
			String num=request.getParameter("num").trim();
			String member=(String)session.getAttribute("member");
			int flag = tb.addPrep(sid, Integer.parseInt(num), member);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		else if(method.equals("upprep")){/////////////////////////////////////////////////更改购物数量
			String id=request.getParameter("id").trim();
			String num=request.getParameter("num").trim();
			String sid=request.getParameter("sid").trim();
			String snum=request.getParameter("snum").trim();
			int flag = tb.updatePrep(id, num,sid,snum);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
			else if(flag == Constant.DEFAULT_ERROR){
				request.setAttribute("message", "购买数量超过剩余商品数量！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
		}
		else if(method.equals("memberdelPrep")){
			String id=request.getParameter("id");
			int flag = tb.memberdelPrep(id);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
		}
		////////////////////////////////////////////////////////////////////////////////////生成订单
		else if(method.equals("scdDan")){
			String check[] = request.getParameterValues("checkit");
			String member=(String)session.getAttribute("member");
			if(check == null){
				request.setAttribute("message", "请选择要提交生成订单的购物记录！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
			}
			else{
				int id2[]= new int[check.length];
				for(int i = 0;i<check.length;i++){
					int s = Integer.parseInt(check[i]);				
					id2[i] = s;
				}
				int flag = tb.scdDan(id2,member); 
				if(flag == Constant.SUCCESS){
					request.setAttribute("message", "订单已成功提交，请等候管理员发货！");
					request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "系统维护中，请稍后再试！");
					request.getRequestDispatcher("member/prep/index.jsp").forward(request, response);
				}
			}
		}
		else if(method.equals("mdelPost")){/////////////////////会员删除已经发货的订单
			String pid=request.getParameter("pid");
			int flag = tb.mdelPost(pid);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/prep/yi.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("member/prep/yi.jsp").forward(request, response);
			}
		}
		/////////////////////////////////////////////////////////////////////////////////管理员管理订单 删除 发货
		else if(method.equals("admindelPrep")){
			String dnum=request.getParameter("dnum");
			int flag = tb.adelPost(dnum);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response);
			}
		}
		else if(method.equals("admindelPrep2")){
			String dnum=request.getParameter("dnum");
			int flag = tb.adelPost(dnum);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
			}
		}
		 
		else if(method.equals("sendB")){
			String dnum=request.getParameter("dnum");
			String member=request.getParameter("member");
			int flag = tb.sendB(dnum,member);
			if(flag == Constant.SUCCESS){
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
			}
			else{
				request.setAttribute("message", "系统维护中，请稍后再试！");
				request.getRequestDispatcher("admin/prep/prep.jsp").forward(request, response);
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
