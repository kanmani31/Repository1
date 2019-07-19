package case_study;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Case_1 {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@Test(priority=1,enabled=false)
	public void register(){
		driver.findElement(By.partialLinkText("In")).click();
		driver.findElement(By.partialLinkText("Register")).click();
		driver.findElement(By.name("userName")).sendKeys("kanmani");
		driver.findElement(By.name("firstName")).sendKeys("abc");
		String a=driver.findElement(By.id("err")).getText();
		if(a.contentEquals("Available")) {
		driver.findElement(By.name("lastName")).sendKeys("def");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("confirmPassword")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Female']")).click();
		driver.findElement(By.id("emailAddress")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("1234567891");
		driver.findElement(By.name("dob")).sendKeys("07/12/1997");
		driver.findElement(By.name("address")).sendKeys("chrompet_chennai");
		Select var=new Select(driver.findElement(By.id("securityQuestion")));
		var.selectByIndex(1);
		driver.findElement(By.name("answer")).sendKeys("pink");
		driver.findElement(By.name("Submit")).click();
		}
		
		
		if(driver.getTitle().contains("Login")) {
		test.log(LogStatus.PASS,"Registration successful", "Registration done successfully");
			 }
	    else {
			 test.log(LogStatus.FAIL,"Registration fail", "Registration failed");
		     }
			  
		
		
		}
	
  @Test(priority=2)
  public void login() {
	  driver.findElement(By.partialLinkText("In")).click();
	  driver.findElement(By.name("userName")).sendKeys("kanmani");
	  driver.findElement(By.id("password")).sendKeys("123456");
	  driver.findElement(By.name("Login")).click();
	  
	  if(driver.getTitle().contains("Home")) {
	    test.log(LogStatus.PASS,"Login successful", "Login done successfully");
	  }
	  else {
		 test.log(LogStatus.FAIL,"Login fail", "Login failed");
          }
	  
  }
  @Test(priority=3)
  public void testcart() throws IOException {
	  
	  Actions act=new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Categories')]"))).
	  moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Electronics')]"))).click().build().perform();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Head')]"))).click().build().perform();
	  
	  
	  /*driver.findElement(By.name("products")).sendKeys("Travel");
	  driver.findElement(By.xpath("//input[@name='val']")).click();*/
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//a[@class='btn btn-success btn-product']")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  String t1=driver.getTitle();
	  System.out.println(t1);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.partialLinkText("Cart")).click();
	  String t2=driver.getTitle();
	  System.out.println(t2);
	  if(driver.getTitle().matches(t2))
	  {
		  //test.log(LogStatus.PASS,"Added to cart","Cart Details viewed successfully ");
		  test.log(LogStatus.PASS, "Added to cart", test.addScreenCapture(capture.takescreenshot(driver)));
		  }
	  
	  else {
		  test.log(LogStatus.FAIL,"Not added to cart","Addition to cart failed");
	  }
	  
	  driver.findElement(By.xpath("//a[@class='btn btn-success btn-block']")).click();
	  driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	  Assert.assertTrue(driver.getTitle().contains("Payment"));
	  
  }
  
  @Test(priority=4)
  public void testPayment() throws InterruptedException, IOException{
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.findElement(By.xpath("//input[@value='UCO Bank']")).click();
	  driver.findElement(By.cssSelector("#swit > div:nth-child(1)")).click();

	  driver.findElement(By.xpath("//a[@id='btn']")).click();
	  //Thread.sleep(5000);
	  driver.findElement(By.name("username")).sendKeys("123456");
	  driver.findElement(By.name("password")).sendKeys("Pass@456");
	  driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
	  driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	  String T=driver.getTitle();
	  System.out.println(T);
	  Assert.assertEquals(driver.getTitle(),T);
	  if(driver.getTitle().equals(T)) {
		  test.log(LogStatus.PASS,"Payment completed","Order successful");
	  }
	  else {
		  test.log(LogStatus.FAIL,"Payment error","Payment not done");
		 // test.log(LogStatus.FAIL,test.addScreenCapture(capture.takescreenshot(driver)),"Payment not done" );
		  
	  }
	  
  }

	  @BeforeSuite
	  public void beforeMethod() {
		  System.setProperty("webdriver.chrome.driver","C:\\TestingBatch13&14\\chromedriver_win32\\chromedriver.exe");
		  driver=new ChromeDriver();
		
		  driver.get("http://10.232.237.143:443/TestMeApp");
	  }

	 
	  
	  
	  @BeforeTest
	  public void ReportBeforetest() {
		  report=new ExtentReports("C:\\TestingBatch13&14\\Report_testMe.html",false);
		  test=report.startTest("Case_1");
	  }
	  @AfterTest
	  public void finalreport() {
		  driver.close();
		  report.flush();
		  report.endTest(test);
		  
	  }
	 /* @AfterMethod
	  public void results_after_method(ITestResult result) {
		  if(result.getStatus()==ITestResult.FAILURE) {
			 test.log(LogStatus.FAIL, "Process not executed", "Unsuccessful");
		  }
			  else {
				  test.log(LogStatus.PASS,"Process executed","Successfull");
				  
			  }
		  }*/
	  }




  

