package stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Login;

public class LoginStep extends Base {
	Login login;
	
	private static final Logger logger = LogManager.getLogger(LoginStep.class);
	
	@Given("user is home page")
	public void user_is_home_page() {
		try {
			Thread.sleep(3000);
			logger.debug("Reached Method");
			getDriver().get(this.getUrl());
			//this.get(this.getUrl());
			Thread.sleep(3000);
			logger.debug("URL Launched");
			getDriver().manage().window().maximize();
			logger.debug("Window Maximized");
			Thread.sleep(3000);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());	
			logger.error("Exception occurred:" + ex.getMessage());
		}
	    
	}
	
	@When("clicks login")
	public void clicks_login() {
		try {
			login = new Login(getDriver());
			Thread.sleep(3000);
			login.getsignUpAndInBtn().click();
			Thread.sleep(3000);
			login.getSignInBtn().click();
			Thread.sleep(3000);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}	    
	    
	}
	
	@When("user enters email {string} and password {string}")
	public void user_enters_email_and_password(String email, String password) {
		try {
			login.getEmail().sendKeys(email);
			Thread.sleep(3000);
			login.getPassword().sendKeys(password);
			Thread.sleep(3000);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}	    
	    
	}
	
	@Then("user is successfully logged in")
	public void user_is_successfully_logged_in() {
		try {
			Thread.sleep(3000);
			login.getSubmitBtn().click();
			Thread.sleep(3000);
			Assert.assertTrue(login.getLoginText().getText().contains("Hi"),"Login Failure");
			logger.debug("User is successfully logged in");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			logger.error("Exception occurred:" + ex.getMessage());
		}
	    
	}

}
