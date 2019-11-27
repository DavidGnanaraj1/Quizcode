package com.atmecs.assessmenttask.testscripts;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.assessmenttask.pageactions.PageActions;

import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.testbase.TestBase;
import com.atmecs.assessmenttask.utils.ExcelFileReader;
import com.atmecs.assessmenttask.utils.ExcelFileWriter;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;
import com.atmecs.helpermethods.PhptravelsHelpers;

public class PolicyDateEvaluator extends TestBase {
	
	Properties properties;
	PageActions pageactions;
	Properties testdata;
	LocatorSeparator separatelocator;
	LogReport log;
	AssertionHelpers assertionhelpers;
	PhptravelsHelpers phptravelshelpers;
	ExcelFileReader excelreader;
	ExcelFileWriter excelwriter;
	
			 
	 
	 
	public PolicyDateEvaluator() throws IOException { 
		
	    pageactions = new PageActions();
	    properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	    testdata=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	    separatelocator = new LocatorSeparator();
	    assertionhelpers= new AssertionHelpers();
	    phptravelshelpers= new PhptravelsHelpers();
	    log= new LogReport();
	    excelreader=new ExcelFileReader(FilePath.TESTDATA_FILE);
	    excelwriter= new ExcelFileWriter();
	}
	
	//@Test
	public void policyDateEvaluator() throws IOException {
		
		String sheetname=testdata.getProperty("expdata.sheetname");
		int arraylength=excelreader.countingTheNumberOfRows(sheetname);
		
	    int initialexcelfilerownumber=Integer.parseInt(testdata.getProperty("expdata.initialexcelfilerownumber"));
		LocalDate localdate= LocalDate.now();
		
		String inputdetailsfordatemethodstring[]=new String[ arraylength];
		int inputdetailsfordatemethod[]=new int[ arraylength];
		inputdetailsfordatemethodstring=excelreader.gettingValuesOfAColumn(sheetname,testdata.getProperty("expdata.columnname"));
		
		for(int index=0;index<arraylength;index++) {
		inputdetailsfordatemethod[index]=Integer.parseInt(inputdetailsfordatemethodstring[index]);
		
		}
		LocalDate onemonthearlier=localdate.minusMonths(inputdetailsfordatemethod[initialexcelfilerownumber-1]);
	    
		LocalDate tendaysafter=localdate.plusDays(inputdetailsfordatemethod[initialexcelfilerownumber]);

		LocalDate onemonthafterwards = localdate.plusMonths(inputdetailsfordatemethod[initialexcelfilerownumber+1]);
	   
		String excelwritinginput[]= new String[arraylength];

		int arrayinputindex=0;
		excelwritinginput[arrayinputindex]=onemonthearlier.toString();
		excelwritinginput[arrayinputindex+1]=tendaysafter.toString();
		excelwritinginput[arrayinputindex+2]=onemonthafterwards.toString();
		
		excelwriter.excelFileWriter(driver,excelwritinginput,2,1,4);
		
	}
}
