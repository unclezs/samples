package com.unclezs.samples.poi.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 随着 1.17 版本上线，事件设计模板校验、批量更新、批量创建角色等功能
 * 对 excel 依赖越来越多，于是实现该统一 excel 读写工具
 *
 * @author zhouenze@sensorsdata.cn
 * @since 2020-03-09
 */

public class SimpleExcelUtils {
  private static final List<String> NONLICET_EXCEL_CELL_STARTCHAR = Arrays.asList("=", "+", "-", "@");
  SimpleExcelUtils() {
  }

  public static XSSFWorkbook getWorkbook(String filepath) throws FileNotFoundException {
    return getWorkbook(new File(filepath));
  }

  public static XSSFWorkbook getWorkbook(File file) throws FileNotFoundException {
    return getWorkbook(new FileInputStream(file));
  }

  public static XSSFWorkbook getWorkbook(InputStream inputStream) {
    XSSFWorkbook workbook = null;
    try {
      workbook = new XSSFWorkbook(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return workbook;
  }

  public static List<String> getOneLineFromToSheet(XSSFSheet sheet, int lineNum) {
    XSSFRow row = sheet.getRow(lineNum);
    if (row == null) {
      return new ArrayList<>();
    }
    List<String> result = new ArrayList<>();
    int maxCell = row.getLastCellNum();
    for (int rowNum = 0; rowNum < maxCell; rowNum++) {
      Cell cellTemp = row.getCell(rowNum);
      if (cellTemp == null) {
        result.add(" ");
        continue;
      }
      cellTemp.setCellType(CellType.STRING);
      result.add(StringUtils.isEmpty(cellTemp.getStringCellValue()) ? " " : cellTemp.getStringCellValue());
    }
    return result;
  }

  public static void insertOneLineToSheet(XSSFSheet sheet, XSSFCellStyle style, int lineNum, String... strings) {
    insertOneLineToSheet(sheet, style, lineNum, Arrays.asList(strings));
  }

  public static void insertOneLineToSheet(XSSFSheet sheet, XSSFCellStyle style, int lineNum, List<String> strings) {
    XSSFRow row = sheet.createRow(lineNum);
    int i = 0;
    for (String string : strings) {
      Cell cell = row.createCell(i++);
      if (string == null) {
        cell.setCellValue(" ");
        continue;
      }
      if (style != null) {
        cell.setCellStyle(style);
      }
      if (string.length() > 0) {
        String startStr = string.substring(0, 1);
        if (NONLICET_EXCEL_CELL_STARTCHAR.contains(startStr)) {
          string = "'".concat(string);
        }
      }
      cell.setCellValue(string);
    }
  }

  public static void freezeRowAndCol(XSSFSheet sheet, int rowNum, int colNum) {
    sheet.createFreezePane(colNum, rowNum);
  }

  public static void flushExcel(Workbook workbook, String filepath) throws IOException {
    flushExcel(workbook, new File(filepath));
  }

  public static void flushExcel(Workbook workbook, File file) throws IOException {
    flushExcel(workbook, new FileOutputStream(file));
  }

  public static void flushExcel(Workbook workbook, OutputStream outputStream) throws IOException {
    workbook.write(outputStream);
    workbook.close();
  }
}
