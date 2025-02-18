package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponents.AbstractComponents;

public class SearchPage extends AbstractComponents {

	WebDriver driver;

	public SearchPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Filters']")
	WebElement filtersHeader;
	@FindBy(xpath = "//div[text()='Brand']")
	WebElement brandFilter;
	@FindBy(xpath = "(//div[contains(@class,'KvHRYS')])[1]")
	WebElement moreBrands;
	@FindBy(css = "div[title='HP']")
	WebElement brandElement;

	@FindBy(css = "div[class='suthUA'] select[class='Gn+jFg']")
	WebElement minPricedp;
	@FindBy(css = "div[class='tKgS7w'] select[class='Gn+jFg']")
	WebElement maxPricedp;
	@FindBy(xpath = "//div[text()='Newest First']")
	WebElement sorting;

	@FindBy(css = ".XQDdHH")
	List<WebElement> ratings;
	@FindBy(css = ".YcSYyC")
	List<WebElement> filtersApplied;
	@FindBy(css = ".Nx9bqj._4b5DiR")
	List<WebElement> prices;
	@FindBy(xpath = "//div[@class='KzDlHZ']")
	List<WebElement> pdtsName;

//	@FindBy(css=".KzDlHZ")
//	List<WebElement> products;
//	@FindBy(xpath="//a[@class='CGtC98']")
//    List<WebElement> clickable;

	public void priceSorting() {
		for (int i = 0; i < prices.size(); i++) {
			String rawPrice = prices.get(i).getText().split("₹")[1];
			int compaPrice = 0;
			int price = Integer.parseInt(rawPrice.replace(",", ""));
			if (compaPrice > price) {
				System.out.println("sorting not working at:" + pdtsName.get(i).getText());
			}
		}
	}

	public void appliedFilter() {
		for (int i = 0; i < filtersApplied.size(); i++) {
			System.out.println(filtersApplied.get(i).getText());
		}
	}

	public void brandsFilter() throws InterruptedException {
		brandFilter.click();
		moreBrands.click();
		brandElement.click();
		threadSleep();
	}

	public void priceFilter(String userminPrice, String usermaxPrice) throws InterruptedException {
		Select minPrice = new Select(minPricedp);
		minPrice.selectByValue(userminPrice);

		Select maxPrice = new Select(maxPricedp);
		maxPrice.selectByVisibleText(usermaxPrice);
		threadSleep();
	}

	public void customerRatingFilter(int userrating) throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(@title,'" + userrating + "★')]")).click();
		threadSleep();
	}

	public void sorting() throws InterruptedException {
		sorting.click();
		threadSleep();
	}

	public void productSearch(String pdts) {
		for (int i = 0; i < pdtsName.size(); i++) {
			if (pdtsName.get(i).getText().toLowerCase().contains(pdts)) {
				pdtsName.get(i).click();
				System.out.println(pdtsName.get(i).getText().toLowerCase());
				break;
			}
		}
	}

	public void productDetails(int pdts) {
		for (int i = 0; i <= Math.min(pdtsName.size(), pdts); i++) {
			if (i == (pdts)) {
				pdtsName.get(i).click();
			}
		}

	}
}

/*

	
*/