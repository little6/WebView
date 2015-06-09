package com.zl.webView.dao;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-12-19
 *          Time: 13:14:28
 */
public interface Dialect {
    /**
     * 获取带页数的Sql语句
     *
     * @param sql    ԭ
     * @param offset
     * @param limit
     * @return
     */
    public String getPageString(String sql, int offset, int limit);
}
