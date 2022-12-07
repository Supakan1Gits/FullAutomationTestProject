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

public class FirstNameValidationTest {
  @Test (dataProvider = "validFirstNames" ,enabled = true)
  public void validFirstName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage firstNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
//	  And I enter a value within the 'First Name’ Textbox
//	  And I click on ‘Sign Up’ Button,
	  firstNameTest.enterFirstNameField.sendKeys(name);
	  firstNameTest.signUpBtn.click();

//	  First Name entered is less than or equal to 50 alphabetical characters in length. 
      Assert.assertTrue(firstNameTest.firstNameSpanText.getText().isEmpty());
  
  }
  @Test (dataProvider = "invalidFirstNames" ,enabled = false)
  public void invalidFirstName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage firstNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
//	  And I enter a value within the 'First Name’ Textbox
//	  And I click on ‘Sign Up’ Button,
	  firstNameTest.enterFirstNameField.sendKeys(name);
	  firstNameTest.signUpBtn.click();
	  
//    If the entered First Name is greater than 50 characters in length then I should see the 
//    following inline error message: ”The firstName may not be greater than 50 characters.”
	  Assert.assertTrue(firstNameTest.firstNameOutOfBoundText.isDisplayed());
	  


  
  }
  @Test (dataProvider = "nonAlphabetFirstNames" ,enabled = false)
  public void nonAlphabetFirstName(String name) {
//	  When I navigate to the ‘Sign Up’ screen, 
	  
	  CommonPage commonPage = new CommonPage();
	  SignUpPage firstNameTest = new SignUpPage();
	  
	  
	  Driver.getDriver().get(TestDataReader.getProperty("appUrl"));
	  commonPage.welcomeLink.click();
	  commonPage.signUpButton.click();
	  
//	  And I enter a value within the 'First Name’ Textbox
//	  And I click on ‘Sign Up’ Button,
	  firstNameTest.enterFirstNameField.sendKeys(name);
	  firstNameTest.signUpBtn.click();


//    First Name entered does not contain non-alphabetical characters. 
//    If the entered First Name entered does contains non-alphabetical characters then I should see  
//    the following inline error message: ”The firstName format is invalid.”
      Assert.assertTrue(firstNameTest.firstNameInvalid.isDisplayed());

  
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
  public String[] validFirstNames() {
 	  String[] firstName = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/validNames.xlsx", "Sheet1");
 	return firstName;
  }
  @DataProvider
  public String[] invalidFirstNames() {
 	  String[] firstName = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/invalidFirstName.xlsx", "Sheet1");
 	return firstName;
  }
  @DataProvider
  public String[] nonAlphabetFirstNames() {
 	  String[] firstName = ExcelUtils.getExcelDataInAColumn
 			  ("./src/test/resources/TestData/nonAlphabicFirstName.xlsx", "Sheet1");
 	return firstName;
  }

  

//  First Name entered does not contain non-alphabetical characters. 
//  If the entered First Name entered does contains non-alphabetical characters then I should see  
//  the following inline error message: ”The firstName format is invalid.”
  
 

}
