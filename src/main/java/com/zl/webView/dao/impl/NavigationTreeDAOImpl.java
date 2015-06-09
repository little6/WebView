package com.zl.webView.dao.impl;

import com.zl.webView.dao.NavigationTreeDAO;
import com.zl.webView.entity.NavigationTree;
import com.zl.webView.dao.impl.BaseDAO;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:41
 */
public class NavigationTreeDAOImpl extends BaseDAO implements NavigationTreeDAO{

    public List<NavigationTree> getChildrenByParId(NavigationTree navigationTree){
        return getSqlMapClientTemplate().queryForList(
                "navigationTree.getChildrenByParId", navigationTree);
    }
}
