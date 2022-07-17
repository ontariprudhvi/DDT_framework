package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.Functionlibrary;
import constants.Apputil;
import utilities.ExcelFile;

public class AppTest extends Apputil{
	String inputpath="C:\\selenium_9clock\\Maven_DDT_FrameWork\\Testinput\\Logindata.xlsx";
	String outputpath="C:\\selenium_9clock\\Maven_DDT_FrameWork\\Testoutput\\DataDrivenResults.xlsx";
	ExtentReports repoter;
	ExtentTest test;
	@Test
	public void StartTest() throws Throwable 
	{
		//Define path of html
		repoter=new ExtentReports("./Repots/DataDriven.html");
		//create object for excel file utile class
		ExcelFile xl=new ExcelFile(inputpath);
		//count no of rows in sheet
		int rc= xl.rowcount("login");
		//count no cell in row
		int cc=xl.cellcount("login");
		Reporter.log("number of rows::"+rc+"    "+"number of cell::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			test=repoter.startTest("validate login");
			String user=xl.getcelldata("login", i, 0);
			String pass=xl.getcelldata("login", i, 1);
			//cell login method from function library
			boolean rsc=Functionlibrary.verifylogin(user, pass);
			if(rsc)
			{
				//write as login success into results cell
				xl.setcelldata("login", i, 2, "login success", outputpath);
				//write as login success into status cell
				xl.setcelldata("login", i, 3, "pass", outputpath);
				test.log(LogStatus.PASS, "login success");

			}
			else
			{
				//take screen short into error message
				File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./screen/Iterations/"+"   "+i+"Loginpage.png"));
				xl.setcelldata("login", i, 2, "login fail", outputpath);
				xl.setcelldata("login", i, 3, "fail", outputpath);
				test.log(LogStatus.FAIL, "login fail");

			}
			repoter.endTest(test);
			repoter.flush();
		}

	}

}
