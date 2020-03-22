import jingsheng.domain.User;
import jingsheng.service.ServiceBussiness;
import jingsheng.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ServiceBussiness serviceBussiness = new UserServiceImpl();
        User user = serviceBussiness.login(username,password);

        if (user == null) {
            request.setAttribute("message","用户名或密码是错的");
        }else {
            request.setAttribute("message","登录成功");
        }
        request.getRequestDispatcher("message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
