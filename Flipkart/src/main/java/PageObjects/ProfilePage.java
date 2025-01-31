package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponents.AbstractComponents;
import Utilities.ActionsHandle;

public class ProfilePage extends AbstractComponents {
	
	ChromeDriver driver;
	ActionsHandle actionsHandle;
	
	public ProfilePage(ChromeDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		actionsHandle = new ActionsHandle(driver);
	}
	
	@FindBy(xpath="//div[contains(@class,'PbekyG') and contains(text(),'Address')]")
	WebElement adresses;
	@FindBy(css=".g8S8Av")
	WebElement newAddress;
	@FindBy(name="name")
	WebElement nameAddress;
	@FindBy(name="phone")
	WebElement phoneAddress;
	@FindBy(name="pincode")
	WebElement pincodeAddress;
	@FindBy(name="addressLine2")
	WebElement localityAddress;
	@FindBy(name="addressLine1")
	WebElement decriptionAddress;
	@FindBy(name="city")
	WebElement cityAddress;
	@FindBy(name="state")
	WebElement stateAddress;
	@FindBy(xpath="//input[@type='radio']//ancestor::label[@for='HOME']")
	WebElement homeRadioAddress;
	@FindBy(xpath="//button[@type='button' and text()='Save']")
	WebElement saveAddress;
	
	@FindBy(css=".Tlp8CN._9gyQif")
	List<WebElement> allAddresses;
	@FindBy(css=".rApBmn")
	List<WebElement> threedotsAddress;
	@FindBy(xpath="//span[text()='Edit']")
	List<WebElement> editAddress;
	@FindBy(xpath="//span[text()='Delete']")
	List<WebElement> deleteAddress;
	@FindBy(xpath="//button[contains(@class,'q0EMge')]")
	WebElement deleteButtonAddress;
	
	
	//phone, pincode, addressLine2-> locality, addressLine1->realAddress, city, select dp = state  
	public void editAddress(String address, String newaddress) throws InterruptedException {
		String oldAddress = address.toLowerCase();
		adresses.click();
		visibilityOf(newAddress);
		
		for(int i=0; i<allAddresses.size(); i++) {
			if(allAddresses.get(i).getText().toLowerCase().contains(oldAddress)) {
				actionsHandle.hoverElements(threedotsAddress.get(i));
				editAddress.get(i).click();
				decriptionAddress.sendKeys(newaddress);
				saveAddress.click();
				break;
			}
		}
	}
	
	public void addAddresses(String name, String phone, String pincode, String locality, String descrption, String city, String state) {
		adresses.click();
		visibilityOf(newAddress);
		newAddress.click();
		visibilityOf(nameAddress);
		nameAddress.sendKeys(name);
		phoneAddress.sendKeys(phone);
		pincodeAddress.sendKeys(pincode);
		localityAddress.sendKeys(locality);
		decriptionAddress.sendKeys(descrption);
		cityAddress.sendKeys(city);
		Select statedrop = new Select(stateAddress);
		statedrop.selectByValue(state);
		homeRadioAddress.click();
		saveAddress.click();
	}
	
	public void deleteAddress(String address) throws InterruptedException {
		visibilityOf(adresses);
		String oldAddress = address.toLowerCase();
		for(int i=0; i<allAddresses.size(); i++) {
			if(allAddresses.get(i).getText().toLowerCase().contains(oldAddress)) {
				actionsHandle.hoverElements(threedotsAddress.get(i));
				deleteAddress.get(i).click();
				visibilityOf(deleteButtonAddress);
				deleteButtonAddress.click();
			}
		}
	}

}
