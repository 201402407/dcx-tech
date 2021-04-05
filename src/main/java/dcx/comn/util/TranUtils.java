package dcx.comn.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

import dcx.comn.exception.TranTypeException;
import dcx.lpoint.prop.DataType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TranUtils {
	
	public static SecureUtils secureUtils = new SecureUtils();
	public static byte[] getEncBytes(String data, DataType dataType, int length) throws TranTypeException {
		data = Strings.nullToEmpty(data);
		return StringUtils.rightPad(secureUtils.encrypMd5(data), length, ' ').getBytes();

	}

	public static byte[] getBytes(String data, DataType dataType, int length) throws TranTypeException  {
		data = Strings.nullToEmpty(data);
		if (data.getBytes().length > length) {
			throw new TranTypeException(length + "byte 이하의 문자만 입력 가능합니다. " +
					"(`" + data + "` length is " + data.getBytes().length + ")");
		}

		return StringUtils.rightPad(data, length, ' ').getBytes();

		// return Bytes.ensureCapacity(data.getBytes(), length, 0);
	}

	public static byte[] getBytes(int data, DataType dataType, int length) throws TranTypeException  {
		String strData = data + "";

		// 문자형으로 0000N 형태로 치환
		return StringUtils.leftPad(strData, length,'0').getBytes();
	}

	public static String getString(byte[] data) throws Exception {
		return new String(data, "euc-kr").replaceAll("\\u0000", "");
	}

	public static Integer getInt(byte[] data) {
		Integer result = 0;
		try {
			result = Integer.parseInt(new String(data, "UTF-8"));
		} catch (NumberFormatException | UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public static String fill(String data, DataType dataType, int length) throws TranTypeException {
		return new String(getBytes(data, dataType, length));
	}

	public static String fill(int data, DataType dataType, int length) throws TranTypeException {
		String strData = data + "";
		if (strData.length() > length) {
			throw new TranTypeException(length + "자리 이하의 숫자만 입력 가능합니다. " +
					"(`" + data + "` length is " + strData.length() + ")");
		}
		if (dataType != DataType.N) {
			throw new TranTypeException("데이터 타입이 잘못 입력되었습니다.");
		}
		return Strings.padStart(data + "", length, dataType.getPadChar());
	}

	public static Integer getInt(byte[] target, DataType dataType, int pos, int length) {
		String temp = getString(target, dataType, pos, length);
		return temp == null ? null : Integer.parseInt(temp);
	}

	public static String getString(byte[] target, DataType dataType, int pos, int length) {
		byte[] source = new byte[length];
		System.arraycopy(source, pos, target, 0, length );
		String temp = null;
		try {
			temp = new String(source, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			log.error(uee.getMessage());
		}
		return temp;
	}
}
