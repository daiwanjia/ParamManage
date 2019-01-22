package com.dcits.paramManage.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelUtil {

	/**
	 * Excel单元格cell按格式读取
	 * @param cell
	 * @return cellValue
	 */
	public static String getCellFormatValue(Cell cell) {
		String cellValue=null;
		if(cell!=null){
			//判断cell类型 
			switch (cell.getCellTypeEnum()) {
			case NUMERIC://数字类型 获取原值转String
				cellValue=new DecimalFormat("0").format(cell.getNumericCellValue()).trim();
				break;
			case FORMULA://日期类型
				if(DateUtil.isCellDateFormatted(cell)){
					cellValue=new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
				}else{//若为数字
					cellValue=new DecimalFormat("0").format(cell.getNumericCellValue()).trim();
				}
				break;
			case STRING:	
				cellValue=cell.getRichStringCellValue().getString().trim();
				break;
			default:
				cellValue="";
			}
		}else{
			cellValue="";
		}
		
		return cellValue;
	}
}
