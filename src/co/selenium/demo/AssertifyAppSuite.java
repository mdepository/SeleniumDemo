package co.selenium.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.Duration;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AssertifyAppSuite {

	static String APP_URL = "";

	String USERNAME = "darshu1290@gmail.com";
	String PASSWORD = "123";
	String TESTCASE ="";

	static WebDriver driver;
	static boolean needLogin;
	static TestExecutor executor; 

	public AssertifyAppSuite(){
		setAppURL("https://dev.assertify.me/login");
		executor  = TestExecutor.getInstance();
	}

	public static void setAppURL(String url) {
		APP_URL = url;
	}

	public void setDriverInstance(WebDriver _driver) {
		driver = _driver;
	}

	@BeforeClass
	public static void preTests() {

		setAppURL("https://dev.assertify.me/login");
		executor  = TestExecutor.getInstance();
		executor.preTests();
		/*
		 * System.out.println(" Path for Selenium web driver for chrome is - " +
		 * selenimChromeDriverPath); if(!logFilePath.isEmpty()) { try { //logFilePath =
		 * logFilePath2; psObj = new PrintStream(new File(logFilePath));
		 * System.out.println(" Log File path - " + logFilePath); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } }else { psObj =
		 * System.out; } System.setOut(psObj);
		 */
		if(driver == null)
			driver = executor.getDriverInstance();
		driver.get(APP_URL);
		driver.manage().window().maximize();
		System.out.println("==============  Assertify Testing  ==============");
	}

	@AfterClass
	public static void postTest() {
		executor.postTests();
	}

	public void testAssertify() {

		//Testcase - create new Job 
		try {
			needLogin = true;
			testCreateJob();
			needLogin = false;
			testCreateJob_MultipleSkill();
			testTagJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create Job Single skill
	 */
	@Test
	@Order(1)
	@Ignore
	public void testCreateJob() throws InterruptedException {

		String jobTitle  = "Teacher";
		String skill = "Physics";
		String price = "200";
		String duration = "60";
		String mcqDuration = "2";
		String description = "Teaching Physics subject";
		String criteria = "70% questions answered correctly ";
		String jobOffered = "Teacher";
		String assertifications = "PhysicsTeacher";
		String impInfo ="Bond of 1 year";
		String microSkill = "English";
		String qLimitPreAssessment = "20";
		String qLimitPrePlacement = "10";

		needLogin = true;
		TESTCASE = "ASSR_CRJ_TS01_TR01";
		System.out.println("============== Testcase - "+ TESTCASE +" ==============");
		try {
			if(needLogin)
				loginAssertify(TESTCASE, USERNAME, PASSWORD);

			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[1]/li[2]/a/button/span")).click();
			System.out.println(TESTCASE + "  - Clicked on  Post Job Request link ");

			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[1]")).sendKeys(jobTitle);	
			System.out.println(TESTCASE + "  - Entered JOb Title - " + jobTitle);

			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[2]")).sendKeys(price);	
			System.out.println(TESTCASE + "  - Entered Price  - " + price);

			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[3]")).sendKeys(duration);	
			System.out.println(TESTCASE + "  - Entered Duration - " + duration);
			//
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[4]")).sendKeys(mcqDuration);	
			System.out.println(TESTCASE + "  - Entered MCQ duration - " + mcqDuration);
			// Job description
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[1]/div[2]/div[1]")).sendKeys(description);
			System.out.println(TESTCASE + "  - Entered JOb description - " + description);
			//add required skills
			driver.findElement(By.xpath("//*[@id=\"skillReqInput\"]")).sendKeys(skill);
			System.out.println(TESTCASE + "  - Entered Skill - " + skill);
			//click add skill
			WebElement buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[2]/button"));
			/*Actions builder = new Actions(driver);
			Action seriesOfActions = builder
				.moveToElement(buttonele)
				.click()
				.build();

			seriesOfActions.perform();
			 */
			//driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[2]/button")).click();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", buttonele);

			//success critera
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/div[4]/div[2]/div[1]/p")).sendKeys(criteria);
			System.out.println(TESTCASE + "  - Entered Job Criteria - " + criteria);
			//Jobs offered	
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[5]")).sendKeys(jobOffered);
			System.out.println(TESTCASE + "  - Entered Job Offered - " + jobOffered);

			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[6]")).sendKeys(assertifications);
			System.out.println(TESTCASE + "  - Entered Assertifications - " + assertifications);

			// enter Important info
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/input[7]")).sendKeys(impInfo);
			System.out.println(TESTCASE + "  - Entered Important Info - " + impInfo);
			//add microskills
			driver.findElement(By.xpath("//*[@id=\"skillInput\"]")).sendKeys(microSkill);
			System.out.println(TESTCASE + "  - Entered MicroSkill - " + microSkill);
			//click add skill
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[5]/button"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - clicked add skill button ");

			//Qestion limit preassessment
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[8]")).sendKeys(qLimitPreAssessment);
			System.out.println(TESTCASE + "  - Entered Question Limit (Pre-assessments) - " + qLimitPreAssessment);

			//enter question limit pre -placement
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[9]")).sendKeys(qLimitPrePlacement);
			System.out.println(TESTCASE + "  - Entered Question Limit (Placements Tests) - " + qLimitPrePlacement);

			//click on submit request button
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/button"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked add skill button for micro skills ");

		}catch(NoSuchElementException e) {
			System.out.println(" NoSuchElementException exception occured - "+e.getMessage());
		}catch( ElementClickInterceptedException e) {
			System.out.println(" ElementClickInterceptedException exception occured"+e.getMessage());
		}

		Thread.sleep(2000);
		if(verifyJobAdded(jobTitle, skill))
			System.out.println(" Job with title " + jobTitle + "and skill "+ skill + " is created successfully.");
		else
			System.out.println(" Job with title " + jobTitle + "and skill "+ skill + " is NOT created.");
	}

	/*
	 * Create Job Single skill
	 */
	@Test
	@Order(2)
	@Ignore
	public void testCreateJob_MultipleSkill() throws InterruptedException {

		String jobTitle  = "Accountant";
		String skill_1 = "Account Finance";
		String skill_2 = "Taxation Finance";
		String price = "100";
		String duration = "30";
		String mcqDuration = "2";
		String description = "Efficiency of calculation, Analytical techniques";
		String criteria = "70% questions answered correctly ";
		String jobOffered = "Accountant";
		String assertifications = "AccountancyExpert";
		String impInfo ="Bond of 1 year";
		String microSkill = "English";
		String qLimitPreAssessment = "20";
		String qLimitPrePlacement = "10";

		needLogin = false;
		TESTCASE = "ASSR_CRJ_TS01_TR02";
		System.out.println("============== Testcase - "+ TESTCASE +" ==============");
		if(needLogin)
			loginAssertify(TESTCASE, USERNAME, PASSWORD);


		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[1]/li[2]/a/button/span")).click();
		System.out.println(TESTCASE + "  - Clicked on  Post Job Request link ");

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[1]")).sendKeys(jobTitle);	
		System.out.println(TESTCASE + "  - Entered JOb Title - " + jobTitle);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[2]")).sendKeys(price);	
		System.out.println(TESTCASE + "  - Entered Price  - " + price);


		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[3]")).sendKeys(duration);	
		System.out.println(TESTCASE + "  - Entered Duration - " + duration);
		//
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[4]")).sendKeys(mcqDuration);	
		System.out.println(TESTCASE + "  - Entered MCQ duration - " + mcqDuration);
		// Job description
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[1]/div[2]/div[1]")).sendKeys(description);
		System.out.println(TESTCASE + "  - Entered JOb description - " + description);
		//add required skills
		driver.findElement(By.xpath("//*[@id=\"skillReqInput\"]")).sendKeys(skill_1);
		System.out.println(TESTCASE + "  - Entered Skill - " + skill_1);

		driver.findElement(By.xpath("//*[@id=\"skillReqInput\"]")).sendKeys(skill_2);
		System.out.println(TESTCASE + "  - Entered Skill - " + skill_2);
		//click add skill
		try {
			WebElement buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[2]/button"));
			/*Actions builder = new Actions(driver);
			Action seriesOfActions = builder
				.moveToElement(buttonele)
				.click()
				.build();

			seriesOfActions.perform();
			 */
			//driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[2]/button")).click();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", buttonele);

			//success critera
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/div[4]/div[2]/div[1]/p")).sendKeys(criteria);
			System.out.println(TESTCASE + "  - Entered Job Criteria - " + criteria);
			//Jobs offered	
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[5]")).sendKeys(jobOffered);
			System.out.println(TESTCASE + "  - Entered Job Offered - " + jobOffered);

			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[6]")).sendKeys(assertifications);
			System.out.println(TESTCASE + "  - Entered Assertifications - " + assertifications);

			// enter Important info
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/input[7]")).sendKeys(impInfo);
			System.out.println(TESTCASE + "  - Entered Important Info - " + impInfo);
			//add microskills
			driver.findElement(By.xpath("//*[@id=\"skillInput\"]")).sendKeys(microSkill);
			System.out.println(TESTCASE + "  - Entered MicroSkill - " + microSkill);
			//click add skill
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[5]/button"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - clicked add skill button ");

			//Qestion limit preassessment
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[8]")).sendKeys(qLimitPreAssessment);
			System.out.println(TESTCASE + "  - Entered Question Limit (Pre-assessments) - " + qLimitPreAssessment);

			//enter question limit pre -placement
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div/input[9]")).sendKeys(qLimitPrePlacement);
			System.out.println(TESTCASE + "  - Entered Question Limit (Placements Tests) - " + qLimitPrePlacement);

			//click on submit request button
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/button"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked add skill button for micro skills ");

		}catch(NoSuchElementException e) {
			System.out.println(" NoSuchElementException exception occured - "+e.getMessage());
		}
		catch( ElementClickInterceptedException e) {
			System.out.println(" ElementClickInterceptedException exception occured - "+e.getMessage());
		}

		Thread.sleep(2000);
		if(verifyJobAdded(jobTitle, skill_1))
			System.out.println(" Job with title " + jobTitle + "and skill "+ skill_1 + " is created successfully.");
		else
			System.out.println(" Job with title " + jobTitle + "and skill "+ skill_1 + " is NOT created.");
	}

	@Test
	@Order(3)
	@Ignore
	public void testTagJob() {
		String jobTitle  = "RetailAccountant";
		String skill = "Accounts Finance";
		TESTCASE = "ASSR_TAGJ_TS01_TR01";
		needLogin = false;
		System.out.println("============== Testcase - "+ TESTCASE +" ==============");
		try {
			if(needLogin)
				loginAssertify(TESTCASE, USERNAME, PASSWORD);

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			//click on tag a job link
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[1]/li[1]/a/button/span")).click();
			System.out.println(TESTCASE + "  - Clicked on  Tag a Job link ");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//select Finance category      
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[1]/button[2]")).click();
			System.out.println(TESTCASE + "  - Selected catefory -  Finance");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//click tag a job from Account finance certificate
			//click on submit request button
			WebElement buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]/div[1]/div[2]/button[1]"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked on Submit Request link");


			//Enter job title RetailAccountant
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[3]/input[1]")).sendKeys(jobTitle);
			System.out.println(TESTCASE + "  - Entered Job Title - " + jobTitle);

			//Verify certifcate is Accounts Finance       
			String skillTag = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[3]/input[2]")).getText();
			System.out.println(TESTCASE + "  - Verify is skill selected is as expected ");

			if(skill.equals(skillTag))
				System.out.println("Skill to tag is as expected");
			else
				System.out.println("Skill to tag is NOT as expected");
			//click on tag a job  
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[3]/button"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked on  Tag Job button ");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//click on Manage jobs
			buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[2]/li[1]/a/button/span"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked on  Manage Job link ");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//click on active jobs
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[1]/div[1]/div/div[1]/div/a/h1")).click();
			System.out.println(TESTCASE + "  - Clicked on  Active Jobs Request link ");

			verifyJobAdded(jobTitle, skill);
		}catch(NoSuchElementException e) {
			System.out.println(" NoSuchElementException exception occured - "+e.getMessage());
		}
	}

	private void loginAssertify(String testCaseName, String userName, String pwd) {
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/input")).sendKeys(userName);
		System.out.println(testCaseName +" - Entered username ") ;

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[4]/input")).sendKeys(pwd);
		System.out.println(testCaseName +" - Entered password " );
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button")).click();
		System.out.println(testCaseName +" - Clicked login button " );
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	private boolean verifyJobAdded(String jobTitle, String skill) {

		boolean jobFound  = false;
		// go to manage jobs link
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[2]/li[1]/a/button")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//click active jobs link
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div/div[1]/div[1]/div/div[1]/div/a")).click();

		/*/html/body/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]
		//job skill
				/html/body/div[1]/div[1]/div[3]/div[3]/div[1]/div[1]
		//job title 
				/html/body/div[1]/div[1]/div[3]/div[3]/div[1]/h1				
		 */
		List<WebElement> jobElements  =  driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]"));
		System.out.println("Number of jobs  - "+jobElements.size());
		for(WebElement element : jobElements ){
			if(element != null) {
				List<WebElement> c = element.findElements(By.xpath("./child::*"));
				System.out.println("Number of children "+ c.size());
				// iterate sub-elements
				for ( WebElement i : c ) {
					if(i != null) {
						List<WebElement> cc = i.findElements(By.xpath("./child::*"));
						System.out.println("Number of children "+ c.size());
						for ( WebElement j : cc ) {
							if(j != null) {
								String jobname = j.getText();
								//getTagName() to get tag of sub-elements
								System.out.println("Tag name is - " + j.getTagName());
								System.out.println("text of job is " + jobname);
								if(jobTitle.equals(jobname))
									jobFound = true;
							}
						}
					}
				}
			}
		}
		return jobFound;
	}
	
	/*
	 * This test case searches for an employee by the employee name 
	 */
	@Test
	@Order(3)
	public void testFindEmployee() {
		String employeeName  = "pvl user1";
		String employeeEmail = "user1@pvl.com";
		TESTCASE = "ASSR_MNGE_TS01_TR01";
		needLogin = true;
		System.out.println("============== Testcase - "+ TESTCASE +" ==============");
		try {
			if(needLogin)
				loginAssertify(TESTCASE, USERNAME, PASSWORD);

			JavascriptExecutor executor = (JavascriptExecutor) driver;

			
			//click on manage employee button
			driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/ul[2]/li[2]/a/button/span")).click();
			System.out.println(TESTCASE + "  - Clicked on  Tag a Job link ");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			//click "view employees" buttom       
			driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div[1]/button[1]")).click();
			System.out.println(TESTCASE + "  - Selected catefory -  Finance");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			//click tag a job from Account finance certificate
			//click on submit request button
			WebElement buttonele = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]/div[1]/div[2]/button[1]"));
			executor.executeScript("arguments[0].click();", buttonele);
			System.out.println(TESTCASE + "  - Clicked on Submit Request link");

			//*[@id="root"]/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]
			
			//Enter job title RetailAccountant
			List<WebElement> empList = driver.findElements(By.className("even:bg-gray-100 text-gray-500"));
			if(empList != null) {
				for(int i =0 ; i < empList.size(); i++) {
					WebElement emp  = empList.get(i);
					List<WebElement> empAttr = emp.findElements(By.xpath("./child::*"));
					
					String empName = "name";
					String empEmail = "email";
				}
			}
		}catch(NoSuchElementException e) {
			System.out.println(" NoSuchElementException exception occured - "+e.getMessage());
		}
	}
	
	//Manage employee buttom
	///html/body/div[1]/div[1]/div[2]/div/div[1]/ul[2]/li[2]/a/button/span
}
