package DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmazonLoginTest {
	// using dataprovider
	WebDriver driver;

	@BeforeMethod
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\Drivers\\Cromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/ap/signin?_encoding=UTF8&openid.assoc_handle=inflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26action%3Dsign-out%26path%3D%252Fgp%252Fyourstore%252Fhome%26ref_%3Dnav_youraccount_nav_youraccount_signout%26signIn%3D1%26useRedirectOnSuccess%3D1");
	}
	
	@DataProvider
	public Object[][] getData()
	{
	Object[][] data=Util.TestUtil.getDataFromSheet("login");
	return data;
	}

	@Test(dataProvider="getData")
	public void amazonLoginTest(String emailId, String password) {
		Login(emailId, password);
		String title=driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Your Amazon.in");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public void Login(String emailId, String password) {
		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
		email.clear();
		email.sendKeys(emailId);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
		Password.sendKeys(password);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}

}
