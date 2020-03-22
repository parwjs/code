package jingsheng.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

public class beanTool {
    public static <T> T request2Bean(HttpServletRequest request, Class<T> tClass) {
        try {
            //创建tClass对象
            T bean = tClass.newInstance();
            //获取得到Parameter中全部的参数名字
            Enumeration enumeration = request.getParameterNames();


            ConvertUtils.register(new DateLocaleConverter(), Date.class);


            //遍历集合
            while (enumeration.hasMoreElements()) {

                //获取得到每一个带过来参数的名字
                String name = (String) enumeration.nextElement();

                //获取得到值
                String value = request.getParameter(name);


                if (value == null) {
                    continue;
                }else {
                    //将数据封装到Bean对象中
                    BeanUtils.setProperty(bean, name, value);
                }
            }

            return bean;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("封装数据到Bean出现错误!");
        }
    }


    public static String makeId() {
        return UUID.randomUUID().toString();
    }
}
