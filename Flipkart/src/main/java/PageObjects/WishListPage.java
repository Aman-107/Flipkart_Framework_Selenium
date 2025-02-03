package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;
import Utilities.WindowHandler;

public class WishListPage extends AbstractComponents {
	ChromeDriver driver;
	WindowHandler windowHandler;
	ProductPage productPage;

	public WishListPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

		// Initialize dependencies after driver is assigned
		windowHandler = new WindowHandler(driver);
		productPage = new ProductPage(driver);
	}

	@FindBy(css = ".VgX0va")
	WebElement userName;

	@FindBy(css = "._16cbFU")
	private List<WebElement> wishlistItems;
	@FindBy(css = ".Mj62aK")
	private List<WebElement> deleteIcon;
	@FindBy(css = ".QqFHMw.AyekA8")
	private WebElement yesRemove;

	public void wishListItemstoCart(String pdts) throws InterruptedException {
		driver.navigate().refresh();

		// Avoid StaleElementException by re-fetching elements
		List<WebElement> items = wishlistItems;

		for (WebElement item : items) {
			// System.out.println(item.getText());
			if (item.getText().toLowerCase().contains(pdts)) {
				System.out.println(item.getText());
				item.click();

				windowHandler.childWindowHandle(); // Ensure correct method name
				productPage.addToCart();
				windowHandler.closeAllWindowExceptMain();
			}
		}
	}

	public void wishListItemsDelete(String pdts) {

		for (int i = 0; i < wishlistItems.size(); i++) {
			// System.out.println(wishlistItems.get(i).getText());
			if (wishlistItems.get(i).getText().toLowerCase().contains(pdts)) {
				wishlistItems.get(i).getText();
				deleteIcon.get(i).click();
				visibilityOf(yesRemove);
				yesRemove.click();
				visibilityOf(userName);
			}
		}

	}

}