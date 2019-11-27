package com.atmecs.helpermethods;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.assessmenttask.pageactions.ExplicitWaitHelpers;
import com.atmecs.assessmenttask.pageactions.PageActions;
import com.atmecs.assessmenttask.pageactions.PageActionsScrollDown;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;
 
public class HeatClinicHelpers {
	 Properties properties;
     Properties expecteddata;
	 PageActions pageactions = new PageActions();
     ExplicitWaitHelpers explicitwait = new ExplicitWaitHelpers();
     LocatorSeparator separatelocator = new LocatorSeparator();
     AssertionHelpers asserttionhelpers= new AssertionHelpers();
     PageActionsScrollDown pageActionsScrollDown= new PageActionsScrollDown();
     
    public HeatClinicHelpers() throws IOException {
		properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		expecteddata= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
     
     
	
	public void checkingFooterLinkText(WebDriver driver) throws InterruptedException {
		int numberOfFooters=6;
		String actualpagetitle[]=new String[numberOfFooters];
		String expectedPageTitle[]= new String[numberOfFooters];
		
		for(int footerIndex=1;footerIndex<numberOfFooters;footerIndex++) {
		
		List<WebElement>footerLinkList=driver.findElements(separatelocator.separatingLocators(properties.getProperty("loc.footers")));
		footerLinkList.get(footerIndex).click();
		
		Thread.sleep(3000);
		actualpagetitle[footerIndex]=driver.getTitle();
	    String expectedallpagetitles=expecteddata.getProperty("expdata.title");
		expectedPageTitle=expectedallpagetitles.split(",");
	   
		Assert.assertEquals(actualpagetitle[footerIndex], expectedPageTitle[footerIndex]);
		
		}
	}
public void addingTshirtToCart(WebDriver driver) throws InterruptedException {
	
	WebElement merchanidise=driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.merchandiselinktext")));
	
	
	pageactions.moveToAnElement(driver,  merchanidise);
    pageactions.clickingTheElement(driver, properties.getProperty("loc.mens"));
	String actualtextvalue=pageactions.gettingTextValue(driver, properties.getProperty("loc.menstext"));
	String expectedtextvalue=expecteddata.getProperty("expdata.menstext");
	Assert.assertEquals(actualtextvalue, expectedtextvalue);
    
	pageactions.clickingTheElement(driver, properties.getProperty("loc.addtocart"));
	pageactions.clickingTheElement(driver, properties.getProperty("loc.colourofshirt"));
	pageactions.clickingTheElement(driver, properties.getProperty("loc.shirtsize"));
    
	pageactions.sendKeys(driver, properties.getProperty("loc.personizedname"), expecteddata.getProperty("expdata.name"));
	WebElement buynowelement =driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.addtocart")));
	
	WebElement shirtColour=driver.findElement(separatelocator.separatingLocators(properties.getProperty("loc.colourofshirt")));
    pageactions.moveToAnElement(driver, shirtColour);
	pageActionsScrollDown.pageScrollDown(driver, "500");

	Thread.sleep(3000);
	pageactions.clickingTheElement(driver, properties.getProperty("loc.buynow"));
    JavascriptExecutor js =(JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", buynowelement);
	pageActionsScrollDown.pageScrollUp(driver);
	
	pageactions.clickingTheElement(driver, properties.getProperty("loc.viewcart"));
	
	
	
}
	
public void cartDetailsVerifcation(WebDriver driver) throws IOException {
	
	int numberOfDetails=5;
	String expectedcartdetails[]= new String[numberOfDetails];
	String allCartDetails=expecteddata.getProperty("expdata.cartdetails");
	expectedcartdetails=allCartDetails.split(",");
	
    asserttionhelpers.assertingMultipleValues(driver, expectedcartdetails, 3, "loc.allcartdetails");
    System.out.println("cart details asssertion done");
 
    asserttionhelpers.assertingStringTexts(driver,"loc.unitprice", "expdata.unitprice");
    asserttionhelpers.assertingStringTexts(driver, "loc.shirtname", "expdata.shirtname");
    asserttionhelpers.assertingStringTexts(driver,"loc.totalprice", "expdata.unitprice");
    System.out.println("All cart details validated");
    
    
}
	
}
