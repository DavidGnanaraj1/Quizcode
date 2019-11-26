package com.atmecs.assessmenttask.testscripts;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.pageactions.AssertionHelpers;
import com.atmecs.assessmenttask.pageactions.PageActions;

import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.testbase.TestBase;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;
import com.atmecs.helpermethods.PhptravelsHelpers;

public class PhpTravelsAutomation extends TestBase {

	Properties properties;
	PageActions pageactions;
	Properties testdata;
	LocatorSeparator separatelocator;
	LogReport log;
	AssertionHelpers assertionhelpers;
	PhptravelsHelpers phptravelshelpers;

	public PhpTravelsAutomation() throws IOException {

		pageactions = new PageActions();
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testdata = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		separatelocator = new LocatorSeparator();
		assertionhelpers = new AssertionHelpers();
		phptravelshelpers = new PhptravelsHelpers();
		log = new LogReport();
	}

	@Test
	public void bookingAutomation() throws IOException, InterruptedException {
	
		pageactions.sendKeys(driver, properties.getProperty("loc.usernameinputbox"),
				testdata.getProperty("expdata.username"));
		pageactions.sendKeys(driver, properties.getProperty("loc.password"), testdata.getProperty("expdata.password"));
		pageactions.clickingTheElement(driver, properties.getProperty("loc.userloginbutton"));
		Thread.sleep(3000);
		WebElement bookingstab = driver
				.findElement(separatelocator.separatingLocators(properties.getProperty("loc.bookingstab")));
		WebElement invoiceelement = driver
				.findElement(separatelocator.separatingLocators(properties.getProperty("loc.invoiceelement")));

		if (invoiceelement.isDisplayed()) {
			log.info("Bookings tab is selected");
		} else {
			log.info("Bookings tabs is not selected by default");
		}
		assertionhelpers.assertingStringTexts(driver, "loc.hiusertext", "expdata.hiusertext");
	
         
//		Random random = new Random(); 
//		int randomNumber=random.nextInt(25);
		int randomNumber=5;
		phptravelshelpers.invoiceDetailsValidation(driver, randomNumber);
	}

//	@AfterTest
//	public void closingTheBrowser() {
//		driver.quit();
//	}
}
