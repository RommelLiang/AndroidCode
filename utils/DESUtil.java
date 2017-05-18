package com.product.jiamiao.healthbooks.utils;



import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Reinhard Tristan Eugen Heydrich on 2016/12/9 17:07
 */
public class DESUtil {
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	public static void main(String[] args) {
		String plaintext = "{\"key\":\"201512\",\"value\":759.0,\"difference\":759.0,\"percent\":null,\"list\":null}";
		String ciphertext = encryptDES(plaintext, Configs.tokenKey);
		System.out.println("明文：" + plaintext);
//	System.out.println("密钥：" + "20120401");
		System.out.println("密文：" + ciphertext);
		//System.out.println("解密后：" + decryptDES(ciphertext, Configs.tokenKey));
	}

	/**
	 * 加密
	 */
	public static String encryptDES(String encryptString, String encryptKey) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
			return String.valueOf(Base64.encode(encryptedData));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 解密
	 */
	public static String decryptDES(String decryptString) {
		try {
			byte[] byteMi = Base64.decode(decryptString);
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(Configs.tokenKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(byteMi);

			return new String(decryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
}