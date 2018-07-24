package TrainingTest1;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.MyAccount;
import pageObjects.PhonePDAPage;
import resources.base;

public class ProductComparison extends base{
	@BeforeTest
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@Test
	public void productcompare() throws IOException, InterruptedException {
		HomePage home = new HomePage(driver);
		MyAccount account = new MyAccount(driver);
		PhonePDAPage phone = new  PhonePDAPage(driver);
		CartPage cart = new CartPage(driver);
		home.Login();
		account.searchProduct();
		account.saveFlatFile("apple_searchFlatFile");
		phone.phoneTab();
		phone.saveFlatFile("feature_flatFile");
		cart.addtoCart();
		cart.checkOut();
		driver.switchTo().alert().accept();
		cart.validateCheckOut();
		cart.validateCart();
		home.orderHistory();
		cart.saveFlatFile("order_flatFile");
		home.suscription();
		home.specials();
		home.LogOut();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
