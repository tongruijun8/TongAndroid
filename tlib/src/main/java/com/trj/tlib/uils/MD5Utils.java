package com.trj.tlib.uils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author tong
 * @date 2018/4/23 9:39
 */
public class MD5Utils {

    private static final String HEX_DIGITS[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @param islong  If true returns a 16-bit string,
     *                if false returns a 8-bit string
     * @return MD5加密后的字符串
     */
    public static String getMD5(String str, boolean islong) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            if (islong) {
                return new BigInteger(1, md.digest()).toString(16);
            } else {
                return md.digest().toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5编码:把密文转换成十六进制的字符串形式
     *
     * @param codingContent 要编码的内容
     * @param coding        编码
     * @return MD5编码之后的内容
     */
    public static String md5Encode(String codingContent, String coding) {
        String resultString = null;
        try {
            resultString = new String(codingContent);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (coding == null || "".equals(coding))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(coding)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }



}
