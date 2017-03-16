package com.smartdot.meeting.server.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密编码
 * @author ms
 * @datetime 2015-11-12
 */
public class EncodEncryUtil {
	private static Log logger = LogFactory.getLog(EncodEncryUtil.class);
	
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return inStr;
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	
	/**
     * 使用base64编码字符串
     * @param data
     * @return 编码后的字符串
     */
    public static String compressData(String data) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DeflaterOutputStream zos = new DeflaterOutputStream(bos);
            zos.write(data.getBytes());
            zos.close();
            return new String(getenBASE64inCodec(bos.toByteArray()));
        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return data;
        }
    }
    /**
     * 调用apache的编码方法
     */
    public static String getenBASE64inCodec(byte [] b) {
        if (b == null){
            return null;
        }
        return new String((new Base64()).encode(b));
    }
    
    /**
     * 使用base64解码字符串
     * @param encdata
     * @return 解码后的字符串
     */
    public static String decompressData(String encdata) {
    	InflaterOutputStream zos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            zos = new InflaterOutputStream(bos);
            zos.write(getdeBASE64inCodec(encdata)); 
            
            return new String(bos.toByteArray());
        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return encdata;
        }finally{
        	try {
				zos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    /**
     * 使用base64解码字符串
     * @param encdata
     * @return 解码后的字符串
     */
    public static String compressData2(String encdata) {
    	String ret = "";
        try {
        	 ret = new  BASE64Encoder().encode(encdata.getBytes());;
            return ret;
        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return encdata;
        }
    }
    /**
     * 使用base64解码字符串
     * @param encdata
     * @return 解码后的字符串
     */
    public static String decompressData2(String encdata) {
		try {
			return new String(new BASE64Decoder().decodeBuffer(encdata));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return encdata;
    }
    
   
    
    
    /**
     * 调用apache的解码方法
     */
    public static byte[] getdeBASE64inCodec(String s) {
        if (s == null){
            return null;
        }
        return new Base64().decode(s.getBytes());
    }
    
    /**
     * 生成随机数字和字母
     */
  	public static String getStringRandom(int length) {
  		String val = "";
  		Random random = new Random();
  		//参数length，表示生成几位随机数
  		for(int i = 0; i < length; i++) {
  			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
  			//输出字母还是数字
  			if( "char".equalsIgnoreCase(charOrNum) ) {
  				//输出是大写字母还是小写字母
  				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
  				val += (char)(random.nextInt(26) + temp);
  			} else if( "num".equalsIgnoreCase(charOrNum) ) {
  				val += String.valueOf(random.nextInt(10));
  			}
  		}
  		return val;
  	}
  	
  	/**
     * 生成随机数字和字母 内容自己定义
     */
  	public static String getRandomString(int length){  
        String str="abcdefghijklmnopqrstuvwxyz0123456789";  
        //String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();  
          
        StringBuffer sb=new StringBuffer();  
          
        for(int i=0;i<length;i++){  
              
            int number =random.nextInt(str.length());  
              
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    }

  	/**
  	 * des解密
  	 * @param decryptString
  	 * @param decryptKey
  	 * @return
  	 * @throws Exception
  	 */
	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		if(StringUtils.isBlank(decryptString) || StringUtils.isBlank(decryptKey)){
			return "";
		}
		byte[] byteMi = Base64.decodeBase64(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData,"UTF-8");
	}
	
	 public static byte[] hexStr2ByteArr(String strIn) throws Exception {  
	        byte[] arrB = strIn.getBytes();  
	        int iLen = arrB.length;  
	  
	        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2  
	        byte[] arrOut = new byte[iLen / 2];  
	        for (int i = 0; i < iLen; i = i + 2) {  
	            String strTmp = new String(arrB, i, 2);  
	            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
	        }  
	        return arrOut;  
	    }  
	
	public static byte[] decode(String s) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;
		int len = s.length();
		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;
			if (i == len)
				break;
			int tri = (decode(s.charAt(i)) << 18)
					+ (decode(s.charAt(i + 1)) << 12)
					+ (decode(s.charAt(i + 2)) << 6)
					+ (decode(s.charAt(i + 3)));
			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);
			i += 4;
		}
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}
	
	
	public static String md5(String str) {
        String s=str;
		if(StringUtils.isBlank(s)){
			return "";
		}else{
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
				}catch (Exception ex) {
					//Logger.getLogger(md5.class.getName()).log(Level.SEVERE, null, ex);
					logger.error(ex.getMessage());
				}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
				} catch (Exception ex) {
					logger.error(ex);
				}
			return value;
		}
	} 
    /** 
     * 生成含有随机盐的密码 ,返回值为盐+盐密码
     */  
    public static Map<String,String> generateSalt(String password) {
    	password=string2MD5(password);
    	String salt =RandomStringUtils.randomAlphanumeric(32);
        password = md5Hex(password + salt);  
        Map<String,String> map=new HashMap<String, String>();
        map.put("salt", salt);
        map.put("saltpassword",password);
        return map;  
    }  
    /** 
     * 生成含有随机盐的密码 ,返回值为盐密码
     */  
    public static String Salt(String password) {
    	password=string2MD5(password);
    	String salt =RandomStringUtils.randomAlphanumeric(32);
        password = md5Hex(password + salt);  
        return password;  
    }  
  
    /** 
     * 校验密码是否正确 
     */  
    public static boolean verify(String password, String md5) {  
        char[] cs1 = new char[32];  
        char[] cs2 = new char[16];  
        for (int i = 0; i < 48; i += 3) {  
            cs1[i / 3 * 2] = md5.charAt(i);  
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);  
            cs2[i / 3] = md5.charAt(i + 1);  
        }  
        String salt = new String(cs2);  
        return md5Hex(password + salt).equals(new String(cs1));  
    }  
  
    /** 
     * 获取十六进制字符串形式的MD5摘要 
     */  
    public static String md5Hex(String src) {  
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] bs = md5.digest(src.getBytes());  
            return new String(new Hex().encode(bs));  
        } catch (Exception e) { 
        	logger.error(e);
            return null;  
        }  
    }  
    
    /**
     * base64编码
     * @param data
     * @return 编码后的字符串
     */
    public static String base64CodeData(String data) {
    	if (StringUtils.isBlank(data)){
			return data;
		}
        try{  
            byte[] encodeBase64 = Base64.encodeBase64(data.getBytes("UTF-8"));  
            return new String(encodeBase64);
        } catch(UnsupportedEncodingException e){  
        	logger.error(e.getMessage());
        }
        return data;
    }
    
    /**
     * base64解码
     * @param data
     * @return 解码后的字符串
     */
    public static String base64DecodeData(String data) {
		if (StringUtils.isBlank(data)){
			return data;
		}
		try {
			byte[] decodeBase64 = Base64.decodeBase64(data.getBytes("UTF-8"));
			return new String(decodeBase64);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return data;
	}
  
    /**
	 * des加密
	 * 
	 * @param encryptKey
	 * @param encryptString
	 * @return
	 */
	public static String desEncode(String encryptKey, String encryptString) {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher;
		byte[] encryptedData = null;
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			encryptedData = cipher.doFinal(encryptString.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return base64Encode(encryptedData);
	}
	
	/**
	 * des解密
	 */
	public static String desDecode(String decryptKey, String decryptString)
			throws Exception {
		byte[] byteMi = base64Decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}

	/**
	 * @param data
	 * @return
	 */
	public static String base64Encode(byte[] data) {
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 0x0ff) << 8)
					| (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16)
					| ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}
	/**
	 * @param data
	 * @return
	 */
	public static byte[] base64Decode(String s) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
		return decodedBytes;
	}
	// 测试主函数
	public static void main(String args[]) throws Exception {
		System.out.println(string2MD5("孙俊东"));
		System.out.println(decryptDES(URLDecoder.decode("FwnBUj6zZwo%3D"), "95880288"));//ios英文
		System.out.println(decryptDES(URLDecoder.decode("Tcprep1Ynjg%3D"), "95880288"));//ios中文
		
		System.out.println(decryptDES(URLDecoder.decode("0bF1x5HN2IQ%3D"), "95880288"));//安卓英文
		System.out.println(decryptDES(URLDecoder.decode("tEUVWjcRMJHxFx6FcR%2FxhQ%3D%3D"), "95880288"));//安卓中文
		
		
		System.out.println(decryptDES(URLDecoder.decode("zf%2Fy8M1fCmV8gpWduGG3SA%3D%3D"), "95880288"));//安卓中文
		/*String s = new String("wuzhen");
		System.out.println("原始：" + s+s.length());
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));		
		System.out.println("base64后：" + compressData(s));
		System.out.println("解密base64的：" + decompressData(compressData(s)));
		System.out.println("生成随机密码：" + getRandomString(8));
		System.out.println("生成随机密码：" + md5(s));
		System.out.println("SALT加密: "+Salt("1"));
		System.out.println("SALT加密: "+Salt("2"));
		String str = "wuzhen";
		String ret = null;
		ret = new BASE64Encoder().encode(str.getBytes());
		System.out.println("加密前:"+str+" 加密后:"+ret);
		str = "d3V6aGVu";
		try {
			ret = new String(new BASE64Decoder().decodeBuffer(str));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("解密前:"+str+" 解密后:"+ret);
	    
	    //string2MD5("8a48b551486441570148689bb2ab0177+c9a8a1d82be94c3681de05171cfbc0f7+20160328110630");
	    System.out.println(string2MD5("8a48b551486441570148689bb2ab0177+c9a8a1d82be94c3681de05171cfbc0f7+20160328110630").toUpperCase());
	    //test.toUpperCase()
		 */	
			
	   
	}
	
}
