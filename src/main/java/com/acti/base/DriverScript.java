package com.acti.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.poifs.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class DriverScript {

public static WebDriver driver;
public static  Properties prop;

@Test
public static void DriverScript()
{
	try 
	{
	File file = new File("./config/config.properties");	
	FileInputStream fis	= new FileInputStream(file);
	 prop =new Properties();
	 prop.load(fis);
	}
	catch(Exception  e)
	{
		System.out.println("Unable to load the Properties file "+e.getMessage() );
	}}

public static void initBrowser() {
	
String browser = prop.getProperty("browser");
if(browser.equalsIgnoreCase("chrome"))
 {
	System.setProperty("webDriver.chrome.driver", "./Browsers/chromedriver.exe");
	driver = new ChromeDriver();
	}
else if(browser.equalsIgnoreCase("firefox")) {
	System.setProperty("webdriver.gecko.driver", "./Browsers/geckodriver.exe");
	driver = new FirefoxDriver();
}
else if(browser.equalsIgnoreCase("ie")){
	System.setProperty("webdriver.ie.driver", "./Browsers/IEDriverServer.exe");
	driver = new InternetExplorerDriver();
	
}
else{
System.out.println("No browser specified properly");
}	
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.manage().deleteAllCookies();

String url = prop.getProperty("url");
driver.get(url);


}

}
