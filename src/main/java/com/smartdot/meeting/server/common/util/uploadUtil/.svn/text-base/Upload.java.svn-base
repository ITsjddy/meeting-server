package com.smartdot.meeting.server.common.util.uploadUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class Upload {
    @SuppressWarnings("unchecked")
	public static void upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 
       	String SavePath = request.getSession().getServletContext().getRealPath("/")+"photos/";   
    	String SaveUrl = "/photos/" ;  
    	String[] ExtArr =  new  String[]{ ".gif" , ".jpg" , ".png" , ".bmp" };   
    	int  MaxSize =  10*1024*1024 ;   
    	String Msg1 =  "上传文件大小超过限制。" ;   
    	String Msg2 =  "上传文件的扩展名不被允许。" ;    
    	String Msg3 =  "文件上传失败。" ;   
    	String Msg=Msg3;   	    	  
    	Date dt =  new  Date();   
    	Random random =  new  Random();   
    	random.nextInt();   
    	String FileNameAuto = String.format( "%X_%X" , new  Object[]{ new  Integer(( int )(dt.getTime())), new  Integer(random.nextInt())});   
    	String FilePath =  null ;   
    	String FileUrl =  null ;   
    	DiskFileUpload fu =  new  DiskFileUpload();   
    	fu.setSizeMax(MaxSize); //   
    	fu.setSizeThreshold( 4096 );   
    	fu.setRepositoryPath( "/" );  
    	List fileItems = fu.parseRequest(request); 
    	Iterator iter = fileItems.iterator();  
    	while  (iter.hasNext()) {   
    	    FileItem item = (FileItem) iter.next();   
    	    String fieldName = item.getFieldName(); 
    	     if  (!item.isFormField()) {   
    	        String name = item.getName();   
    	         long  size = item.getSize();   
    	         if ((name== null ||name.equals( "" )) && size== 0 )   
    	         continue ;   
    	         if (size>MaxSize) {   
    	            Msg=Msg1;   
    	             break ;   
    	        }    
    	         int  pos = name.lastIndexOf( "." );   
    	        String ext = name.substring(pos);   
    	         boolean  b= false ;   
    	         for ( int  m= 0 ;m<ExtArr.length; m++){   
    	             if (ExtArr[m].equalsIgnoreCase(ext)){   
    	                b= true ;   
    	                 break ;   
    	            }   
    	        }   
    	         if  (b== false ){   
    	            Msg=Msg2;   
    	             break ;   
    	        }   
    	        FilePath = SavePath + FileNameAuto+ext;   
    	        FileUrl = SaveUrl + FileNameAuto+ext;  
    	        java.io.File f=  new  java.io.File(FilePath);   
    	        item.write(f);   
    	    } 
    	}
    	PrintWriter out=response.getWriter();
    	out.println(FileUrl);
    }
    
	private static PrintWriter encodehead(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		return response.getWriter();
	}
	public static String rename(FileItem file) {
		String body = "";
		String ext = "";
		Date date = new Date();
		int pot = file.getName().lastIndexOf(".");
		if (pot != -1) {
			body = date.getTime() + "";
			ext = file.getName().substring(pot);
		} else {
			body = (new Date()).getTime() + "";
			ext = "";
		}
		String newName = body + ext;
		return newName;
	}
}
