package com.atmecs.assessmenttask.pageactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageActionsScrollDown {
	public void pageScrollDown(WebDriver driver,String scrollvalue)
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,"+scrollvalue+")");
	}
	
	public  void pageScrollDownTillElementVisible(WebDriver driver,WebElement elementtobeviewed) 
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("arguments[0].scrollIntoView();", elementtobeviewed);
	}

	public  void scrollToBottom(WebDriver driver)
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	public  void pageScrollUp(WebDriver driver) 
	{
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
		javaScriptExecutor.executeScript("window.scrollBy(0,-1700)");
	}

}
