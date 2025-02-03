package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ActionsHandle extends AbstractComponents {

	WebDriver driver;

	public ActionsHandle(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void hoverElements(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		Actions hover = new Actions(driver);
		hover.moveToElement(element).build().perform();
		;
	}

}
