package frame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class capabilities {
	public static String appactivity;
	public static String apppackage;
	public static String devicename;
	public static String platformname;
	public AppiumDriverLocalService service;
    //for starting the server
    public AppiumDriverLocalService startServer()
    {
        boolean flag = checkifserverisRunning(4723); //port code
        if(!flag)
        {
    service = AppiumDriverLocalService.buildDefaultService();//if the same services are available
    service.start();
            }
        return service;
    }
    
    public static boolean checkifserverisRunning(int port)
    {
        boolean isServerRunning=false;
        ServerSocket serversocket;
        try{
            serversocket = new ServerSocket(port);
            serversocket.close();
        }
        catch(IOException e)
        {
            isServerRunning = true;
        }
        finally {
            serversocket=null;
        }
        return isServerRunning;
    }
//	public static void startemulator() throws IOException {
//		Runtime.getRuntime().exec(System.getProperty("user.dir")+"//src//main//resources//extension.bat");
//	}                                                              //for opening automatically emulator
	public static AndroidDriver<AndroidElement> famecapa1(String appactivity,String apppackage,String devicename,String platformname) throws IOException {
		FileReader rp = new FileReader(System.getProperty("user.dir")+"//src/main/java/frame/global.properties");
		Properties pro = new Properties();
		pro.load(rp);
		appactivity = pro.getProperty("appactivity");
		apppackage = pro.getProperty("apppackage");
		devicename = pro.getProperty("devicename");
		platformname = pro.getProperty("platformname");
//		if(devicename.equals("Tanishka")) {
//			startemulator();
//		}                                               //for starting automatically emulator
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"devicename");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"platformname");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
		  dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"apppackage");
	        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"appactivity");
	        AndroidDriver<AndroidElement>  driver=new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);  
		return driver;
	}
}
