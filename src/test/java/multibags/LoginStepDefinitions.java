package multibags;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {

    private WebDriver driver;

    @Before
    public void setUp(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);
    }

    @Given("that the user accessed the functionality login")
    public void logiIn(DataTable table) {
            driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");
    }

    @Given("the customer email address {string}")
    public void the_customer_email_address(String email) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"signin_userName\"]"));
        element.sendKeys(email);
    }

    @Given("the password {string}")
    public void the_password(String password) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"signin_password\"]"));
        element.sendKeys(password);
    }

    @When("click on the SIGN IN button")
    public void click_on_the_button() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"genericLogin-button\"]"));
        element.click();
    }

    @Then("should login {string}")
    public void should_login(String succesfully) {

        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                    if(d.findElements(By.xpath("//*[@id=\"loginError\"]")).size() != 0)
                        assertFalse(Boolean.parseBoolean(succesfully));
                    else
                        assertTrue(Boolean.parseBoolean(succesfully));

                    return true;
            }
        });
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
