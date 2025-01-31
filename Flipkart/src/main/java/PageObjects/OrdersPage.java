package PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	ChromeDriver driver;
	RateAndReviewsPage rateAndReviewsPage;
	
	public OrdersPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		rateAndReviewsPage = new RateAndReviewsPage(driver);
	}
	
	@FindBy(css=".CLxqQT")
	WebElement searchEditBox;
	@FindBy(xpath="//span[text()='Rate & Review Product']")
	List<WebElement> reviewProducts;
	By reviews = By.xpath("//span[text()='Rate & Review Product']");
	
	public List<WebElement> getOrdersName(String productName) {
		String dynammicXpath = "//span[contains(@class,'kn1h+D') and contains(text(),'"+productName+"')]";
		return (driver.findElements(By.xpath(dynammicXpath)));
	}
	
	public void reviewOrders(String review,String comments,String pdt,String images) throws InterruptedException, IOException {
		visibilityOf(searchEditBox);
		if(pdt != null) {
		searchEditBox.sendKeys(pdt,Keys.ENTER);
		}
		List<WebElement> ordersName = getOrdersName(pdt);
		for(int i=0; i<ordersName.size(); i++) {
			try {
				reviewProducts.get(i).click();
				break;
			}
			catch(Exception e) {
				System.out.println("Review Button is not Present");
			}
		}
		rateAndReviewsPage.submit(review,comments,images);
	}
}
	
////span[contains(@class, 'kn1h+D') and contains(text(),'CASIO ECB')]//ancestor::div[contains(@class, 'ejrH5a')]//a[contains(@class, 'B3sOrq')]	
