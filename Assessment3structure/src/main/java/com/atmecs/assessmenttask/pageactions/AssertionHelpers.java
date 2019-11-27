package com.atmecs.assessmenttask.pageactions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;



 
/**
 * This class has methods for various assertion like asserting two string text,
 * pagetitles,asserting given array with getting multiple text values 
 */
public class AssertionHelpers {
	LocatorSeparator separatinglocators=new LocatorSeparator();
	Properties properties;
	PageActions pageactions= new PageActions();
	Properties testdatapropertiesfile;
	LogReport log = new LogReport();
	
	/**
	 * This constructor loads the properties files
	 */
	public AssertionHelpers() throws IOException {
		properties= new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testdatapropertiesfile= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
	

	/**
	 * This method gets the actual string text from webpage and get the expected string text from the properties
	 *  assert whether actual string texts and expected string texts are equal or not
	 */
	public void assertingStringTexts(WebDriver driver,String actualElementLocator,String expectedElementLocator) throws IOException {
		
		WebElement actualTextElement =driver.findElement(separatinglocators.separatingLocators(properties.getProperty(actualElementLocator)));
	    String actualTextValue=actualTextElement.getText();
	    String expectedTextValue=testdatapropertiesfile.getProperty(expectedElementLocator);
		Assert.assertEquals(actualTextValue, expectedTextValue);
		System.out.println();
}
	
	/**
	 * This method gets the actual pagetitle from webpage and get the expected page title from properties file
	 *  assert whether actual pagetitle and expected pagetitle are equal or not
	 */
	public  void assertingPageTitle(WebDriver driver,String expectedTitleLocator) {

		String actualPageTitle=driver.getTitle();
		String expectedPageTitle=properties.getProperty(expectedTitleLocator);
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}
	
	/**
	 * This method is used for checking the actual multipe text from Webpage and 
	 * checks with the expected textarray provided  
	 */
	public void assertingMultipleValues(WebDriver driver,String expectedDataArray[],int numberOfValidations,String actualCommonElementLocator) {
		
		String returnStatement = null;
		
		for(int index=0;index<numberOfValidations;index++) {
	    
		List<WebElement> actualdata=driver.findElements(separatinglocators.separatingLocators(properties.getProperty(actualCommonElementLocator)));
	    String actualdataarray[]= new String [numberOfValidations];
	    actualdataarray[index]=actualdata.get(index).getText();
		Assert.assertEquals(actualdataarray[index], expectedDataArray[index]);
	    log.info("Validated the detail:"+expectedDataArray[index]);
	}
  }
}
