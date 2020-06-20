package selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumScraper {

	public void seleniumScripter() throws InterruptedException {
		//setting the driver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\arpit\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver();
		//Applied wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();

		//open browser with desried URL
		driver.get("https://www.bizjournals.com/seattle/feature/crane-watch");
		try {
			driver.findElement(By.partialLinkText("View the Map")).click();
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			System.out.println(driver.findElements(By.id("main-page")).size());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(
			        ExpectedConditions.presenceOfElementLocated(By.id("map_root")));
			System.out.println(element);
			//driver.findElement(By.cssSelector("image[xlink:href='https://media.bizj.us/view/img/10348119/residential-pin-copy2x.png']")).click();
			//*[@id="seattle_461_layer"]/image[1]
			//*[@id="seattle_461_layer"]/image[2]
			
		}
		catch(NoSuchElementException e) {
			System.out.println("Got here");
			closeThePopup(driver);
		}
		catch(org.openqa.selenium.TimeoutException e) {
			System.out.println("Got even here");
			closeThePopup(driver);
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		SeleniumScraper seleniumScraper = new SeleniumScraper();
		seleniumScraper.seleniumScripter();

	}
	
	public void closeThePopup(WebDriver driver) {
		System.out.println(driver.getWindowHandles().size());
		driver.findElement(By.partialLinkText("I don't want daily insights")).click();
		//*[@id="bx-close-inside-1176467"]/svg/g/path[1]
	}

}
