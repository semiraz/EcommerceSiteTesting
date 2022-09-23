package company.page_objects;

import company.abstract_components.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productTitles;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutElement;

    @FindBy(css = ".btn-danger")
    private List<WebElement> deleteBtn;

    @FindBy(css = "div[class='ng-star-inserted'] h1")
    private WebElement cartIsEmptyMsg;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductDisplay(String productName) {
        return productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutElement.click();
        return new CheckoutPage(driver);
    }


    public void deleteProduct(String productName) {
        if (verifyProductDisplay(productName)) {
            for (WebElement d : deleteBtn) {
                d.click();
            }
        }
    }

    private boolean verifyProductIsDeleted(String productName) {
        return productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public String getNoProductsMessage(String productName) {
        if (verifyProductIsDeleted(productName)) {
            return cartIsEmptyMsg.getText();
        }
        return null;
    }

}
