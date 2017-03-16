package com.smartdot.meeting.server.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
* @ClassName: CommonUtil
* @Description: 常用工具类
* @author: ms
* @date: 2016-01-10
*
*/
public class CommonUtil {
    
    private static Log logger = LogFactory.getLog(CommonUtil.class);
        
    /**
     * cookie 失效时间 2年
     * 
     * @return 返回类型  int
    */
    public static Integer sxTime = 2*365*24*60*60;
    /**
     * cookieid 长度
     * 
     * @return 返回类型  int
    */
    public static Integer coLength = 20 ;
    
    
    
    public final synchronized static boolean notNull(String s){
    	try {
    		boolean result = false; 
        	if (!"".equals(s)&&null!=s) {
        		result = true;
    			return result;
    		}
        } catch (Exception ex) {
            logger.error(ex);
        }
    	
		return false;
    }

    public final synchronized static boolean isfunull(String zhi) {
    	boolean tf = false;
    	try {
    		tf = StringUtils.isNotBlank(zhi);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return tf;
    }
    
    public final synchronized static boolean isnull(String zhi) {
    	boolean tf = false;
    	try {
    		tf = StringUtils.isBlank(zhi);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return tf;
    }
    
    /**
     * 功能：用于判断文件是否是ZIP文件
     */
    public final synchronized static boolean isZipFile(String fileName){
    	boolean jg = false;
    	try {
    		jg=fileName.toLowerCase().endsWith(".zip");
        } catch (Exception ex) {
            logger.error(ex);
        }
        return jg;
    }
    
    /**
       * 当前日期格式转换
       * 
       * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
       */
    public final synchronized static Timestamp getNowDate() {
        Timestamp currentTime2 = null;
        try {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            Date currentdate = formatter.parse(dateString);
            currentTime2 = new Timestamp(currentdate.getTime());
        } catch (Exception e) {
            logger.error(e);
        }
        
        return currentTime2;
    }

    /**
       * 日期格式转换
       * 
       * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
       */
    public final synchronized static Timestamp getTrDate(String date) {
        Timestamp currentTime2 = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String转Timestamp    
        try {
            if (isfunull(date)) {
                Date currentdate = format.parse(date);
                currentTime2 = new Timestamp(currentdate.getTime());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return currentTime2;
    }
    /**
     * 日期格式转换
     * @return 返回时间类型 yyyy-MM-dd HH:mm
     */
    public final synchronized static Timestamp getTrTimeHm(String date) {
	      Timestamp currentTime2 = null;
	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	      // String转Timestamp    
	      try {
	          if (StringUtils.isNotBlank(date)) {
	              Date currentdate = format.parse(date);
	              currentTime2 = new Timestamp(currentdate.getTime());
	          }
	      } catch (Exception e) {
	          e.getMessage();
	      }
	      return currentTime2;
	}

    /**
       * 日期格式转换
       * 
       * @return 返回时间类型 yyyy-MM-dd
       */
    public final synchronized static Date getTrDay(String date) {
        Date currentDate = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // String转Date    
        try {
            if (isfunull(date)) {
                Date currentdate = format.parse(date);
                currentDate = new Date(currentdate.getTime());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return currentDate;
    }
    
    /**
     * 日期格式转换
     * 
     * @return 返回时间类型 HH:mm:ss
     */
  public final synchronized static Timestamp getHmsDate(String date) {
      Timestamp currentTime2 = null;
      DateFormat format = new SimpleDateFormat("HH:mm:ss");
      // String转Timestamp    
      try {
          if (isfunull(date)) {
              Date currentdate = format.parse(date);
              currentTime2 = new Timestamp(currentdate.getTime());
          }
      } catch (Exception e) {
          logger.error(e);
      }
      return currentTime2;
  }
    
    
    /**
     * 将date 转  yyyy-MM-dd
     * @param date
     * @return string
     */
    public final synchronized static String getDateStr(Date date){
    	 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	 String dateStr = null;
    	 try {
    		 if(date !=null){
        		 dateStr = format.format(date);
        	 }
         } catch (Exception e) {
             logger.error(e);
         }
    	 return dateStr;
    }
    /**
     * 将 date 类型转为 yyyy-MM-dd HH:mm:ss 
     * @param date
     * @return string
     */
    public final synchronized static String getDateStr1(Date date){
   	 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   	 String dateStr = null;
	try {
		if(date !=null){
			dateStr = format.format(date);
		}
	} catch (Exception e) {
		logger.error(e);
	}
   	 return dateStr;
   }
    
    /**
     * 将 date 类型转为 yyyy-MM-dd HH:mm:ss 
     * @param date
     * @return string
     */
    public final synchronized static String getDateStr2(Date date){
   	 DateFormat format = new SimpleDateFormat("HH:mm");
   	 String dateStr = null;
	try {
		if(date !=null){
			dateStr = format.format(date);
		}
	} catch (Exception e) {
		logger.error(e);
	}
   	 return dateStr;
   }
    
    /**
     * @param ps
     * @param i
     * @param value
     * @throws SQLException
     */
    public final synchronized static void setIntValue(PreparedStatement ps, int i, Integer value) throws SQLException {
    	try {
    		if (value != null) {
                ps.setInt(i, value);
            } else {
                ps.setNull(i, Types.INTEGER);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * @param ps
     * @param i
     * @param value
     * @throws SQLException
     */
    public final synchronized static void setLongValue(PreparedStatement ps, int i, long value) throws SQLException {
    	try {
    		if (value!=0) {
                ps.setLong(i, value);
            } else {
                ps.setNull(i, Types.LONGNVARCHAR);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * @param ps
     * @param i
     * @param value
     * @throws SQLException
     */
    public final synchronized static void setStrValue(PreparedStatement ps, int i, String value) throws SQLException {
    	try {
    		if (value != null) {
                ps.setString(i, value);
            } else {
                ps.setString(i, "");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /**
     * @param ps
     * @param i
     * @param value
     * @throws SQLException
     */
    public final synchronized static void setTimeStampValue(PreparedStatement ps, int i,
            Timestamp value) throws SQLException {
    	try {
    		if (value != null) {
                ps.setTimestamp(i, value);
            } else {
                ps.setNull(i, Types.TIMESTAMP);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }
  
  /**
   * 获取文件的绝对路径
   * 
   * @return String
   * @throws  
   */
  public final synchronized static String fileUrl(String urlname){
      String fileUrl="";
      try {
          fileUrl=Thread.currentThread().getContextClassLoader().getResource(urlname).toURI().getPath();
      } catch (URISyntaxException e) {
          logger.error(e);
      }
      return fileUrl;
  }
  
  /**
   * @param <T>
   * 获取分页列表信息
   * 
   * @return List
   * @throws  
   */
  public final synchronized static int[] getPageInfo(String pageNo,String pageSize){
	  try {
	      int no = 1;int size = 10;
		  if(StringUtils.isNotBlank(pageNo))
			  no = Integer.valueOf(pageNo.trim());
		  if(StringUtils.isNotBlank(pageSize))
			  size = Integer.valueOf(pageSize.trim());	      
	      return new int[]{no,size};
	  } catch (Exception ex) {
	      logger.error(ex);
	      return null;
	  }
	}
  
  public final synchronized static Object setValues(Object value){
	try {
		if (value == null || value == "null") {
			value="";
	    }
	} catch (Exception e) {
		logger.error(e);
		value="";
	}
      
    return value;
  }
  
  
  
  public final synchronized static Object setNotVal(Object value1,Object value2){
	  try {
		  if(null == value1 || StringUtils.isBlank(value1.toString())){
			  value1=value2;
		  }
		  if(null == value1 || StringUtils.isBlank(value1.toString())){
			  value1 = "";
		  }
	  } catch (Exception e) {
			logger.error(e);
			value1="";
	  }
	  return value1;
  }
  
  /**
   * 日期判断星期几
   * 
   * @return 返回时间类型  星期X
  */
  public final synchronized static String dayForWeek(String pTime,int lg){
	    try {
	    	 /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	 String[] zhstr = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	    	 String [] enstr = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	    	 CalendarData_ar calendar = Calendar.getInstance();
	    	 Date tmpDate = format.parse(pTime);
	    	 calendar.setTime(tmpDate);
	    	 int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    	 if(lg==I18NUtil.ZH){
				return zhstr[intWeek];
			 }else if(lg==I18NUtil.EN){
				return enstr[intWeek];
			 }*/
	    	 
		} catch (Exception e) {
			logger.error(e);
		}
		return "";
  }
  
  /**
   * app返回结果
   * 
   * @return 返回类型  json
  */
  public final synchronized static void reJson(HttpServletResponse response,String values){
	    try {
	    	if(isfunull(values)){
	    		response.setContentType("text/html; charset=utf-8");
	    		PrintWriter out = response.getWriter();
	    		out.print(values);  
	    		out.flush();  
	    		out.close(); 
	    	}
		} catch (Exception e) {
			logger.error(e);
		}
  }
  
  /**
   * app返回结果
   * 
   * @return 返回类型  json
  */
  public final synchronized static String getUrl(HttpServletRequest request){
	  String url="";
	  try {
		  ResourceBundle bundle1 = PropertyResourceBundle.getBundle("config");
			String tranheader = bundle1.getString("tranheader");//
			String port = bundle1.getString("port");
			String context = request.getContextPath();
			String server = request.getServerName();
			String ip = bundle1.getString("ip");
			if(StringUtils.isNotBlank(ip)){
				server=ip;
			}
			url = tranheader+"://"+server + ":" + port + context;
	  } catch (Exception e) {
		  e.printStackTrace();
		  logger.error(e.getMessage());
	  }
	  	
	  return url;
  }
  
  
  /**
   * 文件绝对路径
   * 
   * @return 返回类型  String
  */
  public final synchronized static String reUrl(HttpServletRequest request,String values){
	  	String wjurl="";
	    try {
	    	 if(isfunull(values)){
	    		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
				String tranheader = bundle.getString("tranheader");//
				String port = bundle.getString("port");
				if(StringUtils.isBlank(port)){
					port=String.valueOf(request.getServerPort());
    			}
    			String context = request.getContextPath();
    			String server = request.getServerName();
    			String ip = bundle.getString("ip");
    			if(StringUtils.isNotBlank(ip)){
    				server=ip;
    			}
    			//int port = request.getServerPort();
    			wjurl=tranheader+"://"+server+":"+port+context+values;
    			return wjurl;
	    	 }
		} catch (Exception e) {
			logger.error(e);
		}
	    return wjurl;
  }
  
  
  private static  String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}
  /**
	 * 公用上传模块
	 * 
	 * @param request
	 * @return
	 */
	public final synchronized static String upload(HttpServletRequest request,String elementName,String root,
			String folderName,String fiName) {
		String result = "";
		try {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			MultipartFile file = req.getFile(elementName);
			if (file == null || file.getSize() == 0) {
				return null;
			}
			//获取上传文件主目录
	        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
			String upFolder = bundle.getString("upload_folder");//
			if(StringUtils.isBlank(upFolder)){
				return null;
			}
			String upFolder2=upFolder;
			if(StringUtils.isNotBlank(folderName)){
				upFolder=upFolder + File.separator + folderName;
				upFolder2=upFolder2 + "/" + folderName;
			}
			
			String name = file.getOriginalFilename();
			// 后缀小数点前面的字符数
			int i = name.lastIndexOf(".");
			// 得到后缀
			String dec = name.substring(i);
			dec = dec.toLowerCase();

			String filePath = root;
			
			filePath = filePath + File.separator + upFolder + File.separator;
			result = "/" + upFolder2 + "/";

			String fileName = fiName + dec;
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}else{
				//将原来的文件重命名
				File dirs = new File(filePath + fileName);
				if(dirs.exists()){
					String backupName = format(new Date(), "yyyyMMddHHmmssSS") + RandomStringUtils.randomNumeric(3) + "_hy" + dec;
					dirs.renameTo(new File(filePath + backupName));
				}
			}
			filePath = filePath + fileName;
			result = result + fileName;
			uploadFile(file,filePath);
			String sharedType = bundle.getString("shared_type");
			if(StringUtils.isNotBlank(sharedType)){
				String[] types =sharedType.split(",");
				for(int j = 0;j <types.length;j++){
					if("1".equals(types[j])){
						CommonUtil.shareUpload(filePath,folderName,"shared_info");
					}
					if("2".equals(types[j])){
						CommonUtil.shareUpload(filePath,folderName,"shared_info2");
					}
					if("3".equals(types[j])){
						CommonUtil.shareUpload(filePath,folderName,"shared_info3");
					}
				}
			}
        } catch (Exception ex) {
            logger.error(ex);
        }
		
		return result;
	}
	public static void uploadFile(MultipartFile file,String storePath){
		// 开始上传
		InputStream is =null;
		OutputStream os =null;
		try {
			is = file.getInputStream();
			os = new FileOutputStream(storePath);
			int bytes = 0;
			byte[] buffer = new byte[8192];
			while ((bytes = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytes);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			try{
				if(os!=null){
					os.close();
				}
				if(is!=null){
					is.close();
				}
			}catch(Exception e){
				logger.error(e.getMessage());
			}			
		}
	}
 
	
	/**
	 * 将汉字转换为全拼 
	 * 
	 * @param 	src
	 * @return	String
	 */
    public final synchronized static String stringGetPingYin(String src) {  
    	if(StringUtils.isBlank(src)){
    		return src;
    	}
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        /*HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                	t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                	t4+=toUpperCaseFirstOne(t2[0]);
                } else  
                    t4+= java.lang.Character.toString(t1[i]);  
            }  
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
        	logger.error(e1.getMessage());
        }*/  
        return src;  
    }
	
	/**
	 * 将汉字转换为全拼（姓在后 名在前）  
	 * 
	 * @param 	src
	 * @return	String
	 */
    public final synchronized static String getPingYin(String src) {  
    	if(StringUtils.isBlank(src)){
    		return src;
    	}
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        /*HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        String shou="";
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                	t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                	String zhi=toUpperCaseFirstOne(t2[0]);
                	if(i==0){
                		shou=zhi;
                	}else if(i==1){
                		t4+=zhi;
                	}else{
                		t4+=t2[0];
                	}
                } else  
                    t4+= java.lang.Character.toString(t1[i]);  
            }  
            if(shou.length()>0){
            	if(t4.length()>0){
            		t4=t4+" "+shou;
            	}else{
            		t4=t4+shou;
            	}
            }
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
        	logger.error(e1.getMessage());
        }*/  
        return src;  
    }
    
    /**
	 * 首字母转大写 
	 * 
	 * @param 	s
	 * @return	String
	 */
    public final synchronized static String toUpperCaseFirstOne(String s){
    	try {
    		if(!Character.isUpperCase(s.charAt(0))){
    			s=(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    		}
		} catch (Exception e) {
			logger.error(e);
		}
    	return s;
    }
    
    /**
	 * 首字母转小写
	 * 
	 * @param 	s
	 * @return	String
	 */
    public static String toLowerCaseFirstOne(String s){
    	try {
    		if(!Character.isLowerCase(s.charAt(0))){
    			s=(new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    		}
		} catch (Exception e) {
			logger.error(e);
		}
    	return s;
    }
    
    /**
	 * 生成日志文件
	 * 
	 * @param 	str,path,fileName
	 */
  	public final synchronized static void writelog(String str,String path,String fileName){
  		try {
  			File fi = new File(path,fileName);
  	
  			if(!fi.exists()){
  				fi.getParentFile().mkdirs();
  					fi.createNewFile();
  			} 
  			FileOutputStream fs = new FileOutputStream(path+System.getProperty("file.separator")+fileName,true);
  			fs.write(str.getBytes());
  			fs.flush();
  			fs.close();
  		} catch (Exception e) {
  			 e.printStackTrace();
  		}
  	}
  	/**
  	 * 信息管理富文本框图片共享
  	 */
	public final synchronized static boolean shareUeditor(String file,String type,String shared_infoType) throws IOException{
  		boolean shareUploadState=true;
  		InputStream in = null;
        OutputStream out = null;
        File inputfile=new File(file);
       // File[] allFile=inputfile.listFiles();//所有文件
        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String shInfo = bundle.getString(shared_infoType);//
		if(StringUtils.isBlank(shInfo)){
			shareUploadState=false;
		}
		String remotePhotoUrl = "smb://"+shInfo; //存放文件的共享目录
		/*SmbFile softFileFolder = new SmbFile(remotePhotoUrl + type);  
        if (!softFileFolder.exists()) {  
        	softFileFolder.mkdirs();  
        }
        if(inputfile.exists()){
			try  {
				//for (int i = 0; i < allFile.length; i++) {
				SmbFile remoteFile = new SmbFile(remotePhotoUrl + type+"/"+inputfile.getName()); 
		        remoteFile.connect(); //尝试连接
				 in = new BufferedInputStream(new FileInputStream(inputfile));
		           out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
				    // 考虑大附件上传， 一点点存储
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.write(file.getBytes());
					out.flush(); //刷新缓冲的输出流
				//} 
	         } catch  (IOException e){
	        	 shareUploadState=false;
	             e.printStackTrace();
	         } finally {
	        	 try {
	                 if(out != null) {
	                     out.close();
	                 }
	                 if(in != null) {
	                     in.close();
	                 }
	             }catch (Exception e) {
	            	 shareUploadState=false;
	             	logger.error(e.getLocalizedMessage());
	             }
	         }
        }else{
        	shareUploadState=false;
        }*/
  		return shareUploadState;
  	}
  	/**
	 * 将文件存到远程共享文件夹
	 * 
	 * @param 	file 文件全路径，fileRoute 文件夹
	 * @return  boolean  true为上传成功，false为上传失败
	 */
  	public final synchronized static boolean shareUpload(String file,String type,String shared_infoType){
  		boolean shareUploadState=true;
  		InputStream in = null;
        OutputStream out = null;
       /* try {
            //获取上传文件
            File localFile = new File(file);
            //获取共享文件夹信息
            ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
			String shInfo = bundle.getString(shared_infoType);//
			if(StringUtils.isBlank(shInfo)){
				shareUploadState=false;
			}
			//smb://share:admin@192.168.135.11/sharedFolder/	smb://192.168.135.11/sharedFolder/
            String remotePhotoUrl = "smb://"+shInfo; //存放文件的共享目录
            SmbFile remoteFile = new SmbFile(remotePhotoUrl + type+"/"+localFile.getName()); 
            remoteFile.connect(); //尝试连接
            SmbFile softFileFolder = new SmbFile(remotePhotoUrl + type);  
            if (!softFileFolder.exists()) {  
            	softFileFolder.mkdirs();  
            }
            in = new BufferedInputStream(new FileInputStream(localFile));
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
		    // 考虑大附件上传， 一点点存储
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.write(file.getBytes());
			out.flush(); //刷新缓冲的输出流
        }catch (Exception e) {
        	shareUploadState=false;
        	logger.error(e.getLocalizedMessage());
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(in != null) {
                    in.close();
                }
            }catch (Exception e) {
            	shareUploadState=false;
            	logger.error(e.getLocalizedMessage());
            }
        }*/
  		return shareUploadState;
  	}
  	public static String format2(String name) {
  	 	String regex="[<>#$%^*&\\/?{}()''=\"]"; 
  	 	try {
  	 		if(StringUtils.isNotBlank(name)){
  	 	 		Pattern pat = Pattern.compile(regex);
  	 			Matcher mat = pat.matcher(name);
  	 			if (mat.find()) {
  	 				name=mat.replaceAll("");
  	 			}
  	 			name.replace("%","");
  	 			name.replace("*","");
				name.replace("$","");
				name.replace("!","");
				 
				name.replace("select","");
				name.replace("update","");
				name.replace("delete","");
				name.replace("insert","");
				name.replace("drop","");
			    name.replace("truncate","");
				name.replace("create","");
				name.replace("count","");
				name.replace("char","");
				name.replace("mid","");
				name.replace("group","");
				name.replace("union","");
				name.replace("into","");
				name.replace("from","");
				name.replace("rename","");
				name.replace("xp_cmdshell","");
  	 	 	}
  		} catch (Exception e) {
  			logger.error(e.getMessage());
  		}
  	 	return name;
  	 }
  	
  	
  	/**
     * post请求
     * @param code
     * @return 返回结果
     */
  	public void postVisit(String url) {
		/*String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setContentCharset("UTF-8");
        
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("to", "18515834121");
        postMethod.addParameter("appId", "8a48b551486441570148689bb2ab0177");
        postMethod.addParameter("templateId", "1");
        postMethod.addParameter("datas", "");
        postMethod.addParameter("data", "验证码为");
        try {
            httpClient.executeMethod(postMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = postMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }*/
	}
  	
  	@SuppressWarnings("unchecked")
    public static String getTableName(Class clazz) {
  		try {
  			Table annotation = (Table)clazz.getAnnotation(Table.class);
  	        if(annotation != null){
  	            return annotation.name();
  	        }
		} catch (Exception e) {
			logger.error(e);
		}        
 
        return null;
    }
  	
  	/**
	 * 判断变量是否为空
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}
	
	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 传入原图名称，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				return null;
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}
	
	/**
	 * 获取当前认证通过的用户名
	 * @return
	 */
	public static String getAuthenticatedUsername() { 
	    String username = null; 
	    try {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		    if (principal instanceof UserDetails) { 
		        username = ((UserDetails) principal).getUsername(); 
		    } else { 
		        username = principal.toString(); 
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	     
	    return username; 
	}
	
}
