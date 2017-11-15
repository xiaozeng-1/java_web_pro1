package cn.uc.yiqibang.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.uc.yiqibang.beans.TNews;
import cn.uc.yiqibang.dao.TNewsMapper;
import cn.uc.yiqibang.dao.impl.NewsMapperImpl;
import cn.uc.yiqibang.utils.Result;
import cn.uc.yiqibang.utils.WriteResultToClient;
import net.sf.json.JSONObject;

@WebServlet("/NewsServlet")
public class NewsServlet extends BaseServlet {

	private static final long serialVersionUID = 2901354022165043875L;
	
	TNewsMapper newsDao=new NewsMapperImpl();
	
	public void getAllNews(HttpServletRequest request,HttpServletResponse response){
		System.out.println("getAllNews");
		Result result = newsDao.selectAll();
		//将result对象转化为json字符串响应给客户端
		
		//将对象转化为json格式字符串---在含有时间类型的属性的对象在转化为j'son字符串的时候，会报异常
		String jsonObj = JSONObject.fromObject(result).toString();
		/*JSONArray*/
		
		try {
			//获取响应工具，并向客户端输出结果
			response.getWriter().println(jsonObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adminDeleteNewsById(HttpServletRequest request,HttpServletResponse response){
		int newsid=Integer.parseInt(request.getParameter("id"));
		Result result = newsDao.deleteByPrimaryKey(newsid);
		WriteResultToClient.writeMethod(result, response);
	}
	
	
	/*public void adminAddNewsPic(HttpServletRequest request,HttpServletResponse response){
		Result result = newsDao.insertNewsPic(request);
		WriteResultToClient.writeMethod(result, response);
	}*/
	public void adminGetNewsByLike(HttpServletRequest request,HttpServletResponse response){
		String likeStr = request.getParameter("likeStr");
		Result result = newsDao.selectByLike("%"+likeStr+"%");
		WriteResultToClient.writeMethod(result, response);
	}
	
	public void adminInsertNews(HttpServletRequest request,HttpServletResponse response){
		String title=request.getParameter("title");
		String source=request.getParameter("source");
		String author =request.getParameter("author");
		String content =request.getParameter("content");
		String editorValue=request.getParameter("editorValue");
		int  typeid=Integer.parseInt(request.getParameter("typeid"));
	    boolean ifHot=Boolean.parseBoolean(request.getParameter("ifHot"));
	    
	    TNews news=new TNews();
	    news.setnAuthor(author);
	    news.setnContent(editorValue);
	    news.setnCreatetime(new Date());
	    news.setnIfhot(ifHot);
	    news.setnSource(source);
	    news.setnTitle(title);
	    news.setnContent(content);
	    news.settTId(typeid);
	    Result result = newsDao.insertSelective(news);
	    WriteResultToClient.writeMethod(result, response);
	}
	
	
}
