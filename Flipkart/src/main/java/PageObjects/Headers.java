package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class Headers extends AbstractComponents {
	
	ChromeDriver driver;
	
	@FindBy(linkText="Cart")
	WebElement cartHeader;
	@FindBy(xpath="//*[text()='Place Order']")
    WebElement placeOrder;

	public Headers(ChromeDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void cartPage() {
		cartHeader.click();
		visibilityOf(placeOrder);
	}
}
