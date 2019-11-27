package com.atmecs.assessmenttask.testscripts;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.assessmenttask.pageactions.ExplicitWaitHelpers;
import com.atmecs.assessmenttask.pageactions.PageActions;
import com.atmecs.assessmenttask.testbase.TestBase;
import com.atmecs.assessmenttask.utils.ExcelFileReader;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;
import com.atmecs.assessmenttask.utils.TestDataProvider;
import com.atmecs.helpermethods.HeatClinicHelpers;
 
public class HeatClinicAutomation extends TestBase{
     Properties properties;
     Properties expecteddata;
	 PageActions pageactions = new PageActions();
     ExplicitWaitHelpers explicitwait = new ExplicitWaitHelpers();
     LocatorSeparator separatelocator = new LocatorSeparator();
     AssertionHelpers asserttionhelpers= new AssertionHelpers();
     HeatClinicHelpers heatclinichelpers = new HeatClinicHelpers();
  
     public HeatClinicAutomation() throws IOException {
		properties=new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		expecteddata= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
    
      
	@Test  (dataProvider="Dataprovider",dataProviderClass=TestDataProvider.class)
	public void shoppingAutomation(String fromCity,String toCity) throws InterruptedException, IOException {
     	System.out.println(fromCity+" "+toCity);
	  heatclinichelpers.checkingFooterLinkText(driver);
	  heatclinichelpers.addingTshirtToCart(driver);
	  heatclinichelpers.cartDetailsVerifcation(driver);	
     	
     	

	}
	@AfterTest
	public void closingTheBrowser() {
		driver.close();
	}
}
