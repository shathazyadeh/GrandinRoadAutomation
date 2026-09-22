package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    WebDriver driver;

    By searchResultsHeadingLocator = By.id("mainContentTitle");
    By productTitlesLocator = By.cssSelector(".c-product-title");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeadingText(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        
        wait.until(d -> {
            try {
                WebElement element = d.findElement(searchResultsHeadingLocator);
                String text = element.getText().toLowerCase();
                return text.contains(keyword.toLowerCase());
            } catch (Exception e) {
                return false;
            }
        });

        WebElement headingElement = driver.findElement(searchResultsHeadingLocator);
        return headingElement.getText().toLowerCase();
    }

    public boolean areProductsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        try {
            List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitlesLocator));
            if (products.size() > 0) {
                System.out.println("Products are displayed successfully, Count >  0.");
                return true;
            } else {
                System.out.println(" Product list is empty size = 0");
                return false;
            }
        } catch (Exception e) {
            System.out.println(" No products found or timeout occurred.");
            return false;
        }
        
        
    }

// click on the first product 
    public void clickOnFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productTitlesLocator));
        if (!products.isEmpty()) {
            products.get(0).click();
            System.out.println(" Clicked on the first product successfully.");
        } else {
            throw new RuntimeException("No products available to click");
        }
    }
    }