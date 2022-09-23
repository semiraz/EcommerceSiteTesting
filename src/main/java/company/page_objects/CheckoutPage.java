package company.page_objects;

import company.abstract_components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "input[placeholder='Select Country']")
    private WebElement country;

    @FindBy(css = ".ta-item:nth-of-type(1)")
    private WebElement selectCountryElement;

    @FindBy(css = ".action__submit")
    private WebElement submit;

    private By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountryElement.click();

    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }

}