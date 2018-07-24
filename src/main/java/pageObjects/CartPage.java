package pageObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
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
	@FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement CheckOut;
	@FindBy(xpath = "//input[@value='Continue']")
    private WebElement Continue;
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
}
