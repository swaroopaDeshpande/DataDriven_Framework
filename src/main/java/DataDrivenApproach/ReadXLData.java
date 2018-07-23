package DataDrivenApproach;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Util.XLS_Reader;

public class ReadXLData {

	public static void main(String args[]) {
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
		System.out.println("Total number of rows available in XL sheet is : " + rowCount);

		// write in XLSheet
		reader.addColumn("login", "status");

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String userName = reader.getCellData("login", "username", rowNum);
			String password = reader.getCellData("login", "password", rowNum);
			System.out.println("username is : " + userName + " and " + "password is :" + password);
			driver.findElement(By.xpath("//input[@type='text' and @name='username']")).clear();
			driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys(userName);
			driver.findElement(By.xpath("//input[@type='password']")).clear();
			driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);			
			reader.setCellData("login", "status", rowNum, "pass");
		}

	}
}
