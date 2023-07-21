package com.cyrj.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class ImportExcelUtil {

    public static boolean isRowEmpty(HSSFRow row) {

        for(int i = row.getFirstCellNum();i < row.getLastCellNum();i++){
            HSSFCell cell = row.getCell(i);
            if(null!=cell && cell.getCellType()!=HSSFCell.CELL_TYPE_BLANK){
                return false;
            }
        }

        return true;
    }
}
