package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePurchasePage {
	private Logger logger = Logger.getLogger("devpinoyLogger");
	WebDriver driver;
	
	@FindBy(name="passFirst0")
    private WebElement firstName;
	
	@FindBy(name="passLast0")
    private WebElement lastName;
	
	@FindBy(name="creditnumber")
	private WebElement creditCardNumber;
	
	@FindBy(name="buyFlights")
	private WebElement securePurchase;
	
	public SecurePurchasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// Enter First Name	
	public void enterFirstName (String firstname) {
		try {
		logger.info("Entering '"+firstname+"' in the 'First Name' edit field");
		highlightElement(firstName);
		firstName.sendKeys(firstname);
		logger.info("Entered '"+firstname+"' in the 'First Name' edit field");
		}catch(Exception ex) {
		logger.error("Status -- Failed | Exception occurred while entering '"+firstname+"' in the 'First Name' edit field : "+ex.getMessage());
		}
	}
	// Enter Last Name
	public void enterLastName(String lastname) {
		try {
		logger.info("Entering '"+lastname+"' in the 'Last Name' edit field");
		highlightElement(lastName);
		lastName.sendKeys(lastname);
		logger.info("Entered '"+lastname+"' in the 'Last Name' edit field");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while entering '"+lastname+"' in the 'Last Name' edit field : "+ex.getMessage());
		}
		}
	// Enter Credit Card Number
	public void enterCreditCardNumber(String ccnumber) {
		try {
		logger.info("Entering '"+ccnumber+"' in the 'Credit Number' edit field");
		highlightElement(creditCardNumber);
		creditCardNumber.sendKeys(ccnumber);
		logger.info("Entered '"+ccnumber+"' in the 'Credit Number' edit field");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while entering '"+ccnumber+"' in the 'Credit Number' edit field : "+ex.getMessage());
			
		}
	}
	// Click on 'SECURE PURCHASE'	
	public void clickOnSecurePurchase() {
		try {
		logger.info("Clicking on 'SECURE PURCHASE'");
		highlightElement(securePurchase);
		logger.info("Clicked on 'SECURE PURCHASE'");
		securePurchase.click();
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while clicking on 'SECURE PURCHASE' : "+ex.getMessage());
			
		}
		
	}	
			
		private void highlightElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
		
		
	}

		
		

}
