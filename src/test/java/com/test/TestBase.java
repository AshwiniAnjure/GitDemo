package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeSuite;



public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public TestBase() {
		try{
			prop=new Properties();//1
			FileInputStream ip=new FileInputStream(".\\src\\test\\resources\\config.properties");//2
			prop.load(ip);//3
		}catch(FileNotFoundException e)
		{
		e.printStackTrace();	
		}catch(IOException e)
		{
		e.printStackTrace();	
		}
	}
		@BeforeSuite
		public void initDriver(){
		String browsername=prop.getProperty("browser");
		if(browsername.equals("firefox"))
		{	
			System.setProperty("webdriver.firefox.marionette",prop.getProperty("firefoxexepath")); 
			driver=new FirefoxDriver();
			
			}else if(browsername.equals("chrome")) 
		  {	
			System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeexepath")); 
			driver=new ChromeDriver();
			
	 	 }
	  	 driver.manage().window().maximize();
	  	 driver.manage().deleteAllCookies();
	  	 driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	  	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	}


}
