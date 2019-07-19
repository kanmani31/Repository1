package case_study;

import java.io.File;

	  import java.io.IOException;


	  import org.apache.commons.io.FileUtils;

	  import org.openqa.selenium.OutputType;

	  import org.openqa.selenium.TakesScreenshot;

	  import org.openqa.selenium.WebDriver;


	  public class capture {	   
	   public static String takescreenshot(WebDriver driver) throws IOException{
  	  TakesScreenshot ts=(TakesScreenshot) driver;
	  	 
	   File src=ts.getScreenshotAs(OutputType.FILE);
	  	 
	   String des=("C:\\TestingBatch13&14\\screenshot.html");
	  	 
	   FileUtils.copyFile(src, new File(des));
	  	  
	  return des;
	  	  
	    }


	    }

