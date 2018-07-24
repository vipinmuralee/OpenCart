package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import resources.utils;

public class GalaxyTabPage extends utils{
	@FindBy(xpath = "//a[@href=\"#tab-review\"]")
	private WebElement ReviewTab;
	@FindBy(id = "input-review")
    private WebElement Review;
	@FindBy(name="rating")
    private List<WebElement> Rating;
	@FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement ReviewContinue;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement ReviewWarning;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement ReviewSuccess;
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement AddWishList;
	@FindBy(xpath = "//button[@class='close']")
	private WebElement CloseList;
	
	public GalaxyTabPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void reviewProduct(String review, int row, int rating) throws IOException {
		ReviewTab.click();
		Review.sendKeys(getCelldata("ReviewProduct",row,0));
		Rating.get(rating).click();
		ReviewContinue.click();
		if (review == "Success"){
			String strSuccess = ReviewSuccess.getText();
			if (strSuccess.contains("Thank you for your review. It has been submitted to the webmaster for approval.")) {
				Reporter.log("\n Review Submitted Successfully");
			}				
		}
		else if (review == "Warning") {
			String strWarning = ReviewWarning.getText();
			if(strWarning.contains("Warning: Review Text must be between 25 and 1000 characters!")) {
				Reporter.log("\n Review Warning Message Dispalyed");		
			}
		}
	}
	
	public void wishList() throws InterruptedException {
		AddWishList.click();
		Thread.sleep(2000);
		CloseList.click();
	}

}