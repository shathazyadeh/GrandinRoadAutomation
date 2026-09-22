package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
WebDriver driver;

By productNameLocator=By.cssSelector(".t-universal-product-details-heading-info_title");
By productPriceLocator = By.cssSelector(".c-universal-price-new div.price");


public ProductDetailsPage(WebDriver driver) {
    this.driver = driver;
}


public String getProductName() {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(12));
	WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));
	String name=nameElement.getText();
	System.out.print("Product name found: "+ name);
	return name;
	}

public boolean isProductPriceDisplayed() {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(12));
	try {
	WebElement priceElement=wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
	String price=priceElement.getText();
	System.out.print("product Price found: "+price);
	return !price.isEmpty();
}
catch (Exception e) {
    System.out.println(" Price element not found.");
    return false;
}
	}
}
