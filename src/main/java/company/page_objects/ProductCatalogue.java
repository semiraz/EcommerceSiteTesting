package company.page_objects;

import company.abstract_components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    @FindBy(id = "sidebar")
    private WebElement sidebarSection;

    @FindBy(xpath = "(//input[@placeholder='search'])[2]")
    private WebElement searchButton;


    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private By productsBy = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".card-body button:last-of-type");
    private By toastMessage = By.cssSelector("#toast-container");
    private By viewButton = By.xpath("//button[text()=' View']");


        public int filterProduct(String searchFor) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.sendKeys(searchButton, searchFor).build().perform();
        actions.keyDown(Keys.ENTER).build().perform();
        Thread.sleep(1000);
        return products.size();
    }

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }


    public WebElement getProductByName(String productName) {
                WebElement prod = getProductList().stream()
                        .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                        .findFirst()
                        .orElse(null);
                return prod;
    }


    public ViewPage goToViewProductPage(String productName) throws InterruptedException {
        getProductByName(productName).findElement(viewButton).click();
        Thread.sleep(1000);
        return new ViewPage(driver);
    }


    public void addProductToCart(String productName) throws InterruptedException {
        getProductByName(productName).findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}














