package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://certwcs.grandinroad.com/?aka_bypass=5C73514EE7A609054D81DE61DD9CA3D6\r\n");
	}
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
}

