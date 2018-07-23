package DataDrivenApproach;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import Util.XLS_Reader;

public class ReadandWriteXLData_LoginFreeCRM {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\Drivers\\Cromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com/index.html");

		XLS_Reader reader = new XLS_Reader(
				"C:\\Users\\HP\\workspace\\DataDrivenFramework\\src\\main\\java\\TestData\\FreeCrmTestData.xlsx");
		int rowCount = reader.getRowCount("login");
		System.out.println("Total Number rows are" + rowCount);

		reader.addColumn("login", "status");
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String userName = reader.getCellData("login", "username", rowNum);
			String password = reader.getCellData("login", "password", rowNum);
			System.out.println("username is " + userName + " and " + "password is " + password);
			driver.findElement(By.xpath("//input[@type='text' and @name='username']")).clear();
			driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys(userName);
			driver.findElement(By.xpath("//input[@type='password']")).clear();
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
			Thread.sleep(5000);
//			driver.switchTo().frame("intercom-borderless-frame");
//
//			Actions action = new Actions(driver);
//			action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'intercom-chat-card-author')]")))
//					.build().perform();
//
//			driver.findElement(By.xpath("//div[contains(@class,'intercom-borderless-dismiss-button')]//span")).click();
			WebElement LgnBtn = driver.findElement(By.xpath("//input[@type='submit']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", LgnBtn);
			String Title = driver.getTitle();
			if (Title.contains("CRMPRO") ) {
				reader.setCellData("login", "status", rowNum, "pass");
			} else {
				reader.setCellData("login", "status", rowNum, "fail");
			}
		}

	}

}
