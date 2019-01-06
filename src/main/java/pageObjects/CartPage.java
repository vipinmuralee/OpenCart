package pageObjects;

import java.io.*;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utils;

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
	private WebElement Message;
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
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void addtoCart() throws InterruptedException {
		AddCart.click();
		ShoppingCart.click();
	}
	
	public void checkOut() throws InterruptedException {
		CheckOut.click();	
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
	}
	
	public void validateCheckOut() {
		verifyValue(Message, "Your order has been placed!");
	}
	
	public void validateCart() {
		ShoppingCart.click();
		verifyValue(Message, "Your shopping cart is empty!");
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
}
