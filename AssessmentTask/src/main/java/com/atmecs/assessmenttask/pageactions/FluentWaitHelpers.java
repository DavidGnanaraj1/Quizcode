
package com.atmecs.assessmenttask.pageactions;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import freemarker.template.utility.Constants;


public class FluentWaitHelpers {
	
	
	
	public WebElement fluentWait(WebDriver driver,String exceptiontobeignored) {
	
	FluentWait<WebDriver> fluentWait;
	String exceptionType=exceptiontobeignored.toLowerCase();
	
	switch(exceptionType) {
	
	case "staleelementexception":
	fluentWait = new FluentWait<WebDriver>(driver)
			.withTimeout(30,TimeUnit.SECONDS)
			.pollingEvery(5,TimeUnit.SECONDS)
			.ignoring(StaleElementReferenceException.class);
	    break;
	case "elementnotinteractableexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Constants.mxaximumFluentWait))
				.pollingEvery(Duration.ofSeconds(Constants.pollingWait))
				.ignoring(ElementNotInteractableException.class);
		break;
	case "elementnotselectableexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Constants.mxaximumFluentWait))
				.pollingEvery(Duration.ofSeconds(Constants.pollingWait))
				.ignoring(ElementNotSelectableException.class);
		break;
	case "elementnotvisibleexception":
		fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Constants.mxaximumFluentWait))
				.pollingEvery(Duration.ofSeconds(Constants.pollingWait))
				.ignoring(ElementNotVisibleException.class);
		break;
		
	default :
	fluentWait = new FluentWait<WebDriver>(driver)
	.withTimeout(Duration.ofSeconds(Constants.mxaximumFluentWait))
	.pollingEvery(Duration.ofSeconds(Constants.pollingWait))
	.ignoring(NoSuchElementException.class);
	break;
	}
	}
}
	public void sleepWaitCommand(timeinmilliseconds) {
		Thread.sleep(timeinmilliseconds);
	}
}
