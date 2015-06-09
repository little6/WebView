package com.zl.webView.entity;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-12-5
 *          Time: 11:50:31
 */
public class PublicCode {
    private long publicCodeId;
    private String type;//编码类型
    private String code;//编码
    private String name;//名称

    public long getPublicCodeId() {
        return publicCodeId;
    }

    public void setPublicCodeId(long publicCodeId) {
        this.publicCodeId = publicCodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PublicCode{" +
                "publicCodeId=" + publicCodeId +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
