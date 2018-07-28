package StepDefinition;

import java.io.IOException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.MyAccount;
import pageObjects.PhonePDAPage;
import resources.base;

public class ProductComparison extends base{
	@Given("^I login to Opencart Application$")
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@And("^In Between Steps$")
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
	
	@And("^I Close the browser$")
	public void closeBrowser() {
		driver.close();
	}
}
