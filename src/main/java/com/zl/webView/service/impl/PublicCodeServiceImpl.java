package com.zl.webView.service.impl;


import com.zl.webView.entity.PublicCode;
import com.zl.webView.service.PublicCodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-6-28
 *          Time: 0:00:48
 */
public class PublicCodeServiceImpl implements PublicCodeService {
    public static final Log LOG = LogFactory.getLog(PublicCodeServiceImpl.class);
    private LinkedHashMap<String, LinkedHashMap<String, String>> publicCodeMap;

    public LinkedHashMap<String, LinkedHashMap<String, String>> getPublicCodeMap() {
        return publicCodeMap;
    }

    public void setPublicCodeMap(LinkedHashMap<String, LinkedHashMap<String, String>> publicCodeMap) {
        this.publicCodeMap = publicCodeMap;
    }

    public PublicCode getOne(PublicCode publicCode) {
        publicCode.setName(
                getOneMap(publicCode.getType()).get(publicCode.getCode()));
        return publicCode;
    }

    public LinkedHashMap<String, String> getOneMap(String type) {
        return publicCodeMap.get(type);
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getAllMap() {
        return publicCodeMap;
    }

}
