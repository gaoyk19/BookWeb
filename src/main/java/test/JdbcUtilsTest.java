package test;

import org.junit.jupiter.api.Test;
import utils.JdbcUtils;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){

            System.out.println(JdbcUtils.getConnection());


    }
}
