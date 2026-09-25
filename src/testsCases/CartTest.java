package testsCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultsPage;

public class CartTest extends BaseTest {
    SearchResultsPage searchResultsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;

    @Test
    public void testAddAndRemoveProductsFromCart() {
    	HomePage homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);

        
        String firstKeyword = "chair";
        homePage.searchForProduct(firstKeyword);
        Assert.assertTrue(searchResultsPage.areProductsDisplayed(), "No products found for keyword: " + firstKeyword);
        searchResultsPage.clickOnFirstProduct();

        
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.selectFirstColorIfAvailable();
        Assert.assertTrue(productDetailsPage.isProductAvailable(), "Product is not available");
       
        productDetailsPage.clickAddToCart();
          
        Assert.assertTrue(productDetailsPage.isProductAddedSuccessfully(), "First product was not added successfully");
        productDetailsPage.closeMiniCartIfDisplayed();
        
        
        // second product
        
        String secondKeyword = "lamp";
        homePage.searchForProduct(secondKeyword);
        Assert.assertTrue(searchResultsPage.areProductsDisplayed(), "No products found for keyword: " + secondKeyword);
        
        searchResultsPage.clickOnFirstProduct();

        productDetailsPage.selectFirstColorIfAvailable();
        Assert.assertTrue(productDetailsPage.isProductAvailable(), "Product is not available");

        
        productDetailsPage.clickAddToCart();
        
        Assert.assertTrue(productDetailsPage.isProductAddedSuccessfully(), "Second product was not added successfully");
        productDetailsPage.closeMiniCartIfDisplayed();
        

        
      

        cartPage = new CartPage(driver);
        cartPage.openCartPage();
        
        int initialCount = cartPage.getCartItemsCount();
        System.out.println("Items count in cart before removal: " + initialCount);
        Assert.assertTrue(initialCount >= 2, "Cart does not contain the expected number of products.");

        cartPage.removeFirstItem();
        
        int finalCount = cartPage.getCartItemsCount();
        System.out.println("Items count in cart after remove: " + finalCount);
        Assert.assertEquals(finalCount, initialCount - 1, "Cart item count did not decrease correctly after removal.");

        System.out.println("Products added, verified, and one removed successfully.");
    }
}