package com.kblive.usersystem.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * title: DeleteSheet
 * projectName kbLive
 * description: 删除sheet
 * author 2671242147@qq.com
 * date 2019-06-29 14:59
 ***/
public class DeleteSheet {

        /**
         * 删除指定的Sheet
         * @param targetFile  目标文件
         * @param sheetName   Sheet名称
         */
        public static void deleteSheet(String targetFile,String sheetName) {
            try {
                FileInputStream fis = new FileInputStream(targetFile);
                HSSFWorkbook wb = new HSSFWorkbook(fis);
                fileWrite(targetFile, wb);
                //删除Sheet
                wb.removeSheetAt(wb.getSheetIndex(sheetName));
                fileWrite(targetFile, wb);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 写隐藏/删除后的Excel文件
         * @param targetFile  目标文件
         * @param wb          Excel对象
         * @throws Exception
         */
        public static void fileWrite(String targetFile,HSSFWorkbook wb) throws Exception{
            FileOutputStream fileOut = new FileOutputStream(targetFile);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }

}
