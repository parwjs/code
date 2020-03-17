package downservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DownFileServlet")
public class DownFileServlet extends HttpServlet {

    private String makeFilePath(String fileName,String path) {

        int hashCode = fileName.hashCode();

        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;

        String dir = path + File.separator + dir1 + File.separator + dir2 + File.separator +  fileName;
        return dir;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到文件的全名
        String fileName = request.getParameter("fileName");

        //如果是中文数据，需要转码
        fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
        System.out.println("fileName = " + fileName);


        //得到保存文件的位置
        String path = this.getServletContext().getRealPath("/WEB-INF/uploadfile");

        //文件是通过文件名进行hashCode打散保存的，通过文件名拿到文件绝对路径
        String fileRealPath = makeFilePath(fileName,path);
        System.out.println(fileRealPath);

        //判断文件是否存在
        File file = new File(fileRealPath);
        if (!file.exists()) {
            request.setAttribute("message","您要下载的资源不存在了!");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return;
        }

        //存在
        //读取该文件并把数据写给浏览器
        FileInputStream inputStream = new FileInputStream(fileRealPath);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            response.getOutputStream().write(bytes,0,len);
        }

        inputStream.close();

        //设置消息头，告诉浏览器，这是下载的文件
        String name = fileName.substring(fileName.lastIndexOf("_") + 1);
        response.setHeader("content-disposition","attachment;filename=" + URLEncoder.encode(name,"UTF-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
