package app.book;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import app.base.Base;

public class Book extends Base{
@FindBy(id="first_name")
private WebElement f_name;
@FindBy(id="last_name")
private WebElement l_name;
@FindBy(id="address")
private WebElement Address;
@FindBy(id="cc_num")
private WebElement card_num;
@FindBy(id="cc_type")
private WebElement card_type;
@FindBy(id="cc_exp_month")
private WebElement exp_mon;
@FindBy(id="cc_exp_year")
private WebElement exp_year;
@FindBy(id="cc_cvv")
private WebElement Cvv;
@FindBy(id="book_now")
private WebElement Bknow;
public WebElement getBknow() {
	return Bknow;
}
public WebElement getF_name() {
	return f_name;
}
public WebElement getL_name() {
	return l_name;
}
public WebElement getAddress() {
	return Address;
}
public WebElement getCard_num() {
	return card_num;
}
public WebElement getCard_type() {
	return card_type;
}
public WebElement getExp_mon() {
	return exp_mon;
}
public WebElement getExp_year() {
	return exp_year;
}
public WebElement getCvv() {
	return Cvv;
}

}
