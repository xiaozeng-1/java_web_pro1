package cn.uc.yiqibang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CommentServlet")
public class CommentServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
    
	//����һ������
	public void insertComment(HttpServletRequest request,HttpServletResponse response){
	  //Ҫ�����û��Ĳ������۵�������Ҫ�õ�request���������response��Ӧ����
	  //����������ȡ���۲���
		String content=request.getParameter("content");//��ȡ��������
		System.out.println("ִ�в������۲�������������Ϊ��"+content);
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("����ɹ�:"+content);
	}
	
	
	     //ɾ��һ������
		public void deleteComment(HttpServletRequest request,HttpServletResponse response){
		  //Ҫ�����û��Ĳ������۵�������Ҫ�õ�request���������response��Ӧ����
		  //����������ȡ���۲���
			String commId=request.getParameter("commId");//��ȡ��������
			System.out.println("ִ��ɾ�����۲�����Ҫɾ��������idΪ��"+commId);
			PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("ɾ���ɹ�:"+commId);
		}
		
		
		//.������
}









