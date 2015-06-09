package com.zl.webView.entity;


import java.util.Date;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-7
 *          Time: 9:42:57
 */
public class User {
    //用户ID
    private long userId;
    //用户名
    private String name;
    //昵称
    private String nickName;
    //密码
    private String password;
    //创建用户ID
    private long createUserId;
    //创建日期
    private Date createDate;
    //修改用户ID
    private long updateUserId;
    //修改日期
    private Date updateDate;
    //旧密码
    private String oldPassword;
    /********查询条件*******/
    //起始日期
    private Date startDate;
    //截止日期
    private Date endDate;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (createUserId != user.createUserId) return false;
        if (updateUserId != user.updateUserId) return false;
        if (userId != user.userId) return false;
        if (createDate != null ? !createDate.equals(user.createDate) : user.createDate != null) return false;
        if (endDate != null ? !endDate.equals(user.endDate) : user.endDate != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (nickName != null ? !nickName.equals(user.nickName) : user.nickName != null) return false;
        if (oldPassword != null ? !oldPassword.equals(user.oldPassword) : user.oldPassword != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (startDate != null ? !startDate.equals(user.startDate) : user.startDate != null) return false;
        if (updateDate != null ? !updateDate.equals(user.updateDate) : user.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) (createUserId ^ (createUserId >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (int) (updateUserId ^ (updateUserId >>> 32));
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (oldPassword != null ? oldPassword.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", updateUserId=" + updateUserId +
                ", updateDate=" + updateDate +
                ", oldPassword='" + oldPassword + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
