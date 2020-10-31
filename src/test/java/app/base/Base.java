package app.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Base {
	public static WebDriver driver;
public static void url(String url)
{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\thara\\eclipse-workspace\\Selenium_Project\\Driver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(url);
}
public static void clickData(WebElement element)
{
	element.click();
}
public static void typeData(WebElement element,String data)
{
	element.sendKeys(data);
}
public static void drop(WebElement element,String data)
{
	Select s=new Select(element);
	s.selectByVisibleText(data);
}
public static void waitfn()
{
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println();
	
}

}
