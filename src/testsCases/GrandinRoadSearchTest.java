package testsCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

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

        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct(keyword);
        
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlContains("searchTerm=" + keyword.toLowerCase()));
        	
        	
       	System.out.println("Successfully searched and verified for: " + keyword);

	}
}