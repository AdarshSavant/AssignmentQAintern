package Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePage  {

	public WebDriver driver;
	@Test
	public void Login() throws Throwable {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");
		driver.get("https://sakshingp.github.io/assignment/login.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("username")).sendKeys("Adarsh");
		driver.findElement(By.id("password")).sendKeys("Savant");
		Thread.sleep(1000);
		driver.findElement(By.className("form-check-input")).click();
		driver.findElement(By.className("btn-primary")).click();

		js.executeScript("window.scrollBy(0,300)", "");
		List<WebElement> beforeColnames = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]")); 

		List<Double> beforSortList=new ArrayList<>();
		List<Double> afterSortList=new ArrayList<>();

		for (int i=0; i<beforeColnames.size(); i++) { 


			String str=beforeColnames.get(i).getText().replaceAll("[\\s+a-zA-Z :]","");
			String StringVar=str.replaceAll(",", "");
			beforSortList.add(Double.parseDouble(StringVar));
		}
		Collections.sort(beforSortList);
		System.out.println("Before Sorted List:-----"+beforSortList);

		Thread.sleep(1000);
		driver.findElement(By.id("amount") ).click();


		List <WebElement> afterColnames = driver.findElements(By.xpath(
				"//table[@id='transactionsTable']/tbody/tr/td[5]"));

		for (int i=0; i<afterColnames.size(); i++) { 


			String str=afterColnames.get(i).getText().replaceAll("[\\s+a-zA-Z :]","");
			String StringVar=str.replaceAll(",", "");
			afterSortList.add(Double.parseDouble(StringVar));
		}

		System.out.println("After Sorted List:-----"+afterSortList);
		Assert.assertEquals(beforSortList, afterSortList,"Result is True");
		System.out.println("Assertion Successful");
		driver.quit();
	}


}