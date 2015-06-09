package com.zl.webView.util;

import java.security.MessageDigest;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 15-2-7
 *          Time: 上午1:33
 */
//MD5加密
public class MD5Util {
    public final static String MD5(String input) {
        char hexDigits[] = {'0', '1', '2', '3',
                            '4', '5', '6', '7',
                            '8', '9', 'A', 'B',
                            'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = input.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //测试
    public static void main(String[] args) {
        System.out.print(MD5Util.MD5("123456"));
    }
}