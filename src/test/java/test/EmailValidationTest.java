package test;

import org.testng.annotations.Test;

import pages.CommonPage;
import pages.SignUpPage;
import utils.Driver;
import utils.ExcelUtils;
import utils.TestDataReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EmailValidationTest {
  @Test (dataProvider = "validEmails", enabled = true)
  public void validEmail(String email) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage emailTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  

//	  And I enter a value within the 'Email Address’ Textbox
//	  And I click on ‘Sign Up’ Button, 
  
	  emailTest.enterEmailField.sendKeys(email);
	  emailTest.signUpBtn.click();
	  
//    The Email Address entered is less than or equal to 125 characters in length. 
//    Email Address entered follows the following format [string@domain.com].	  

	  Assert.assertTrue(emailTest.emailSpanText.getText().isEmpty());

}
  @Test (dataProvider = "invalidEmails",enabled = false)
  public void invalidEmail(String email) {

	  
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage emailTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  

//	  And I enter a value within the 'Email Address’ Textbox
//	  And I click on ‘Sign Up’ Button, 
  
	  emailTest.enterEmailField.sendKeys(email);
	  emailTest.signUpBtn.click();
	  
//    If email is greater than 125 characters in length then I should see the following inline error 
//    message: ”The email may not be greater than 125 characters.”
	  Assert.assertTrue(emailTest.emailOutOfBoundText.isDisplayed());
	  
}
  @Test (dataProvider = "invalidEmailDomains",enabled = false)
  public void invalidEmailDomain(String email) {

	  
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage emailTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();  
	  
//	  And I enter a value within the 'Email Address’ Textbox
//	  And I click on ‘Sign Up’ Button, 
  
	  emailTest.enterEmailField.sendKeys(email);
	  emailTest.signUpBtn.click();
	  
//    If the entered email is not in [string@domain.com] format then I should see the following 
//    inline error message: ”Please enter a valid email address.”
	  Assert.assertTrue(emailTest.enterValidEmailText.isDisplayed());
	  
}
@BeforeMethod
  public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

  @AfterMethod
  public void quitDriver() {
		Driver.quitDriver();
	}
  
  @DataProvider
  public String[] validEmails() {
 	  String[] emails = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/validEmail.xlsx", "Sheet1");
 	return emails;
  }
  @DataProvider
  public String[] invalidEmails() {
 	  String[] emails = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/invalidEmail.xlsx", "Sheet1");
 	return emails;
  }
  @DataProvider
  public String[] invalidEmailDomains() {
 	  String[] emails = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/invalidDomain.xlsx", "Sheet1");
 	return emails;
  }







}
