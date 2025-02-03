package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponents;

public class FlightBookingPage extends AbstractComponents {

	static ChromeDriver driver;

	public FlightBookingPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'QqFHMw')]//span[contains(text(),'SEARCH')]")
	WebElement search;
	@FindBy(xpath = "//input[@type='radio' and @id='ONE_WAY']")
	WebElement oneWayRadio;
	@FindBy(xpath = "//input[@type='radio' and @id='ROUND_TRIP']")
	WebElement roundTripRadio;

	@FindBy(xpath = "//input[@name='0-departcity']")
	WebElement source;
	@FindBy(xpath = "//input[@name='0-departcity']/ancestor::div[@class='lJPVok']//div[@class='_98hP1j']")
	List<WebElement> sourcedp;
	// (//div[contains(@class,'ecAhsD')])[1]//div[@class='_98hP1j']
	@FindBy(name = "0-arrivalcity")
	WebElement destination;
	@FindBy(xpath = "//input[@name='0-arrivalcity']/ancestor::div[@class='lJPVok']//div[@class='_98hP1j']")
	List<WebElement> destinationdp;
	// (//div[contains(@class,'ecAhsD')])[2]//div[@class='_98hP1j']

	@FindBy(name = "0-datefrom")
	WebElement calender;
	@FindBy(name = "0-travellerclasscount")
	WebElement passengers;
	@FindBy(xpath = "//div[@class='_1w7bXX']")
	List<WebElement> months;
	@FindBy(xpath = "(//button[@class='R0r93E'])[2]")
	WebElement nextMonths;
	@FindBy(xpath = "(//button[@class='R0r93E'])[1]")
	WebElement previousMonths;

	public static List<WebElement> specificDates(int i) {
		return (driver.findElements(
				By.xpath("(//table[@class='RYl+NW'])[" + (i + 1) + "]//button[contains(@class,'pl8ttv')]")));
	}

	@FindBy(xpath = "(//div[@class='Do40aG'])[1]")
	WebElement book1;
	@FindBy(xpath = "//div[@class='cthO4-' and contains(text(),'amount')]")
	WebElement totalAmntTxt;
	@FindBy(xpath = "//div[@class='cthO4-' and contains(text(),'amount')]/ancestor::div[@class='uJ4ZKF']//span[contains(text(),'₹')]")
	WebElement totalAmntPrice;
	@FindBy(css = ".xb9OJT")
	List<WebElement> allCoupons;

	@FindBy(name = "Enter Coupon Code")
	WebElement couponEditBox;
	@FindBy(xpath = "//div[text()='Apply']")
	WebElement apply;

	public void bookTickets(String srce, String desti, String date, String month, String adult, String child,
			String infants) throws InterruptedException {
		visibilityOf(search);
		// oneWayRadio.click();

		source.sendKeys(Keys.CONTROL + "a", Keys.DELETE); // If clear() does not work (due to JavaScript restrictions),
															// use this alternative approach
		source.sendKeys(srce);
		String src = srce.toLowerCase();
		threadSleep();
		for (WebElement it : sourcedp) {
			// System.out.println(it.getText());
			if (it.getText().toLowerCase().contains(src)) {
				it.click();
			}
		}

		// visibilityOf(destination);
		destination.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		destination.sendKeys(desti);
		visibilityOfAllElements(destinationdp);
		String dest = desti.toLowerCase();
		for (WebElement it : destinationdp) {
			System.out.println(it.getText());
			if (it.getText().toLowerCase().contains(dest)) {
				it.click();
			}
		}

		String mnth = month.toLowerCase();
		calender.click();
		previousMonths.click();
		boolean monthFound = false;
		for (int i = 0; i < 12; i++) { // Limit iterations to prevent infinite loops
			List<WebElement> monthsre = months; // Re-fetch elements

			for (int j = 0; j < monthsre.size(); j++) { // System.out.println("Month: " + monthsre.get(j).getText());
				if (monthsre.get(j).getText().toLowerCase().contains(mnth)) {
					monthFound = true;
					selectDate(j, date); // Call method to select date
					break;
				}
			}
			if (monthFound)
				break;

			WebElement nextMonthsre = nextMonths; // Re-fetch before clicking
			nextMonthsre.click();
			visibilityOf(nextMonthsre);
		}
		search.click();
		visibilityOf(book1);
	}

	public static void selectDate(int i, String date) throws InterruptedException {
		Thread.sleep(1000);
		// Re-fetch date elements after navigating months
		List<WebElement> specificDates = specificDates(i);

		for (WebElement dateElement : specificDates) {
			if (dateElement.getText().equals(date)) {
				System.out.println("Selecting date: " + dateElement.getText());
				dateElement.click();
				return;
			}
		}
	}

	public void coupons(String coupons, int value) throws InterruptedException {
		book1.click();
		visibilityOf(totalAmntTxt);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		int totalAmnt = Integer.parseInt(totalAmntPrice.getText().split("₹")[1].replace(",", ""));
		couponEditBox.sendKeys(coupons);
		apply.click();

		visibilityOf(totalAmntPrice);
		int newAmnt = totalAmnt - value;

		driver.navigate().refresh();
		int uiConversionAmnt = Integer.parseInt(totalAmntPrice.getText().split("₹")[1].replace(",", ""));
		Assert.assertEquals(newAmnt, uiConversionAmnt);
	}

}

/*
 * Check the codition for : <button class="R0r93E" disabled=""> i.e. attributes
 * present or not
 */