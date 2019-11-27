package com.atmecs.assessmenttask.utils;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.atmecs.assessmenttask.constants.FilePath;
  
/**
 * This class is used for running the testscript multiple times 
 * for data in each row starting from the rowindex 1 provided in the testdata file
 */
public class TestDataProvider {
	String path;
	ExcelFileReader readExcel;
	Properties expectedData;
	
    
    
    public  TestDataProvider() throws IOException {
    	expectedData=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
    
    
	@DataProvider(name = "Dataprovider")
	public Object[][] providingTestData() throws IOException {
		readExcel = new ExcelFileReader(FilePath.TESTDATA_FILE);
		String sheetName=expectedData.getProperty("expdata.sheetname");
		int sheetIndex = 0;
		int rowNumber = 1;
		int rowCount = readExcel.countingTheNumberOfRows(sheetName);
		System.out.println(rowCount);
		int columnCount = readExcel.countingTheNumberOfColumns(sheetName, rowNumber);
		System.out.println(columnCount);
		Object[][] excelFileData = new Object[rowCount][columnCount];

		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

				  excelFileData[rowIndex][columnIndex] = readExcel.gettingExcelFileData(sheetIndex, rowIndex + 1,
						columnIndex+1);
				  System.out.println(excelFileData[rowIndex][columnIndex]);
		  }
		}
		return excelFileData;
	}
}
