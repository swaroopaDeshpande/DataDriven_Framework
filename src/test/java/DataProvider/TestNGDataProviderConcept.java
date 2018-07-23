package DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProviderConcept {
	WebDriver driver;

	@BeforeMethod
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\Drivers\\Cromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com/index.html");

	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = Util.TestUtil.getDataFromSheet("login");
		return data;
	}

	@Test(dataProvider = "getData")
	public void loginTest(String username, String password) {
		login(username, password);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public void login(String username, String password) {
		System.out.println("username is : " + username + " and " + "password is :" + password);
		driver.findElement(By.xpath("//input[@type='text' and @name='username']")).clear();
		driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
	}

}
