package constants;

import java.io.FileInputStream;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class PrimusApputili {
	public static WebDriver driver;
	public static Properties confing;
	@BeforeTest
	public static void Setup() throws Throwable
	{
		confing=new Properties();
		confing.load(new FileInputStream("C:\\selenium_9clock\\Maven_DDT_FrameWork\\PropertyFiles\\PrimusEnvironment.properties"));
		driver=new ChromeDriver();

	}
	@AfterTest
	public static void Teardwon()
	{
		driver.close();
	}

}
