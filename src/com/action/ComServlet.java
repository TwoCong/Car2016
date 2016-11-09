package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
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

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		if(method.equals("addTH")){  //add teacher
			String title=request.getParameter("title");
			String content=request.getParameter("infoContent");   
				 int flag = cBean.comUp("insert into news(title, content,sj ) " +
							"values('"+title+"', '"+content+"', '"+date2+"' )");
					if(flag == Constant.SUCCESS){ 
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
					}
					else { 
						request.setAttribute("message", "操作失败！");
						request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
					}
			 
		}
		else if(method.equals("upTH")){ ///update teacher
			String id = request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("infoContent");  
			int flag = cBean.comUp("update news set title='"+title+"', content='"+content+"'  where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
			}
			else if(flag == Constant.NAME_ERROR){ 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("delTH")){  //del teacher
			String id = request.getParameter("id");
			int flag = cBean.comUp("delete from news where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/news/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("qyJS")){  //del teacher
			String js = request.getParameter("infoContent");
			int flag = cBean.comUp("update js set js='"+js+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/qy/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/qy/index.jsp").forward(request, response); 
			}
		}
		
		else if(method.equals("delQC")){  //del teacher
			String id = request.getParameter("id");
			int flag = cBean.comUp("delete from qc where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/hzp/index.jsp").forward(request, response); 
			}
		}
		
		
		else if(method.equals("addPREP")){  //del teacher
			String id = request.getParameter("id");
			String qcsj = request.getParameter("qcsj"); //取车时间
			String qcdd = request.getParameter("qcdd");  //取车地点
			String hcsj = request.getParameter("hcsj");  //还车时间
			String hcdd = request.getParameter("hcdd");  //还车地点
			String member=(String)session.getAttribute("member");
			int flag = cBean.comUp("insert into zc(qcid,qcsj,qcdd,hcsj,hcdd,member) " +
					"values('"+id+"','"+qcsj+"','"+qcdd+"','"+hcsj+"','"+hcdd+"','"+member+"')");
			if(flag == Constant.SUCCESS){ 
				
				request.setAttribute("message", "操作成功，请登录会员中心查看详细信息！");
				request.getRequestDispatcher("pay.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("nhzp.jsp").forward(request, response); 
			}
		}
		else if(method.equals("upPREP")){  //del teacher
			String id = request.getParameter("id");
			String qcsj = request.getParameter("qcsj");
			String qcdd = request.getParameter("qcdd");
			String hcsj = request.getParameter("hcsj");
			String hcdd = request.getParameter("hcdd"); 
			String sql="update zc set  qcsj='"+qcsj+"',qcdd='"+qcdd+"',hcsj='"+hcsj+"',hcdd='"+hcdd+"' where id='"+id+"'";
			 
			int flag = cBean.comUp(sql);
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("delP")){  //del teacher
			String id = request.getParameter("id");
			int flag = cBean.comUp("delete from zc where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("member/prep/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("AdelP")){  //del teacher
			String id = request.getParameter("id");
			int flag = cBean.comUp("delete from zc where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("shP")){  //del teacher
			String id = request.getParameter("id");
			int flag = cBean.comUp("update zc set zt='已审核' where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/prep/index.jsp").forward(request, response); 
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
