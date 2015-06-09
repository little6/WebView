package com.zl.webView.controller;


import com.zl.webView.entity.PageList;
import com.zl.webView.entity.User;
import com.zl.webView.service.UserService;
import com.zl.webView.controller.base.BaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-1
 *          Time: 上午12:35
 */

@Controller
public class UserController extends BaseController {

    private static Log LOG = LogFactory.getLog(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 获取用户信息列表
     *
     * @param modelMap
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/userList.do")
    public ModelAndView userList(ModelMap modelMap,HttpSession session,
                                 User user,int page,int rows) throws IOException {
        PageList pageList =
                userService.searchPageUser(user, page, rows);
        User currentUser = getUserManager().getUser(session);
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total",pageList.getCount());
        resultMap.put("rows",pageList.getObjList());
        getMapper().getSerializationConfig().setDateFormat(sdf2);
        String result = getMapper().writeValueAsString(resultMap);
        modelMap.put("result", result);
        return new ModelAndView("/public/ajaxMessage");
    }

    /**
     * 新增一条用户信息
     *
     * @param modelMap
     * @param user
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/addUser.do")
    public ModelAndView addUser(ModelMap modelMap, User user,
                                HttpSession session) throws IOException {
        user.setCreateUserId(
                getUserManager().getUser(session).getUserId());
        user = userService.createUser(user);
        if (user.getUserId() == 0) {
            modelMap.put("result", "0");
        } else {
            modelMap.put("result", "1");
        }
        return new ModelAndView("/public/ajaxMessage");
    }

    /**
     * 获取一条用户详情
     *
     * @param modelMap
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/getOneUser.do")
    public ModelAndView getOneUser(ModelMap modelMap, User user) throws IOException {
        List<User> userList = userService.searchUser(user);
        if (userList != null && userList.size() == 1) {
            getMapper().getSerializationConfig().setDateFormat(sdf2);
            String result = getMapper().writeValueAsString(userList);
            modelMap.put("result", result);
        } else {
            modelMap.put("result", "0");
        }
        return new ModelAndView("/public/ajaxMessage");
    }

    /**
     * 修改用户信息
     *
     * @param modelMap
     * @param user
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/modifyUser.do")
    public ModelAndView modifyUser(ModelMap modelMap, User user,
                                   HttpSession session) throws IOException {
        user.setUpdateUserId(
                getUserManager().getUser(session).getUserId());
        if (userService.modifyUser(user)) {
            modelMap.put("result", "1");
        } else {
            modelMap.put("result", "0");
        }
        return new ModelAndView("/public/ajaxMessage");
    }

    /**
     * 删除用户信息
     * @param modelMap
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/deleteUser.do")
    public ModelAndView deleteUser(ModelMap modelMap, User user)
            throws IOException {
        if (userService.deleteUser(user)) {
            modelMap.put("result", "1");
        } else {
            modelMap.put("result", "0");
        }
        return new ModelAndView("/public/ajaxMessage");
    }

    @RequestMapping("/user/modifyPassword.do")
    public ModelAndView modifyPassword(ModelMap modelMap, User user,
                                   HttpSession session) throws IOException {
        user.setUserId(
                getUserManager().getUser(session).getUserId());
        if (userService.modifyPassword(user)) {
            modelMap.put("result", "1");
        } else {
            modelMap.put("result", "0");
        }
        return new ModelAndView("/public/ajaxMessage");
    }
}
