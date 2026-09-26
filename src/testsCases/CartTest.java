package testsCases;

import java.util.List;

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

        
        String firstKeyword = "table";
        homePage.searchForProduct(firstKeyword);
        Assert.assertTrue(searchResultsPage.areProductsDisplayed(), "No products found for keyword: " + firstKeyword);
        searchResultsPage.clickOnFirstProduct();

        
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.selectFirstColorIfAvailable();
        Assert.assertTrue(productDetailsPage.isProductAvailable(), "Product is not available");
      
        String firstProductName = productDetailsPage.getProductName(); //*
        String firstProductPrice = productDetailsPage.getProductPrice();//*

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

        String secondProductName = productDetailsPage.getProductName(); //*
        String secondProductPrice = productDetailsPage.getProductPrice();//*
        productDetailsPage.clickAddToCart();
        
        Assert.assertTrue(productDetailsPage.isProductAddedSuccessfully(), "Second product was not added successfully");
        productDetailsPage.closeMiniCartIfDisplayed();
        

        
      

        cartPage = new CartPage(driver);
        cartPage.openCartPage();
        
        List<String> cartNames = cartPage.getCartItemNames();//*
        List<String> cartPrices = cartPage.getCartItemPrices();//*
        
        boolean foundFirst = false;
        boolean foundSecond = false;
        boolean foundFirstPrice = false;
        boolean foundSecondPrice = false;
        
        for (String cartName : cartNames) {
            if (cartName.equals(firstProductName)) {
                foundFirst = true;
                System.out.println("Name saved from Product Details: " + firstProductName);
                System.out.println("Name found in Shopping Cart: " + cartName);
            }
            if (cartName.equals(secondProductName)) {
                foundSecond = true;
                System.out.println("Name saved from Product Details: " + secondProductName);
                System.out.println("Name found in Shopping Cart: " + cartName);
            }
        }

        Assert.assertTrue(foundFirst, "First product name does not match exactly in the cart: " + firstProductName);
        Assert.assertTrue(foundSecond, "Second product name does not match exactly in the cart: " + secondProductName);
        System.out.println("Product names verified successfully");
      
        
        for (String cartPrice : cartPrices) {
            String cleanCartPrice = extractPurePrice(cartPrice);
            String cleanFirstPrice = extractPurePrice(firstProductPrice);
            String cleanSecondPrice = extractPurePrice(secondProductPrice);

            if (!cleanFirstPrice.isEmpty() && cleanCartPrice.equals(cleanFirstPrice)) {
                foundFirstPrice = true;
                System.out.println("Price verified for Product 1: " + cartPrice);
            }
            if (!cleanSecondPrice.isEmpty() && cleanCartPrice.equals(cleanSecondPrice)) {
                foundSecondPrice = true;
                System.out.println("Price verified for Product 2: " + cartPrice);
            }
        }
        Assert.assertTrue(foundFirstPrice, "First product price does not match exactly in the cart: " + firstProductPrice);
        Assert.assertTrue(foundSecondPrice, "Second product price does not match exactly in the cart: " + secondProductPrice);
        System.out.println("Product prices verified successfully");
        
        
        
        int initialCount = cartPage.getCartItemsCount();
        System.out.println("Items count in cart before removal: " + initialCount);
        Assert.assertTrue(initialCount >= 2, "Cart does not contain the expected number of products.");

        cartPage.removeFirstItem();
        
        int finalCount = cartPage.getCartItemsCount();
        System.out.println("Items count in cart after remove: " + finalCount);
        Assert.assertEquals(finalCount, initialCount - 1, "Cart item count did not decrease correctly after removal.");

        System.out.println("Products added, verified, and one removed successfully.");
    }
    
    
    

    public String extractPurePrice(String priceText) {
        if (priceText == null) return "";
        return priceText.replaceAll("[^0-9.]", "");
    }
}

