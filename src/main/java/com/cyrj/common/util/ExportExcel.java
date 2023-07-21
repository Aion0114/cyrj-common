package com.cyrj.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 *
 * 应用泛型，代表任意一个符合javabean风格的类
 * 注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() byte[]表jpg格式的图片数据
 */
public class ExportExcel {
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public void exportExcel(Collection dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
	}

	public static void exportExcel(String[] headers, Collection dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection dataset, OutputStream out, String pattern) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title   表格标题名
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public static void exportExcel(String title, String[] headers, Collection dataset, OutputStream out,
			String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("cht");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object t = it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					// if (value instanceof Integer) {
					// int intValue = (Integer) value;
					// cell.setCellValue(intValue);
					// } else if (value instanceof Float) {
					// float fValue = (Float) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(fValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Double) {
					// double dValue = (Double) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(dValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Long) {
					// long longValue = (Long) value;
					// cell.setCellValue(longValue);
					// }
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
								index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					System.out.println(e);
				} catch (NoSuchMethodException e) {
					System.out.println(e);
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				} catch (IllegalAccessException e) {
					System.out.println(e);
				} catch (InvocationTargetException e) {
					System.out.println(e);
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 导出Excel
	 * 
	 * @param sheetName sheet名称
	 * @param title     标题
	 * @param values    内容
	 * @param wb        HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, ArrayList title, List<LinkedHashMap> values,ArrayList fields) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		//准备样式
        Map<String,HSSFCellStyle> styleMap = createCellStyle(wb);

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);


		// 声明列对象
		HSSFCell cell = null;

		// 创建标题
		for (int i = 0; i < title.size(); i++) {
			cell = row.createCell((short) i);
			cell.setCellValue(new HSSFRichTextString(title.get(i).toString()));
			cell.setCellStyle(styleMap.get("contentStyle"));
		}

		// 创建内容
		for (int i = 0; i < values.size(); i++) {
			row = sheet.createRow(i + 1);
			LinkedHashMap con = values.get(i);
			for (int j = 0; j < fields.size(); j++) {
				// 将内容按顺序赋给对应的列对象
				if (con.get(fields.get(j)) != null) {
					String nr = con.get(fields.get(j)).toString();
					row.createCell((short) j).setCellValue(new HSSFRichTextString(nr));
					row.getCell((short) j).setCellStyle(styleMap.get("contentStyle"));
				}
			}
		}
		return wb;
	}

	public static void main(String[] args) {

		String docPath = "E:\\channel-system\\target\\channel-system\\docs";
		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
		String docName = "exportExcel" + "保健" + System.currentTimeMillis() + ".xls";
		List dataset = new ArrayList();
		// dataset.add(new Student(10000001, "张三", 20, true, new Date()));
		// dataset.add(new Student(20000002, "李四", 24, false, new Date()));
		// dataset.add(new Student(30000003, "王五", 22, true, new Date()));
		try {
			OutputStream out = new FileOutputStream(docPath + FILE_SEPARATOR + docName);
			ExportExcel.exportExcel(headers, dataset, out);
			out.close();
			// JOptionPane.showMessageDialog(null, "导出成功!");
			// System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Map<String, HSSFCellStyle> createCellStyle(HSSFWorkbook workbook) {

		Map<String, HSSFCellStyle> styleMap = new HashMap<String, HSSFCellStyle>();

		// 标题格式

		HSSFCellStyle titleStyle = workbook.createCellStyle();

		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		setCellBorderStyle(titleStyle);

		setBoldCellFontStyle(workbook, titleStyle, (short) 10, HSSFColor.RED.index);

		setBackgroundStyle(titleStyle, HSSFColor.SKY_BLUE.index);

		styleMap.put("titleStyle", titleStyle);

		// 内容样式

		HSSFCellStyle contentStyle = workbook.createCellStyle();

		setCellBorderStyle(contentStyle);

		setSimpleCellFontStyle(workbook, contentStyle, (short) 10, HSSFColor.BLACK.index);

		styleMap.put("contentStyle", contentStyle);

		// 总计样式

		HSSFCellStyle totalStyle = workbook.createCellStyle();

		setCellBorderStyle(totalStyle);

		setBoldCellFontStyle(workbook, totalStyle, (short) 9, HSSFColor.BLACK.index);

		setBackgroundStyle(totalStyle, HSSFColor.YELLOW.index);

		styleMap.put("totalStyle", totalStyle);

		return styleMap;

	}

	private static HSSFCellStyle setCellBorderStyle(HSSFCellStyle cellStyle) {

		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		return cellStyle;

	}

	private static HSSFCellStyle setSimpleCellFontStyle(HSSFWorkbook workbook, HSSFCellStyle cellStyle, short size,
			short color) {

		HSSFFont font = workbook.createFont();

		font.setFontHeightInPoints(size);

		font.setColor(color);

		cellStyle.setFont(font);

		return cellStyle;

	}

	private static HSSFCellStyle setBoldCellFontStyle(HSSFWorkbook workbook, HSSFCellStyle cellStyle, short size,
			short color) {

		HSSFFont font = workbook.createFont();

		font.setBoldweight(font.BOLDWEIGHT_BOLD);

		font.setFontHeightInPoints(size);

		font.setColor(color);

		cellStyle.setFont(font);

		return cellStyle;

	}

	private static HSSFCellStyle setBackgroundStyle(HSSFCellStyle cellStyle, short color) {

		cellStyle.setFillForegroundColor(color);

		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return cellStyle;

	}
	
	   /* 根据excel单元格类型获取excel单元格值
		 * @param cell
		 * @return
		 */
		public static String getCellValue(HSSFCell cell) {
			String cellValue = "";  
			if(cell != null)
			{
		        DecimalFormat df = new DecimalFormat("#");  
		        switch (cell.getCellType()){
		        case HSSFCell.CELL_TYPE_STRING:  
		            cellValue = cell.getRichStringCellValue().getString().trim();  
		            break;  
		        case HSSFCell.CELL_TYPE_NUMERIC:  
		        	double a = cell.getNumericCellValue();
		            cellValue = df.format(cell.getNumericCellValue()).toString();  
		            double b = Double.valueOf(cellValue);
		            if(a != b) 
		            {
		            	cellValue = String.valueOf(a);
		            }
		            break;  
		        case HSSFCell.CELL_TYPE_BOOLEAN:  
		            cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
		            break;  
		        case HSSFCell.CELL_TYPE_FORMULA:  
		            cellValue = cell.getCellFormula();  
		            break;  
		        default:  
		            cellValue = "";  
		        } 
			}
			return cellValue;
		}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 */
	@SuppressWarnings("unchecked")
	public static void exportExcelString(String title, String[] headers,Collection dataset, OutputStream out) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("cht");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		int index = 0;
		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLUE.index);
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object t =  it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				//      System.out.println("fieldName:"+fieldName);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = value==null?"":value.toString();
					//        System.out.println("textValue:"+textValue);
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						HSSFRichTextString richString = new HSSFRichTextString(textValue);
//                        richString.applyFont(font3);
						cell.setCellValue(richString);
					}
				} catch (SecurityException e) {
					System.out.println(e);
				} catch (NoSuchMethodException e) {
					System.out.println(e);
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				} catch (IllegalAccessException e) {
					System.out.println(e);
				} catch (InvocationTargetException e) {
					System.out.println(e);
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
