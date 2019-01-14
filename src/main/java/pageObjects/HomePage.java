package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import resources.utils;

import static org.testng.Assert.assertTrue;

public class HomePage extends utils{

	@FindBy(xpath = "//*[@id='logo']/h1/a")
	private WebElement HomePage;
	@FindBy(css = "a[title='My Account']")
    private WebElement MyAccount;
	@FindBy(xpath = "//a[contains(text(), 'Register')]")
    private WebElement Register;
	@FindBy(xpath = "//a[contains(text(), 'Order History')]")
    private WebElement OrderHistory;
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
    private WebElement Logout;
    @FindBy(xpath = "//*[@title='Checkout']")
    public WebElement CheckOut;
    @FindBy(xpath = "//*[@id='content']/p")
    public WebElement ErrorCheckOut;
	@FindBy(xpath = "//a[contains(text(), 'Login')]")
    private WebElement Login;
	@FindBy(xpath = "//*[@class='btn btn-primary']")
    private WebElement Continue;
	@FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement LoginButton;
	@FindBy(id = "input-email")
    private WebElement Email;
	@FindBy(id = "input-password")
    private WebElement Password;
	@FindBy(id = "input-telephone")
	private WebElement PhoneNumber;
	@FindBy(xpath = "//a[contains(text(),'Newsletter')]")
	private WebElement Newsletter;
	@FindBy(xpath = "//a[contains(text(),'Specials')]")
	private WebElement Specials;
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement ListGrid;
	@FindBy(xpath = "//*[@id='account-login']/div[1]")
	public WebElement ErrorMessage;
    @FindBy(name ="search")
    private WebElement Search;
    @FindBy(id = "content")
    private WebElement SearchList;
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement SearchButton;
    @FindBy(id = "list-view")
    private WebElement ListView;
	@FindBy(xpath = "//a[contains(text(),'Edit Account')]")
	public WebElement EditAccount;
    @FindBy(id = "input-sort")
    private WebElement inputSort;

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
	    HomePage.click();
		MyAccount.click();
		Logout.click();
		Continue.click();
	}

	public void invalidEmail() throws IOException {
		MyAccount.click();
		Login.click();
		Email.sendKeys(getCelldata("RegistrationDetails",6,1));
		Password.sendKeys(getCelldata("RegistrationDetails",4,1));
		LoginButton.click();
        String actualString = ErrorMessage.getText();
        assertTrue(actualString.contains("Warning: No match for E-Mail Address and/or Password"));
	}

	public void invalidPassword() throws IOException {
		MyAccount.click();
		Login.click();
		Email.sendKeys(getCelldata("RegistrationDetails",2,1));
		Password.sendKeys(getCelldata("RegistrationDetails",7,1));
		LoginButton.click();
        String actualString = ErrorMessage.getText();
        assertTrue(actualString.contains("Warning: No match for E-Mail Address and/or Password"));
	}

    public void searchProduct(String search) throws IOException {
        Search.sendKeys(search);
        SearchButton.click();
        ListView.click();
    }

    public void selectCategory(WebDriver driver, String subCategory, String category) {
        driver.findElement(By.xpath("//*[contains(text(), '" + category + "')]")).click();
        driver.findElement(By.xpath("//*[contains(text(), '" + subCategory + "')]")).click();
    }

    public void editPhone(String phone) {
        PhoneNumber.clear();
		PhoneNumber.sendKeys(phone);
		Continue.click();
	}

    public void sortBy(WebDriver driver, String sort) {
        Select sel = new Select(driver.findElement(By.xpath("//select[@id='input-sort']")));
        sel.selectByVisibleText(sort);
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList= driver.findElements(By.xpath("//div[@class = 'product-thumb']//img"));
            for(WebElement we:elementList){
                obtainedList.add(we.getAttribute("title"));
            }
        ArrayList<String> sortedList = new ArrayList<>();
            for(String s:obtainedList){
                sortedList.add(s);
            }
                if (sort.equals("Name (A - Z)")) {
                    Collections.sort(sortedList);
                    Assert.assertTrue(sortedList.equals(obtainedList));
                } else if (sort.equals("Name (Z - A)")) {
                    Collections.sort(sortedList);
                    Collections.reverse(sortedList);
                    Assert.assertTrue(sortedList.equals(obtainedList));
                }
    }
}
