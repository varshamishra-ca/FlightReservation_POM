package pageFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private Logger logger = Logger.getLogger("devpinoyLogger");
	WebDriver driver;
	
	@FindBy(name="userName")
	private WebElement userID;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement signIn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// Enter UserName
	public void enterUserName(String username) {
		try {
		logger.info("Entering '"+username+"' in the 'UserName' edit field");
		highlightElement(userID);
		userID.clear();
		userID.sendKeys(username);
		logger.info("Entered '"+username+"' in the 'UserName' edit field");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while entering '"+username+"' in the 'UserName' edit field : "+ex.getMessage());
		}
	}
	
	// Enter Password
	public void enterPassword(String pwd) {
		try {
		logger.info("Entering '"+pwd+"' in the 'Password' edit field");
		highlightElement(password);
		password.clear();
		password.sendKeys(pwd);
		logger.info("Entered '"+pwd+"' in the 'Password' edit field");
		}catch(Exception ex) {
			logger.error("Status -- Failed | Exception occurred while entering '"+pwd+"' in the 'Password' edit field : "+ex.getMessage());
		}
	}
	
	// Click on 'Sign-In'
	public void clickOnSignIn() {
		try {
		logger.info("Clicking on 'Sign-In'");
		highlightElement(signIn);
		signIn.click();
		logger.info("Clicked on 'Sign-In'");
		}catch(Exception ex) {
			logger.error("Exception occurred while clicking on 'Sign-In' : "+ex.getMessage());
		}
	}
	
	private void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}
	

}
