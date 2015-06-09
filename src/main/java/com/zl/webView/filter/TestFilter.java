package com.zl.webView.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2015/5/27.
 */
public class TestFilter implements Filter {
    private static Log LOG = LogFactory.getLog(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("==============执行:TestFilter================");
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
