package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import constants.PrimusApputili;

public class PrimusFunctionlibrary extends PrimusApputili {

	public static boolean verifylogin(String username,String password)
	{
		driver.get(confing.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(confing.getProperty("objuser"))).sendKeys("Admin");
		driver.findElement(By.cssSelector(confing.getProperty("objpass"))).sendKeys("Admin");
		driver.findElement(By.cssSelector(confing.getProperty("objlogin"))).sendKeys(Keys.ENTER);
		String expected="aspx";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("login success::"+expected+"   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("login fail::"+expected+"    "+actual,true);
			return false;
		}
	}


}
