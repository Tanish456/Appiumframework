package frame;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Test_case extends capabilities {
	AndroidDriver<AndroidElement> driver;
	@BeforeTest
	public void bt() throws IOException {
		service = startServer();
		driver = famecapa1(appactivity,apppackage,devicename,platformname);
	}
	@Test
	public void app() throws InterruptedException  {
		//service = startServer;
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	        Thread.sleep(3000);
	        service.stop();    //for stop server
}}
