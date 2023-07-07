package com.springbootbasicsetup.usermanagement.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String encryptPassword(String password) {
        return DigestUtils.md5Hex(password);
    }

//    public static String encryptPassword(String value)
//    {
//    {
//        try
//        {
//            if (value != null)
//            {
//                byte[] bytesOfMessage = value.getBytes(StandardCharsets.UTF_8);
//                MessageDigest md = MessageDigest.getInstance("MD5");
//                byte[] thedigest = md.digest(bytesOfMessage);
//                return MD5Encoder.encode(thedigest);
//            }
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//        }
//        return null;
//    }
}
