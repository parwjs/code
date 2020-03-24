import xyz.perwjs.domain.Cart;
import xyz.perwjs.exception.CartNotFoundException;
import xyz.perwjs.service.BusinessService;
import xyz.perwjs.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateQuantity")
public class UpdateQuantity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("bookid");
        String quantity = request.getParameter("quantity");

        //得到当前用户的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        try {

            BusinessService service = new BusinessServiceImpl();
            service.UpdateQuantity(id,cart,quantity);

            request.getRequestDispatcher("listCart.jsp").forward(request,response);
        }catch (CartNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message","购物车是空的");
            request.getRequestDispatcher("message.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
