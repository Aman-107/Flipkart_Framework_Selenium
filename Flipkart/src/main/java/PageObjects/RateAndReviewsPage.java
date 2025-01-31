package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class RateAndReviewsPage extends AbstractComponents{

	ChromeDriver driver;
	public RateAndReviewsPage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="star-0-add-rating")
	private WebElement star5;
	@FindBy(id="star-1-add-rating")
	private WebElement star4;
	@FindBy(id="star-2-add-rating")
	private WebElement star3;
	@FindBy(id="star-3-add-rating")
	private WebElement star2;
	@FindBy(id="star-4-add-rating")
	private WebElement star1;
	
	@FindBy(tagName="textarea")
	private WebElement descriptiopn;
	@FindBy(xpath="//input[@class='gdns+F']")
	private WebElement title;
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;
	@FindBy(css=".MGFguu")
	private WebElement images;

	public void ratings(String ratings) {
		switch (ratings) {
		case "1": 
			star1.click();
			break;
		case "2": 
			star2.click();
			break;
		case "3": 
			star3.click();
			break;
		case "4": 
			star4.click();
			break;
		case "5": 
			star5.click();
			break;
		}
			
	}
	
	public void description(String comments) {
		descriptiopn.sendKeys(comments);
	}
	
	public void title(String info) {
		title.sendKeys(info);
	}
	
	public void uploadImages(String yes) throws InterruptedException, IOException {
		System.out.println(yes);
		images.click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("E://SDET Codes Ecllipse//Upload_Picture_AutoIT.exe");
	}
	
	public void submit(String review,String comments,String images) throws InterruptedException, IOException {
		if(review != null) {
			ratings(review);
		}
		if(comments != null) {
			description(comments);
		}
		if(images != null) {
			uploadImages(images);
		}
		
		//submitButton.click();
	}
}



// to add after methods on submit function after each reviews section 