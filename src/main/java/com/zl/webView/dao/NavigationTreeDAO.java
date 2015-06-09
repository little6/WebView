package com.zl.webView.dao;

import com.zl.webView.entity.NavigationTree;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:40
 */
public interface NavigationTreeDAO {

    /**
     * 通过父节点ID获取子节点
     * @param navigationTree
     * @return
     */
    List<NavigationTree> getChildrenByParId(NavigationTree navigationTree);
}
