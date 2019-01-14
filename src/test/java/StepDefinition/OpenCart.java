package StepDefinition;

import java.io.File;
import java.io.IOException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.MyAccount;
import pageObjects.PhonePDAPage;
import resources.base;

public class OpenCart extends base {

	@Given("^I open Opencart Application$")
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	@And("^In Between Steps$")
	public void productcompare() throws IOException, InterruptedException {
        HomePage home = new HomePage(driver);
		home.Login();
        MyAccount account = new MyAccount(driver);
	    PhonePDAPage phone = new PhonePDAPage(driver);
	    CartPage cart = new CartPage(driver);
		account.searchProduct();
		account.saveFlatFile("apple_searchFlatFile");
		phone.phoneTab();
		phone.saveFlatFile("feature_flatFile");
		cart.addtoCart();
		cart.checkOut(driver);
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

	@And("^I login with invalid Email$")
	public void iLoginWithInvalidEmail() throws IOException {
	    HomePage home = new HomePage(driver);
		home.invalidEmail();
	}

	@And("^I login with invalid Password$")
	public void iLoginWithInvalidPassword() throws Throwable {
        HomePage home = new HomePage(driver);
		home.invalidPassword();
        Assert.assertEquals(home.ErrorMessage.getText(), "Warning: No match for E-Mail Address and/or Password.");
	}

	@And("^I login with Valid Username and password$")
	public void iLoginWithValidUsernameAndPassword() throws Throwable {
        HomePage home = new HomePage(driver);
		home.Login();
	}

	@And("^I log Off$")
	public void iLogOff() throws Throwable {
        HomePage home = new HomePage(driver);
		home.LogOut();
        driver.close();
	}

    @And("^I enter Search text \"([^\"]*)\"$")
    public void iEnterSearchText(String search) throws IOException {
        HomePage home = new HomePage(driver);
        home.searchProduct(search);
    }

    @And("^I click on the product and Add to Cart$")
    public void iClickOnTheProductAndAddToCart() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.clickOnFirstResult();
        cart.addtoCart();
    }

    @And("^Continue shopping$")
    public void continueShopping() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.continueShopping.click();
    }

    @When("^I Confirm order$")
    public void iConfirmOrder() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.checkOut(driver);
        cart.validateCheckOut();
        cart.validateCart();
    }

	@When("^I remove Item from cart$")
	public void iRemoveItemFromCart() throws Throwable {
		CartPage cart = new CartPage(driver);
		cart.RemoveItem.click();
	}

    @When("^I enter Quantity and update cart$")
    public void iEnterQuantityAndUpdateCart() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.updateCart("2");
    }

    @And("^I select \"([^\"]*)\" from \"([^\"]*)\" category$")
    public void iSelectFromCategory(String subCategory, String Category) throws Throwable {
        HomePage home = new HomePage(driver);
        home.selectCategory(driver, subCategory, Category);
    }

    @And("^I click on the product$")
    public void iClickOnTheProduct() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.clickOnFirstResult();
    }

    @And("^I verify product description is displayed$")
    public void iVerifyProductDescriptionIsDisplayed() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.ProductDescription.isDisplayed();
    }

    @Then("^I view additional Images for the item$")
    public void iViewAdditionalImagesForTheItem() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.viewImages(driver);
    }

    @And("^I click on Edit Account$")
    public void iClickOnEditAccount() throws Throwable {
        HomePage home = new HomePage(driver);
        home.EditAccount.click();
    }

    @Then("^I Edit the phone number (\\d+)$")
    public void iEditThePhoneNumber(String phoneNumber) throws Throwable {
        HomePage home = new HomePage(driver);
        home.editPhone(phoneNumber);
    }

    @When("^I Sort and verify using (.*)$")
    public void iSortAndVerifyUsing(String sort) throws Throwable {
        HomePage home = new HomePage(driver);
        home.sortBy(driver, sort);
    }

    @When("^I click on CheckOut$")
    public void iClickOnCheckOut() throws Throwable {
        HomePage home = new HomePage(driver);
        home.CheckOut.click();
    }

    @Then("^I validate the error message$")
    public void iValidateTheErrorMessage() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.validateCart();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("D:\\Selenium\\SeleniumTopGear\\OpenCart\\screenshots\\screenshot.png"));
    }

    @And("^I add New Address details$")
    public void iAddNewAddressDetails() throws Throwable {
        CartPage cart = new CartPage(driver);
        cart.addNewAddress();
        cart.continueChekout(driver);
        cart.validateCheckOut();
        cart.validateCart();
    }
}
