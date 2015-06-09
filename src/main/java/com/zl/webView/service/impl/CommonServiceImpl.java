package com.zl.webView.service.impl;

import com.zl.webView.dao.NavigationTreeDAO;
import com.zl.webView.entity.NavigationTree;
import com.zl.webView.service.CommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:42
 */
public class CommonServiceImpl implements CommonService{

    private static Log LOG = LogFactory.getLog(CommonServiceImpl.class);

    private NavigationTreeDAO navigationTreeDAO;

    public NavigationTreeDAO getNavigationTreeDAO() {
        return navigationTreeDAO;
    }

    public void setNavigationTreeDAO(NavigationTreeDAO navigationTreeDAO) {
        this.navigationTreeDAO = navigationTreeDAO;
    }

    public List<NavigationTree> getChildrenTreeNode(NavigationTree navigationTree){
        List<NavigationTree> list = null;
        try {
            list = getNavigationTreeDAO().getChildrenByParId(navigationTree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
