package com.zl.webView.entity;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-7
 *          Time: 上午12:33
 */
public class PageList {
    //页码
    private int pageNumber;
    //每页数据条数
    private int pageSize;
    //该页数据
    private List objList;
    //总条数
    private long count;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getObjList() {
        return objList;
    }

    public void setObjList(List objList) {
        this.objList = objList;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
