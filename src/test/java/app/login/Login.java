package app.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import app.base.Base;

public class Login extends Base{
	public Login()
	{
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//input[@id='username']")
	private WebElement userName;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login")
	private WebElement login;
	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}
	
	public WebElement getLogin() {
		return login;
	}
	


}
