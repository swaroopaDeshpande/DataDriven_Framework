package DataDrivenApproach;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Util.XLS_Reader;

public class SignUpDataFreeCRM {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\Drivers\\Cromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com/register/");

		XLS_Reader reader = new XLS_Reader(
				"C:\\Users\\HP\\workspace\\DataDrivenFramework\\src\\main\\java\\TestData\\FreeCrmTestData.xlsx");
		int rowCount = reader.getRowCount("signup");
		System.out.println("Total number of rows in signup XL Sheet is " + rowCount);

		reader.addColumn("signup", "status");

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			String edition_FreeCRM = reader.getCellData("signup", "Edition", rowNum);
			String firstname_FreeCRM = reader.getCellData("signup", "firstname", rowNum);
			String lastname_FreeCRM = reader.getCellData("signup", "lastname", rowNum);
			String email_FreeCRM = reader.getCellData("signup", "Email", rowNum);
			String confirm_FreeCRM = reader.getCellData("signup", "confirmEmail", rowNum);
			String username_FreeCRM = reader.getCellData("signup", "username", rowNum);
			String password_FreeCRM = reader.getCellData("signup", "password", rowNum);
			String confirmpassword_FreeCRM = reader.getCellData("signup", "confirmpassword", rowNum);

			System.out.println("Registraion data for users" + edition_FreeCRM + firstname_FreeCRM + lastname_FreeCRM
					+ email_FreeCRM + confirm_FreeCRM + username_FreeCRM + password_FreeCRM + confirmpassword_FreeCRM);

			Select select = new Select(driver
					.findElement(By.xpath("//select[@name='payment_plan_id' and @class='form-control input-text']")));
			select.selectByVisibleText("Free Edition");

			WebElement firstName = driver.findElement(By.xpath("//input[@type='text' and @name='first_name']"));
			firstName.clear();
			firstName.sendKeys(firstname_FreeCRM);

			WebElement lastName = driver.findElement(By.xpath("//input[@type='text' and @name='surname']"));
			lastName.clear();
			lastName.sendKeys(lastname_FreeCRM);

			WebElement email = driver.findElement(By.xpath("//input[@type='text' and @name='email']"));
			email.clear();
			email.sendKeys(email_FreeCRM);

			WebElement email_confirm = driver.findElement(By.xpath("//input[@type='text' and @name='email_confirm']"));
			email_confirm.clear();
			email_confirm.sendKeys(confirm_FreeCRM);

			WebElement userName = driver.findElement(By.xpath("//input[@type='text' and @name='username']"));
			userName.clear();
			userName.sendKeys(username_FreeCRM);

			WebElement passWord = driver.findElement(By.xpath("//input[@type='password' and @name='password']"));
			passWord.clear();
			passWord.sendKeys(password_FreeCRM);

			WebElement confirmpassword = driver
					.findElement(By.xpath("//input[@type='password' and @name='passwordconfirm']"));
			confirmpassword.clear();
			confirmpassword.sendKeys(confirmpassword_FreeCRM);

			boolean flag = driver.findElement(By.xpath("//input[@type='checkbox' and @name='agreeTerms']"))
					.isSelected();
			if (flag) {
				driver.findElement(By.xpath("//input[@type='checkbox' and @name='agreeTerms']"));
			} else {
				driver.findElement(By.xpath("//input[@type='checkbox' and @name='agreeTerms']")).click();

			}
			
			driver.findElement(By.xpath("//div//button[@type='submit']")).click();
			boolean flag1=driver.findElement(By.xpath("//div//button[@type='submit']")).isEnabled();
			if(flag1)
			{
			Thread.sleep(2000);
			String title = driver.getTitle();
			System.out.println(title);
			if (title.equals("CRMPRO - CRM Pro for customer relationship management, sales, and support")) {
				reader.setCellData("signup", "status", rowNum, "pass");
			} else {
				reader.setCellData("signup", "status", rowNum, "fail");

			}
			}

			driver.navigate().back();

		}

		driver.close();
	}

}
