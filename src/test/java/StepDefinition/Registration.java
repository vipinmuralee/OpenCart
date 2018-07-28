package StepDefinition;

import java.io.IOException;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.GalaxyTabPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import pageObjects.WishListPage;
import resources.base;

@Test
public class Registration extends base{
	public static Logger log = Logger.getLogger(base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	public void createAccount() throws IOException, InterruptedException {
		HomePage home = new HomePage(driver);
		RegisterPage reg = new RegisterPage(driver);
		GalaxyTabPage galaxy = new GalaxyTabPage(driver);
		WishListPage wishlist = new WishListPage(driver);
		home.Register();
		reg.Register();
		reg.contactVerify();
		galaxy.reviewProduct("Warning",0,4);
		galaxy.reviewProduct("Success",1,0);
		galaxy.wishList();
		wishlist.verifyWishList("Samsung Galaxy Tab 10.1");
		wishlist.getCurrency();
		home.LogOut();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
