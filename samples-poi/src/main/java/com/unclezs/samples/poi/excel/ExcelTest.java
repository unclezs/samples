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
    XSSFWorkbook workbook =
        SimpleExcelUtils.getWorkbook(
            "/Users/zhanghongguo/coder/samples/samples-poi/src/main/resources/account_import.xlsx");
    XSSFSheet sheet = workbook.getSheet("支持绑定项目");
    int maxRowNum = sheet.getLastRowNum();
    // 冻结前面两行
    SimpleExcelUtils.freezeRowAndCol(sheet, 2, 0);
    // 从第三行开始拿数据
    for (int rowNum = 2; rowNum <= maxRowNum; rowNum++) {
      List<String> rowInfo = SimpleExcelUtils.getOneLineFromToSheet(sheet, rowNum);
      System.out.println(rowInfo.get(0));
      System.out.println(rowInfo);
    }
  }
}
