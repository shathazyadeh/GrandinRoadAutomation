package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://certwcs.grandinroad.com/?aka_bypass=5C73514EE7A609054D81DE61DD9CA3D6\r\n");
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	@Test
	public void sampleTest() {
	    System.out.println("TestNG");
	}
}

