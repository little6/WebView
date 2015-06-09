package com.zl.webView.controller;

import com.zl.webView.entity.NavigationTree;
import com.zl.webView.util.MD5Util;
import com.zl.webView.entity.User;
import com.zl.webView.service.UserService;
import com.zl.webView.controller.base.BaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:24
 */
@Controller
public class CommonController extends BaseController {

    private static Log LOG = LogFactory.getLog(CommonController.class);

    @Resource
    private UserService userService;
    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param session
     * @param modelMap
     * @return
     * @throws IOException
     */
    @RequestMapping("/common/login.do")
    public ModelAndView login(String userName, @RequestParam("passWord") String password,
                              HttpSession session, ModelMap modelMap) throws IOException {
        User user = new User();
        user.setName(userName);
        user.setPassword(MD5Util.MD5(password));
        List<User> userList = userService.searchUser(user);
        if (userList == null || userList.size() == 0) {
            modelMap.put("result", "0");
        } else {
            getUserManager().addUser(userList.get(0), session);
            modelMap.put("result", "1");
        }
        return new ModelAndView("/public/ajaxMessage");
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/common/logout.do")
    public ModelAndView logout(HttpSession session) {
        getUserManager().remUser(session);
        return new ModelAndView("/common/login");
    }


    @RequestMapping("/common/getTree.do")
    public ModelAndView getTree(Long id, ModelMap modelMap) throws IOException {
        NavigationTree navigationTree = new NavigationTree();
        if (id == null) {
            //如何参数为空
            id = new Long(0);
        }
        navigationTree.setNavigationTreeId(id);
        List<NavigationTree> nodes =
                getCommonService().getChildrenTreeNode(navigationTree);
        List<Map> resultMaps = new ArrayList();
        for (NavigationTree node : nodes) {
            Map<String, Object> nodeMap = new HashMap();
            nodeMap.put("id", node.getNavigationTreeId());
            nodeMap.put("text", node.getText());
            nodeMap.put("state", node.getState());
            nodeMap.put("iconCls", node.getIconCls());
            nodeMap.put("url", node.getUrl());
            nodeMap.put("nid", node.getParNodeId());
            resultMaps.add(nodeMap);
        }
        String result = getMapper().writeValueAsString(resultMaps);
        modelMap.put("result", result);
        return new ModelAndView("/public/ajaxMessage");
    }
}
