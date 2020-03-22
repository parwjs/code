package jingsheng.dao.impl;

import jingsheng.dao.UserDao;
import jingsheng.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserImplXML implements UserDao {
    //通过传递进来的username和password在XML文件中查找是否存在该记录
    @Override
    public User find(String username, String password) {
//        System.out.println(System.getProperty("user.dir"));
        //得到XML文档的流对象
        InputStream inputStream = UserImplXML.class.getClassLoader().getResourceAsStream("user.xml");

        //得到dom4j的解析器对象
        SAXReader reader = new SAXReader();

        try {

            //解析XML文档
            Document document = reader.read(inputStream);

            //使用XPATH查找XML文档中是否有传递进来的username和password
            Element element = (Element) document.selectSingleNode("//user[@username='"+ username + "' and@password='" + password + "']");
            //XPATH的意思是：查找所有的User节点，节点满足：属性username的值是传递进来的username...

            if (element == null) {
                return null;
            }

            //如果有，把XML查出来的节点信息封装到User对象
            User user = new User();
            user.setId(element.attributeValue("id"));
            user.setUsername(element.attributeValue("username"));
            user.setPassword(element.attributeValue("password"));
            user.setEmail(element.attributeValue("email"));

            //将字符串的生日转换成Date类型
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            Date birthday = simpleDateFormat.parse(element.attributeValue("birthday"));
            user.setBirthday(birthday);

            return user;

        }catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("初始化时出现错误！");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("生日格式错误！");
        }
    }


    //注册功能(通过外界传递的User对象，在XML中新增一条记录)
    @Override
    public void register(User user) {

        //获取XML文档路径
        String path = UserImplXML.class.getClassLoader().getResource("user.xml").getPath();

        try {
            //获取dom4j解析器，解析XML文档
            SAXReader reader = new SAXReader();
            Document document = reader.read(path);

            //在XML文档中创建新的节点
            Element newElement = DocumentHelper.createElement("user");
            newElement.addAttribute("id",String.valueOf(user.getId()));
            newElement.addAttribute("username",user.getUsername());
            newElement.addAttribute("password",user.getPassword());
//            newElement.addAttribute("email",user.getEmail());

            if (user.getEmail() == null) {
                newElement.addAttribute("email","");
            }else {
                newElement.addAttribute("email",user.getEmail());
            }


            if (user.getBirthday() != null) {

                //日期返回指定的格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                String birthday = simpleDateFormat.format(user.getBirthday());
                newElement.addAttribute("birthday", birthday);
            }else {
                newElement.addAttribute("birthday","");
            }
            //把新创建的节点增加到父节点上
            document.getRootElement().add(newElement);

            //把XML内容中文档的内容写到硬盘上
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(path),outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("文档路径错误！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文档写入错误！");
        }

    }
}
