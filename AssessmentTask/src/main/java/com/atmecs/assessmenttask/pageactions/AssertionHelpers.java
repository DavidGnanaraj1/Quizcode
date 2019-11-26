package com.atmecs.assessmenttask.pageactions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

/**
 * This class has methods for various assertion like asserting two string text,
 * verifying the expected page title with the actual page title
 */


/**
 * This method is used for checking the actual 
 */
public class AssertionHelpers {
	LocatorSeparator separatinglocators;
	Properties properties;
	PageActions pageactions;
	Properties testdatapropertiesfile;
	
	/**
	 * This constructor loads the locators.properties file
	 */
	public AssertionHelpers() throws IOException {
		
		separatinglocators=new LocatorSeparator();
		properties= new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		pageactions= new PageActions();
		testdatapropertiesfile= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
	

	/**
	 * This method gets the actual string text from webpage and get the expected string text from the properties
	 *  assert whether actual string texts and expected string texts are equal or not
	 */
	public void assertingStringTexts(WebDriver driver,String actualelementlocator,String expectedelementlocator) throws IOException {
		
		WebElement actualtextelement =driver.findElement(separatinglocators.separatingLocators(properties.getProperty(actualelementlocator)));
	    String actualtextvalue=actualtextelement.getText();
	    String expectedtextvalue=testdatapropertiesfile.getProperty(expectedelementlocator);
	    
	    Assert.assertEquals(actualtextvalue, expectedtextvalue);
	    
}
	
	/**
	 * This method gets the actual pagetitle from webpage and get the expected page title from properties file
	 *  assert whether actual pagetitle and expected pagetitle are equal or not
	 */
	public  void assertingPageTitle(WebDriver driver,String expectedtitlelocator) {

		String actualpagetitle=driver.getTitle();
		String expectedpagetitle=properties.getProperty(expectedtitlelocator);
		Assert.assertEquals(actualpagetitle, expectedpagetitle);
	}
	
	
	public String assertingMultipleValues(WebDriver driver,String expecteddataarray[],int iterationnumber,int numberofproducts,String actualcommonelementlocator) {
		String returnstatement = null;
		for(int index=0;index<iterationnumber;index++) {
	    
		List<WebElement> actualdata=driver.findElements(separatinglocators.separatingLocators(properties.getProperty(actualcommonelementlocator)));
	    String actualdataarray[]= new String [numberofproducts];
	    actualdataarray[index]=actualdata.get(index).getText();
	    try {
			Assert.assertEquals(actualdataarray[index], expecteddataarray[index]);
			return returnstatement="Validation completed for "+expecteddataarray[index];
		} catch (Exception e) {
			e.printStackTrace();
			return returnstatement="Assertion failed" + actualdataarray[index] + "," +expecteddataarray[index] + " not matching";

		}
		}
	        return returnstatement;
	    
		    	
   	}
	}
   
	


