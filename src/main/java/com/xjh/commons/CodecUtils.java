package com.xjh.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodecUtils {
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static String sha1Hex(final byte[] data) {
		return encodeHexString(sha1(data));
	}

	public static byte[] sha1(final byte[] data) {
		return getSha1Digest().digest(data);
	}

	public static MessageDigest getSha1Digest() {
		return getDigest("SHA-1");
	}

	public static String md5(byte[] input) {
		byte[] data = getDigest("MD5").digest(input);
		return encodeHexString(data);
	}

	public static MessageDigest getDigest(final String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String encodeHexString(final byte[] data) {
		return new String(encodeHex(data));
	}

	public static byte[] hexStringToByte(String hex) {
		if (hex == null || hex.trim().length() == 0) {
			return new byte[0];
		}
		if (hex.length() % 2 != 0) {
			throw new RuntimeException("invalid hex string : " + hex);
		}
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toUpperCase().toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			int idx1 = "0123456789ABCDEF".indexOf(achar[pos]);
			int idx2 = "0123456789ABCDEF".indexOf(achar[pos + 1]);
			if (idx1 < 0 || idx2 < 0) {
				throw new RuntimeException("invalid hex string : " + hex);
			}
			byte b1 = (byte) idx1;
			byte b2 = (byte) idx2;
			result[i] = (byte) (b1 << 4 | b2);
		}
		return result;
	}

	public static char[] encodeHex(final byte[] data) {
		return encodeHex(data, DIGITS_UPPER);
	}

	private static char[] encodeHex(final byte[] data, final char[] toDigits) {
		final int l = data.length;
		final char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

//	public static void main(String args[]) {
//		for (int i = 0; i < 100; i++) {
//			String str = CoreCommonUtil.randomString(CoreCommonUtil.randomNumber(0, 100));
////			str = "爱都百科LLLL";
//			System.out.println(str);
//			String hex = encodeHexString(str.getBytes());
//			System.out.println("HEX========"+hex);
//			System.out.println("HEX--------"+Hex.encodeHexString(str.getBytes()).toUpperCase());
//			System.out.println(hex.equals(Hex.encodeHexString(str.getBytes()).toUpperCase()));
//			byte[] bs = hexStringToByte(hex);
//			System.out.println(encodeHexString(bs));
//			System.out.println(new String(bs));
//			System.out.println(new String(str.getBytes()));
//
//			// System.out.println(WeixinUtils.decryptOpenId("FmDxx++qq5g+/U7EuL8imQJsKvHMQC8LUW+d5p0d34Y="));
//		}
//	}
}
