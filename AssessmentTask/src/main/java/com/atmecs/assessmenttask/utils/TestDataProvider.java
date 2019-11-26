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
	ExcelFileReader readexcel;
	Properties properties;
	
    String sheetname=properties.getProperty("expdata.sheetname");
    
    public  TestDataProvider() throws IOException {
    	properties=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
    
    
	@DataProvider(name = "login")
	public Object[][] providingTestData() throws IOException {
		readexcel = new ExcelFileReader(FilePath.TESTDATA_FILE);
		int sheetindex = 0;
		int rownumber = 1;
		int rowcount = readexcel.countingTheNumberOfRows(sheetname);
		int colcount = readexcel.countingTheNumberOfColumns(sheetname, rownumber);

		Object[][] excelfiledata = new Object[rowcount][colcount];

		for (int rowindex = 0; rowindex < rowcount; rowindex++) {
			for (int columnindex = 0; columnindex < colcount; columnindex++) {

				  excelfiledata[rowindex][columnindex] = readexcel.gettingExcelFileData(sheetindex, rowindex + 1,
						columnindex);
			}
		}
		return excelfiledata;
	}
}
