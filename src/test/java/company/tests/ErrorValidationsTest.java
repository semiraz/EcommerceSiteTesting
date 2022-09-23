package company.tests;

import company.page_objects.CartPage;
import company.page_objects.ProductCatalogue;
import company.test_components.BaseTest;
import company.test_components.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
        //wrong password
        landingPage.loginApplication("hahah@gmail.com", "555555");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("haha@gmail.com", "12345678");
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay("ZARA COAT 5");
        Assert.assertFalse(match);

    }

}







