package multibags;

import io.cucumber.datatable.DataTable;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePasswordStepsDefinitions {

    private WebDriver driver;

    @Before
    public void setUp(){


        driver = new FirefoxDriver();
    }

    @Given("an logged user accessed the functionality")
    public void an_logged_user_accessed_the_functionality(DataTable dataTable) {

        driver.get("http://multibags.1dt.com.br/shop/customer/customLogon.html");

        WebElement usuario = driver.findElement(By.xpath("//*[@id=\"signin_userName\"]"));
        usuario.sendKeys("teste@teste.com.br");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"signin_password\"]"));
        password.sendKeys("123456");

        WebElement logar = driver.findElement(By.xpath("//*[@id=\"genericLogin-button\"]"));
        logar.click();


        new WebDriverWait(driver,120).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                driver.get("http://multibags.1dt.com.br/shop/customer/password.html");

                return true;
            }
        });

    }

    @Given("the current password {string}")
    public void the_current_password(String currentPassword) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"currentPassword\"]"));
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

    }

    @Then("show message")
    public void show_message() {
    }

}
