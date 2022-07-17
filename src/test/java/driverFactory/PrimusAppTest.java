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

import commonFunctions.PrimusFunctionlibrary;
import constants.PrimusApputili;
import utilities.ExcelFile;
import utilities.PrimusExcelFile;

public class PrimusAppTest extends PrimusApputili {

	String inputpath="C:\\selenium_9clock\\Maven_DDT_FrameWork\\Testinput\\Primuslogindata.xlsx";
	String outputpath="C:\\selenium_9clock\\Maven_DDT_FrameWork\\Testoutput\\PrimusResults.xlsx";
	ExtentReports reporter;
	ExtentTest test;
	@Test
	public void starttest() throws Throwable
	{
		//Defined  path of html
		reporter=new ExtentReports("./PrimusResults/Results.html");	
		//create object for excel file utili class
		PrimusExcelFile xl=new PrimusExcelFile(inputpath);
		//count for number of rows
		int rc = xl.rowcount("login");
		//count for cell in row
		int cc = xl.cellcount("login");
		Reporter.log("number of rows::"+rc+"number of cell::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			test=reporter.startTest("Primus Validation login");
			String user=xl.getcelldata("login", i, 0);
			String pass=xl.getcelldata("login", i, 1);
			////cell login method from function library
		boolean rcs=PrimusFunctionlibrary.verifylogin(user, pass);
		if(rcs)
		{
			//write as login success into results cell
			xl.setcelldata("login", i, 2, "login success", outputpath);
			//write as login success in status cell
			xl.setcelldata("login", i, 3, "pass", outputpath);
			test.log(LogStatus.PASS, "login success");
		}
		else 
		{
			//take screen short into error message
			File pic=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(pic, new File("./Primuspics/ErrorPic/"+"   "+i+"loginpage.png"));
			xl.setcelldata("login", i, 2, "login fail", outputpath);
			xl.setcelldata("login", i, 3, "fail", outputpath);
			test.log(LogStatus.FAIL, "login fail");
		}
		reporter.endTest(test);
		reporter.flush();
		}
		
		
	}
}
