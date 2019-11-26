package com.atmecs.assessmenttask.pageactions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

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
	
	public PhptravelsHelpers() throws IOException {
		
	    pageactions = new PageActions();
	    properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	    testdata=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	    pageactionsscrolldown= new PageActionsScrollDown();
	    separatelocator= new LocatorSeparator();
	    log= new LogReport();
	    assertionhelpers= new AssertionHelpers();
	}
	
	public void invoiceDetailsValidation(WebDriver driver,int randomnumber) throws InterruptedException
	{
	   
   	    String myaccountpageinvoicedetails[]=new String[numberofdetails];
   	 String myaccountpageinvoicedetailsarray[]=new String[numberofdetails];
		String myaccountpagebookingidlocatorfirstpart=properties.getProperty("loc.myaccountpagebookingidlocatorfirstpart");
		String myaccountpagebookingidlocatorsecondpart=Integer.toString(randomnumber);
		String myaccountpagebookingidlocatorthirdpart=properties.getProperty("loc.myaccountpagebookingidlocatorthirdpart");
		String myaccountpagebookingidtextfulltext =myaccountpagebookingidlocatorfirstpart+myaccountpagebookingidlocatorsecondpart+myaccountpagebookingidlocatorthirdpart;
	
		pageactionsscrolldown.pageScrollDown(driver,"500");
	 
		String myaccountpageinvoicecompletedata=driver.findElement(By.xpath("(//span[@class='grey'])["+randomnumber+"]")).getText();
		myaccountpageinvoicedetailsarray=myaccountpageinvoicecompletedata.split("\n");
 for(int arrayindex=0;arrayindex<numberofdetails-1;arrayindex++) {
	    for(int index=0;index<numberofdetails-1;index++) {
	    	
	    	myaccountpageinvoicedetails[index]=myaccountpageinvoicedetailsarray[arrayindex];
	    	
	    }
}
	    
	    
		WebElement invoicebutton=driver.findElement(By.xpath((properties.getProperty("loc.invoicebuttonn")+"####"+"]/a").replaceAll("####",randomnumber+"")));
		explicitwait.explicitwaitForElementToBeClickable(driver,invoicebutton);
		Thread.sleep(3000);
		invoicebutton.click();
        
		
		
	    String invoicepagedetails[]=new String[numberofdetails];
	    
	    String invoicepagestatustext=driver.findElement(By.xpath(properties.getProperty("loc.invoicepagestatus"))).getText();
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
	
	public void gettingMultipleText(WebDriver driver) {
		
		List<WebElement> listoftexts = driver.findElements(separatelocator.separatingLocators(properties.getProperty("expdata")));
		
	}
	public String splittingStringText(WebDriver driver,String actualmultipletextlocator,int arrayelementneeded) {
		String multipletextarray[]= null;
		
		String multipletext=driver.findElement(separatelocator.separatingLocators(properties.getProperty(actualmultipletextlocator))).getText();
		multipletextarray=multipletext.split("\\s");
		return multipletextarray[arrayelementneeded-1];
		
		
	}
	}
	

