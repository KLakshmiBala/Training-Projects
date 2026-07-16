package stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddToCartPage;

public class ValidateAddToCartStep extends Base {
	AddToCartPage addToCartPage;
	String userFlower;
	String userQuantity;
	
	private static final Logger logger = LogManager.getLogger(ValidateAddToCartStep.class);
	
	@Given("clear filter is applied")
	public void user_is_on_the_search_page_and_clear_filter_is_applied() {
		try {
			Thread.sleep(3000);
			//driver.get("https://www.engineerdiaries.com/framework");
			//Thread.sleep(5000);
			addToCartPage = new AddToCartPage(getDriver());
			System.out.println("REACHED CART");
			//System.out.println(driver.getCurrentUrl());
			Thread.sleep(3000);
			addToCartPage.getClearFilter().click();
			//addToCartPage.getClearFilter(getDriver()).click();
			System.out.println("CLEAR FILTER IS CLICKED");
			logger.debug("clear filter is applied");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.debug("Exception occurred:" + ex.getMessage());
		}
	    
	}
	
	@When("user search flower {string} and quantity {string}")
	public void user_search_flower_and_quantity(String flower, String quantity) {
		try {
			userFlower = flower;
			userQuantity = quantity;
			//System.out.println("REACHED METHOD2" + flower);
			addToCartPage.getInputSearch().sendKeys(flower);
			Thread.sleep(3000);
			for(int i=1;i<=Integer.parseInt(quantity);i++) {
				addToCartPage.getAddToCartBtn().click();
				Thread.sleep(3000);
			}
			logger.debug("item added to cart");
			Thread.sleep(3000);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}		
	    
	}
	
	@Then("validate correct item is added")
	public void validate_correct_item_is_added() {
		try {
			addToCartPage.getCartBtn().click();
			Thread.sleep(3000);
			String flower = addToCartPage.getItemName().getText();
			System.out.println("FLOWER:" + flower);
			Assert.assertTrue(flower.equals(userFlower));		
			logger.debug("CORRECT ITEM GOT ADDED TO CART");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}
	    
	}
	
	@Then("validate correct item quantity")
	public void validate_correct_item_quantity() {
		try {
			Thread.sleep(3000);
			String quantity = addToCartPage.getItemQuantity().getText();
			quantity = quantity.replaceAll("x","");
			System.out.println(quantity);
			Assert.assertTrue(quantity.equals(userQuantity));
			logger.debug("CORRECT QUANTITY GOT ADDED TO CART");
			//assertion
			logger.debug("TEST CASE IS PASSED");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}
	    
	}

}
