package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class EmailFieldPage {
	
	public EmailFieldPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath = "//label[text()='Email Address']")
	public WebElement emailAddrressText;
	
	@FindBy(name = "email")
	public WebElement enterEmailField;
	
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	public WebElement signUpBtn;
	
	@FindBy(xpath = "//span[contains(text(),'The email may not be')]")
	public WebElement emailOutOfBoundText;
	
	@FindBy(xpath = "//span[text()='Please enter a valid email address.']")
	public WebElement enterValidEmailText;
	
	@FindBy(xpath = "//span[text()='First Name is required.']")
	public WebElement firstNameRequireText;


}
