package com.jizhi.phonemall.util;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类MD5
 */
public class MD5Util {
    /**
     * 使用md5进行加密
     * @param pass
     * @return
     */
    public final static String md5(String pass) {
        //为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
        MessageDigest messageDigest = null;
        try {
            //选择使用md5进行加密， 返回实现指定摘要算法的 MessageDigest 对象。
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = pass.getBytes();
        //对bytes内容处理
        messageDigest.update(bytes);
        //再次进行处理
        messageDigest.update(pass.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());
    }

    /**
     * 使用sha1加密
     * @param pass
     * @return
     */
    public final static String sha1(String pass){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = pass.getBytes();
        messageDigest.update(bytes);

        messageDigest.update(pass.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());
    }

    /**
     * md5与sha1相互调用，进行多级加密
     * @param pass
     * @return
     */
    public final static String encode(String pass){
        return md5(sha1(md5(pass)));
    }

}
