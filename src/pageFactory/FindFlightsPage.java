package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindFlightsPage {
	private Logger logger = Logger.getLogger("devpinoyLogger");
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='tripType'][@value='oneway']")
	private WebElement oneWay;
	
	@FindBy(name="fromPort")
	private WebElement departingFrom;
	
	@FindBy(name="toPort")
	private WebElement arrivingIn;
	
	@FindBy(xpath="//input[@name='servClass'][@value='First']")
	private WebElement first;
	
	@FindBy(name="airline")
	private WebElement airlinePreference;
	
	@FindBy(name="findFlights")
	private WebElement continue1;
	
	public FindFlightsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Verify Presence Of One Way Trip Type
	public boolean verifyPresenceOfOneWayTripType() {
		
		try {
		logger.info("Verifying presence of 'One Way' trip type");
		return oneWay.isDisplayed();
		
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while verifying presence of 'One Way' trip type : "+ex.getMessage());
			return false;
		}finally {
			logger.info("Verified presence of 'One Way' trip type");
		}
		
	}
	// Click on OneWay 
	public void clickOnOneWay() {
		try {
		logger.info("Clicking on 'One Way' trip type");
		highlightElement(oneWay);
		oneWay.click();
		logger.info("Clicked on 'One Way' trip type");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while clicking on 'One Way' trip type : "+ex.getMessage());
		}
	}
	
	// Select Departure From
	
	public void selectDepartureFrom(String departureFrom) {
		try {
		logger.info("Selecting '"+departureFrom+"' from the 'Departing From' drop down");
		highlightElement(departingFrom);
		Select flyFrom = new Select(departingFrom);
		flyFrom.selectByVisibleText(departureFrom);
		logger.info("Selected '"+departureFrom+"' from the 'Departing From' drop down");
		}catch(Exception ex) {
			logger.error("Exception occurred while selecting '"+departureFrom+"' from the 'Departing From' drop down : "+ex.getMessage());
		}
	}
	
	// Select Arrival To
	
	public void selectArrivalTo(String arrivalTo) {
		try {
		logger.info("Selecting '"+arrivalTo+" from 'Arriving In' drop down");
		highlightElement(arrivingIn);
		Select flyTo = new Select(arrivingIn);
		flyTo.selectByVisibleText(arrivalTo);
		logger.info("Selected '"+arrivalTo+" from 'Arriving In' drop down");
		}catch(Exception ex) {
			logger.error("Satus -- Failed | Exception occurred while selecting '"+arrivalTo+" from 'Arriving In' drop down : "+ex.getMessage());
		}
	}
	
	// Select Class Preference
	public void selectClassPreference() {
		try {
		logger.info("Clicking on 'First' class preference");
		highlightElement(first);
		first.click();
		logger.info("Clicked on 'First' class preference");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while clicking on 'First' class preference : "+ex.getMessage());
		}
	}
	
	// Select Airline Preference
	
	public void selectAirlinePreference(String airline) {
		try {
		logger.info("Selecting '"+airline+"' from 'Airline Preference' drop down");
		highlightElement(airlinePreference);
		Select airLine = new Select(airlinePreference);
		airLine.selectByVisibleText(airline);
		logger.info("Selected '"+airline+"' from 'Airline Preference' drop down");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while selecting '"+airline+"' from 'Airline Preference' drop down : "+ex.getMessage());
		}
	}
	
	// Click on Find Flights
	public void clickOnFindFlights() {
		try {
		logger.info("Clicking on 'Find Flights'");
		highlightElement(continue1);
		continue1.click();
		logger.info("Clicked on 'Find Flights'");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while clicking on 'Find Flights' : "+ex.getMessage());
		}
	}
	
	
	private void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}
}
