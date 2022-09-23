package company.tests;

import company.data.StaticDataProvider;
import company.page_objects.CartPage;
import company.page_objects.ProductCatalogue;
import company.test_components.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class DeleteProductFromCartTest extends BaseTest {
    ProductCatalogue productCatalogue;

    @Test(dataProvider = "getData", dataProviderClass = StaticDataProvider.class)
    public void deleteOrder(HashMap<String, String> input) throws InterruptedException {
        productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));

        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.deleteProduct(input.get("productName"));

        String message = cartPage.getNoProductsMessage(input.get("productName"));
        Assert.assertEquals(message, "No Products in Your Cart !");
    }

}
