package com.AutomationTask.demoProject1;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCalss1 {

	public static WebDriver driver;

	@BeforeMethod
	public void launchDriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\LearnSelenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Test1() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		driver.navigate().to("https://tessa.equine.co.id/");
		//Thread.sleep(10000);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Welcome to New TESSA";
		// Assert.assertEquals(ActualTitle, ExpectedTitle);
		softAssert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("TC 1 is executed");
		// System.out.println(driver.getTitle());
		// softAssert.assertAll();
		softAssert.assertAll();
		// System.out.println("Assert passed");

	}

	@Test
	public void Test2() throws InterruptedException {
		driver.navigate().to("https://tessa.equine.co.id/");
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//span[normalize-space()='Let me in']")).click();
		//Thread.sleep(5000);
		String parentId = null;
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		System.out.println("Test 2 title is = " + driver.getTitle());
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		//Thread.sleep(5000); // WebElement webElement; String validationLoginNeNp =
		String validationLoginNeNp = driver.findElement(By.xpath("//div[@class='text-center text-warning validation-summary-errors']//ul"))
				.getText();
		System.out.println("Pesan Error Login tanpa email dan pass :");
		System.out.println(validationLoginNeNp);
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='Username']")).sendKeys("dwiki.olajuwan@equine.co.id");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		String validationLoginNp = driver
				.findElement(By.xpath("//div[@class='text-center text-warning validation-summary-errors']//ul"))
				.getText();
		System.out.println("Pesan Error Login tanpa pass :");
		System.out.println(validationLoginNp);
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='Username']")).clear();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("ini password");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		String validationLoginNe = driver
				.findElement(By.xpath("//div[@class='text-center text-warning validation-summary-errors']//ul"))
				.getText();
		System.out.println("Pesan Error Login tanpa email :");
		System.out.println(validationLoginNe);
		//Thread.sleep(10000);
	}


	@AfterMethod
	public void quit() {
		driver.quit();
	}
}
