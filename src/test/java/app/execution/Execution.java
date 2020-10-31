package app.execution;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import app.base.Base;
import app.book.Book;
import app.login.Login;
import app.search.Search_detail;
import app.selectHotel.SelectHotel;

public class Execution extends Base {

	@Test
	public void test1() {

		Base.url("http://adactinhotelapp.com/");
		Base.typeData(new Login().getUserName(), "TharaniArjun");
		Base.typeData(new Login().getPassword(), "Tharsarj");
		Base.waitfn();
		Base.clickData(new Login().getLogin());
		Base.drop(new Search_detail().getLocation(), "London");
		Base.drop(new Search_detail().getHotels(), "Hotel Creek");
		Base.drop(new Search_detail().getRoom_type(), "Super Deluxe");
		Base.drop(new Search_detail().getRoom_num(), "1 - One");
		Base.drop(new Search_detail().getCheckin(), "27/10/2020");
		Base.drop(new Search_detail().getCheckout(), "29/10/2020");
		Base.drop(new Search_detail().getNum_Adults(), "2 - Two");
		Base.drop(new Search_detail().getNum_Children(), "1 - One");
		Base.clickData(new Search_detail().getNext());
		Base.clickData(new SelectHotel().getHotelselt());
		Base.clickData(new SelectHotel().getContinue());
		Base.typeData(new Book().getF_name(), "Tharani");
		Base.typeData(new Book().getL_name(), "Arjun");
		Base.typeData(new Book().getAddress(), "India");
		Base.typeData(new Book().getCard_num(), "1234567890123456");
		Base.drop(new Book().getCard_type(), "VISA");
		Base.drop(new Book().getExp_mon(), "April");
		Base.drop(new Book().getExp_year(), "2022");
		Base.typeData(new Book().getCvv(), "234");
		Base.clickData(new Book().getBknow());

	}

}
