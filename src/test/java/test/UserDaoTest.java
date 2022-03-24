package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import junit.framework.TestCase;
import pojo.User;

import javax.jws.soap.SOAPBinding;

public class UserDaoTest extends TestCase {
    private UserDao userDao=new UserDaoImpl();
    public void testQueryUserByUsername() {
//        UserDao userDao=new UserDaoImpl();
        if(userDao.queryUserByUsername("gaoyk")==null){
            System.out.println("用户名可用！");
        }else{
            System.out.println("用户名已经存在");
        }
    }

    public void testQueryUserByUsernameAndPassword() {
//        UserDao userDao=new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("gaoyk","620")==null){
            System.out.println("用户名或密码错误，登陆失败");
        }else{
            System.out.println("登陆成功！");
        };
    }

    public void testSaveUser() {
//        UserDao userDao=new UserDaoImpl();
        userDao.saveUser(new User(null,"wjg","633","wjg@163.com"));
    }
}