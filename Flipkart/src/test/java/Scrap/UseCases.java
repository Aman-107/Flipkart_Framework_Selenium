package Scrap;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UseCases {

    public static void main(String[] args) throws InterruptedException {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String s1 = "₹17,007";
        System.out.println(Integer.parseInt(s1.split("₹")[1].replace(",","")));
        
    }
}



		
		
		
		
		
		/*
		Here are additional scenarios to validate the Flipkart platform comprehensively:

---

### **Scenario 1: Validate User Registration Flow**  
**Steps:**  
1. Navigate to the Flipkart homepage.  
2. Click on "Sign Up" or "New User? Create an Account".  
3. Enter valid mobile number/email address.  
4. Complete the OTP verification process.  
5. Fill in the user details (e.g., name, password, etc.).  
6. Submit the registration form.  

**Validation Points:**  
- OTP is sent to the provided mobile number/email.  
- Account is successfully created, and the user is redirected to the homepage.

---

### **Scenario 2: Validate Search Functionality with Filters**  
**Steps:**  
1. Search for a product category (e.g., "Laptops").  
2. Apply filters (e.g., brand, price range, customer ratings).  
3. Verify that the results reflect the selected filters.  
4. Sort the products by price (low to high).  

**Validation Points:**  
- Filters are applied correctly.  
- Sorting works as expected.  
- Product details match the filter criteria.

---

### **Scenario 3: Validate Adding Multiple Products to Cart**  
**Steps:**  
1. Search for and add multiple products to the cart.  
2. Navigate to the cart page.  
3. Verify the total price calculation.  
4. Remove one item from the cart.  

**Validation Points:**  
- All added products are displayed in the cart.  
- Total price is calculated accurately.  
- Cart updates correctly when an item is removed.

---

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

### **Scenario 5: Validate Payment Gateway Integration**  
**Steps:**  
1. Add a product to the cart and proceed to checkout.  
2. Select different payment options (e.g., Credit/Debit Card, UPI, Net Banking).  
3. Complete the payment process using dummy data in a test environment.  

**Validation Points:**  
- Payment is processed successfully.  
- Error messages appear for invalid payment details.  
- User is redirected to the order confirmation page after successful payment.

---

### **Scenario 6: Validate Product Review and Ratings Submission**  
**Steps:**  
1. Navigate to the "My Orders" section after an order is delivered.  
2. Select an order and click "Write a Review".  
3. Submit a rating and review for the product.
4. Upload the Images for review.   

**Validation Points:**  
- Rating and review are submitted successfully.  
- Review is displayed on the product page.  

---

### **Scenario 7: Validate Address Management**  
**Steps:**  
1. Navigate to the "My Account" section and open "My Addresses".  
2. Add a new address with all valid details.  
3. Edit the newly added address.  
4. Delete an existing address.  

**Validation Points:**  
- Address is added, updated, and deleted successfully.  
- The address list reflects the changes immediately.

---

### **Scenario 8: Validate Order Cancellation**  
**Steps:**  
1. Place an order and navigate to the "My Orders" section.  
2. Select the order and click "Cancel".  
3. Provide a reason for cancellation and confirm.  

**Validation Points:**  
- Order cancellation is processed successfully.  
- Refund is initiated (if applicable).  
- Status of the order changes to "Cancelled".

---

### **Scenario 9: Validate Offers and Coupon Codes**  
**Steps:**  
1. Add a product to the cart.  
2. Apply an available coupon code during checkout.  
3. Verify that the discount is applied correctly.  

**Validation Points:**  
- Discount is reflected in the total price.  
- Invalid coupon codes display an appropriate error message.

---

### **Scenario 10: Validate Guest User Checkout**  
**Steps:**  
1. Add a product to the cart without logging in.  
2. Proceed to checkout as a guest.  
3. Enter address and payment details.  
4. Complete the purchase process.  

**Validation Points:**  
- Guest checkout works without requiring login.  
- Confirmation email/SMS is sent to the guest user.

---

### **Scenario 11: Validate Page Responsiveness**  
**Steps:**  
1. Open Flipkart on different devices (desktop, mobile, tablet).  
2. Resize the browser window and test various screen sizes.  

**Validation Points:**  
- All elements adjust correctly for different screen sizes.  
- No overlap or misalignment issues.

---

### **Scenario 12: Validate Error Handling for Network Failures**  
**Steps:**  
1. Simulate a network disconnection during payment.  
2. Attempt to load the website with a slow network.  

**Validation Points:**  
- Payment failure message is displayed.  
- User is not charged for unsuccessful payments.  
- A retry option is provided for slow network conditions.

---

NOTE : All the above scenario's should have : 
1. Reports i.e Extent Report.
2. Listeners.
3. Retry mechanism to run for atleast 2 times.
	
		*/
