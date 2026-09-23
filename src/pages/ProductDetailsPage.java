package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
    WebDriver driver;

    By productNameLocator = By.cssSelector("h1.t-universal-product-details-heading-info");
    By productPriceLocator = By.cssSelector(".c-universal-price-new div.price");
    By addToCartLocator = By.cssSelector("button[data-analytics-name='add_to_cart']");
    
    
    
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));
        String name = nameElement.getText();
        System.out.println("Product name found: " + name);
        return name;
    }

    public boolean isProductPriceDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
            String price = priceElement.getText();
            System.out.println("Product Price found: " + price);
            return !price.isEmpty();
        } catch (Exception e) {
            System.out.println("Price element not found.");
            return false;
        }
    }
    
    
    public boolean isProductAvailable() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	
    	try {
            WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator));
            boolean isDisplayed = addToCartBtn.isDisplayed();
            boolean isEnabled = addToCartBtn.isEnabled();
            if (isDisplayed && isEnabled) {
                System.out.println("Product availability status: Available Button is visible and enabled");
                return true;
            } else {
                System.out.println("Product availability status: Not Available Button is visible but disabled");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Product availability status: Not Available");
            return false;
    }
}
    }