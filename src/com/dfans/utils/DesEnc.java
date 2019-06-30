package com.dfans.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesEnc {
    public static byte[] desEncrypt(byte[] source, byte rawKeyData[])
            throws GeneralSecurityException {
        // 处理密钥
        SecretKeySpec key = new SecretKeySpec(rawKeyData, "DES");
        // 加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);
    }
    
    public static byte[] desDecrypt(byte[] data, byte rawKeyData[])
            throws GeneralSecurityException {
        // 处理密钥
        SecretKeySpec key = new SecretKeySpec(rawKeyData, "DES");
        // 解密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
    
    
    public static void main(String[] args) throws Exception {
        // DES算法要求密鈅64位(8字节)
        byte rawKeyData[] = "hellodes".getBytes("UTF-8");
        // 读取文件内容(为了简单忽略错误处理）
        //File file = new File("src.txt");
        byte[] source = "test001".getBytes();
        //FileInputStream is = new FileInputStream(file);
        //is.read(source, 0, (int) file.length());
        //is.close();
        // 加密
        byte[] enc = desEncrypt(source, rawKeyData);
        System.out.println("desEncrypt:" + source.length + "->" + enc.length);
        // 输出到文件
        System.out.println(new String(enc,"GBK"));
//        FileOutputStream os = new FileOutputStream(new File("D:/enc.txt"));
//        os.write(enc, 0, enc.length);
//        os.close();
    }
}