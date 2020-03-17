package downservlet;



import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * SmartUploadFile上传文件涉及到乱码问题，待解决
 */
@WebServlet(name = "Servlet")
public class smartuploadfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //实例化组件
        SmartUpload smartUpload = new SmartUpload();

        //初始化上传操作
        smartUpload.initialize(this.getServletConfig(),request,response);

        try {
            //上传准备
            smartUpload.upload();

            //对于普通数据，单纯到request对象是无法获取得到提交参数的。也是需要依赖smartUpload
            String password = smartUpload.getRequest().getParameter("password");
            System.out.println(password);

//            System.out.println(request.getSession().getServletContext().getRealPath(""));
//            System.out.println(request.getContextPath());

            //对输出路径的文件夹进行检测
            String savepath = this.getServletContext().getRealPath("/uploadFile");
            File file = new File(savepath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            //上传到uploadFile文件夹中
            smartUpload.save("uploadFile");

        }catch (SmartUploadException e) {
             e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
