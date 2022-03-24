package service;

import pojo.User;

//三个业务：用户注册、用户登陆、检查用户名是否可用
public interface UserService {
    public void registUser(User user);
    public User login(User user);
    public boolean isExistUser(String username);


}
