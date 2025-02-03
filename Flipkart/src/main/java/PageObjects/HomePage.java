package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class HomePage extends AbstractComponents {

	ChromeDriver driver;

	@FindBy(name = "q")
	WebElement searchBox;

	@FindBy(xpath = "//span[text()='Filters']")
	WebElement filtersHeader;

	public HomePage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'YBLJE4')]//span[contains(text(),'Flight')]")
	WebElement flightBooking;
	@FindBy(xpath="(//a[@class='_1TOQfO'])[1]")
	WebElement userName; 
	
	public String accountName() {
		String name = userName.getAttribute("title");
		return name;
	}

	public void dashBoard() {
		driver.get("https://www.flipkart.com/");
	}
	
	public void searchBox(String pdts) {
		dashBoard();
		visibilityOf(searchBox);
		searchBox.clear();
		searchBox.sendKeys(pdts, Keys.ENTER);
		visibilityOf(filtersHeader);
	}

	public void flightBooking() {
		dashBoard();
		visibilityOf(flightBooking);
		flightBooking.click();
	}

}