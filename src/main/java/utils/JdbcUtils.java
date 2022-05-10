package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//            System.out.println(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接池中的连接
     * @return */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭连接
     * @return */
    public static void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

//        //从文件中读取输入流
//        FileInputStream fis = new FileInputStream("F:\\BookWeb\\src\\main\\resources\\jdbc.properties");
//        //创建Properties对象
//        Properties pro = new Properties();
//        //从流中加载数据
//        pro.load(fis);
//        //关闭流
//        fis.close();
//        //从Properties对象中根据键读取值
//        String url = pro.getProperty("url");
//        String username = pro.getProperty("username");
//        String password = pro.getProperty("password");
//        //打印值
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(password);

    }
}
