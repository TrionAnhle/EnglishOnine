package com.cnpm.utils;


import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
    private String chu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String chuThuong = "abcdefghijklmnopqrstuvwxyz";
    private String so = "0123456789";
    private char[] chuChar = chu.toCharArray();
    private char[] chuCharThuong = chuThuong.toCharArray();
    private char[] soChar = so.toCharArray();
    int key = 3;

    public static String boKhoangTrang(String nguon) {
        nguon = nguon.trim();
        while (nguon.indexOf("  ") != -1) {
            nguon = nguon.replace("  ", " ");
        }
        return nguon;
    }

    public String maHoa(String chuoi) {
        char[] source = chuoi.toCharArray();
        StringBuilder kq = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            String kitu = String.valueOf(source[i]);
            if (kitu.compareTo("A") >= 0 && kitu.compareTo("Z") <= 0) {
                kitu = String.valueOf(chuChar[(chu.indexOf(kitu) + key) % 26]);
                kq.append(kitu);
            } else if (kitu.compareTo("a") >= 0 && kitu.compareTo("z") <= 0) {
                kitu = String.valueOf(chuCharThuong[(chuThuong.indexOf(kitu) + key) % 26]);
                kq.append(kitu);
            } else if (kitu.compareTo("0") >= 0 && kitu.compareTo("9") <= 0) {
                kitu = String.valueOf(soChar[(so.indexOf(kitu) + key) % 10]);
                kq.append(kitu);
            } else kq.append(kitu);
        }
        return kq.toString();
    }

    public String giaiMa(String chuoi) {
        char[] source = chuoi.toCharArray();
        StringBuilder kq = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            String kitu = String.valueOf(source[i]);
            if (kitu.compareTo("A") >= 0 && kitu.compareTo("Z") <= 0) {
                int vitri = chu.indexOf(kitu) - key;
                vitri = (vitri < 0 ? 26 + vitri : vitri);
                kitu = String.valueOf(chuChar[vitri % 26]);
                kq.append(kitu);
            } else if (kitu.compareTo("a") >= 0 && kitu.compareTo("z") <= 0) {
                int vitri = chuThuong.indexOf(kitu) - key;
                vitri = (vitri < 0 ? 26 + vitri : vitri);
                kitu = String.valueOf(chuCharThuong[vitri % 26]);
                kq.append(kitu);
            } else if (kitu.compareTo("0") >= 0 && kitu.compareTo("9") <= 0) {
                int vitri = so.indexOf(kitu) - key;
                vitri = (vitri < 0 ? 10 + vitri : vitri);
                kitu = String.valueOf(soChar[vitri % 10]);
                kq.append(kitu);
            } else kq.append(kitu);
        }
        return kq.toString();
    }

    public static String chuyenMa(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String code = pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");

        String[] codes = code.split(" ");
        StringBuilder codeNew = new StringBuilder(codes[0]);
        for(int i=1;i<codes.length;i++) codeNew.append("-"+codes[i]);
        return codeNew.toString().toLowerCase();
    }
}
