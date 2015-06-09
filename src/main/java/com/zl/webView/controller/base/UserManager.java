package com.zl.webView.controller.base;

import com.zl.webView.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-1
 *          Time: 下午7:45
 */
public class UserManager {
    public static String USER_KEY = "com.zl.user";

    /**
     * 将用户信息保存至session
     * @param user
     * @param session
     */
    public void addUser(User user,HttpSession session){
        session.setAttribute(USER_KEY,user);
    }

    /**
     * 将用户信息从session中移除
     * @param session
     */
    public void remUser(HttpSession session){
        session.removeAttribute(USER_KEY);
    }

    public User getUser(HttpSession session){
        return (User)session.getAttribute(USER_KEY);
    }
}
