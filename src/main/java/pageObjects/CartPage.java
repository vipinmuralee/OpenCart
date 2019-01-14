package pageObjects;

import java.io.*;
import java.util.List;

import javafx.geometry.Pos;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import resources.utils;

import static org.testng.Assert.assertTrue;

public class CartPage extends utils{

	@FindBy(id = "button-cart")
	private WebElement AddCart;
	@FindBy(xpath = "//span[contains(text(),'Shopping Cart')]")
	private WebElement ShoppingCart;
	@FindBy(xpath = "//a[contains(text(),'Continue Shopping')]")
	public WebElement continueShopping;
	@FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement CheckOut;
	@FindBy(xpath = "//input[@value='Continue']")
    public WebElement Continue;
	@FindBy(xpath = "//input[@id='button-shipping-address']")
	private WebElement ShippingContinue;
	@FindBy(xpath = "//input[@id='button-shipping-method']")
	private WebElement ShippingMethod;	
	@FindBy(xpath = "//input[@id='button-payment-method']")
	private WebElement PayementMethod;
	@FindBy(name = "agree")
	private WebElement AgreeCheck;
	@FindBy(id = "button-confirm")
	private WebElement ConfirmOrder;
	@FindBy(id = "content")
    public WebElement Message;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover']")
	private WebElement OrderTable;
	@FindBy(xpath = "//*[@id='content']/div[3]/div/div/div[1]/a/img")
	private WebElement FirstResult;
	@FindBy(xpath = "//button[@data-original-title='Remove']")
	public WebElement RemoveItem;
	@FindBy(xpath = "//*[@id='content']//td[4]//input")
    private WebElement EnterQuantity;
    @FindBy(xpath = "//button[@data-original-title='Update']")
    private WebElement UpdateItem;
    @FindBy(xpath = "//button[@class='mfp-close']")
    private WebElement Close;
    @FindBy(id = "tab-description")
    public WebElement ProductDescription;
    @FindBy(xpath = "//input[@name='payment_address' and @value='new']")
	private WebElement NewAddress;
	@FindBy(id = "input-payment-firstname")
	private WebElement FirstName;
	@FindBy(id = "input-payment-lastname")
	private WebElement LastName;
	@FindBy(id = "input-payment-company")
	private WebElement Company;
	@FindBy(id = "input-payment-address-1")
	private WebElement Address1;
	@FindBy(id = "input-payment-address-2")
	private WebElement Address2;
	@FindBy(id = "input-payment-city")
	private WebElement City;
	@FindBy(id = "input-payment-postcode")
	private WebElement PostCode;
	@FindBy(id = "input-payment-country")
	private WebElement Country;
	@FindBy(id = "input-payment-zone")
	private WebElement Zone;

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void addtoCart() throws InterruptedException {
		AddCart.click();
		ShoppingCart.click();
	}
	
	public void checkOut(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		Continue.click();
		Thread.sleep(2000);
		ShippingContinue.click();
		Thread.sleep(2000);
		ShippingMethod.click();
		Thread.sleep(2000);
		AgreeCheck.click();
		PayementMethod.click();
		Thread.sleep(2000);
		ConfirmOrder.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        ConfirmOrder.click();
        Thread.sleep(2000);
	}
	
	public void validateCheckOut() {
        String actualString = Message.getText();
        assertTrue(actualString.contains("Your order has been placed!"));
	}
	
	public void validateCart() {
		ShoppingCart.click();
        String actualString = Message.getText();
        assertTrue(actualString.contains("Your shopping cart is empty!"));
	}
	
	public void saveFlatFile(String fileName) throws IOException {
		File file = new File("D:\\Selenium\\SeleniumTopGear\\OpenCart\\" +fileName+".txt");
		file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        int columnSize = OrderTable.findElements(By.xpath("table[@class='table table-bordered table-hover']/tbody/tr/td")).size();
		for(int i=1;i<columnSize+1; i++) {	
        	String value = OrderTable.findElement(By.xpath("table[@class='table table-bordered table-hover']/tbody/tr/td["+i+"]")).getText();
        	bw.newLine();
	        bw.write(value);
		}
        bw.newLine();
        bw.close();
	}

	public void clickOnFirstResult() {
		FirstResult.click();
	}

    public void updateCart(String quantity) {
	    EnterQuantity.clear();
	    EnterQuantity.sendKeys(quantity);
        UpdateItem.click();
    }

    public void viewImages(WebDriver driver) throws InterruptedException {
	    List<WebElement> images = driver.findElements(By.xpath("//*[@id='content']//ul[1]/li/a"));
	    for (int i = 1; i < images.size(); i++) {
            driver.findElement(By.xpath("//*[@id='content']//ul[1]/li[" + i + "]/a")).click();
            Thread.sleep(2000);
            Close.click();
            String imageName = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].innerHTML;", driver.findElement(By.xpath("//*[@id='content']//ul[1]/li[" + i + "]/a")));
            System.out.println(imageName);
        }
    }

	public void addNewAddress() throws InterruptedException {
		Thread.sleep(3000);
		NewAddress.click();
		FirstName.sendKeys("New");
		LastName.sendKeys("Name");
		Company.sendKeys("Wipro");
		Address1.sendKeys("Address1");
		Address2.sendKeys("Address2");
		City.sendKeys("Chennai");
		PostCode.sendKeys("600119");
		Select country = new Select(Country);
		country.selectByVisibleText("India");
		Select zone = new Select(Zone);
		zone.selectByVisibleText("Tamil Nadu");
	}

	public void continueChekout(WebDriver driver) throws InterruptedException {
		Continue.click();
		Thread.sleep(2000);
		ShippingContinue.click();
		Thread.sleep(2000);
		ShippingMethod.click();
		Thread.sleep(2000);
		AgreeCheck.click();
		PayementMethod.click();
		Thread.sleep(2000);
		ConfirmOrder.click();
		Thread.sleep(2000);
        driver.switchTo().alert().accept();
        ConfirmOrder.click();
        Thread.sleep(2000);
	}
}
