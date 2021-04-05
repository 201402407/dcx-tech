package dcx.comn.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecureUtils {

	SecretKey secretKey = null;
	@Autowired
	sun.misc.BASE64Encoder encoder;
	byte[] keyBytes;

	public SecureUtils () {

		SecretKey secretKey = null;
		encoder = new sun.misc.BASE64Encoder();
		keyBytes = new byte[]
//				{
//						0x4C, 0x31, 0x33, 0x30, 0x4C, 0x31, 0x33, 0x30,
//						0x32, 0x30, 0x31, 0x33, 0x30, 0x35, 0x30, 0x32,
//						0x57, 0x4F, 0x52, 0x4C, 0x44, 0x5F, 0x5F, 0x5F
//				};
		{
				0x4C, 0x33, 0x38, 0x30, 0x4C, 0x33, 0x38, 0x30,
				0x32, 0x30, 0x31, 0x35, 0x31, 0x31, 0x31, 0x33,
				0x4C, 0x6F, 0x74, 0x74, 0x65, 0x5F, 0x52, 0x65
		};
		keySet();
	}

	public void keySet(){
//		System.out.println("1. 암호화 키 설정");
		try{
			DESedeKeySpec desKeySpec = new DESedeKeySpec(keyBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("TripleDes");
			secretKey = keyFactory.generateSecret(desKeySpec);

//			System.out.println("2. 키 설정 완료");
		}
		catch(IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e){
			log.error(e.getMessage());
		}
	}
	public byte[] Encryption(byte[] orgBufByte)
	{
		byte[] encByte = null;
		try{
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] encBlockBuf = new byte[1024];
			encByte = new byte[orgBufByte.length];

			int nBlock = orgBufByte.length / 8;
			int reminder = orgBufByte.length % 8;

            /* 데이터가 없을 때 */
			if (nBlock == 0 && reminder == 0)
				return encByte;

            /* 길이가 8의 배수가 아닐 때 */
			if (reminder != 0)
			{
                /* 길이가 8 이상 일 때 */
				if (nBlock != 0)
				{
					encBlockBuf = cipher.doFinal(orgBufByte, 0, nBlock * 8);

					for (int i = 0; i < nBlock * 8; i++)
						encByte[i] = encBlockBuf[i];

					for (int i = 0; i < reminder; i++)
						encByte[nBlock * 8 + i] = (byte)(orgBufByte[nBlock * 8 + i] ^ keyBytes[i]);
				}
                /* 길이가 8 이하 일 때 */
				else if (nBlock == 0)
				{
					for (int i = 0; i < reminder; i++)
						encByte[i] = (byte)(orgBufByte[i] ^ keyBytes[i]);
				}
			}
            /* 길이가 8의 배수 일 경우 */
			else if (reminder == 0)
			{
				encByte = cipher.doFinal(orgBufByte, 0, nBlock * 8);
			}

		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
			logger.error(e.getMessage());
		}
		return encByte;
	}

	public byte[] Encryption(String orgData)
	{
		byte[] encByte = null;
		try{
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] orgBufByte = orgData.getBytes("UTF-8");
			byte[] encBlockBuf = new byte[1024];
			encByte = new byte[orgBufByte.length];

			int nBlock = orgBufByte.length / 8;
			int reminder = orgBufByte.length % 8;

            /* 데이터가 없을 때 */
			if (nBlock == 0 && reminder == 0)
				return encByte;

            /* 길이가 8의 배수가 아닐 때 */
			if (reminder != 0)
			{
                /* 길이가 8 이상 일 때 */
				if (nBlock != 0)
				{
					encBlockBuf = cipher.doFinal(orgBufByte, 0, nBlock * 8);

					for (int i = 0; i < nBlock * 8; i++)
						encByte[i] = encBlockBuf[i];

					for (int i = 0; i < reminder; i++)
						encByte[nBlock * 8 + i] = (byte)(orgBufByte[nBlock * 8 + i] ^ keyBytes[i]);
				}
                /* 길이가 8 이하 일 때 */
				else if (nBlock == 0)
				{
					for (int i = 0; i < reminder; i++)
						encByte[i] = (byte)(orgBufByte[i] ^ keyBytes[i]);
				}
			}
            /* 길이가 8의 배수 일 경우 */
			else if (reminder == 0)
			{
				encByte = cipher.doFinal(orgBufByte, 0, nBlock * 8);
			}

		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchPaddingException e) {
			logger.error(e.getMessage());
		}
		return encByte;
	}

	public byte[] Decryption(byte[] encByte)
	{
		byte[] decByte = null;
		try{
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE,  secretKey);

			byte[] decBlockBuf = new byte[1024];
			decByte = new byte[encByte.length];

			int nBlock = encByte.length / 8;
			int reminder = encByte.length % 8;

            /* 데이터가 없을 때 */
			if (nBlock == 0  && reminder == 0) {
				return decByte;
			}
			if (reminder != 0) {	/* 길이가 8의 배수가 아닐 때 */
				if (nBlock != 0) {	/* 길이가 8 이상 일 때 */
					decBlockBuf = cipher.doFinal(encByte, 0, nBlock * 8);

					for (int i = 0; i < nBlock * 8; i++)
						decByte[i] = decBlockBuf[i];

					for (int i = 0; i < reminder; i++)
						decByte[nBlock * 8 + i] = (byte)(encByte[nBlock * 8 + i] ^ keyBytes[i]);
				} else if (nBlock == 0) {	/* 길이가 8 이하 일 때 */
					for (int i = 0; i < reminder; i++)
						decByte[nBlock * 8 + i] = (byte)(encByte[nBlock * 8 + i] ^ keyBytes[i]);
				}
			} else if (reminder == 0)	{ 	/* 길이가 8의 배수 일 경우 */
				decByte = cipher.doFinal(encByte, 0, nBlock * 8);;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
			logger.error(e.getMessage());
		}
		return decByte;
	}

	public String encrypMd5(String str){
		String md5 = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			md5 = sb.toString();
		} catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
		}
		return md5;

	}
}

