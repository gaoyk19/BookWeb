package web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "UploadServlet", value = "/UploadServlet")
public class UploadServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletInputStream inputStream = request.getInputStream();
//        byte[] buffer = new byte[102400];
//        int n = inputStream.read(buffer);
//        System.out.println(new String(buffer,0,n));
//        System.out.println("接受到内容！");
        //1. 判断是否是多点数据
        if(ServletFileUpload.isMultipartContent(request)){
            //2.创建工厂实现类fileItemFactory
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //3.创建用于解析上传数据的工具类ServletFileUpload
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            //4. 解析上传的数据，得到每一个表单项目
            try {
                List<FileItem> list=servletFileUpload.parseRequest(request);
                //循环判断每一个表单项，是普通类型还是上传的文件
                for(FileItem fileItem:list){
                    if(fileItem.isFormField()){
                        System.out.println("表单项name值："+fileItem.getFieldName());
                        System.out.println("表单项value值："+fileItem.getString("UTF-8"));
                    }else{
                        System.out.println("表单项name值："+fileItem.getFieldName());
                        System.out.println("上传的文件名："+fileItem.getName());
                        try {
                            fileItem.write(new File("f:\\"+fileItem.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
