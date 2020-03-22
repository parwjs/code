package jingsheng.domain;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class FormBean {

    private String username;
    private String password;
    private String password2;
    private String email;
    private String birthday;

    //用于判断表单提交过来的数据是否合法(建议使用Javascript进行表单判断)
    public boolean validate() {

        //用户名不能为空，并且是3-8的字符 abcdABCD
        if (this.username == null || this.username.equals("")) {
            return false;
        }
        else {
            if (!this.username.matches("[a-zA-Z]{3,8}"))
                return false;
        }

        //密码不能为空，并且是3-8的数字
        if (this.password == null || this.password.trim().matches("")) {
            return false;
        }else {
            if (!this.password.matches("\\d{3,8}")) {
                return false;
            }
        }

        //两次密码保持一致
        if (this.password2 != null && !this.password2.equals("")) {
            if (!this.password2.equals(this.password)){
                return false;
            }
        }

        //邮箱可以为空，如果为空就必须合法
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                System.out.println("邮箱格式错误！");
                return false;
            }
        }

        //日期可以为空，如果为空就必须合法
        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter dateLocaleConverter = new DateLocaleConverter();
                dateLocaleConverter.convert(this.birthday);
            }catch (Exception e) {
                System.out.println("日期格式错误!");
                return false;
            }
        }

        return true;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }
}
