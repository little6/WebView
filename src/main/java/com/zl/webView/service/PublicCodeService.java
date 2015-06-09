package com.zl.webView.service;

import com.zl.webView.entity.PublicCode;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-6-28
 *          Time: 0:10:08
 */
public interface PublicCodeService {

    /**
     * 通过类型与编码获取通用编码详情
     *
     * @param publicCode
     * @return
     */
    PublicCode getOne(PublicCode publicCode);

    /**
     * 获取某个类型的编码字典
     *
     * @param type
     * @return
     */
    LinkedHashMap<String, String> getOneMap(String type);

    /**
     * 获取所有类型的编码字典
     *
     * @return
     */
    LinkedHashMap<String, LinkedHashMap<String, String>> getAllMap();

}
