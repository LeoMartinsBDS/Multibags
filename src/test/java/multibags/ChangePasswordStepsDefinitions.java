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

import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordStepsDefinitions {

    private WebDriver driver;

    @Before
    public void setHeadless()
    {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
    }

    @Given("an logged user accessed the functionality")
    public void an_logged_user_accessed_the_functionality(DataTable dataTable) {

        driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");

        WebElement usuario = driver.findElement(By.xpath("//*[@id=\"signin_userName\"]"));
        usuario.sendKeys("teste@teste.com");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"signin_password\"]"));
        password.sendKeys("123456");

        WebElement logar = driver.findElement(By.xpath("//*[@id=\"genericLogin-button\"]"));
        logar.click();
        WebDriverWait wait = new WebDriverWait(driver,20L);
        WebElement loading = driver.findElement(By.className("loadingoverlay"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(loading)));
        String url = driver.getCurrentUrl();
        System.out.println(url);
        if(!url.contains("dashboard")){
            WebElement element = driver.findElement(By.xpath("//*[@id=\"loginError\"]"));
            System.err.println(element.getText());
            fail();
        }
        driver.get("http://multibags.1dt.com.br/shop/customer/password.html");

    }

    @Given("the current password {string}")
    public void the_current_password(String currentPassword) {
        WebDriverWait _wait = new WebDriverWait(driver, 1L);
        String xpath = "//*[@id=\"currentPassword\"]";
        _wait.until(d -> d.findElement(By.xpath(xpath)));
        WebElement element = driver.findElement(By.xpath(xpath));
        element.sendKeys(currentPassword);
    }

    @Given("the new password {string}")
    public void the_new_password(String newPassword) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        element.sendKeys(newPassword);
    }

    @Given("the repeat new password {string}")
    public void the_repeat_new_password(String repeatNewPassword) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"checkPassword\"]"));
        element.sendKeys(repeatNewPassword);
    }

    @When("click on the CHANGE PASSWORD button")
    public void click_on_the_button() {
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"submitChangePassword\"]"));
        if(submit.isEnabled()){
            submit.click();
            WebDriverWait wait = new WebDriverWait(driver,10L);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkPassword\"]")));
        }
    }

    @Then("show message {string} and set {int}")
    public void show_message(String message, int success) {
        WebDriverWait wait = new WebDriverWait(driver,10L);
        WebElement formError = driver.findElement(By.xpath("//*[@id=\"formError\"]"));
        WebElement storeSuccess = driver.findElement(By.xpath("//*[@id=\"store.success\"]"));
        System.out.println(message);

        if (storeSuccess.isDisplayed()){
            assertEquals(1, success);
            assertTrue(driver.getCurrentUrl().contains("changePassword.html"));
        } else if(formError.isDisplayed()){
            assertEquals(0, success);
            assertFalse(driver.getCurrentUrl().contains("changePassword.html"));
        } else {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"checkPassword\"]"));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            WebElement passwdErrors = driver.findElement(By.xpath("//*[@id=\"password.errors\"]"));
            if (passwdErrors.isDisplayed()){
                assertEquals(0, success);
            } else { // Aí já não sei mais o que pode ser.
                fail();
            }
        }
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
