package com.unclezs.samples.poi.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

/**
 * @author blog.unclezs.com
 * @since 2020/12/25 17:59
 */
public class ExcelTest {
  public static void main(String[] args) throws IOException, InvalidFormatException {
    XSSFWorkbook workbook = SimpleExcelUtils.getWorkbook("/Users/zhanghongguo/Documents/support.xlsx");
    XSSFSheet sheet = workbook.getSheet("支持绑定项目");
    int maxRowNum = sheet.getLastRowNum();
    for (int rowNum = 2; rowNum <= maxRowNum; rowNum++) {
      List<String> rowInfo = SimpleExcelUtils.getOneLineFromToSheet(sheet, rowNum);
      System.out.println(rowInfo);
    }
  }
}
