package test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CommonPage;
import pages.SignUpPage;
import utils.Driver;
import utils.ExcelUtils;
import utils.TestDataReader;

public class PasswordValidationTest {
  @Test (enabled = false)
  public void passwordEncryted() {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage passwordTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();

//	  And I enter a value within the 'Password’ Textbox
//	  And I click on ‘Sign Up’ Button, 
	  passwordTest.enterPasswordField.sendKeys("Password%55555");
	  passwordTest.signUpBtn.click();
	  

//    The system should encrypt the “password” value by the user.
	  Assert.assertTrue(passwordTest.enterPasswordField.getText().isEmpty());
	  
}
  @Test (dataProvider = "validPasswords" , enabled = false)
  public void validPasswordValidation(String password) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage passwordTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
	 

//	  And I enter a value within the 'Password’ Textbox
//	  And I click on ‘Sign Up’ Button, 
	  passwordTest.enterPasswordField.sendKeys(password);
	  passwordTest.signUpBtn.click();
	  Assert.assertTrue(passwordTest.passwordSpanText.getText().isEmpty());
	  
	 
}
  @Test (dataProvider = "invalidPasswords" , enabled = true)
  public void invalidPasswordValidation(String password) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage passwordTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
	 

//	  And I enter a value within the 'Password’ Textbox
//	  And I click on ‘Sign Up’ Button, 
	  passwordTest.enterPasswordField.sendKeys(password);
	  passwordTest.signUpBtn.click();
	  
//    The system should validate that the “password” value meets the following criteria:
//    A. Password length between 8-12 characters.
//    B. Password must contain at least 1 special character. 
//    C. Password must have at least 1 uppercase letter.
//    D. Password must have have at least 1 number. 
//    If the password entered does not meet the criteria mentioned in A - D, then the system
//    should provide the following inline error message: ”Please enter a valid password.”
	  Assert.assertFalse(passwordTest.passwordSpanText.isDisplayed());
	 
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
  public String[] validPasswords() {
 	  String[] password = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/validPassword.xlsx", "Sheet1");
 	return password;
  }
  @DataProvider
  public String[] invalidPasswords() {
 	  String[] password = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/invalidPassword.xlsx", "Sheet1");
 	return password;
  }



}
