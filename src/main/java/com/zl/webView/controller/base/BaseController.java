package com.zl.webView.controller.base;

import com.zl.webView.entity.NavigationTree;
import com.zl.webView.service.CommonService;
import com.zl.webView.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-1
 *          Time: 下午7:11
 */
public class BaseController {
    @Resource
    private UserManager userManager;

    @Resource
    private CommonService commonService;

    @Resource
    private ObjectMapper mapper;//负责json转换

    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        sdf1.setLenient(false);
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(sdf1, true));//true:允许输入空值，false:不能为空值
    }
}
