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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefinitions {

    private WebDriver driver;

    @Before
    public void setHeadless()
    {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
    }
    @Given("that the user accessed the functionality login")
    public void logIn(DataTable table) {
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
    public void should_login(String successfully) {
        WebDriverWait wait = new WebDriverWait(driver,10L);
        WebElement loading = driver.findElement(By.className("loadingoverlay"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(loading)));
        String url = driver.getCurrentUrl();
        System.out.println(url);
        assertEquals(successfully.equals("true"), url.contains("dashboard"));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
