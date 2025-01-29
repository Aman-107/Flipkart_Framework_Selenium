package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.Headers;
import PageObjects.HomePage;
import PageObjects.ProductPage;
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
	
	@Test
	public void Login() throws IOException {
		homePage.landingPage();
		LoadCookies(driver);
		System.out.println("Login Successfully");
	}
	
	@Test
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
	
	
	@Test
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
	
	@Test
	public void wishlistFunctionality() throws InterruptedException {
		homePage.searchBox("Samsung S24 ultra");
		String pdts = ("S24 ultra").toLowerCase();
		//System.out.println(pdts);
		searchPage.productSearch(pdts);
		//searchPage.productDetails(3);
		windowHandler.childWindowHandle();
		productPage.wishList();
		windowHandler.closeAllWindowExceptMain();
		headers.profileHover();
		headers.wishList(pdts);
	}
	
 }	
//		 Clean up
//		 driver.quit();


/*
 ### **Scenario 4: Validate Wishlist Functionality**  
**Steps:**  
1. Search for a product.  
2. Add the product to the wishlist.  
3. Navigate to the wishlist page.  
4. Move the product from the wishlist to the cart.  

**Validation Points:**  
- Product is added to the wishlist successfully.  
- Wishlist page displays all added items.  
- Product is removed from the wishlist after being added to the cart.

---
 */