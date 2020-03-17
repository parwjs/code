package downservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "downloadfiles")
public class downloadfiles extends HttpServlet {

    private void getAllFiles(File filePath,Map map) {
        //如果不是文件，那么它就是文件夹
        if (!filePath.isFile()) {
            //列出文件夹下所有的文件(可能是文件，可能是文件夹)
            File[] files = filePath.listFiles();
            for (File file : files) {
                //得到的文件（或者是文件夹）再对其进行判断
                getAllFiles(file,map);
            }
        }else {
            //进入else语句了，肯定是文件了

            //得到文件名
            String fileName = filePath.getName().substring(filePath.getName().lastIndexOf("_") + 1);

            //将文件全名作为key，文件名作为value保存在map集合中
            map.put(filePath.getName(),fileName);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的目录
        String filePath = this.getServletContext().getRealPath("/WEB-INF/uploadfile");

        Map map = new HashMap();

        //使用递归来得到所有的文件，并添加到Map集合中
        getAllFiles(new File(filePath),map);

        //遍历map
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " = " + value);
        }

        request.setAttribute("map",map);
        request.getRequestDispatcher("/listfiles.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
