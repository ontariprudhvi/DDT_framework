package constants;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Apputil {
	public static WebDriver driver;
	public static Properties config;
	@BeforeTest
	public static void Setup()throws Throwable 
	{
		config=new Properties();
		config.load(new FileInputStream("C:\\selenium_9clock\\Maven_DDT_FrameWork\\PropertyFiles\\Environment.properties"));
		driver=new ChromeDriver();
	}
	@AfterTest
	public static void Teardwon()
	{
		driver.close();
	}
	public  static void add()
	{
		int a=19,b=25,c;
		c=a+b;
		System.out.println(c);
		
	}
}
