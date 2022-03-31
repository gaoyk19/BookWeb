package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import java.util.Map;

public class WebUtils {
    public static void copyParamToBean( Map value,Object bean){
        User user = new User();
        try {
//            Map<String,String[]> paraMap= request.getParameterMap();
//            for(Map.Entry<String,String[]> entry : paraMap.entrySet()){
//                System.out.println(entry.getKey()+" = "+entry.getValue());
//            }
            //把请求对象的参数一次性注入到user对象中
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public static void copyParamToBean(HttpServletRequest request,Object bean){
        User user = new User();
        try {
            Map<String,String[]> paraMap= request.getParameterMap();
            for(Map.Entry<String,String[]> entry : paraMap.entrySet()){
                System.out.println(entry.getKey()+" = "+entry.getValue());
            }
            //把请求对象的参数一次性注入到user对象中
            BeanUtils.populate(bean,request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

}
