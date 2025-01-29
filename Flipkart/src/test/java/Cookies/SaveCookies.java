package Cookies;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaveCookies {
    public static void main(String[] args) throws IOException, InterruptedException {
      //  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.flipkart.com");

        // Manually log in to Flipkart before saving cookies
        driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@class,'r4vIwl BV+Dqf')]")).sendKeys("your_phone_number",Keys.ENTER);
		
        System.out.println("🚀 Log in manually, then press Enter...");
        new java.util.Scanner(System.in).nextLine(); // Wait for user input

        // Save cookies to a file
        File file = new File("cookies.data");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Cookie cookie : driver.manage().getCookies()) {
            bufferedWriter.write(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" + cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();

        System.out.println("✅ Cookies saved!");
        driver.quit();
    }
}
