package pages;

import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class FirstNamePage {
	
	public FirstNamePage() {
		
		PageFactory.initElements(Driver.getDriver(), this);
		
	}

}