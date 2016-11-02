package com.bean;

/**
 * ���Ź���
 * 
 */
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class NewsBean {

	private String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	private List list;
	private ResultSet rs = null;
	
	
	//���ö����ŷ�ҳ
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from news where up='0' ";
	private String sql2="select * from news where up='0' order by id desc ";
	private String sql3="select count(*) from news where up='1' ";
	private String sql4="select * from news where up='1' order by id desc ";
    public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount() { //�õ���Ϣ����
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }
    public int getPageCount() { //�õ�������ҳ������ÿҳҪ��ʾ������Ϣ��
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page) { //�õ�ÿҳҪ��ʾ����Ϣ
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql2);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getString(1));
    				list2.add(rs.getString(2));
    				list2.add(rs.getString(3));
    				list2.add(rs.getString(5));
    				list2.add(rs.getString(6));
    				list2.add(rs.getString(7));
    				list2.add(rs.getString(8));
    				list2.add(rs.getString(9));
    				list.add(list2);
                } else {
                    break; //���ٿ�ѭ����ʱ��
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        } finally {
            dbo.close();
        }
    }
    /////////////////////////////////////////
    //�ö����ŷ�ҳ
    public int getMessageCountUp() { //�õ���Ϣ����
        DBO dbo=new DBO();
        dbo.open();
         try { 
             rs = dbo.executeQuery(sql3);
             rs.next();
             count = rs.getInt(1);
             return count;
         } catch (SQLException ex) {
             ex.printStackTrace();
             return -1;
         } finally {
             dbo.close();
         }
     }
     public List getMessageUp(int page) { //�õ�ÿҳҪ��ʾ����Ϣ
         DBO dbo=new DBO();
         dbo.open();
         List list = new ArrayList();
         try {
             rs = dbo.executeQuery(sql4);
             for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                 rs.next();
             }
             for (int t = 0; t < EVERYPAGENUM; t++) {
                 if (rs.next()) {
                     qq++;
                     List list2=new ArrayList();
                     list2.add(rs.getString(1));
     				list2.add(rs.getString(2));
     				list2.add(rs.getString(3));
     				list2.add(rs.getString(5));
     				list2.add(rs.getString(6));
     				list2.add(rs.getString(7));
     				list2.add(rs.getString(8));
     				list2.add(rs.getString(9));
     				list.add(list2);
                 } else {
                     break; //���ٿ�ѭ����ʱ��
                 }
             }
             return list;
         } catch (SQLException ex) {
             ex.printStackTrace();
             return list;
         } finally {
             dbo.close();
         }
     }
	
	//add news
	public int addNews(String title,String pic,String content,String adder,String ifhide){
		String sql = "insert into news (title,pic,content,addtime,adder,ifhide,visit,up) " +
				"values ('"+title+"','"+pic+"','"+content+"','"+date+"','"+adder+"','"+ifhide+"','0','0')";
		String sql2 = "update news set ifhide='0' where pic!='��'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			if(!pic.equals("��")){
				dbo.executeUpdate(sql2);
			}
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//update news no pic
	public int updateNews(int id,String title,String content,String adder,String ifhide){
		String sql = "update news set title = '"+title+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',ifhide='"+ifhide+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
//	update news with pic
	public int updateNewsWithPic(int id,String title,String pic,String content,String adder,String ifhide){
		String sql = "update news set title = '"+title+"',pic='"+pic+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',ifhide='"+ifhide+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//delete news
	public int delNews(int id[],String dir){
		DBO dbo = new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				rs = dbo.executeQuery("select pic from news where id='"+id[i]+"'");
				rs.next();
				String str=rs.getString(1);
				del(dir+str);
				dbo.executeUpdate("delete from  news  where  id = '"+id[i]+"'");				
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	public void del(String filepath) {
		try{
			File f = new File(filepath);//�����ļ�·��        
			if(f.exists()){//�ж����ļ�����Ŀ¼
			    f.delete();//�ݹ����
			}
		}catch(Exception e){
			
		}
	}
	//���Ρ���ʾ����
	public int hideNews(int id){
		String sql = "update news set ifhide='1' where id='"+id+"'";
		String sql2 = "update news set ifhide='0' where id='"+id+"'";
		String sql3 = "select ifhide,pic from news where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			String pic=rs.getString(2);
			if(!pic.trim().equals("��")){
				dbo.executeUpdate("update news set ifhide='0' where pic!='��'");
			}
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
   //�ö� ȡ���ö� ����
	public int upNews(int id){
		String sql = "update news set up='1' where id='"+id+"'";
		String sql2 = "update news set up='0' where id='"+id+"'";
		String sql3 = "select up from news where id='"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql3);
			rs.next();
			int i = rs.getInt(1);
			if(i == 1){
				int flag = dbo.executeUpdate(sql2);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
			else{
				int flag = dbo.executeUpdate(sql);
				if(flag == 1)
					return Constant.SUCCESS;
				else
					return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//get one news to update
	public List getOneNews(int id){
		String sql = "select * from news where id = '"+id+"'";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));//0id
				list.add(rs.getString(2));//1title
				list.add(rs.getString(3));//2pic
				list.add(rs.getString(4));//3content
				list.add(rs.getString(6));//5adder
				list.add(rs.getString(7));//6ifhide
				list.add(rs.getString(8));//7visit
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get news count
	public int getNewsCount(){
		String sql = "select count (*) from news ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
			else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			dbo.close();
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//��ҳ10������
	public List getIndexNews(){
		String sql = "select  id,title,addtime from news where  ifhide='1' order by id desc  limit 0,10 ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);			
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	��ҳ12������
	public List getIndexNews12(){
		String sql = "select  id,title,addtime from news where  ifhide='1' order by id desc  limit 0,13 ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			for(int i=0;i<6;i++){
				rs.next();
			}
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	��ҳͼƬ����
	public List getIndexPicNews(){
		String sql = "select  id,pic from news where  ifhide='1' and pic!='��' ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	//ǰ̨����ҳ�����б�
	public List getAllNews(){
		String sql = "select id,title,addtime  from news   order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	5����������
	public List get5HotNews(){
		String sql = "select  id,title from news where  ifhide='1' order by visit desc  limit 0,10  ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	���µ����
	public int upVisit(int id){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate("update  news set visit=visit+1  where  id = '"+id+"'");		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//��һ��
	public List getNextNews(int id){
		String sql = "select id,title from news where  ifhide='1' and id>'"+id+"' order by id asc  limit 0,1 ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("id"));
				list.add(rs.getString("title"));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	} 
//	 ��һ��
	public List getFollowNews(int id){
		String sql = "select id,title from news where  ifhide='1' and id<'"+id+"' order by id desc  limit 0,1 ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("id"));
				list.add(rs.getString("title"));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	} 
}
