package com.zl.webView.service;

import com.zl.webView.entity.PageList;
import com.zl.webView.entity.User;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-1-9
 *          Time: 21:38:12
 */
public interface UserService {
    /**
     * 创建用户信息
     *
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * 删除用户信息
     *
     * @param user
     */
    boolean deleteUser(User user);

    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    List searchUser(User user);

    /**
     * 查询用户信息（分页）
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageList searchPageUser(User user, int pageNumber, int pageSize);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    boolean modifyUser(User user);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    boolean modifyPassword(User user);

}
