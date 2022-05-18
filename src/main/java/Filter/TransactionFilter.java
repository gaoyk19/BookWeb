package Filter;


import utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

//注意：在设计Filter过滤器用于对servlet程序中可能出现的异常进行捕获处理时，servlet程序需要把异常进行抛出（而不能只是简单的进行捕获，否则Filter可能不一定起到理想的效果）
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
