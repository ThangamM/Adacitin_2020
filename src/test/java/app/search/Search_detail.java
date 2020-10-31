package app.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import app.base.Base;

public class Search_detail extends Base {
	@FindBy(id="location")
	private WebElement location;
	@FindBy(id="hotels")
	private WebElement hotels;
	@FindBy(id="room_type")
	private WebElement Room_type;
	public WebElement getLocation() {
		return location;
	}
	public WebElement getHotels() {
		return hotels;
	}
	public WebElement getRoom_type() {
		return Room_type;
	}
	public WebElement getRoom_num() {
		return Room_num;
	}
	public WebElement getCheckin() {
		return Checkin;
	}
	public WebElement getCheckout() {
		return Checkout;
	}
	public WebElement getNum_Adults() {
		return Num_Adults;
	}
	public WebElement getNum_Children() {
		return Num_Children;
	}
	public WebElement getNext() {
		return Next;
	}
	@FindBy(id="room_nos")
	private WebElement Room_num;
	@FindBy(id="datepick_in")
	private WebElement Checkin;
	@FindBy(id="datepick_out")
	private WebElement Checkout;
	@FindBy(id="adult_room")
	private WebElement Num_Adults;
	@FindBy(id="child_room")
	private WebElement Num_Children;
	@FindBy(id="Submit")
	private WebElement Next;
	
}
