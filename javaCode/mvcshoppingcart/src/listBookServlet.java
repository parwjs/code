import xyz.perwjs.service.BusinessService;
import xyz.perwjs.service.impl.BusinessServiceImpl;

import java.io.IOException;
import java.util.Map;

public class listBookServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        BusinessService service = new BusinessServiceImpl();
        Map books = service.getAll();


        request.setAttribute("books",books);

        //跳转到相应的jsp
        request.getRequestDispatcher("listBook.jsp").forward(request,response);


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
