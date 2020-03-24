import xyz.perwjs.domain.Cart;
import xyz.perwjs.service.BusinessService;
import xyz.perwjs.service.impl.BusinessDBServiceImpl;
import xyz.perwjs.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BuyServlet")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取传递过来的bookid
        String id = request.getParameter("bookid");

        //把用户想要买的书放到购物车上
        //用户不单单只有一个，要让购物车上只为当前的用户服务，需要用到会话跟踪技术
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //如果当前用户还没有点击过购买的商品，那么用户的购物车是空的
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }

        //调用BusinessService的方法，实现购物
//        BusinessService service = new BusinessServiceImpl();
        BusinessService service = new BusinessDBServiceImpl();
        service.BuyBook(id,cart);

        //跳转到购物车显示页面
        request.getRequestDispatcher("listCart.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
