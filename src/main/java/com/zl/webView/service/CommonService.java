package com.zl.webView.service;

import com.zl.webView.dao.NavigationTreeDAO;
import com.zl.webView.entity.NavigationTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:40
 */
public interface  CommonService {

    /**
     * 获取导航某个父节点下的子节点
     * @param navigationTree
     * @return
     */
    List<NavigationTree> getChildrenTreeNode(NavigationTree navigationTree);
}
