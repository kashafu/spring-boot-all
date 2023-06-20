package com.marcapo.exercise.springbootstartup;

import org.apache.tomcat.util.security.MD5Encoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {


    static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashedPassword = no.toString(16);

            // Pad with zeros if the hashed password length is less than 32 characters
            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception accordingly
            e.printStackTrace();
            return null;
        }
    }

}
