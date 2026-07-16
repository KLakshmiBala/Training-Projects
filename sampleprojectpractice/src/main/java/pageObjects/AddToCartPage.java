package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddToCartPage {
	WebDriver driver;
	By clearFilter = By.xpath("//*[text()='Clear Filters']");
	By inputSearch = By.xpath("//input[@data-testid='search-input']");
	By addToCartBtn = By.xpath("//*[text()='Add to Cart']");
	By cartBtn = By.xpath("//*[@data-testid='toggle-cart']");
	By itemName = By.xpath("//p[@class='font-semibold']");
	By itemQuantity = By.className("text-gray-600");
	
	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getClearFilter() {
		return this.driver.findElement(this.clearFilter);
	}

	public WebElement getInputSearch() {
		return this.driver.findElement(this.inputSearch);
	}

	public WebElement getAddToCartBtn() {
		return this.driver.findElement(this.addToCartBtn);
	}

	public WebElement getCartBtn() {
		return this.driver.findElement(this.cartBtn);
	}

	public WebElement getItemName() {
		return this.driver.findElement(this.itemName);
	}

	public WebElement getItemQuantity() {
		return this.driver.findElement(this.itemQuantity);
	}	

}
