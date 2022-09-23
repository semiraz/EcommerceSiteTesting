package step_definitions;

import company.page_objects.*;
import company.test_components.BaseTest;;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinition extends BaseTest {
    LandingPage landingPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;
    ViewPage viewPage;
    int productsSize;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String name, String password) {
        productCatalogue = landingPage.loginApplication(name, password);
    }
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
        cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("bosnia");
        confirmationPage = checkoutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_confirmation_page(String message) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
        driver.close();
    }


    @Then("{string} message is displayed")
    public void messageIsDisplayed(String message) {
        Assert.assertEquals(message, landingPage.getErrorMessage());
        driver.close();
    }

    @When("^I enter (.+) to SearchBox$")
    public void iEnterProductNameToSearchBox(String productName) throws InterruptedException {
        productsSize = productCatalogue.filterProduct(productName);
    }

    @Then("{int} element is filtered")
    public void elementIsFiltered(int pSize) {
        Assert.assertEquals(productsSize, pSize);
        driver.close();
    }

    @When("^I click on view button on (.+) and go to view page$")
    public void iClickOnViewButtonAndGoToViewPage(String productName) throws InterruptedException {
        viewPage = productCatalogue.goToViewProductPage(productName);
    }

    @Then("{string} text is displayed")
    public void textIsDisplayed(String productName) {
        String productDetailsText = viewPage.getProductDetails();
        Assert.assertEquals(productName, productDetailsText);
        driver.close();
    }

    @And("^I go to Cart and click on delete button on (.+)$")
    public void iGoToCartAndClickOnDeleteButtonOnProductName(String productName) {
        cartPage = productCatalogue.goToCartPage();
        cartPage.deleteProduct(productName);
    }

    @Then("^(.+) is deleted and (.+) is displayed$")
    public void productNameIsDeletedAndMessageIsDisplayed(String productName, String message) {
        String expectedMessage = cartPage.getNoProductsMessage(productName);
        Assert.assertEquals(message, expectedMessage);
        driver.close();
    }

}
















