package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	ChromeDriver driver;
	
	public OrdersPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".CLxqQT")
	WebElement searchEdit;
	@FindBy(xpath="//div[@data-val='Delivered']")
	WebElement deliveredCheckBox;
	@FindBy(xpath="//span[text()='Rate & Review Product']")
	List<WebElement> reviewProduct;
	By reviews = By.xpath("//span[text()='Rate & Review Product']");
	@FindBy(xpath="//span[contains(@class,'kn1h+D')]")
	List<WebElement> ordersName;
	
	public void searchOrders(String pdt) {
		visibilityOf(searchEdit);
		searchEdit.sendKeys(pdt);
	}
	
	public void deliverdOrders(String review,String comments,String pdts) {
		visibilityOf(searchEdit);
		deliveredCheckBox.click();
		visibilityOf(deliveredCheckBox);
		Assert.assertTrue(deliveredCheckBox.isEnabled());
		if(review != null) {
			review(review,comments,pdts);
		}
	}
	
	public void review(String review,String comments,String pdts) {
		 while(true) {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			 increasingOf(reviews, reviewProduct.size());
			 List<WebElement> productsAfter = driver.findElements(By.cssSelector(".product-item"));
			    if (reviewProduct.size() == productsAfter.size()) {
			        break; // No new elements loaded
			    }
			    reviewProduct = productsAfter;
		 }
		 
		 for(int i=0; i<ordersName.size();i++) {
			 if(ordersName.get(i).getText().toLowerCase().contains(pdts)) {
			// 	 reviewProduct.get(i).click(); -> Have to use sibling concept
			 }
		 }
		
	}
}
