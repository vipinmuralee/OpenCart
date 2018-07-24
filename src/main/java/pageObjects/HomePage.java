package pageObjects;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utils;

public class HomePage extends utils{
	@FindBy(css = "a[title='My Account']")
    private WebElement MyAccount;
	@FindBy(xpath = "//a[contains(text(), 'Register')]")
    private WebElement Register;
	@FindBy(xpath = "//a[contains(text(), 'Order History')]")
    private WebElement OrderHistory;
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement Logout;
	@FindBy(xpath = "//a[contains(text(), 'Login')]")
    private WebElement Login;
	@FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement Continue;
	@FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement LoginButton;
	@FindBy(id = "input-email")
    private WebElement Email;
	@FindBy(id = "input-password")
    private WebElement Password;
	@FindBy(xpath = "//a[contains(text(),'Newsletter')]")
	private WebElement Newsletter;
	@FindBy(xpath = "//a[contains(text(),'Specials')]")
	private WebElement Specials;
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement ListGrid;
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void Register() {
		MyAccount.click();
		Register.click();
	}
	
	public void Login() throws IOException {
		MyAccount.click();
		Login.click();
		Email.sendKeys(getCelldata("RegistrationDetails",2,1));
		Password.sendKeys(getCelldata("RegistrationDetails",4,1));
		LoginButton.click();
	}
	
	public void orderHistory() {
		MyAccount.click();
		OrderHistory.click();
	}
	
	public void suscription() {
		Newsletter.click();
	}
	
	public void specials() {
		Specials.click();
		ListGrid.click();
	}
	
	public void LogOut() {
		MyAccount.click();
		Logout.click();
		Continue.click();
	}
	
}
