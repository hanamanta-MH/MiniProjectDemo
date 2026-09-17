package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Icicipru {
	WebDriver driver;
	
  @BeforeClass
  @Parameters("browser")
  public void browserSetUp(String browser)
  {
	  if(browser.equalsIgnoreCase("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	  }
	  else if(browser.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  driver = new EdgeDriver();
	  }
	  
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://www.iciciprulife.com/");
	  
  }
	
  @Test(priority=1)
  public void validateHomePageTitle() {
	  String actualPageTitle=driver.getTitle();
	  String expectedPageTitle="Life Insurance - ICICI Prudential Life Insurance India 2026";
	  Assert.assertEquals(actualPageTitle, expectedPageTitle);
  }
  @Test(priority=2)
  public void validateTermInsuranceMenu() {
	  driver.findElement(By.xpath("//span[text()='Term Insurance']")).click();
	  List<WebElement> termmenulist = driver.findElements(By.xpath("//a//span[text()='Term Insurance']//following::ul[1]//li//a"));
	  for(WebElement element : termmenulist)
	  {
		  String text = element.getText();
		  System.out.println(text);	  
		  //Assert.assertTrue(text.contains("TERM INSURANCE"));
	  }
  }
  @Test(priority=3)
  public void validateLogin() {
	  driver.findElement(By.id("login-toggle")).click();
	  driver.findElement(By.linkText("Individual")).click();
	  String expecctedUrl="https://customer.iciciprulife.com/csr/customerLogin.htm?execution=e1s1";
	  String actualUrl=driver.getCurrentUrl();
	  Assert.assertEquals(actualUrl, expecctedUrl);
  }
  
  @AfterTest
  public void tearDown()
  {
	  driver.quit();
  }
}
