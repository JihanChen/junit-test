package com.test.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Md5Util {
        public static final String ENCODING = "UTF-8";
        private static final int HEX_VALUE_COUNT = 16;
        public Md5Util() {
        }

        public static String getMD5(byte[] bytes) {
            char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            char[] str = new char[32];

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(bytes);
                byte[] tmp = md.digest();
                int k = 0;

                for(int i = 0; i < 16; ++i) {
                    byte byte0 = tmp[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 15];
                    str[k++] = hexDigits[byte0 & 15];
                }
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            return new String(str);
        }

        public static String getMD5(String value, String encode) {
            String result = "";

            try {
                result = getMD5(value.getBytes(encode));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return result;
        }
}
