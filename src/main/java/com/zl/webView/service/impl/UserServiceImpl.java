package com.zl.webView.service.impl;

import com.zl.webView.entity.PageList;
import com.zl.webView.util.MD5Util;
import com.zl.webView.dao.UserDAO;
import com.zl.webView.entity.User;
import com.zl.webView.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:30:27
 */
public class UserServiceImpl implements UserService {
    public static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
    private UserDAO userDAO;


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(User user) {
        try {
            //先将用户密码加密
            user.setPassword(MD5Util.MD5(user.getPassword()));
            user = getUserDAO().create(user);
        } catch (Exception e) {
            LOG.error("创建用户信息失败：" + user);
            e.printStackTrace();
        }
        return user;
    }

    public boolean deleteUser(User user) {
        boolean flag = false;
        try {
            getUserDAO().deleteUser(user);
            flag = true;
        } catch (Exception e) {
            LOG.error("删除用户信息失败：" + user);
            e.printStackTrace();
        }
        return flag;
    }

    public List<User> searchUser(User user) {
        List userList = null;
        try {
            userList = getUserDAO().searchUser(user);
        } catch (Exception e) {
            LOG.error("查询用户信息失败：" + user);
            e.printStackTrace();
        }
        return userList;
    }

    public PageList searchPageUser(User user, int pageNumber, int pageSize){
        PageList pageList = new PageList();
        try {
            pageList.setObjList(
                    getUserDAO().searchPageUser(user, pageNumber, pageSize));
            pageList.setCount(
                    getUserDAO().searchUserCount(user));
        } catch (Exception e) {
            LOG.error("查询用户信息失败：" + user);
            e.printStackTrace();
        }
        return pageList;
    }
    public boolean modifyUser(User user) {
        boolean flag = false;
        try {
            getUserDAO().modifyUser(user);
            flag = true;
        } catch (Exception e) {
            LOG.error("修改用户信息失败：" + user);
            e.printStackTrace();
        }
        return flag;
    }
    public boolean modifyPassword(User user) {
        boolean flag = false;
        try {
            user.setPassword(MD5Util.MD5(user.getPassword()));
            user.setOldPassword(MD5Util.MD5(user.getOldPassword()));
            //查询系统用户现在情况
            User temp = new User();
            temp.setUserId(user.getUserId());
            temp = getUserDAO().searchUser(temp).get(0);
            if(temp.getPassword().equals(user.getOldPassword())){
                getUserDAO().modifyPassword(user);
                flag = true;
            }
        } catch (Exception e) {
            LOG.error("修改用户信息失败：" + user);
            e.printStackTrace();
        }
        return flag;
    }


}
