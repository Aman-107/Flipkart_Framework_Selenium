package Utilities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class WindowHandler extends AbstractComponents {

	WebDriver driver;
	@FindBy(xpath = "//a[@class='CGtC98']")
	List<WebElement> clickable;

	public WindowHandler(ChromeDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to switch to a window by title
	public void switchToWindowByTitle(String windowTitle) {
		String originalWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();

		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			if (driver.getTitle().equals(windowTitle)) {
				return;
			}
		}
		driver.switchTo().window(originalWindow); // Switch back if not found
	}

	// Method to switch to a window by index
	public void switchToWindowByIndex(int index) {
		Set<String> windowHandles = driver.getWindowHandles();
		int i = 0;
		for (String windowHandle : windowHandles) {
			if (i == index) {
				driver.switchTo().window(windowHandle);
				return;
			}
			i++;
		}
	}

	// Method to close all windows except the main window
	public void closeAllWindowExceptMain() {
		//String mainWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it =windowHandles.iterator();
		String mainWindow = it.next();
		
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle).close();
			}
		}
		driver.switchTo().window(mainWindow);
	}

	public int openAllTabWithoutChangingFrame(int tab) throws InterruptedException {
		for (int i = 0; i < Math.min(clickable.size(), tab); i++) {
			WebElement move = clickable.get(i);
			move.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
		}
		threadSleep();
		return (driver.getWindowHandles().size());
	}

	public void childWindowHandle() throws InterruptedException {

		Set<String> wind = driver.getWindowHandles();
		Iterator<String> it = wind.iterator();
		String parent = it.next(); // parent window
		String child = it.next();
		driver.switchTo().window(child);
		threadSleep();
	}

	public void parentWindowHandle() throws InterruptedException {

		Set<String> wind = driver.getWindowHandles();
		Iterator<String> it = wind.iterator();
		String parent = it.next(); // parent window
		// String child = it.next();
		driver.switchTo().window(parent);
		threadSleep();
	}

}
