package com.zl.webView.dao;

import com.zl.webView.entity.User;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:29:32
 */
public interface UserDAO {
    /**
     * 创建用户信息
     *
     * @param user
     * @return
     */
    User create(User user);

    /**
     * 删除用户信息
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    List<User> searchUser(User user);

    /**
     * 查询用户信息(分页）
     *
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List searchPageUser(User user, int pageNumber, int pageSize);

    /**
     * 查询用户信息条数
     * @param user
     * @return
     */
    long searchUserCount(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    void modifyUser(User user);

    /**
     * 修改用户密码
     * @param user
     */
    void modifyPassword(User user);
}
