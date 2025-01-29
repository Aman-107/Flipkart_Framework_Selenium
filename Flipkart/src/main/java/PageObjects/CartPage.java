package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	ChromeDriver driver;
	
	
	@FindBy(xpath="//span[contains(@class,'LAlF6k re6bBo')]")
	List<WebElement> pdtsPrice;
	@FindBy(xpath="//div[contains(@class,'xvz6eC')]//div[@class='_1Y9Lgu']")
	WebElement totalAmt;
	@FindBy(xpath="//div[text()='Remove']")
	List<WebElement> removeIcons;
	@FindBy(css="._1TWLMK.icF5zO")
	WebElement removePop;
	@FindBy(xpath="//div[contains(@class,'sBxzFz fF30ZI A0MXnh')]")
	WebElement removeConfirm;
	@FindBy(css="div[class='IFZV3u _844jJT e1Z+OY']")
	WebElement headerCount;

	
	public CartPage(ChromeDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String priceCal() {
		int sum = 0;
		for(int i=0; i<pdtsPrice.size();i++) {
			sum += Integer.parseInt(pdtsPrice.get(i).getText().split("₹")[1].replace(",", ""));
		}
		return (String.valueOf(sum));
	}
	
	public String totalAmount() {
		return (totalAmt.getText().replace(",", "").split("₹")[1]);	
	}
	
	public void removeProducts(int removeItems) throws InterruptedException {
		for(int i=0; i<Math.min(removeIcons.size(),removeItems);i++) {
			threadSleep();
			removeIcons.get(i).click();
			visibilityOf(removePop);
			//driver.switchTo().frame(removePop);
			removeConfirm.click();
			visibilityOf(headerCount);
			visibilityOf(totalAmt);
		}
	}
}
