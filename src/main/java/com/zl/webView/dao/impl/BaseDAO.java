package com.zl.webView.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.execution.DefaultSqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.zl.webView.dao.base.PageExecutor;
import com.zl.webView.dao.base.ReflectUtil;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-12-19
 *          Time: 14:09:00
 */
public class BaseDAO extends SqlMapClientDaoSupport {
    //SQL执行器
    private DefaultSqlExecutor sqlExecutor;

    public DefaultSqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(DefaultSqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    //该类初始化方法，在Spring配置文件中配置
    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                //sqlExecutor没有公共set方法，则通过反射方式设置
                ReflectUtil.setFieldValue(((ExtendedSqlMapClient) sqlMapClient)
                        .getDelegate(), "sqlExecutor", SqlExecutor.class,
                        sqlExecutor);
            }
        }
    }
}
