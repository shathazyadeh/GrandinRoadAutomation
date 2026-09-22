package testsCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SearchResultsPage;

public class GrandinRoadSearchTest extends BaseTest {

    @DataProvider(name = "searchDataProvider")
    public Object[][] getData() {
        return new Object[][] {
            { "lamp" },
            { "chairs" },
            { "bed" }
        };
    }
    
    @Test(dataProvider = "searchDataProvider")
    public void testSearch(String keyword) {
    
        // 1. Search for product
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct(keyword);
        
        // 2. Verify URL contains searchTerm=keyword
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains("searchTerm=" + keyword.toLowerCase()));
        Assert.assertTrue(isUrlCorrect, " URL does not match for keyword: " + keyword);
        System.out.println(" URL correctly contains: searchTerm=" + keyword.toLowerCase());
        
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        
        // 3. Verify heading contains keyword
        String heading = resultsPage.getHeadingText(keyword);
        System.out.println("Heading found on page: " + heading );
        Assert.assertTrue(heading.contains(keyword.toLowerCase()),  
            " Search heading does not contain keyword: " + keyword);
        System.out.println(" Heading successfully contains the keyword: " + keyword);
        
        // 4. Verify number of search results is greater than 0
        boolean areProductsDisplayed = resultsPage.areProductsDisplayed();
        Assert.assertTrue(
            areProductsDisplayed,
            " No products found for: " + keyword
        );
        System.out.println(" Search results are displayed successfully (count > 0)");
        
        System.out.println("Test Passed Successfully for: " + keyword );
    }
}