package com.lakala.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtil {

	public HashUtil() {
	}

	public static String ByteArrayToHexString(byte d[]) {
		if (d == null)
			return "";
		if (d.length == 0)
			return "";
		int len = d.length * 2;
		byte strData[] = new byte[len];
		for (int i = 0; i < strData.length; i++)
			strData[i] = 48;

		byte data[] = new byte[d.length + 1];
		data[0] = 0;
		System.arraycopy(d, 0, data, 1, d.length);
		BigInteger bi = new BigInteger(data);
		byte src[] = bi.toString(16).getBytes();
		int offset = strData.length - src.length;
		len = src.length;
		System.arraycopy(src, 0, strData, offset, len);
		return new String(strData);
	}

	public static byte[] hashData(String algorithm, byte b[]) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(b);
		byte digest[] = md.digest();
		return digest;
	}

	public static String md5(byte b[]) {

		try {
			byte a[];
			a = hashData("MD5", b);
			return ByteArrayToHexString(a);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String md5(String plainTxt) {

		try {
			byte a[];
			a = hashData("MD5", plainTxt.getBytes());
			return ByteArrayToHexString(a);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String SHA1(String plainTxt) {

		try {
			byte a[];
			a = hashData("SHA1", plainTxt.getBytes());
			return ByteArrayToHexString(a);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String rawTxt = "123456";
		// System.out.println(ByteArrayToHexString(HashUtil.hashData("SHA", rawTxt.getBytes())));

		System.out.println(HashUtil.SHA1(rawTxt));
	}

}
