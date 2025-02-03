package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	ChromeDriver driver;

	public ProductPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Place Order']")
	WebElement placeOrder;
	@FindBy(xpath = "//button[contains(@class,'QqFHMw vslbG+ In9uk2')]")
	WebElement addToCart;
	@FindBy(xpath = "//*[text()='Buy Now']")
	WebElement buyNow;
	@FindBy(id = "pincodeInputId")
	WebElement pincodeEditBox;
	@FindBy(css = ".N1bADF")
	WebElement wishlist;
	@FindBy(css = ".eIDgeN")
	WebElement popUp;

	public void wishList() {
		visibilityOf(wishlist);
		wishlist.click();
		// popUp.getText();
		visibilityOf(popUp);
		System.out.println(popUp.getText());
	}

	public void pincode(String pincode) throws InterruptedException {
		pincodeEditBox.sendKeys(pincode, Keys.ENTER);
		threadSleep();
	}

	public void addToCart() {
		addToCart.click();
		visibilityOf(placeOrder);
	}

	public void buyNow() {
		buyNow.click();
	}
}