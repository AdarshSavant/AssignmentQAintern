package Assignment;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage {

	public WebDriver driver;
	@Test
	public void errorMessage() throws Throwable {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sakshingp.github.io/assignment/login.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,300)", "");
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.className("form-check-input")).click();
		driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(1000);

		String errorMessage = driver.findElement( By.className("alert-warning")).getText();
		String ExpectedMsg = "Both Username and Password must be present";
		Assert.assertEquals(errorMessage,ExpectedMsg,"Successfully Asserted Data");
		System.out.println("Result:-"+"Both Username and Password must be present");
		System.out.println("=============================================================");
		
		
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			verifyLink(url);
		}
		driver.quit();
	}
	public static void verifyLink(String url) {
		try {
			URL link = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
			httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
				System.out.println(url + " - " + httpURLConnection.getResponseMessage());
			} else {
				System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
			}
		} catch (Exception e) {
			System.out.println(url + " - " + "is a broken link");
		}
	}


}