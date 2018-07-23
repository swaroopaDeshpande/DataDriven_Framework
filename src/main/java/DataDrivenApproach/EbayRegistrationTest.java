package DataDrivenApproach;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Util.XLS_Reader;

public class EbayRegistrationTest {

	public static void main(String[] args) {
		// https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=
		System.setProperty(
				"webdriver.chrome.driver", "E:\\selenium\\Drivers\\Cromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driver.get(" https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=");

		XLS_Reader reader = new XLS_Reader(
				"C:\\Users\\HP\\workspace\\DataDrivenFramework\\src\\main\\java\\TestData\\EBAY_TestData.xlsx");
		int rowCount = reader.getRowCount("registration");
		System.out.println("Total number of rows available in Xl Sheet is : " + rowCount);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String firstname = reader.getCellData("registration", "firstname", rowNum);
			String lastname = reader.getCellData("registration", "lastname", rowNum);
			String address = reader.getCellData("registration", "add", rowNum);
			String city = reader.getCellData("registration", "city", rowNum);
			String state = reader.getCellData("registration", "state", rowNum);
			String zip = reader.getCellData("registration", "zip", rowNum);
			String ph1 = reader.getCellData("registration", "ph1", rowNum);
			String ph2 = reader.getCellData("registration", "ph2", rowNum);
			String ph3 = reader.getCellData("registration", "ph3", rowNum);
			String ph4 = reader.getCellData("registration", "ph4", rowNum);
			String email = reader.getCellData("registration", "email", rowNum);
			String Confirm_email = reader.getCellData("registration", "confirm_Email", rowNum);
			String userid = reader.getCellData("registration", "userid", rowNum);
			String password = reader.getCellData("registration", "password", rowNum);
			String confirm_password = reader.getCellData("registration", "confirm_password", rowNum);
			String secq = reader.getCellData("registration", "sec_Q", rowNum);
			String ans = reader.getCellData("registration", "ans", rowNum);
			String bday_Mnt = reader.getCellData("registration", "bday_Mnt", rowNum);
			String Bday = reader.getCellData("registration", "bday_day", rowNum);
			String year = reader.getCellData("registration", "bday_year", rowNum);

			System.out.println(firstname + lastname);
			System.out.println(userid);
			
			driver.findElement(By.xpath(""));
		}
	}

}
