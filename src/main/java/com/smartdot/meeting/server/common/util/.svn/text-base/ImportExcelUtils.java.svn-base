package com.smartdot.meeting.server.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelUtils {

	/**
	 * 对外提供读取excel 的方法
	 * */
	public static List[] readExcel(File file) throws IOException {
		String fileName = file.getName();
		
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xlsx".equalsIgnoreCase(extension)) {
			return read2007Excel(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	/**
	 * 对外提供读取excel 的方法
	 * */
	public static List<Map<String,Object>> readExcel(File file,Map<String,Object> map) throws IOException {
		if(null==file||null==map){
			return null;
		}
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xlsx".equalsIgnoreCase(extension)) {
			return read2007Excels(file,map);
		} else if("xls".equalsIgnoreCase(extension)){
			return read2003Excel(file, map);
		}else{
			throw new IOException("不支持的文件类型");
		}
	}
	
	
	/**
	 * 读取Office 2007 excel
	 * */
	private static List<Map<String, Object>> read2007Excels(File file, Map<String,Object> memmap) throws IOException {
		Map<String, Integer> columnMap = new HashMap<String, Integer>();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		int sheetNum = xwb.getNumberOfSheets();
		for (int index = 0; index < sheetNum; index++) {
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(index);
			Object value = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			// 通过properties对应关系找到excel和数据库的对应关系
			row = sheet.getRow(0);
			if(row==null){
				break;
			}
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				default:
					value = cell.toString();
				}
				if(null!=memmap){
					String column = String.valueOf(memmap.get(value.toString()));
					if (StringUtils.isNotBlank(column)) {
						columnMap.put(column.trim(), j);
					}
				}else{
					columnMap.put(value.toString(), j);
				}
			}
			// 获取excel内容
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				int shu=0;
				for (String key : columnMap.keySet()) {
					int cellIndex = columnMap.get(key);
					//System.out.println("---key---"+key+"---cellIndex---"+cellIndex);
					cell = row.getCell(cellIndex);
					value = get2007Value(cell);
					if(null!=value&&""!=value&&StringUtils.isNotBlank(value.toString())){
						map.put(key, value);
					}else{
						shu++;
					}
				}
				if(shu!=columnMap.size()){
					list.add(map);
				}else{
					list.add(null);
				}
			}
		}
		System.out.println("读取....................................");
		return list;
	}
	
	/**
	 * 读取Office 2007 excel
	 * */
	public static List<String[]> read2007UserExcel(File file) throws IOException {
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		FileInputStream in = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			in = new FileInputStream(file);
			XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
			int sheetNum = xwb.getNumberOfSheets();
			for (int index = 0; index < sheetNum; index++) {
				XSSFSheet sheet = xwb.getSheetAt(index);
				XSSFRow row = null;
				XSSFCell cell = null;
				// 获取excel内容
				for (int i = sheet.getFirstRowNum()+1; i < sheet.getLastRowNum()+1; i++) {
					row = sheet.getRow(i);
					if (row == null) {
						continue;
					}
					String[] rr = new String[30];
					for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {  
				        cell = row.getCell(j);
				            cell = row.getCell(j);
					        rr[j]=get2007Value(cell).toString();
				    } 
					list.add(rr);
				}
			}
		} catch (Exception e) {
			e.getLocalizedMessage();
		}finally {
            try {
                if(in != null) {
                    in.close();
                }
            }catch (Exception e) {
            	e.getLocalizedMessage();
            }
        }
		
		return list;
	}
	
	
	/**
	 * 读取Office 2007 excel
	 * */
	private static List[] read2007Excel(File file) throws IOException {
		Map<String, Integer> columnMap = new HashMap<String, Integer>();
		List[] tablelist = new List[2];
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		int sheetNum = xwb.getNumberOfSheets();
		for (int index = 0; index < sheetNum; index++) {
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(index);
			Object value = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			// 通过properties对应关系找到excel和数据库的对应关系
			row = sheet.getRow(0);
			if(row==null){
				break;
			}
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				default:
					value = cell.toString();
				}
				columnMap.put(value.toString(), j);
			}
			// 获取excel内容
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				for (String key : columnMap.keySet()) {
					int cellIndex = columnMap.get(key);
					cell = row.getCell(cellIndex);
					value = get2007Value(cell);
					map.put(key, value);
				}
				
				list.add(map);//内容
			}
		}
		List<String> titleList = new ArrayList<String>();//表头
		for(String s: columnMap.keySet()){
			titleList.add(s);
		}
		tablelist[0]=titleList;
		tablelist[1]=list;
		System.out.println("读取....................................");
		return tablelist;
	}
	

	private static Object get2007Value(XSSFCell cell){
		if (cell == null) {
			return "";
		}
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");// 格式化 number
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
		DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
		cell.setCellType(Cell.CELL_TYPE_STRING);
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			if ("@".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = nf.format(cell.getNumericCellValue());
			} else {
				value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value;
	}
	
	private static Object get2003Value(HSSFCell cell){
		if (cell == null) {
			return "";
		}
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");// 格式化 number
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
		DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
		cell.setCellType(Cell.CELL_TYPE_STRING);
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if ("@".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = nf.format(cell.getNumericCellValue());
			} else {
				value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value;
	}
	
	public static List<Map<String, Object>> read2003Excel(File file, Map<String,Object> memmap) throws IOException{
		Map<String, Integer> columnMap = new HashMap<String, Integer>();
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	    HSSFWorkbook wb = new HSSFWorkbook(fs);
	    int sheetNum = wb.getNumberOfSheets();
		for (int index = 0; index < sheetNum; index++) {
			// 读取第一章表格内容
			HSSFSheet sheet = wb.getSheetAt(index);
			Object value = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			// 通过properties对应关系找到excel和数据库的对应关系
			row = sheet.getRow(0);
			if(row==null){
				break;
			}
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				default:
					value = cell.toString();
				}
				if(null!=memmap){
					String column = String.valueOf(memmap.get(value.toString()));
					if (StringUtils.isNotBlank(column)) {
						columnMap.put(column.trim(), j);
					}
				}else{
					columnMap.put(value.toString(), j);
				}
			}
			// 获取excel内容
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				int shu=0;
				for (String key : columnMap.keySet()) {
					int cellIndex = columnMap.get(key);
					cell = row.getCell(cellIndex);
					value = get2003Value(cell);
					if(null!=value&&""!=value&&StringUtils.isNotBlank(value.toString())){
						map.put(key, value);
					}else{
						shu++;
					}
				}
				if(shu!=columnMap.size()){
					list.add(map);
				}else{
					list.add(null);
				}
			}
		}
	    
	    return list;
	}
	
	
	
	public static List<String[]> read2003UserExcel(File file) throws IOException{
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	    HSSFWorkbook wb = new HSSFWorkbook(fs);
	    int sheetNum = wb.getNumberOfSheets();
	    List<String[]> list = new ArrayList<String[]>();
		for (int index = 0; index < sheetNum; index++) {
			HSSFSheet sheet = wb.getSheetAt(index);
			HSSFRow row = null;
			HSSFCell cell = null;
			// 获取excel内容
			for (int i = sheet.getFirstRowNum()+1; i < sheet.getLastRowNum()+1; i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				String[] rr = new String[30];
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {  
			        cell = row.getCell(j);
			        rr[j]=get2003Value(cell).toString();
			    }
				list.add(rr);
			}
		}
		return list;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		try {
			List[] dataList = read2007Excel(new File("F:\\java\\exhibitapi\\web\\upload\\excel\\20150326053137543828.xlsx"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
