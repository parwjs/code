import jingsheng.domain.FormBean;
import jingsheng.domain.User;
import jingsheng.service.ServiceBussiness;
import jingsheng.service.serviceImpl.UserServiceImpl;
import jingsheng.util.beanTool;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        FormBean formBean = beanTool.request2Bean(request,FormBean.class);

        if (!formBean.validate()) {
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }

        //可以直接使用BeanUtils.copyProperties方法将formBean对象的数据拷贝到user对象
/*        try {
            User user = new User();
            user.setId(beanTool.makeId());
            BeanUtils.copyProperties(user,formBean);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/


        User user = beanTool.request2Bean(request,User.class);
        user.setId(beanTool.makeId());


        //调用service层的注册方法，实现注册
        ServiceBussiness service = new UserServiceImpl();
        service.register(user);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
