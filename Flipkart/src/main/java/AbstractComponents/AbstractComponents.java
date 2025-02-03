package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	
	public void visibilityOf(WebElement by) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(by));
	}
	
	public void visibilityOfAllElements(List<WebElement> by) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(by));
		
	}
	
	public void clickable(WebElement by) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void invisibilityOf(WebElement by) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(by));
	}
	
	public void increasingOf(By by, int size) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by,size));
	}
	
	public void threadSleep() throws InterruptedException {
		Thread.sleep(1000);
	}
}	



/*
WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Filters']"))));
*/