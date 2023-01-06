package co.selenium.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SeleniumDemo {

	public static void main(String[] args) {

		// Store current System.out
		// before assigning a new value

		// Assign o to output stream
		// using setOut() method
		TestExecutor executor = TestExecutor.getInstance();
		if(args != null && args.length > 0) {
			executor.setSelenimChromeDriverPath(args[0]);
			System.out.println(" Path for Selenium web driver for chrome is - " + args[0]);
			if(args.length > 1) {
				executor.setLogFilePath(args[1]);
			}else {
				String path = Paths.get("").toAbsolutePath().toString();
				File file = new File(path+"\\TestBuddy.txt");
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				executor.setLogFilePath(file.getPath());
			}
		}else {
			executor.setSelenimChromeDriverPath("C:\\Minal\\selenium\\chromedriver_win32\\chromedriver.exe");
			String path = Paths.get("").toAbsolutePath().toString();
			File file = new File(path+"\\TestBuddy.txt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			executor.setLogFilePath(file.getPath());
		}

		Result result = JUnitCore.runClasses(SeleniumTestRunner.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}
