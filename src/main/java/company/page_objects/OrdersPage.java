package company.page_objects;

import company.abstract_components.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productName;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public boolean verifyOrderDisplay(String productName) {
        return this.productName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }
}
