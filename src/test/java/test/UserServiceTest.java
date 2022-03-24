package test;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServiceTest extends TestCase {
    UserService userService=new UserServiceImpl();

    public void testRegistUser() {
        userService.registUser(new User(null,"lixh","113","lixh@163.com"));
        userService.registUser(new User(null,"wangl","417","wangl@163.com"));
        userService.registUser(new User(null,"hanyf","417","hanyf"));
    }


    public void testLogin() {
        if(userService.login(new User(null,"gaoyk","620","gaoyk@163.com"))==null){
            System.out.println("登陆失败！");
        }else{
            System.out.println("登陆成功！");
        }
    }

    public void testIsExistUser() {
        if(userService.isExistUser("gaoyk")){
            System.out.println("用户存在！");
        }else{
            System.out.println("用户不存在！");
        }
    }
}