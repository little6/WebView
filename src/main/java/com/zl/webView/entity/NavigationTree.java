package com.zl.webView.entity;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-2
 *          Time: 下午10:38
 */
//导航树
public class NavigationTree {

    private long navigationTreeId;
    private String text;//导航名称
    private String state;//节点状态【open-叶子节点，closed-目录】
    private String iconCls;//导航图片
    private String url;//导航链接
    private long parNodeId;//父节点

    public long getNavigationTreeId() {
        return navigationTreeId;
    }

    public void setNavigationTreeId(long navigationTreeId) {
        this.navigationTreeId = navigationTreeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  long getParNodeId() {
        return parNodeId;
    }

    public void setParNodeId(long parNodeId) {
        this.parNodeId = parNodeId;
    }
}
