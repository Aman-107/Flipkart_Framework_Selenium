package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;
import Utilities.ActionsHandle;

public class Headers extends AbstractComponents {

	ChromeDriver driver;
	WishListPage wishListPage;
	ActionsHandle actionsHandle;
	
	public Headers(ChromeDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		wishListPage = new WishListPage(driver);
		actionsHandle = new ActionsHandle(driver);
	}
	
	@FindBy(xpath="//*[text()='Place Order']")
	WebElement placeOrder;
	@FindBy(linkText="Cart")
	WebElement cartHeader;
	@FindBy(xpath="(//div[@class='tP+nZg _2E9UgX'])[1]")
	WebElement userHover;
	@FindBy(xpath="//*[text()='Orders']")
	WebElement orderHeader;
	@FindBy(xpath="//div[text()='Wishlist']")
	WebElement wishListHeader;
	@FindBy(xpath="//div[text()='My Profile']")
	WebElement myProfile;
	

	public void cartPage() {
		cartHeader.click();
		visibilityOf(placeOrder);
	}
	
	public void wishList(String pdts) throws InterruptedException {       
		actionsHandle.hoverElements(userHover);
		wishListHeader.click();
		wishListPage.wishListItemstoCart(pdts);		
	}
  
	public void myOrders() throws InterruptedException {
		actionsHandle.hoverElements(userHover);
		orderHeader.click();
	} 

	public void myProfile() throws InterruptedException {
		actionsHandle.hoverElements(userHover);
		myProfile.click();
	}


}
