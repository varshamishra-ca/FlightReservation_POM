package testcases;

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import pageFactory.FindFlightsPage;
import pageFactory.FlightConfirmationPage;
import pageFactory.LoginPage;
import pageFactory.SecurePurchasePage;
import pageFactory.SelectFlightPage;
import testdata.TestDataReader;
import utility.Constant;

public class ExecuteTest {
	
	WebDriver driver;
	LoginPage loginPage;
	FindFlightsPage findFlightsPage;
	SelectFlightPage selectFlightPage;
	SecurePurchasePage securePurchasePage;
	FlightConfirmationPage flightConfirmationPage;
	TestDataReader reader;
	Properties testData;
	ExtentReports extent;
	ExtentTest extentLogger;
	ExtentHtmlReporter htmlReporter;
	String screenShotPath;
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browser) throws UnknownHostException {
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",Constant.chromeDriverPath);
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constant.geckoDriverPath);
			driver = new FirefoxDriver();
		}
		
		// Maximize Browser
		driver.manage().window().maximize();
		
		// Set Implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		loginPage = new LoginPage(driver);
		findFlightsPage = new FindFlightsPage(driver);
		selectFlightPage = new SelectFlightPage(driver);
		securePurchasePage = new SecurePurchasePage(driver);
		flightConfirmationPage = new FlightConfirmationPage(driver);
		
		reader = new TestDataReader();
		testData = reader.getTestData();
		
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(new File(Constant.reportPath));
		
		// Set Configuration for Extent Report
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report : Flight Reservation");
		htmlReporter.config().setReportName("E2E Testing of Flight Reservation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent.attachReporter(htmlReporter);
		
		// Provide System Information
		
		extent.setSystemInfo("Project Name", "Flight Reservation");
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		InetAddress myHost = InetAddress.getLocalHost();
		extent.setSystemInfo("Host Name", myHost.getHostName());
		
		
	}
	
	@BeforeMethod
	public void getMethodName(Method method) {
		extentLogger = extent.createTest(method.getName());
		
	}
	
	
	@Test(priority=0)
	public void launchFlightReservationApplication() throws IOException {
		driver.get(testData.getProperty("url"));
		screenShotPath=getScreenshotPath("launchFlightReservationApplication"); 
		if(driver.getTitle().equals(testData.getProperty("expHomePageTitle"))) {
			extentLogger.log(Status.PASS,"Flight Reservation Application has been invoked successfully");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}else {
			extentLogger.log(Status.FAIL,"Flight Reservation Application has not been invoked successfully");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}
	}
	
	@Test(priority=1)
	public void loginToFlightReservation() throws IOException {
		// Enter UserName
		loginPage.enterUserName(testData.getProperty("username"));
		// Enter Password
		loginPage.enterPassword(testData.getProperty("password"));
		// Click on 'Sign-In'
		loginPage.clickOnSignIn();
		screenShotPath = getScreenshotPath("loginToFlightReservation");
		if(findFlightsPage.verifyPresenceOfOneWayTripType()) {
			extentLogger.log(Status.PASS, "Flight Reservation Login Successful");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}else {
			extentLogger.log(Status.FAIL, "Flight Reservation Login Unsuccessful");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}
	}
	
	@Test(enabled=false)
	public void testFail() {
		Assert.assertFalse(true);
	}
	
	@Test(enabled=false)
	public void testSKip() {
		throw new SkipException("The test got skipped as it was not ready to be executed");
	}
	
	@Test(priority=2)
	public void bookATicket() throws IOException {
		// Select OneWay Trip Type
		findFlightsPage.clickOnOneWay();
		// Select Departure From
		findFlightsPage.selectDepartureFrom(testData.getProperty("flyFrom"));
		// Select Arrival To
		findFlightsPage.selectArrivalTo(testData.getProperty("flyTo"));
		// Select Class Preference
		findFlightsPage.selectClassPreference();
		// Select Airline Preference
		findFlightsPage.selectAirlinePreference(testData.getProperty("airlinePreference"));
		// Click on 'Find Flights'
		findFlightsPage.clickOnFindFlights();
		// Click on 'Reserve Flight'
		selectFlightPage.clickOnReserveFlight();
		// Enter First Name
		securePurchasePage.enterFirstName(testData.getProperty("firstname"));
		// Enter Last Name
		securePurchasePage.enterLastName(testData.getProperty("lastname"));
		// Enter Credit Card Number
		securePurchasePage.enterCreditCardNumber(testData.getProperty("ccnumber"));
		// Click on 'Buy Flights'
		securePurchasePage.clickOnSecurePurchase();
		// Verify Booking Confirmation Text
		screenShotPath = getScreenshotPath("bookATicket");
		if(flightConfirmationPage.getBookingConfirmationText().contains(testData.getProperty("confirmationText"))) {
			extentLogger.log(Status.PASS, "Ticket has been booked successfully");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}else {
			extentLogger.log(Status.FAIL, "Ticket has not been booked successfully");
			extentLogger.addScreenCaptureFromPath(screenShotPath);
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}

	private String getScreenshotPath(String screenShotName) {
		try {
		TakesScreenshot srcShot = (TakesScreenshot)driver;
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		String dateFormat = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss").format(new Date());
		String destination = Constant.screenShotPath+"\\"+screenShotName+"_"+dateFormat+".png";
		File destFile = new File(destination);
		Files.copy(srcFile, destFile);
		return destination;
		}catch(IOException ex) {
			System.out.println("Exception occurred while taking screenshot : "+ex.getMessage());
			return null;
		}
	}
	
}
