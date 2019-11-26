package com.atmecs.assessmenttask.utils;

import org.openqa.selenium.By;

/**
 * This class is to separate the locators value from the locator type 
 * and return the By object
 */


public class LocatorSeparator {
	
	
	

	
	public  By separatingLocators(String locatorwithtype) {
		String locators[] = locatorwithtype.split(",");
		String locatortype = locators[0];
		String locatorvalue = locators[1];
		By by = null;
		
		switch (locators[0]) {
		case "XPATH":
			by = By.xpath(locatorvalue);
			break;
		case "CSS":
			by = By.cssSelector(locatorvalue);
			break;
		case "ID":
			by = By.id(locatorvalue);
			break;
	    case "PARTIALLINKTEXT": 
	          by= By.partialLinkText(locatorvalue);
	            break; 
	    case "LINKTEXT": 
	          by=By.linkText(locatorvalue);
	            break; 
	    case "NAME": 
	             by=By.name(locatorvalue);
	            break; 
	    case "CLASS": 
	           by=By.className(locatorvalue);
	            break; 
		default:

			break;
		}
		return by;

	}
}
