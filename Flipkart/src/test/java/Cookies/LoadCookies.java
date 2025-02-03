package Cookies;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import java.util.StringTokenizer;

public class LoadCookies {
	public static void main(String[] args) throws IOException {
		// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");

		// Load saved cookies
		File file = new File("cookies.data");
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

		// Refresh the page to apply cookies
		driver.navigate().refresh();

		System.out.println("✅ Flipkart opened with saved session!");

		// driver.quit();
	}
}
