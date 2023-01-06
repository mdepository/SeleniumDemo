package co.selenium.demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class AcvissAppSuite {

	String acvissURL;
	WebDriver driver;
	AcvissAppSuite(){
		setAppURL("https://test.acviss.co/");
	}
	
	AcvissAppSuite(WebDriver _driver){
		setAppURL("https://test.acviss.co/");
		setDriverInstance(_driver);
	}
	public void setAppURL(String url) {
		acvissURL = url;
	}
	
	public void setDriverInstance(WebDriver _driver) {
		driver = _driver;
	}
	
	public void testAcviss() {
		driver.get(acvissURL);
		driver.manage().window().maximize();
		String userName = "Demo";
		String pwd= "Demo";
		try {
			loginAcviss(userName, pwd);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		//Testcase - create new customer 
		try {
			testCreateCustomer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private  void loginAcviss(String userName, String pwd) throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[3]/main/div/div/div[2]/div/form/div[1]/input")).sendKeys(userName);
		driver.findElement(By.xpath("/html/body/div[3]/main/div/div/div[2]/div/form/div[2]/input")).sendKeys(pwd);
		driver.findElement(By.xpath("/html/body/div[3]/main/div/div/div[2]/div/form/div[3]/button")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	/*
	 * create customer test case 
	 */
	private  void testCreateCustomer() throws InterruptedException {

		WebElement addCustButton = null;
		
		//click on customer management link
		driver.findElement(By.xpath("/html/body/aside/div[2]/nav/a[2]")).click();

		addCustButton = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[8]/button"));
		
		String windowTitle  = driver.getTitle();		
		System.out.println("Window title before add cutomer is - "+ windowTitle);
		// Enter customer name
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[1]/input")).sendKeys("DemoPCust");
		// Enter customer short code
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[2]/input")).sendKeys("DPCust");
		// Enter country code
		
		Select cCodes = new Select(
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[3]/select")));
		
		cCodes.selectByVisibleText("India (+91)");
		// Enter phone number
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[4]/input")).sendKeys("9876543210");
		// Enter email
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[5]/input")).sendKeys("Ademo@demo.com");
		// Enter address
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[6]/textarea")).sendKeys("Mumbai M");
		// Enter domain		
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[7]/input")).sendKeys("SomePdomain");
		// click Add customer button
		Thread.sleep(1000);

		//addCustButton.submit();
		addCustButton.submit();
		Thread.sleep(5000);
		getCreateCustomerErrors();
		/*with click may be error messages will be shown assnackbar
		
		check here with click and not submit*/
		windowTitle  = driver.getTitle();		
		System.out.println("Window title after add customer is - "+ windowTitle);
		
		//verify customer is added 
		//check for any error
		/*
		 * try {
			String attr = driver.findElement(By.className("invalid-feedback")).getAttribute("id");
			System.out.println("ID attribute - "+ attr);
			
			String idstr = "///*[@id=\"";
			idstr += attr;
			idstr += "\"]";
			System.out.println("ID attribute - "+ idstr);
			String errorMsg = driver.findElement(By.xpath(idstr)).getText();
			System.out.println(errorMsg);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	List<String> getCreateCustomerErrors(){
		String str = driver.getPageSource();
		System.out.println("page source - " + str);
		try {
			WebElement errorElement  = driver.findElement(By.className("mdc-snackbar__label"));
			String errorMsg = errorElement.getText();
			String htmlText  = errorElement.getAttribute("innerHTML");
			System.out.println("mdc-snackar message - "+errorMsg);
			System.out.println("mdc-snackar html - "+htmlText);
		}catch(NoSuchElementException e) {
			System.out.println("ERROR - mdc-snackbar__label Element not found");
		}
		try {
			List<WebElement> errorElements = driver.findElements(By.className("invalid-feedback"));
			for(WebElement element :errorElements ){
				
				//element.getDomAttribute(str);
				String errorMsg = element.getText();
				System.out.println("Error message by className element" + errorMsg);
				/*String attr = element.getAttribute("id");
				String idstr = "///*[@id=\"";
				idstr += attr;
				idstr += "\"]";
				System.out.println("ID attribute - "+ idstr);
				WebElement errorElement  = driver.findElement(By.xpath(idstr));
				errorMsg = errorElement.getText();
				String htmlText  = errorElement.getAttribute("innerHTML");
				System.out.println("Error message by id element" + errorMsg);
				*/
			}
		}catch(NoSuchElementException e) {
			System.out.println("ERROR - Element not found" + e.getMessage());
		}
		return null;
		
	}

	/*
	 * FAILED create customer test case 
	 */
	private  void testCreateCustomer_fail() {

		WebElement addCustButton = null;
		
		//click on customer management link
		driver.findElement(By.xpath("/html/body/aside/div[2]/nav/a[2]")).click();

		addCustButton = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[8]/button"));
		
		//Actions a = new Actions(driver);
		
		// click Add customer button
		addCustButton.click();
		try {
		
		/*String errorTitle = driver.findElement(By.className("toast")).getTagName();
		String errorText = driver.findElement(By.className("toast")).getText();
		 String toastId = driver.findElement(By.id("toast_id")).getText();
		 String toastName = driver.findElement(By.className("toast-class-name")).getText();
		 
		System.out.println("Toast ID - " + toastId);
		System.out.println("Toast name  - " + toastName);
		*/
			WebDriverWait webwait = new WebDriverWait(driver, Duration.ofSeconds(5));
			String attr = driver.findElement(By.className("invalid-feedback")).getAttribute("id");
			System.out.println("ID attribute - "+ attr);
			
			String idstr = "///*[@id=\"";
			idstr+=attr;
					idstr += "\"]";
			//String errorMsg = webwait.until(ExpectedConditions.alertIsPresent(By.partialLinkText("Please fill out "))).getText();
					System.out.println("ID attribute - "+ idstr);
		String errorMsg = driver.findElement(By.xpath(idstr)).getText();
		/*String errorMsgVal = errorElement.getAttribute("innerHTML");
		System.out.println("Is selected -"+ errorElement.isSelected());*/
		
		/* System.out.println("Error title - "+ errorTitle);
		System.out.println("Error text - "+ errorText);*/
		System.out.println("Error msg - "+ errorMsg);
		//System.out.println("Error msg value- "+ errorMsgVal);
		Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter customer name
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[1]/input")).sendKeys("DemoCust1");
		// Enter customer short code
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[2]/input")).sendKeys("DCust1");
		// Enter country code
		//driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[3]/input")).sendKeys("+91");
		// Enter phone number
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[4]/input")).sendKeys("9876543210");
		// Enter email
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[5]/input")).sendKeys("demo@demo.com");
		// Enter address
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[6]/textarea")).sendKeys("Mumbai");
		/*String error = "Error not shown";
		Alert err = driver.switchTo().alert(); 
		System.out.println("Alert text - "+ err.getText());
		System.out.println("Active element Type  - "+activeE.getAttribute("Type"));
		System.out.println("Active element Title - "+activeE.getAttribute("Title"));
		  //identify actual error message
	    System.out.println("Element after error  -   "+activeE.toString());*/
		// Enter domain
		
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/form/div[7]/input")).sendKeys("Somedomain1");
		// click Add customer button
		addCustButton.click();
	}

}
