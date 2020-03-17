package downservlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "loadFile")
public class loadFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //测试一：读取上传用户名
        //        String ss = request.getParameter("username");
        //        System.out.println(ss);

        //测试二：读取所有上传的数据(文件+用户名和密码)
//        ServletInputStream inputStream = request.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len = 0;
//
//        while ((len = inputStream.read(bytes)) > 0) {
//            System.out.println(new String(bytes,0,len));
//        }

        //测试三：使用FileUpload组件进行文件和数据的分割
        try {
            //1.得到解析器工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            //2.得到解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8"); //设置解析器的编码格式为UTF-8

            //3.判断上传表单的类型,如果上传表单为普通表单
            if (!upload.isMultipartContent(request)) {
                return;
            }
            //为上传表单，则调用解析器解析上传数据
            List<FileItem> list = upload.parseRequest(request);

            //遍历list,得到用于封装第一个上传输入项数据fileItem对象
            for (FileItem item : list) {
                if (item.isFormField()) {
                        //得到的是普通输入项
                    String name = item.getFieldName();//得到输入项的名称
                    String value = item.getString("UTF-8"); //获取表单时使用UTF-8编码
                    System.out.println(name + "=" + value);
                }else {
                    //得到上传输入项
                    String fileName = item.getName(); //得到上传文件名
                    System.out.println("上传文件名为：" + fileName); //输出上传文件名
                    int index = fileName.lastIndexOf("\\");
                    if (index != -1) {
                        fileName = fileName.substring(index + 1);
                    }
                    //fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    InputStream in = item.getInputStream();  //得到上传数据
                    int len = 0;
                    byte[] buffer = new byte[1024];

                    String savepath = this.getServletContext().getRealPath("/upload");
                    File file = new File(savepath);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdir();
                    }
                    System.out.println(savepath);
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + fileName);//向upload目录写入文件
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer,0,len);
                    }
                    in.close();
                    out.close();

                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
