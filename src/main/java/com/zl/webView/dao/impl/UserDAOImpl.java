package com.zl.webView.dao.impl;

import com.zl.webView.dao.UserDAO;
import com.zl.webView.entity.User;
import com.zl.webView.dao.impl.BaseDAO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:37:49
 *    EhCache使用说明
 *    1.默认不填写key,需要实现参数的hashCode方法
 *    2.key支持条件，key="#user.userId"，包括属性condition，可以 id < 10 等等类似操作
 *    3.allEntries表示调用之后，清空缓存，默认false,
 *    4beforeInvocation属性，表示先清空缓存，再进行查询
 *
 *
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @CacheEvict(value = "userCache",allEntries=true)
    public User create(User user) {
        getSqlMapClientTemplate().insert(
                "user.insert", user);
        return user;
    }

    @CacheEvict(value = "userCache",allEntries=true)
    public void deleteUser(User user) {
        getSqlMapClientTemplate().delete(
                "user.delete", user);
    }

    @Cacheable(value="userCache")
    public List<User> searchUser(User user) {
        return getSqlMapClientTemplate().queryForList(
                "user.searchUser", user);
    }

    @Cacheable(value = "userCache")
    public List<User> searchPageUser(User user, int pageNumber, int pageSize) {
        return getSqlMapClientTemplate().queryForList(
                "user.searchUser", user, pageNumber, pageSize);
    }

    @Cacheable(value = "userCache")
    public long searchUserCount(User user){
        return (Long)getSqlMapClientTemplate().queryForObject(
                "user.searchUserCount", user);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void modifyUser(User user) {
        getSqlMapClientTemplate().update(
                "user.modifyUser", user);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void modifyPassword(User user) {
        getSqlMapClientTemplate().update(
                "user.modifyPassword", user);
    }
}
