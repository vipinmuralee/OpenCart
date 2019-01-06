package SeleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class seleniumGrid {

	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();//creating desired capability oblect as per the browser 
		dc.setBrowserName("chrome");//setting browser name which we want to automate
		dc.setPlatform(Platform.WIN10);//set platform of the node system
		String con ="http://192.168.0.104:4444/wd/hub";
		WebDriver driver = new RemoteWebDriver(new URL(con),dc);//passing desiredcapabilities object

		//below is the program which iis going to run in the node system
			driver.get("https://www.opencart.com/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//*[@id=\"navbar-collapse-header\"]/div/a[1]")).click();
			driver.quit();
	}

}
