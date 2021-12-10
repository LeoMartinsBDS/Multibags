package multibags;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.UUID;
import multibags.forms.XPath;
import multibags.forms.newaccount.PersonalInformationForm;
import multibags.forms.newaccount.SignInForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewAccountStepsDefinitions {

    private final String REGISTRATION_URL = "http://multibags.1dt.com.br/shop/customer/registration.html";
    private final String SHOULD_GENERATE_RANDOM_EMAIL = "<random-email>";

    private WebDriver driver;

    @Before
    public void setHeadless() {
        FirefoxOptions options = new FirefoxOptions();
        boolean useHeadless = System.getenv("USE_WEB_DRIVER_HEADLESS").equals("true");
        options.setHeadless(useHeadless);
        driver = new FirefoxDriver(options);
    }

    @After
    public void afterEachTest() {
        driver.quit();
    }

    @Given("that the user accessed the functionality register")
    public void register(DataTable table) {
        driver.get(REGISTRATION_URL);
    }

    @Given("the first name {string}")
    public void the_first_name(String firstName) {
        findElementByXPath(PersonalInformationForm.FIRST_NAME)
                .sendKeys(firstName);
    }

    @Given("the last name {string}")
    public void the_last_name(String lastName) {
        findElementByXPath(PersonalInformationForm.LAST_NAME)
                .sendKeys(lastName);
    }

    @Given("the country {string}")
    public void the_country(String country) {
        new Select(findElementByXPath(PersonalInformationForm.COUNTRY))
                .selectByVisibleText(country);
    }

    @Given("the state {string}")
    public void the_state_province(String state) {
        findElementByXPath(PersonalInformationForm.STATE)
                .sendKeys(state);
    }

    @Given("the email address {string}")
    public void the_email_address(String email) {
        String emailToSet = email;
        if (email.equals(SHOULD_GENERATE_RANDOM_EMAIL)) {
            emailToSet = UUID.randomUUID() + "@test.com";
        }
        findElementByXPath(SignInForm.EMAIL)
                .sendKeys(emailToSet);
    }

    @Given("the new account password {string}")
    public void the_password(String password) {
        findElementByXPath(SignInForm.PASSWORD)
                .sendKeys(password);
    }

    @Given("the repeat password {string}")
    public void the_repeat_password(String repeatPassword) {
        findElementByXPath(SignInForm.REPEAT_PASSWORD)
                .sendKeys(repeatPassword);
    }

    @When("click on the CREATE AN ACCOUNT button")
    public void click_on_the_button_register() {
        findElementByXPath(SignInForm.BUTTON_CREATE_NEW_ACCOUNT)
                .click();
    }

    @Then("redirected to the profile page {string}")
    public void redirected_to_the_profile_page(String successfully) {
        WebDriverWait wait = new WebDriverWait(driver,10L);
        String url = driver.getCurrentUrl();

        if (successfully.equals("true")) {
            WebElement main = driver.findElement(By.xpath("//*[@id=\"main-content\"]"));
            wait.until(ExpectedConditions.visibilityOf(main));
            assertTrue(url.contains("dashboard"));
        } else {
            WebElement errors = driver.findElement(By.xpath("//*[@id=\"customer.errors\"]"));
            wait.until(ExpectedConditions.visibilityOf(errors));
            assertNotNull(errors);
            assertFalse(url.contains("dashboard"));
        }
    }

    private WebElement findElementByXPath(XPath xpath) {
        return driver.findElement(By.xpath(xpath.getXpath()));
    }
}
