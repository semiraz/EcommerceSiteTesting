package company.page_objects;

import company.abstract_components.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".border-product p")
    private WebElement productDetailsNameElem;

    public ViewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductDetails () {
         return productDetailsNameElem.getText();
    }


}

