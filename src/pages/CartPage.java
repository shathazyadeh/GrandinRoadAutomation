package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;

   
    By cartItemNamesLocator = By.cssSelector(".c-product-card-layout__title, .c-product-card__name, [data-analytics-name='product_name']");
    By removeButtonLocator = By.cssSelector("button[data-analytics-name='remove_item']");
    By cartIconLocator = By.cssSelector("button[data-analytics-name='show_mini_cart']");
    
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCartPage() {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement cartIcon=wait.until(ExpectedConditions.elementToBeClickable(cartIconLocator));
    	org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartIcon);
        System.out.println("clicked on cart icon successfully");
    }
  
    
    
    public int getCartItemsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> removeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeButtonLocator));
        System.out.println("Number of items found in cart: " + removeButtons.size());
        return removeButtons.size();
    }

    public void removeFirstItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(removeButtonLocator));
         
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", removeButton);
        
        System.out.println("Clicked remove on the first item in the cart successfully");
      
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        } 
    }
