package cn.uc.yiqibang.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public void adminLogin(HttpServletRequest request,HttpServletResponse response){
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String code=request.getParameter("code");
    	
    	System.out.println(username+"-"+password+"-"+code);
    	//��ȡ��֤�����ɵ�4����ȷ������
    	HttpSession session = request.getSession();
    	String ccode = String.valueOf(session.getAttribute("ccode"));
    	
    	try{
	    	//���û��������֤�����ȷ�Ľ��бȽ�
	    	if(code!=null&&code.equals(ccode)){
	    		//�����ĺ��û������һ��--��֤�������
	    		System.out.println("��¼");
	    		if("xiaoming".equals(username)&&"1234".equals(password)){
	    			response.sendRedirect(request.getContextPath()+"/index.jsp");
	    		}else{
	    			session.setAttribute("tip", "�û��������������");
	    			response.sendRedirect(request.getContextPath()+"/login.jsp");
	    		}
	    	}else{
	    		session.setAttribute("tip", "��֤�����");
	    		response.sendRedirect(request.getContextPath()+"/login.jsp?ifFirst=false");
	    	}
    	}catch(Exception e){
    		System.out.println("��תʧ��");
    	}
    	
    }

}
