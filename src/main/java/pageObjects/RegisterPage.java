package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utils;

public class RegisterPage extends utils{
	
	@FindBy(id = "input-firstname")
    private WebElement FirstName;
	@FindBy(id = "input-name")
    private WebElement Name;
	@FindBy(id = "input-lastname")
    private WebElement LastName;
	@FindBy(id = "input-email")
    private WebElement Email;
	@FindBy(id = "input-telephone")
    private WebElement TelePhone;
	@FindBy(id = "input-password")
    private WebElement Password;
	@FindBy(id = "input-confirm")
    private WebElement ConfirmPassword;
	@FindBy(name = "agree")
    private WebElement Agree;
	@FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement Continue;
	@FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement LoginContinue;
	@FindBy(id = "content")
	private WebElement AccountCreated;
	@FindBy(xpath = "//a[contains(text(),'Contact Us')]")
	private WebElement ContactUs;
	@FindBy(id = "input-enquiry")
	private WebElement Enquiry;	
	@FindBy(xpath = "//img[@alt='iPhone 6']")
	private WebElement IPhone6;
	
	
	public RegisterPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void Register() throws IOException {
		FirstName.sendKeys(getCelldata("RegistrationDetails",0,1));
		LastName.sendKeys(getCelldata("RegistrationDetails",1,1));
		Email.sendKeys(getCelldata("RegistrationDetails",2,1));
		TelePhone.sendKeys(getCelldata("RegistrationDetails",3,1));
		Password.sendKeys(getCelldata("RegistrationDetails",4,1));
		ConfirmPassword.sendKeys(getCelldata("RegistrationDetails",5,1));
		Agree.click();
		Continue.click();
		verifyValue(AccountCreated, "Congratulations! Your new account has been successfully created!");
		LoginContinue.click();
		ContactUs.click();
	}
	
	public void contactVerify() throws IOException {
		verifyText(Name, getCelldata("RegistrationDetails",0,1));
		verifyText(Email, getCelldata("RegistrationDetails",2,1));
		Enquiry.sendKeys("This is to Change of Address/Phone number");
		Continue.click();
		LoginContinue.click();
		IPhone6.click();
	}
	
}
