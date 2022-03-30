package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public static void copyParamToBean(HttpServletRequest request,Object bean){
        User user = new User();
        try {
            //把请求对象的参数一次性注入到user对象中
            BeanUtils.populate(bean,request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
