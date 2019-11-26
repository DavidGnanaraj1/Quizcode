package com.atmecs.helpermethods;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.assessmenttask.pageactions.ExplicitWaitHelpers;
import com.atmecs.assessmenttask.pageactions.PageActions;
import com.atmecs.assessmenttask.pageactions.PageActionsScrollDown;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;
import com.atmecs.helpermethods.DateHandlingHelpers;

public class PhptravelsHelpers {
	Properties properties;
	PageActions pageactions;
	Properties testdata;
	LocatorSeparator separatelocator;
	LogReport log;
	AssertionHelpers assertionhelpers;
	int numberofdetails =4;
	PageActionsScrollDown pageactionsscrolldown;
	ExplicitWaitHelpers explicitwait = new ExplicitWaitHelpers();
	DateHandlingHelpers datehandling=new DateHandlingHelpers();
	public PhptravelsHelpers() throws IOException {
		
	    pageactions = new PageActions();
	    properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	    testdata=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	    pageactionsscrolldown= new PageActionsScrollDown();
	    separatelocator= new LocatorSeparator();
	    log= new LogReport();
	    assertionhelpers= new AssertionHelpers();
	}
	
	public void invoiceDetailsValidation(WebDriver driver,int randomNumber) throws InterruptedException
	{
		String actualcurrentdate=driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.dateinmyaccountpage"))).getText();
	    System.out.println(actualcurrentdate);
	    String expectedcurrentdate=datehandling.getDateFormat("dd MMMM yyyy");
	    Assert.assertEquals(actualcurrentdate, expectedcurrentdate);
		 
   	    String myaccountpageinvoicedetails[]=new String[numberofdetails];
   	    String myaccountpageinvoicedetailsarray[]=new String[numberofdetails];
		String myaccountpagebookingidlocatorfirstpart=properties.getProperty("loc.myaccountpagebookingidlocatorfirstpart");
		String myaccountpagebookingidlocatorsecondpart=Integer.toString(randomNumber);
		String myaccountpagebookingidlocatorthirdpart=properties.getProperty("loc.myaccountpagebookingidlocatorthirdpart");
		String myaccountpagebookingidtextfulltext =myaccountpagebookingidlocatorfirstpart+myaccountpagebookingidlocatorsecondpart+myaccountpagebookingidlocatorthirdpart;
	
		WebElement invoicebutton=driver.findElement(By.xpath((properties.getProperty("loc.invoicebutton")+"####"+"]/a").replaceAll("####",randomNumber+"")));
		
//		pageactionsscrolldown.pageScrollDownTillElementVisible(driver, invoicebutton);
	    pageactionsscrolldown.pageScrollDown(driver, "500");
		String myaccountpageinvoicecompletedata=driver.findElement(By.xpath("(//span[@class='grey'])["+randomNumber+"]")).getText();
		myaccountpageinvoicedetailsarray=myaccountpageinvoicecompletedata.split("\n");
		
 for(int arrayindex=0;arrayindex<numberofdetails-1;arrayindex++) {
	    for(int index=0;index<numberofdetails-1;index++) {
	    	
	    myaccountpageinvoicedetails[index]=myaccountpageinvoicedetailsarray[arrayindex];
	    	
	    }
}

 
		explicitwait.explicitwaitForElementToBeClickable(driver,invoicebutton);
		Thread.sleep(3000);
		invoicebutton.click();
       
		String invoicepagedetails[]=new String[numberofdetails];

	    String invoicepagestatustext=driver.findElement(By.xpath("//div[@class='content'])[1]//h4")).getText();
	    System.out.println(invoicepagestatustext);
	    invoicepagedetails[0]=splittingStringText(driver,"loc.invoicepagebookinid",3);
	    Assert.assertEquals(invoicepagedetails[0],myaccountpageinvoicedetails[3]);
	  
	    invoicepagedetails[1]=splittingStringText(driver, "loc.invoicepagebookingnumber", 3);
	    Assert.assertEquals(invoicepagedetails[1],myaccountpageinvoicedetails[6]);
	    
	    String invoicepagestatusarray[]= null;
	    invoicepagestatusarray=invoicepagestatustext.split("\\s");		
	    String invoicepagestatus=invoicepagestatusarray[invoicepagestatusarray.length];		
	    Assert.assertEquals(myaccountpageinvoicedetails[numberofdetails], invoicepagestatus);		
	    
	   
			
	
	}
//	public String[] gettingMultipleText(WebDriver driver,String elementLocator,int noOfIteration) {
//		
//		List<WebElement> listoftexts = driver.findElements(separatelocator.separatingLocators(properties.getProperty(elementLocator)));
//		for(int noOfIteration=0;noOfIteration.)
//		
//	}
	
	   
	public String splittingStringText(WebDriver driver,String actualMultipleTextLocator,int arrayElementNeeded) {
		
		String multipleTextArray[]= null;
		String multipleText=driver.findElement(separatelocator.separatingLocators(properties.getProperty(actualMultipleTextLocator))).getText();
		multipleTextArray=multipleText.split("\\s");
		return multipleTextArray[arrayElementNeeded-1];
		
		
	}
	}
	

