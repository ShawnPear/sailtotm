package com.utils;

import java.nio.charset.Charset;

public class StringEncodingUtil {
    public static String convertToUTF8(String str) {
        if (str == null) return null;
        String utf8String = null;

        // 尝试使用系统默认字符集进行解码
        try {
            byte[] bytes = str.getBytes();
            utf8String = new String(bytes, Charset.defaultCharset());
        } catch (Exception e) {
            // 解码失败，尝试其他常见字符集进行解码
            String[] charsets = {"UTF-8", "ISO-8859-1", "GBK", "Big5"};
            for (String charset : charsets) {
                try {
                    byte[] bytes = str.getBytes(charset);
                    utf8String = new String(bytes, Charset.forName(charset));
                    break;
                } catch (Exception ex) {
                    // 解码失败，继续尝试下一个字符集
                }
            }
        }

        // 使用 UTF-8 进行编码
        if (utf8String != null) {
            byte[] utf8Bytes = utf8String.getBytes(Charset.forName("UTF-8"));
            utf8String = new String(utf8Bytes, Charset.forName("UTF-8"));
        }

        return utf8String;
    }
}