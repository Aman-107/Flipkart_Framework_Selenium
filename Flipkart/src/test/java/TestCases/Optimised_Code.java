package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.FlightBookingPage;
import PageObjects.Headers;
import PageObjects.HomePage;
import PageObjects.OrdersPage;
import PageObjects.ProductPage;
import PageObjects.ProfilePage;
import PageObjects.SearchPage;
import PageObjects.WishListPage;
import TestComponents.BaseTest;
import Utilities.WindowHandler;

public class Optimised_Code extends BaseTest {

	ChromeDriver driver = invokeDriver();
	HomePage homePage = new HomePage(driver);
	SearchPage searchPage = new SearchPage(driver);
	WindowHandler windowHandler = new WindowHandler(driver);
	Headers headers = new Headers(driver);
	CartPage cartPage = new CartPage(driver);
	ProductPage productPage = new ProductPage(driver);
	WishListPage wishListPage = new WishListPage(driver);
	OrdersPage ordersPage = new OrdersPage(driver);
	ProfilePage profilePage = new ProfilePage(driver);
	FlightBookingPage flightBookingPage = new FlightBookingPage(driver);
	
	@Test
	public void Login() throws IOException {
		homePage.landingPage();
		LoadCookies(driver);
		System.out.println("Login Successfully");
	}
	
	//@Test
	public void Search_Functionality_Filters() throws InterruptedException {
		
		double queryRating = 3.0;
		int castRating = (int) queryRating;
		//homePage.landingPage();
		homePage.searchBox("Laptops");

		searchPage.brandsFilter();
		searchPage.priceFilter("50000", "₹75000+");
		searchPage.customerRatingFilter(castRating); // Customer Ratings
		searchPage.sorting(); // Price high to low
		searchPage.appliedFilter(); // Applied Filters
		searchPage.priceSorting(); // Pricing Order Verification
		
			// Ratings Verification
		List<WebElement> ratings = driver.findElements(By.cssSelector(".XQDdHH")); // total products in the page	
		for (int i = 1; i <=ratings.size(); i++) {
		double pdtRating = Double.parseDouble((driver.findElement(By.xpath("(//div[@class='XQDdHH'])[" + i + "]")).getText()));
			if (pdtRating < queryRating) {
				System.out.println(driver.findElement(By.xpath("(//div[@class='KzDlHZ'])[" + i + "]")).getText());
			}
				}
				// sorting verification
				String expectedSort = driver.findElement(By.cssSelector(".zg-M3Z._0H7xSG")).getText();
				Assert.assertEquals(expectedSort, "Newest First");
	}
	
	//@Test
	public void addingMultipleProductsCart() throws InterruptedException {
         //driver.get("https://www.flipkart.com/search?q=Laptops&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off&p%5B%5D=facets.price_range.from%3D50000&p%5B%5D=facets.price_range.to%3DMax&p%5B%5D=facets.brand%255B%255D%3DHP&p%5B%5D=facets.rating%255B%255D%3D3%25E2%2598%2585%2B%2526%2Babove&sort=price_desc");
		 
		for(int i=0; i<3;i++) { // 3 = number of products
		searchPage.productDetails(i);
		windowHandler.childWindowHandle();
		productPage.pincode("221007");
		productPage.addToCart();
		driver.close();
		windowHandler.parentWindowHandle();
		}
		windowHandler.closeAllWindowExceptMain();
		headers.cartPage();
		
		String actualPrice = cartPage.priceCal();
		String expectedPrice = cartPage.totalAmount();
		Assert.assertEquals(actualPrice, expectedPrice);
		
		cartPage.removeProducts(1);
		driver.navigate().refresh();
		actualPrice = cartPage.priceCal();
		expectedPrice = cartPage.totalAmount();
		Assert.assertEquals(actualPrice, expectedPrice);
		}
	
	//@Test
	public void wishlistFunctionality() throws InterruptedException {
		homePage.searchBox("Samsung S24 ultra");
		String pdts = ("S24 ultra").toLowerCase();
		searchPage.productSearch(pdts);
		windowHandler.childWindowHandle();
		productPage.wishList();
		windowHandler.closeAllWindowExceptMain();
		headers.wishList(pdts);// navigating to wishlist page
		wishListPage.wishListItemsDelete(pdts);
	}
	
	//@Test
	public void reviews() throws InterruptedException, IOException {
		driver.get("https://www.flipkart.com/account/orders?link=home_orders");
		headers.myOrders();
		ordersPage.reviewOrders("4","Good Smell", "Beardo","ImageYes");	
	} 
 
	//@Test
	public void address() throws InterruptedException {
		driver.get("https://www.flipkart.com/account/orders?link=home_orders");
		headers.myProfile();
//name, phone, pincode, locality, descrption, city, state		
		profilePage.addAddresses("Shadow","8796458321","221007","Khaliya","Sa 16/192-D-6, shadow, Varanasi","","Uttar Pradesh");
		profilePage.editAddress("Sa 16/192-D-6107, shadow, Varanasi", "Sa 16/192-D-6, shadow, Varanasi");
		driver.navigate().refresh();
		profilePage.deleteAddress("Sa 16/192-D-6, shadow, Varanasi");
	}

	//@Test
	public void cancellation() throws InterruptedException {
		//ordersPage.getOrdersName(null)
		driver.get("https://www.flipkart.com/account/orders?link=home_orders");
		headers.myOrders();
		ordersPage.cancelledOrders("alDivo Mug");
	}
	
	@Test
	public void coupons() throws InterruptedException { //for coupons done the implemented the code for flight bookings
		homePage.flightBooking();
		flightBookingPage.bookTickets("Varanasi", "Mumbai", "10", "July", "1", "0", "0");
		flightBookingPage.coupons("FLYFK",975);
	}
	
	
}	
//		 Clean up
//		 driver.quit();

/*

*/