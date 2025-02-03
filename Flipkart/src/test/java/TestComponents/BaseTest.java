package TestComponents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

	static ChromeDriver driver;

	public static ChromeDriver invokeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Resources//GlobalData.properties");
		properties.load(file);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
		// String browserName = properties.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();

			options.addArguments("--incognito");
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

//	@BeforeMethod(alwaysRun = true)
	
	public ChromeDriver LoadCookies() throws IOException {  // landing page
		
		driver = invokeDriver();
		driver.get("https://www.flipkart.com/");
		
		File file = new File("cookies.data"); // Load saved cookies
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line, ";");
			String name = token.nextToken();
			String value = token.nextToken();
			String domain = token.nextToken();
			String path = token.nextToken();
			String expiry = token.nextToken();
			boolean isSecure = Boolean.parseBoolean(token.nextToken());

			Cookie cookie = new Cookie.Builder(name, value).domain(domain).path(path).isSecure(isSecure).build();
			driver.manage().addCookie(cookie);
		}
		bufferedReader.close();

		driver.navigate().refresh(); // Refresh the page to apply cookies
		System.out.println("✅ Flipkart opened with saved session!");
		return driver;
	}
	
//	public void closeApplication() {
//	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File ss_loc = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
		FileUtils.copyFile(ss, ss_loc);
		return (System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

	}

}
