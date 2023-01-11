package co.selenium.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestExecutor {
	WebDriver driver = null;
	String selenimChromeDriverPath = "";
	String logFilePath = "";
	PrintStream psObj;
	private static TestExecutor executorInstance;
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getSelenimChromeDriverPath() {
		return selenimChromeDriverPath;
	}

	public void setSelenimChromeDriverPath(String selenimChromeDriverPath) {
		this.selenimChromeDriverPath = selenimChromeDriverPath;
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public PrintStream getPsObj() {
		return psObj;
	}

	public void setPsObj(PrintStream psObj) {
		this.psObj = psObj;
	}

	private TestExecutor() {

	}

	public static TestExecutor getInstance() {
		if(executorInstance == null)
			executorInstance =  new TestExecutor();
		return executorInstance;
	}

	public void postTests() {
		psObj.flush();
		closeDriver();
	}

	public void preTests() {

		System.out.println(" Path for Selenium web driver for chrome is - " + selenimChromeDriverPath);
		if(!logFilePath.isEmpty()) {
			try {
				//logFilePath = logFilePath2;
				psObj = new PrintStream(new File(logFilePath));
				System.out.println(" Log File path - " + logFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			psObj = System.out;
		}
		System.setOut(psObj);

		driver = getDriverInstance();
	}

	public  void executeTests() {
		//Test Assertify App
		//assertApp.testAssertify();
		//Test Acviss app
		/*AcvissAppSuite acvissApp = new AcvissAppSuite(driver);
			acvissApp.testAcviss();*/
	}

	public  WebDriver getDriverInstance() {
		if(selenimChromeDriverPath.isEmpty())
			selenimChromeDriverPath = "C:\\Minal\\selenium\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", selenimChromeDriverPath);	
		if(driver == null)
			driver = new ChromeDriver();
		return driver;
	}

	public  void closeDriver() {
		driver.close();
	}
}
