package com.atmecs.assessmenttask.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.assessmenttask.constants.FilePath;

/**
 * This class is used to read the excel file's particular cell data and get the
 * row count and column count of the excel file
 */


public class ExcelFileReader {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	File file;
	String path;

	/**
	 * This constructor will get the filepath of the testdata file and create the
	 * Xssfworkbook object
	 */
	public ExcelFileReader(String filepath) throws IOException {

		File file = new File(filepath);
		FileInputStream fileinput = new FileInputStream(file);
		workbook = new XSSFWorkbook(fileinput);
	}

	/**
	 * This method will return the value of the particular cell from the excel file
	 */
	public String gettingExcelFileCellValue(int index, int rownumber, int cellnumber) {

		sheet = workbook.getSheetAt(index);
		String data = sheet.getRow(rownumber).getCell(cellnumber).getStringCellValue();
		return data;
	}

	/**
	 * This method will return the total number of rows in an excel file
	 */
	public int countingTheNumberOfRows(String sheetname) {

		int rowcount = workbook.getSheet(sheetname).getLastRowNum();
		return rowcount;
	}

	/**
	 * This method will return the total number of column in an excel file
	 */
	public int countingTheNumberOfColumns(String sheetname, int rownumber) {

		int columncount = workbook.getSheet(sheetname).getRow(rownumber).getLastCellNum();
		return columncount;
	}
    
	
	/**
	 * This method is used by testdata provider to provide each row value to the maximum column limit
	 */
	public String gettingExcelFileData(int sheetindex, int rownumber, int columnnumber) {

		int index;
		String[] array = new String[30];
		for (index = rownumber; index < array.length; index++) {
			array[index - 1] = gettingExcelFileCellValue(sheetindex, index, columnnumber);
		}
		return array[index - 1];
	}

	/**
	 * This method is used to get the values of the complete column by using the sheetname,columnname as an input
	 */
	public String[] gettingValuesOfAColumn(String sheetname, String columnname) {
		int columnnumber = 0;
		int rownumber = 0;
		String excelcolumndata[] = new String[countingTheNumberOfRows(sheetname)];
		for (int cellindex = 1; cellindex <countingTheNumberOfColumns(sheetname, rownumber); cellindex++) {
			if (workbook.getSheet(sheetname).getRow(rownumber).getCell(cellindex).getStringCellValue().trim()
					.equals(columnname)) {
				columnnumber = cellindex;
			}

			for (int rowindex = 1; rowindex <=countingTheNumberOfRows(sheetname); rowindex++) {

				excelcolumndata[rowindex - 1] = workbook.getSheet(sheetname).getRow(rowindex).getCell(columnnumber)
						.getStringCellValue();
			}
		}
		return excelcolumndata;
	}
    
	
	public static void main(String[] args) throws IOException {

		ExcelFileReader readexcelfile = new ExcelFileReader(FilePath.TESTDATA_FILE);

	}
}
