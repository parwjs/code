package downservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import java.util.UUID;

@WebServlet(name = "morefiles")
public class morefiles extends HttpServlet {

    private String makeDirPath(String fileName,String path) {
        //通过文件名算出一级目录和二级目录
        int hashCode = fileName.hashCode();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;

        String dir = path + File.separator + dir1 + File.separator + dir2;

        //检测目录是否存在
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        return dir;
    }

    //生成独一无二的文件名
    private String makeFileName(String fileName) {
        //使用下划线把UUID和文件名分割开来，用于解析文件名
        return UUID.randomUUID().toString() + "_" + fileName;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //通过工厂创建解析器
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //设置fileUpload编码
        fileUpload.setHeaderEncoding("UTF-8");

        //判断上传表单的类型
        if (!fileUpload.isMultipartContent(request)) {
            //上传表单为普通表单，按照传统方式获取数据
            return;
        }
        try {
            //解析request对象，得到List【装载着上传到全部内容】
            List<FileItem> list = fileUpload.parseRequest(request);

            //遍历list，判断装载的内容是普通字段还是上传文件
            for (FileItem fileItem : list) {

                //如果是普通输入项
                if (fileItem.isFormField()) {
                    //得到输入项的名称和值
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name + "=" + value);
                }else {
                    //如果是上传的文件
                    //得到上传的名称【包括路径名】
                    String fileName = fileItem.getName();

                    //截取文件名
                    int index = fileName.lastIndexOf("\\");
                    if (index != -1) {
                        fileName = fileName.substring(index + 1); //fileName不发生改变
                    }

                    //生成独一无二的文件名
                    fileName = makeFileName(fileName);

                    InputStream inputStream = fileItem.getInputStream();
                    //得到项目的路径，把上传文件写到项目中
                    String path = this.getServletContext().getRealPath("/WEB-INF/uploadfile");

                    //判断uploadfile是否存在
                    File file = new File(path);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdir();
                    }

                    //得到分散后的目录路径
                    String realPath = makeDirPath(fileName,path);

                    FileOutputStream outputStream = new FileOutputStream(realPath + File.separator + fileName);

                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(bytes)) > 0) {
                        outputStream.write(bytes,0,len);
                    }

                    inputStream.close();
                    outputStream.close();

                    //删除临时文件数据
                    fileItem.delete();

                    request.getRequestDispatcher("/success.jsp").forward(request,response);

                }
            }
        }catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
