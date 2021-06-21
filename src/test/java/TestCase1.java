import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 {
	
	public static WebDriver driver;

	@Test
	public void BookMyShowTest() {
		System.setProperty("webdriver.chrome.driver", "./Drivers//chromedriver.exe");
		// This code is for handel notification in google chrome
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://in.bookmyshow.com/");

		System.out.println("Lunch Bookmyshow Url");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//span[.='Mumbai']")).click();
		
		WebElement text = driver.findElement(By.xpath("(//div[.='The Best of Entertainment'])[7]"));

		System.out.println(text.getText());	//Printing after clicking mumbai as city
		
		Assert.assertEquals(text.getText(), "The Best of Entertainment");	//Verify actual with expected result for content after clicking city as mumbai.
		
		driver.findElement(By.xpath("//span[contains(text(),'Mumbai')]")).click();
		
		driver.findElement(By.xpath("//span[.='View All Cities']")).click();

		WebElement city = driver.findElement(By.xpath("//input[@type='text']"));
		
		Actions act = new Actions(driver);	//To perform clicking operation by the help of Actions class
		
		act.click(city).perform();

		city.sendKeys("hio");

		WebElement notfound = driver.findElement(By.xpath("//span[contains(text(),'No Results found')]"));

		System.out.println(notfound.getText());
		
		//verify if we entered invali city in city text field.
		
		Assert.assertEquals(notfound.getText(), "No Results found"); 
		city.clear();
		
		//Entered value "Co" for finding multiple cities with starting letter Co
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("co"); 
		
		List<WebElement> listCity = driver.findElements(By.xpath("//span[@class='sc-eNNmBn iEDgGS']"));

		for (int i = 0; i <= listCity.size() - 1; i++) {

			System.out.println(listCity.get(i).getText());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 listCity.get(0).click();	//Clicking the Coimbatore city from search suggestion
			 
			// Humberger verification
			 
			driver.findElement(By.xpath("//header/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/*[1]")).click();
			
			System.out.println(driver.findElement(By.xpath("(//div[.='Hey!'])[3]")).getText());
		}

	}

}
