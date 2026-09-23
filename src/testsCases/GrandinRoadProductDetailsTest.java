package testsCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultsPage;

public class GrandinRoadProductDetailsTest extends BaseTest {
    SearchResultsPage searchResultsPage;
    ProductDetailsPage productDetailsPage;
    
    @Test
    public void testProductDetailsValidation() {
        String searchKeyword = "chair";
        
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct(searchKeyword);
    
        searchResultsPage = new SearchResultsPage(driver);
        
        boolean isDisplayed = searchResultsPage.areProductsDisplayed();
        Assert.assertTrue(isDisplayed, "No products found for keyword: " + searchKeyword);
      
        String searchResultUrl = driver.getCurrentUrl();
        
        searchResultsPage.clickOnFirstProduct();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(searchResultUrl)));
        System.out.println("Current URL after click: " + driver.getCurrentUrl());
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.t-universal-product-details-heading")));
        
        productDetailsPage = new ProductDetailsPage(driver);
        
        String productName = productDetailsPage.getProductName();
        Assert.assertFalse(productName.isEmpty(), "Product name is empty");    
        
        boolean isPriceVisible = productDetailsPage.isProductPriceDisplayed();
        Assert.assertTrue(isPriceVisible, "Product price is not displayed");
        
        boolean isAvailable=productDetailsPage.isProductAvailable();
        Assert.assertTrue(isAvailable, "product is not available");
        
        
        System.out.println("Product validated successfully");
    }
}