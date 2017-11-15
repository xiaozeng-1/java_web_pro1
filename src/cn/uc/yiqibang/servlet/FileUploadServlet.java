package cn.uc.yiqibang.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   public void addUserPhoto(HttpServletRequest request,HttpServletResponse response){
	   //���û�����FileUploadServlet�����������action=addUserPhotoʱ����
	   
	   //�����ļ��Ĵ������ǲ���ʹ��request.getParameter()�������ļ�����Ҫʹ�ô��̹������ļ��ϴ���
	   
	   //���ȴ������̹���
	   DiskFileItemFactory factory=new DiskFileItemFactory();
	   factory.setRepository(new File("D://"));//���ô��̹����Ŀ�--���ص�һ���ļ���
	   factory.setSizeThreshold(50*1024*1024);//50M--������̵Ĵ�С--��byteΪ��λ

	   //�����ļ��ϴ���servlet���������
	   ServletFileUpload upload=new ServletFileUpload(factory);
	   upload.setFileSizeMax(-1);//�ϴ��ĵ����ļ��Ĵ�С    -1��ʾ������
	   upload.setHeaderEncoding("UTF-8");
	   upload.setSizeMax(50*1024*1024);//�����ϴ������ļ����ܴ�С
	   boolean result=false;
	   String saveName="";
	   try {
		   //�����fileItem����ָ�����������==�ļ�+����
		List<FileItem> files = upload.parseRequest(request);//�����������ȡ���еĲ���������+�ļ���
		Iterator<FileItem> it = files.iterator();
		while(it.hasNext()){
			FileItem file=it.next();
			if(file.isFormField()){//ͨ��file.isFormFild()�жϸò����ǲ�����ֵ���ԣ�������ļ�����������false
				System.out.println("��ֵ������"+file.getFieldName());//ͨ��file.getFieldName()��ȡ������
			    System.out.println(file.getString("UTF-8"));//file.getString("UTF-8")�Թ̶������ʽ��ȡ����ֵ
			}else{
				//System.out.println("�ļ�����,����Ϊ:"+file.getName());//ͨ��file.getName()��ȡ�ļ���
				String fileName=file.getName();
				//�����ȡ���ļ��������ļ���
				if(fileName!=null){
					//���ļ�����д�뵽��ǰ��Ŀ��ĳ��Ŀ¼--img
					//��ȡ��Ŀ��ĳ����Դ�ļ��е���ʵ/����·����ʹ��servletContext����.getRealPath(��Դ�ļ�����)
					String fileImgPath = request.getServletContext().getRealPath("img");
					/*File imgPath = new File(fileImgPath);
					if(!imgPath.exists()){
						imgPath.mkdirs();
					}*/
				    System.out.println("Ҫ����ļ�����ʵ·��Ϊ��"+fileImgPath);
//				    C:\Users\12644\workspace_eclipse_jee\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\java_web_pro\img\img1.jpg
				   
				    //�޸ı�����ļ����ļ���Ϊ����ǰʱ��+��׺
				    //��ǰʱ���ȡ��new Date().getTime()    /   System.currentTimeMillis
				    long time = System.currentTimeMillis();
				    //��ȡ�ļ���׺
				    String nextFix = fileName.substring(fileName.lastIndexOf("."));
				    saveName=time+nextFix;//�ļ�����
				    String fileRealPath = fileImgPath+File.separator+saveName;
				    File file1=new File(fileRealPath);//�ļ���ȫ·������ʾ��File����-�ļ�
				    
				    file.write(file1);//��file���ļ�����д���Ӧ���ļ�Ŀ¼��Ӧ���ļ���
				    result=true;
				}else{
					System.out.println("û���ļ�");
				}
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("�ϴ�ʧ��");
		e.printStackTrace();
	}
	 try {
		response.getWriter().println("{\"retMsg\":"+result+",\"imgName\":\""+saveName+"\"}");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
   }

   
   
}
