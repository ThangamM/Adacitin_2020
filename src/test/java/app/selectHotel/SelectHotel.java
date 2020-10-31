package app.selectHotel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import app.base.Base;

public class SelectHotel extends  Base {
@FindBy(id="radiobutton_0")
private WebElement hotelselt;
@FindBy(id="continue")
private WebElement Continue;
public WebElement getHotelselt() {
	return hotelselt;
}
public WebElement getContinue() {
	return Continue;
}

}
