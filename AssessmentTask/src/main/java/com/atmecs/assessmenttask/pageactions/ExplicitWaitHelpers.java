package com.atmecs.assessmenttask.pageactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.assessmenttask.utils.LocatorSeparator;


/**
 * This class has methods for explicit wait for different expected conditions
 * and  fluent wait for ignoring  different exceptions
 */

public class ExplicitWaitHelpers {
  
	LocatorSeparator separatelocators = new LocatorSeparator();


	/**
	 * This  method has explicit wait for the expected condition-Element to be clickable when By locator is the input
	 */
  public void explicitwaitElementToBEClickableWithInputAsByobject(WebDriver driver,By byobject) {
	   
	   WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.elementToBeClickable(byobject));
  }
	
  
  /**
   * This method ha explicit wait for the expected condition -Element to be clickable but WebElement is given as an input
   */
   public void explicitwaitForElementToBeClickable(WebDriver driver,WebElement element) {
	
	   WebDriverWait explicitwait = new WebDriverWait(driver, 30);
	   explicitwait.until(ExpectedConditions.elementToBeClickable(element));
  }
   
  
   /**
    * This method ha explicit wait for the expected condition -Checking the page title but PageTitle is given as an input
    */ 
    public void explicitwaitForCheckingTheTitle(WebDriver driver,String pagetitle) {
	  
    	WebDriverWait explicitwait = new WebDriverWait(driver, 30);
	    explicitwait.until(ExpectedConditions.titleIs(pagetitle));
  }
    
   
    /**
     * This method checks for the frame availablity and switch to that frame
     */
     public void explicitwaitCheckForFrameAvailablityAndSwitchToIt(WebDriver driver,String framelocator) {
	 
    	 WebDriverWait explicitwait = new WebDriverWait(driver, 30);
	     explicitwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
  }
  


	
	
	
	
}
