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
    By colorSwatchLocator = By.cssSelector("button.c-universal-options__option-swatch"); 
    
    
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
    
    
    
    public void selectFirstColorIfAvailable() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement firstColor = shortWait.until(ExpectedConditions.elementToBeClickable(colorSwatchLocator));
            if (firstColor.isDisplayed()) {
                firstColor.click();
                System.out.println("First color option selected successfully");
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            System.out.println("No color options required for this product");
        }
    }
    
    
    
    
    public boolean isProductAvailable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
            
            if (addButton.isEnabled()) {
                System.out.println("Product is Available ");
                return true;
            } else {
                System.out.println(" Product is NOT available ");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Add to Cart button not found or product is unavailable.");
            return false;
        }
    }

    }