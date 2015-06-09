package com.zl.webView.filter;

import com.zl.webView.entity.User;
import com.zl.webView.controller.base.UserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-1
 *          Time: 下午8:32
 */
public class SessionFilter implements HandlerInterceptor {
    private static Log LOG = LogFactory.getLog(SessionFilter.class);
    private String mappingURL;//url匹配表达式

    @Resource
    private UserManager userManager;

    public String getMappingURL() {
        return mappingURL;
    }

    public void setMappingURL(String mappingURL) {
        this.mappingURL = mappingURL;
    }

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("==============执行顺序: 1、preHandle================");
        }
        String url = request.getRequestURL().toString();
        if (mappingURL != null && !url.endsWith(mappingURL)) {
            User user = userManager.getUser(request.getSession());
            if(user == null  || "".equals(user)){
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }

    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("==============执行顺序: 2、postHandle================");
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("==============执行顺序: 3、afterCompletion================");
        }
    }
}
