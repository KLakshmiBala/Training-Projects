package stepDefinations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.PriceFilterPage;

public class ValidateFilterByPriceStep extends Base {
	
	PriceFilterPage priceFilterPage;
	WebElement filterByPrice;
	String inputRange;
	List<WebElement>flowerList = new ArrayList<>();
	
	private static final Logger logger = LogManager.getLogger(ValidateFilterByPriceStep.class);	
	
	@Given("user is on search screen and in stock is checked")
	public void user_is_on_search_screen_and_in_stock_is_checked() {
	    try {
	    	Thread.sleep(3000);
	    	getDriver().get(this.getFrameworkUrl());
	    	Thread.sleep(3000);
	    	priceFilterPage = new PriceFilterPage(getDriver());
	    	priceFilterPage.getInStockCheckbox().click();	    	
	    }
	    catch(Exception ex) {
	    	System.out.println(ex.getMessage());
	    	logger.debug("Exception occurred:" + ex.getMessage());
	    }
	}
	
	@When("Range {string} is selected")
	public void range_is_selected(String range) {
		try {
			priceFilterPage = new PriceFilterPage(getDriver());
			filterByPrice = priceFilterPage.getPriceFilter();
			inputRange = range;
			Select selectFilter = new Select(filterByPrice);
			selectFilter.selectByContainsVisibleText(inputRange);
			Thread.sleep(3000);
			flowerList = priceFilterPage.getItemCard();
			System.out.println("size:" + flowerList.size());			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());	
			logger.debug("Exception occurred:" + ex.getMessage());
		}
	    
	}
	
	@Then("price is validated")
	public void price_is_validated() {
		try {
			System.out.print(flowerList.size() + "****SIZE");
			for(int i=1;i<=flowerList.size();i++) {
				String text = getDriver().findElement
						(By.xpath("(//p[contains(@data-testid,'flower-price')])["+i+"]")).getText();
						text = text.replaceAll("[^0-9]","");
						//Assert.assertTrue("", text)
						System.out.println(text);
						
						if(inputRange.equals("Below ₹60") && !text.isEmpty()) {
							int value = Integer.parseInt(text);
							Assert.assertTrue(value < 60, "Value is not less than 60");
						}
						else if(inputRange.equals("₹60 - ₹70") && !text.isEmpty()) {
							int value = Integer.parseInt(text);
							Assert.assertTrue(value >= 60, "Value is not less than 60");
							Assert.assertTrue(value <= 70, "Value is not greater than 70");
						}
						else if(inputRange.equals("Above ₹70") && !text.isEmpty()) {
							int value = Integer.parseInt(text);
							Assert.assertTrue(value >= 70, "Value is greater than 70");						
						}
			}
			System.out.println("Price Filter For " + inputRange + " is working");
			logger.debug("Price Filter For " + inputRange + " is working");
			Thread.sleep(3000);		
			System.out.println("URL last" + getDriver().getCurrentUrl());		
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());	
			logger.debug("Exception occurred:" + ex.getMessage());
		}
	    
	}

}
