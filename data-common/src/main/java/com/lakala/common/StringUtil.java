package com.lakala.common;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * <p>字符串处理的支持类</p>
 * 
 * @author Liuxiaohui 2013-10-8 上午9:47:03
 */
public class StringUtil {
	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
	public static final Charset GBK = Charset.forName("GBK");


	/**
	 * 
	 * <p>将多个对象信息拼接成一个字符串.</p>
	 * 
	 * @author Liuxiaohui 2013-10-8 上午9:49:23
	 */
	public static String concatString(final Object... objects) {
		final StringBuilder buf = new StringBuilder(64);
		for (final Object obj : objects) {
			buf.append(obj);
		}
		return buf.toString();
	}

	/*
	 * 将字符串转成16进制数字解码成字符串
	 */
	public static String encode(final String str) {
		final String hexString = "0123456789ABCDEF";
		// 根据默认编码获取字节数组
		final byte[] bytes = str.getBytes();
		final StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (final byte b : bytes) {
			sb.append(hexString.charAt((b & 0xf0) >> 4));
			sb.append(hexString.charAt((b & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/**
	 * 
	 * <p>去除空格换行等等</p>
	 * 
	 * @param str
	 * @return
	 * @author 刘晓辉 Jacky.Liu 2013-11-13 下午1:59:44
	 */
	public static String getStringNoBlank(String str) {
		if (str != null && !"".equals(str)) {
			Pattern p = Pattern.compile("\\t|\r|\n");
			Matcher m = p.matcher(str);
			return m.replaceAll("");
		} else {
			return str;
		}
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes) {
		final String hexString = "0123456789ABCDEF";
		bytes = bytes.toUpperCase();
		final ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2) {
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		}
		return new String(baos.toByteArray());
	}

	public static void main(final String[] args) {
		final String str = "7777772e73696e612e636f6d";
		System.out.println(decode(str));
		System.out.println(StringUtil.cutString(null, 2));
	}

	private StringUtil() {
	};

	/**
	 * 构造指定数量的空格字符串并返回。
	 * 
	 * @param amount int 空格符的数量
	 * @return String
	 */
	public static String getSpacebar(final int amount) {
		final StringBuffer sb = new StringBuffer();
		sb.append("");
		if (amount > 0) {
			for (int i = 0; i < amount; i++) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * 向某个字符串右边补字符c，使该串达到length长度并返回。
	 * 
	 * @param s String 字符串 length int 定长 c char 补的字符
	 * @return String
	 */
	public static String rpadString(final String s, final int length, final char c) {
		if (s.length() >= length || length <= 0) {
			return s;
		} else {
			final StringBuffer sb = new StringBuffer();
			sb.append(s);
			final int addSize = length - s.getBytes().length;
			for (int i = 0; i < addSize; i++) {
				sb.append(c);
			}
			return sb.toString();
		}

	}

	/**
	 * <p>用指定字符填充原字符串到指定长度(一个中文汉字算一个长度)</p> <p>如果长度大于指定长度,则根据传入的isLeft 截掉相应的字符. 如 : src:"000001"
	 * length:4 c:'0' isLeft:true 则返回的字符串为:0001</p><p>如果传入的src为 null 则返回 null</p>
	 * 
	 * @param src 原字符串
	 * @param length 长度
	 * @param c 填充字符
	 * @param isLeft 是否填充到左边 true:左边；false:右边
	 * @return
	 * @author Liuxiaohui 2013-10-8 上午9:50:07
	 */
	public static String lrpadString(final String src, final int length, final char c, boolean isLeft) {
		if (src == null)
			return null;

		StringBuilder sb = new StringBuilder(src);

		while (Math.abs(sb.length() - length) != 0) {
			if (sb.length() > length && isLeft) {
				sb.deleteCharAt(0);
			} else if (sb.length() > length && !isLeft) {
				sb.deleteCharAt(sb.length() - 1);
			} else if (sb.length() < length && isLeft) {
				sb.insert(0, c);
			} else if (sb.length() < length && !isLeft) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 构造指定数量的零字符组成字符串并返回。
	 * 
	 * @param amount int 零字符的数量
	 * @return String
	 */
	public static String getZerobar(final int amount) {
		final StringBuffer sb = new StringBuffer();
		sb.append("");
		if (amount > 0) {
			for (int i = 0; i < amount; i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/**
	 * 按照位数将源字符串切割成字符串数组
	 * 
	 * @param src 源字符串
	 * @param length 指定的位数
	 * @return
	 */
	public static String[] cutByLength(final String src, final int length) {
		if (null == src || src.length() == 0 || length <= 0) {
			return null;
		}
		int count = src.length() / length;
		if (src.length() % length != 0) { // 如果源字符串位数不能整除
			count++;
		}
		final String[] result = new String[count];
		for (int i = 0; i < count - 1; i++) { // 注意此循环不切割最后一项
			result[i] = src.substring(i * length, (i + 1) * length);
		}
		result[count - 1] = src.substring((count - 1) * length); // 在此切割最后一项
		return result;
	}

	/*
	 * 金额小写转换成大写
	 */
	public static String convertDaxie(double money) {
		final String hanzi[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		final String wei[] = { "分", "角", "元", "拾", "佰", "仟", "萬", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "萬", "拾", "佰", "仟" };
		String retvalue = new String();
		int i, j = 0;
		boolean is_have = false, is_negative = false;

		if (money < 0) // 负数
		{
			money = Math.abs(money);
			is_negative = true;
		}

		money = Double.valueOf((new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()).doubleValue(); // 四舍五入
		if (money == 0.00) {
			return "零元";
		}

		for (i = 17; i >= 0; i--) // i 为位数
		{
			if (money == 0) {
				break;
			}

			final double d1 = money / Math.pow(10.0, i - 2);
			String str = new String();

			if (i == 0) {
				str = (new BigDecimal(d1)).setScale(0, BigDecimal.ROUND_HALF_DOWN).toString();
			} else {
				str = (new BigDecimal(d1)).setScale(0, BigDecimal.ROUND_DOWN).toString();
			}

			// System.out.println("str = " + str + " d1=" + d1);
			j = Integer.valueOf(str).intValue(); // j 为 i 位上的数字
			// System.out.println("i = " + i + " j=" + j + " retvalue=" +
			// money);
			if (j == 0 && (i - 2) % 4 == 0 && is_have) {
				if (retvalue.substring(retvalue.length() - 1).equals(hanzi[0])) {
					if (!(retvalue.substring(retvalue.length() - 2).equals("亿零"))) {
						retvalue = retvalue.substring(0, retvalue.length() - 1) + wei[i];
					}
				} else {
					retvalue = retvalue + wei[i];
				}

			} else if (j == 0 && is_have) {
				if (!(retvalue.substring(retvalue.length() - 1).equals(hanzi[0]))) {
					retvalue = retvalue + hanzi[0];
				}
			} else if (j > 0) {
				retvalue = retvalue + hanzi[j] + wei[i];
				final double d2 = Math.pow(10.0, i - 2);
				money = money - j * d2;
				money = Double.valueOf((new BigDecimal(money)).setScale(2, BigDecimal.ROUND_HALF_UP).toString())
						.doubleValue();
				is_have = true;
			}
			// System.out.println("retvalue=" + retvalue);
		}

		if (i >= 14) {
			retvalue = retvalue + "萬亿元整";
		} else if (i >= 10) {
			retvalue = retvalue + "亿元整";
		} else if (i == 9) {
			retvalue = retvalue + "元整";
		} else if (i >= 6) {
			retvalue = retvalue + "萬元整";
		} else if (i >= 2) {
			retvalue = retvalue + "元整";
		} else if (i >= 0) {
			retvalue = retvalue + "整";
		}

		if (is_negative) {
			retvalue = "负" + retvalue; // 添前缀
		}

		return retvalue;
	}

	/*
	 * 十六进制转换成二进制
	 */
	public static int Hex2Bin(final char[] hexStr, final long hexLen, final byte[] binStr) {
		if (hexStr.length == 0 || hexLen <= 0 || (hexLen % 2) != 0) {
			return -1;
		}

		boolean errflag = false;
		final int date[] = new int[2];

		int num = 0, pos = 0;

		while (num < hexLen && !errflag) {
			for (int i = 0; i < 2; i++) {
				if (hexStr[num + i] >= '0' && hexStr[num + i] <= '9') {
					date[i] = hexStr[num + i] - '0';
				} else if (hexStr[num + i] >= 'A' && hexStr[num + i] <= 'F') {
					date[i] = 10 + hexStr[num + i] - 'A';
				} else {
					errflag = true;
					break;
				}
			}

			binStr[pos] = (byte) (date[0] * 16 + date[1]);
			num += 2;
			pos++;
		}

		if (!errflag) {
			return 0;
		} else {
			return 1;
		}

	}

	/*
	 * 二进制转十六进制
	 */
	public static int Bin2Hex(final byte[] binStr, final int binLen, final char[] hexStr) {
		// 不对binStr是否为NULL进行判断，因无法确定NULL对于二进制字符串而言是不是有效值
		if (binLen == 0) {
			return -1;
		}

		// define character for output
		final char digitList[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char inData;
		final boolean errflag = false;

		int num = 0, pos = 0;

		while (num < binLen && !errflag) {
			inData = (char) binStr[num];

			final int charInd1 = (inData & 0xFF); // 禁止符号扩展

			hexStr[pos + 1] = digitList[charInd1];
			inData >>= 4;
			final int charInd2 = (inData & 0xFF);
			hexStr[pos] = digitList[charInd2];
			num++;
			pos += 2;
		}

		if (!errflag) {
			return 0;
		} else {
			return 1;
		}
	}

	/*
	 * 将数字转换成百分数 scale：百分数的小数点后面的位数
	 */
	public static String getPercent(final double d, final int scale) {
		String str = String.valueOf(d);
		final BigDecimal bd = new BigDecimal(str);
		str = bd.multiply(new BigDecimal(100)).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		final int iPos = str.indexOf(".");
		if (iPos > -1) {
			for (int i = scale; i >= 1; i--) {
				if (str.charAt(iPos + i) == '0') {
					str = str.substring(0, iPos + i);
				} else {
					break;
				}
			}
			if (str.length() == iPos + 1) {
				str = str.substring(0, iPos);
			}
		}

		return str + "%";
	}

	/**
	 * 获取前面不带0755区号、0、-的电话号码
	 * 
	 * @param tel 电话号码
	 * @return
	 */
	public static String getPureTel(String tel) {
		if (tel != null && tel.length() > 0) {
			if (tel.startsWith("0755")) {
				tel = tel.substring(4);
				tel = tel.trim();
			}
			if (tel.startsWith("-")) {
				tel = tel.substring(1);
			}
			if (tel.startsWith("0")) {
				tel = tel.substring(1);
				tel = tel.trim();
			}
		}
		return tel;
	}

	/**
	 * 获取前面不带0的手机号码
	 * 
	 * @param telNo 手机号码
	 * @return
	 */
	public static String getPureMobileTel(String telNo) {
		if (telNo != null && telNo.length() > 0 && telNo.startsWith("01")) {
			telNo = telNo.substring(1);
		}
		return telNo;
	}

	/**
	 * 根据15位身份证号码获取18位升位后号码
	 * 
	 * @param perIDSrc 15位身份证号码
	 * @return
	 */
	public static String per15To18(final String perIDSrc) {

		if (perIDSrc.length() != 15) {
			return null;
		}

		long per15 = 0;

		// １５位身份证号不是整数
		try {
			per15 = Long.parseLong(perIDSrc);
		} catch (final NumberFormatException e) {
			return null;
		}

		// １５位身份证号不是正整数
		if (per15 < 0) {
			return null;
		}

		int iS = 0;

		// 加权因子常数
		final int[] iW = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

		// 校验码常数
		final String LastCode = "10X98765432";

		// 新身份证号
		String perIDNew;

		perIDNew = perIDSrc.substring(0, 0 + 6);

		// 填在第6位及第7位上填上‘1’，‘9’两个数字
		perIDNew += "19";

		perIDNew += perIDSrc.substring(6, 6 + 9);

		// 进行加权求和
		for (int i = 0; i < 17; i++) {
			iS += Integer.parseInt(perIDNew.substring(i, i + 1)) * iW[i];
		}

		// 取模运算，得到模值
		final int iY = iS % 11;

		// 从LastCode中取得以模为索引号的值，加到身份证的最后一位，即为新身份证号。
		perIDNew += LastCode.substring(iY, iY + 1);

		return perIDNew;
	}

	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String qjStr2BjStr2(final String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (final java.io.UnsupportedEncodingException e) {
				log.error(e.getMessage());
			}

			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;

				try {
					outStr = outStr + new String(b, "unicode");
				} catch (final java.io.UnsupportedEncodingException e) {
					log.error(e.getMessage());
				}
			} else {
				outStr = outStr + Tstr;
			}
		}

		return outStr;
	}

	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String qjStr2BjStr(final String QJstr) {
		if (null == QJstr || QJstr.length() == 0) {
			return QJstr;
		}
		String outStr = "";

		outStr = QJstr.replaceAll("１", "1").replaceAll("２", "2").replaceAll("３", "3").replaceAll("４", "4")
				.replaceAll("５", "5").replaceAll("６", "6").replaceAll("７", "7").replaceAll("８", "8")
				.replaceAll("９", "9").replaceAll("０", "0").replaceAll("。", ".").replaceAll("Ｘ", "X");
		return outStr;
	}

	/**
	 * 半角转全角
	 * 
	 * @param QJstr
	 * @return
	 */
	// public static final String bjStr2QjStr(String QJstr) {
	// String outStr = "";
	// String Tstr = "";
	// byte[] b = null;
	//
	// for (int i = 0; i < QJstr.length(); i++) {
	// try {
	// Tstr = QJstr.substring(i, i + 1);
	// b = Tstr.getBytes("unicode");
	// } catch (java.io.UnsupportedEncodingException e) {
	// Log4jUtil.error(e);
	// }
	//
	// if (b[3] != -1) {
	// b[2] = (byte) (b[2] - 32);
	// b[3] = -1;
	// try {
	// outStr = outStr + new String(b, "unicode");
	// } catch (java.io.UnsupportedEncodingException e) {
	// Log4jUtil.error(e);
	// }
	// } else
	// outStr = outStr + Tstr;
	// }
	//
	// return outStr;
	// }
	// public static void main(String[] args) {
	// /*
	// * String src = "1234567890"; String[] result =
	// * StringUtil.cutByLength(src, 3); for (int i = 0; i < result.length;
	// * i++) { System.out.println(i + " : " + result[i]); }
	// */
	// System.out.println(new String(Base64.encode("ylink123456".getBytes())));
	// System.out.println(new String(Base64.decode("MTIzNDU2cQ==")));
	// // double d = 2558637.34 ;
	//
	// // String QJstr="hello!！ 全角转换，ＤＡＯ１２３４1234５６78";
	// //
	// // String result=qjStr2BjStr(QJstr);
	// //
	// // System.out.println(QJstr+"\n"+result);
	//
	// }
	// ///////////////////////add by 071291 on
	// 20071105//////////////////////////////////////
	/**
	 * 取得随机数字符串（在整数范围内）
	 * 
	 * @param amount
	 * @return
	 */
	private static java.util.Random r = new java.util.Random();

	public static String getRandomNumber() {
		return r.nextInt() + "";
	}

	// ///////////////////////end////////////////////////////////////////

	public static boolean isNumeric(final String str) {
		final Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static boolean isBlank(final String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static String byteToString(final byte[] b) {
		return new String(b, StringUtil.GBK);
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < b.length; i++) {
		// sb.append(b[i]);
		// }
		// return sb.toString();
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2HexStr(final byte[] b) {
		String hs = "";
		String stmp = "";
		for (final byte element : b) {
			stmp = (Integer.toHexString(element & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	private static byte uniteBytes(final String src0, final String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		final byte b1 = Byte.decode("0x" + src1).byteValue();
		final byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * 十六进制字符串转换成bytes
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] hexStr2Bytes(final String src) {
		int m = 0, n = 0;
		final int l = src.length() / 2;
		final byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}

	/**
	 * 截取字符串
	 * 
	 * @param value
	 * @param charNumber
	 * @return
	 */
	public static String cutString(final String value, final int charNumber) {
		if (value == null) {
			return "";
		}
		if (charNumber < value.getBytes().length) {
			return new String(value.getBytes(), 0, charNumber);
		} else {
			return value;
		}
	}

	public static String fillStringWithDefaultChar(String value, int length, char defaultChar, boolean isFilledOnLeft,
			String encodeType) {
		int fillLength;
		try {
			fillLength = length - value.getBytes(encodeType).length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported encode type:" + encodeType);
		}
		if (fillLength < 0) {
			throw new IllegalArgumentException("String " + value + " is too long, length=" + length);
		}
		char[] fillString = new char[fillLength];
		while (fillLength > 0) {
			fillString[--fillLength] = defaultChar;
		}
		return isFilledOnLeft ? new String(fillString) + value.toString() : value.toString() + new String(fillString);
	}

	public static String fillStringWithSpaceOnRight(String value, int length) {
		return fillStringWithDefaultChar(value, length, ' ', false, "GBK");
	}

	public static String fillNumberWithZeroOnLeft(String value, int length) {
		return fillStringWithDefaultChar(value, length, '0', true, "GBK");
	}

	public static String trimNumericString(String numericStr) {
		int i = 0;
		for (char c : numericStr.toCharArray()) {
			if (c != '0') {
				break;
			}
			i++;
		}
		return i == numericStr.length() ? "0" : numericStr.substring(i);
	}

	public static String subGBKString(String gbkString, int start, int length) {
		String result = null;
		try {
			result = new String(gbkString.getBytes("GBK"), start, length, "GBK");
		} catch (Exception e) {
			throw new RuntimeException("subGBKString error!,gbkString=" + gbkString, e);
		}
		return result;
	}

	/**
	 * 
	 * <p>将数字型字符串转成Int值，不符合要求则返回默认值</p>
	 * 
	 * @param sourceMathStr
	 * @param defaultVal
	 * @return
	 * @author 刘晓辉 Jacky.Liu 2013-11-29 上午10:02:57
	 */
	public static Integer getIntValue(String sourceMathStr, Integer defaultVal) {
		Integer targetVal = 0;
		try {
			targetVal = Integer.parseInt(sourceMathStr);
		} catch (NumberFormatException e) {
			targetVal = defaultVal;
		}
		return targetVal;
	}

	/**
	 * 
	 * <p>将数字型字符串转成Long值，不符合要求则返回默认值</p>
	 * 
	 * @param sourceMathStr
	 * @param defaultVal
	 * @return
	 * @author 刘晓辉 Jacky.Liu 2014-6-15 上午10:07:26
	 */
	public static Long getLongValue(String sourceMathStr, Long defaultVal) {
		Long targetVal = 0l;
		try {
			targetVal = Long.parseLong(sourceMathStr);
		} catch (NumberFormatException e) {
			targetVal = defaultVal;
		}
		return targetVal;
	}
	/**
	 * 判断字符串是null或""
	 * @param str
	 * @return
	 */
	public static Boolean IsNullOrEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
	}

}
