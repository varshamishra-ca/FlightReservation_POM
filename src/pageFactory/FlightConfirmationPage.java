package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmationPage {
	WebDriver driver;
	private Logger logger = Logger.getLogger("devpinoyLogger");
	@FindBy(xpath="//p[@align='left']")
	private WebElement confirmationText;
	
	public FlightConfirmationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getBookingConfirmationText() {
		try {
		logger.info("Fetching Booking Confirmation Text");
		highlightElement(confirmationText);
		return confirmationText.getText();
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while fetching Booking Confirmation Text : "+ex.getMessage());
			return null;
		}finally {
			logger.info("Fetched Booking Confirmation Text");
		}
	}
	    private void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}
}

