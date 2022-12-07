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

public class LastNameValidationTest {
  @Test (dataProvider = "validNames" ,enabled = true)
  public void validLastName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage lastNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  

//	  And I enter a value within the 'Last Name’ Textbox
//	  And I click on ‘SIGN UP’ Button, 
	  lastNameTest.enterLastNameField.sendKeys(name);
	  lastNameTest.signUpBtn.click();

//    Last Name entered is less than or equal to 50 alphabetical characters in length. 
//	  Assert.assertTrue(lastNameTest.passwordRequiredText.isDisplayed());
	  Assert.assertTrue(lastNameTest.lastNameSpanText.getText().isEmpty());

  }
  @Test (dataProvider = "invalidNames" ,enabled = false)
  public void invalidLastName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage lastNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  

//	  And I enter a value within the 'Last Name’ Textbox
//	  And I click on ‘SIGN UP’ Button, 
	  lastNameTest.enterLastNameField.sendKeys(name);
	  lastNameTest.signUpBtn.click();
	  
//    If the entered First Name is greater than 50 characters in length the following inline
//    error message:”The lastName may not be greater than 50 characters.” should appear
	  Assert.assertTrue(lastNameTest.lastNameOutOfBoundText.isDisplayed());



  }
  @Test (dataProvider = "nonAlphabetNames" ,enabled = false)
  public void nonAplhabetLastName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage lastNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  

//	  And I enter a value within the 'Last Name’ Textbox
//	  And I click on ‘SIGN UP’ Button, 
	  lastNameTest.enterLastNameField.sendKeys(name);
	  lastNameTest.signUpBtn.click();
	  
//    Last Name entered does not contain non-alphabetical characters. 

//    If the entered First Name entered does contains non-alphabetical characters the 
//    following inline error message:”The lastName format is invalid.” should appear
	  Assert.assertTrue(lastNameTest.lastNameInvalid.isDisplayed());



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
  public String[] validNames() {
 	  String[] firstName = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/validNames.xlsx", "Sheet1");
 	return firstName;
  }
  @DataProvider
  public String[] invalidNames() {
 	  String[] name = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/invalidFirstName.xlsx", "Sheet1");
 	return name;
  }
  @DataProvider
  public String[] nonAlphabetNames() {
 	  String[] name = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/nonAlphabicFirstName.xlsx", "Sheet1");
 	return name;
  }



 

}
