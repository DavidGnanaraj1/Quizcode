package com.atmecs.assessmenttask.pageactions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.assessmenttask.constants.FilePath;
import com.atmecs.assessmenttask.reports.LogReport;
import com.atmecs.assessmenttask.utils.LocatorSeparator;
import com.atmecs.assessmenttask.utils.PropertiesFileReader;

/**
 * This class has methods to perform actions in an element like clicking the
 * element,selecting the dropdown, sending the values to textbox, to check
 * whether an element is displayed or not,moveByOffset,MouseOver
 */

/**
 * @author David.Easterraj
 *
 */

public class PageActions {
	LogReport log;
	WebDriver driver;
	LocatorSeparator separatelocator;
    ExplicitWaitHelpers waithelper;
    Properties properties;
	
    public PageActions() throws IOException {

		log = new LogReport();
		separatelocator = new LocatorSeparator();
		waithelper = new ExplicitWaitHelpers();
		properties= new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	}
	
	
	/**
	 * This method is used for clicking an element 
	 */
	public void clickingTheElement(WebDriver driver, String locatorwithtype) {
		
		WebElement elementtobeclicked = driver.findElement(separatelocator.separatingLocators(locatorwithtype));
		waithelper.explicitwaitForElementToBeClickable(driver, elementtobeclicked);
		elementtobeclicked.click();
	}
	
	
	/**
	 * This method is used for selecting the dropdown value by using the visisble text
	 */
	public void selectingTheDropdownvalueByVisibleText(WebElement element,String locatorwithtype, String value) {

		waithelper.explicitwaitForElementToBeClickable(driver, element);
		WebElement dropdownelement = driver.findElement(separatelocator.separatingLocators(locatorwithtype));
		Select select = new Select(dropdownelement);
		select.selectByVisibleText(value);
	}
	
	
	/**
	 * This method is used for sending the value to an text box
	 */
	public void sendKeys(WebDriver driver, String locatorwithtype, String value) {

		WebElement element = driver.findElement(separatelocator.separatingLocators(locatorwithtype));
		element.sendKeys(value);
	}

	
	/**
	 * This method is used for checking the given element is displayed or not
	 */
	public void isDisplayed(WebDriver driver, String locatorwithtype) {

		boolean isDisplayed = driver.findElement(separatelocator.separatingLocators(locatorwithtype)).isDisplayed();

		if (isDisplayed == true) {
			log.info("Element is displayed");
		} else {
			log.info("Element not found");
		}
	}
	
	
	/**
	 * This method is used to move the mouse and keep it over an element 
	 */
	public void moveToAnElement(WebDriver driver,WebElement element) {
	
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}


	/**
	 * This method will get the value attribute of the given attribute
	 */
	 public  String getAttributeValue(WebDriver driver,String locatorwithtype,String value)
	    {
			WebElement element = driver.findElement(separatelocator.separatingLocators(locatorwithtype));
			String text = element.getAttribute(value);
			return text;
		}
	
	
	 /**
	  * This method is used to move the mouse by the given X,Y values
	  */
	 public void moveByOffsetHelper(WebDriver driver, String locatorwithtype) {
			
		    WebElement elm = driver.findElement(separatelocator.separatingLocators(locatorwithtype));
			Point pt = elm.getLocation();
			int NumberX = pt.getX();
			int NumberY = pt.getY();
			Actions act = new Actions(driver);
			act.moveByOffset(NumberX + 1, NumberY).click().build().perform();
		}
	 
	 
	 public void  checkingPageRedirection(WebDriver driver,String targetpagedetail,String targetpagedetailtype) {
	    	
		    WebElement targetpageelement=driver.findElement(separatelocator.separatingLocators(properties.getProperty(targetpagedetailtype)));
		    if(!(targetpageelement.isDisplayed())){
		    	log.info("Not in the correct targeted page");
		    }
	    	
	    	
	    }
    public void isSelected(WebDriver driver,String locatorwithtype) {
    	 WebElement elementtobecheckedwhetherselected=driver.findElement(separatelocator.separatingLocators(locatorwithtype));
    
        if(elementtobecheckedwhetherselected.isSelected()) {
     	   log.info(elementtobecheckedwhetherselected+"is selected ");
     	   
        }else if(!(elementtobecheckedwhetherselected.isSelected())) {
     	   log.info(elementtobecheckedwhetherselected+"not selected ");
    	
    	
    }
    }
}