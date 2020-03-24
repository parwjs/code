import xyz.perwjs.domain.Cart;
import xyz.perwjs.service.BusinessService;
import xyz.perwjs.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCartBook")
public class DeleteCartBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取想要删除的书的id
        String id = request.getParameter("bookid");

        //获取该用户相对应的购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        try {
            //使用Service的删除功能
            BusinessService service = new BusinessServiceImpl();
            service.deleteBook(id,cart);

            request.getRequestDispatcher("listCart.jsp").forward(request,response);
        }catch (ClassCastException e) {
            request.setAttribute("message","购物车为空");
            request.getRequestDispatcher("message.jsp").forward(request,response);
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message","删除中出现异常");
            request.getRequestDispatcher("message.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
