package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import constants.Apputil;

public class Functionlibrary extends Apputil {
	public static boolean verifylogin(String username,String password)
	{
		driver.get(config.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(config.getProperty("objuser"))).sendKeys(username);
		driver.findElement(By.cssSelector(config.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.cssSelector(config.getProperty("objloginbtn"))).sendKeys(Keys.ENTER);
		String expected="dashboard";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("login sucess::"+expected+"    "+actual,true);
			return true;
		}
		else
		{   
			//capture errormassege
			String Erroremsg =driver.findElement(By.cssSelector(config.getProperty("objerrormsg"))).getText();
			Reporter.log("login fail::"+expected+"    "+actual,true);
			return false;
		}
	}

}
