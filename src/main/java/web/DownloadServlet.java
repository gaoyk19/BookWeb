package web;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

//@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要下载的文件名
        String downloadFileName = "2.png";
        //2.读取要下载的文件内容(通过ServletContext对象可以获取)
        ServletContext servletContext = getServletContext();
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        //获取要下载文件的类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);

        //获取响应的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //3.把下载的文件内容传回给客户端（利用commons-IO）
        IOUtils.copy(resourceAsStream,outputStream);

        //4.设置响应头
        response.setContentType(mimeType);



    }


}
