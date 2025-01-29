package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponents;

public class Headers extends AbstractComponents {

	ChromeDriver driver;
	WishListPage wishListPage;
	
	public Headers(ChromeDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		wishListPage = new WishListPage(driver);
	}
	
	@FindBy(linkText="Cart")
	WebElement cartHeader;
	@FindBy(xpath="//*[text()='Place Order']")
	WebElement placeOrder;
	@FindBy(xpath="(//div[@class='tP+nZg _2E9UgX'])[1]")
	WebElement userHover;
	@FindBy(xpath="//div[text()='Wishlist']")
	WebElement wishList;

	public void cartPage() {
		cartHeader.click();
		visibilityOf(placeOrder);
	}
	
	public void wishList(String pdts) throws InterruptedException {       
		wishList.click();
		wishListPage.wishListItems(pdts);		
	}
  	
	public void profileHover() throws InterruptedException {
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		actions.moveToElement(userHover).build().perform();
	}

}
