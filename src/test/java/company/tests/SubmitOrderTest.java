package company.tests;

import company.data.StaticDataProvider;
import company.page_objects.*;
import company.test_components.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//Select Sample Ecommerce Application to automate the end2end flow
public class SubmitOrderTest extends BaseTest {
    String productName = "zara coat 3";
    ProductCatalogue productCatalogue;

    @Test(dataProvider = "getData",dataProviderClass = StaticDataProvider.class, groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));

        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("bosnia");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }


    @Test(dataProvider = "getData", dataProviderClass = StaticDataProvider.class)
    public void verifySearchButtonTest(HashMap<String, String> input) throws InterruptedException {
        productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        int productsSize = productCatalogue.filterProduct(productName);
        Assert.assertEquals(productsSize, 1);
    }

    @Test(dataProvider = "getData", dataProviderClass = StaticDataProvider.class)
    public void checkProductDetailsTest(HashMap<String, String> input) throws InterruptedException {
        productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        ViewPage viewPage = productCatalogue.goToViewProductPage(productName.toUpperCase());

        Assert.assertEquals("zara coat 3", viewPage.getProductDetails());
    }

    //To verify ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        //ZARA COAT 3
        ProductCatalogue productCatalogue = landingPage.loginApplication("haha@gmail.com", "12345678");
        OrdersPage ordersPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

}


