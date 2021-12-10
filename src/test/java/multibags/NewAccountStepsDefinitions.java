package multibags;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewAccountStepsDefinitions {

    private final WebDriver driver = new FirefoxDriver();

    @Given("that the user accessed the functionality register")
    public void register(DataTable table) {
        driver.get("http://multibags.1dt.com.br/shop/customer/registration.html");
    }

    @Given("the first name {string}")
    public void the_first_name(String string) {
        throw new io.cucumber.java.PendingException();
    }
    @Given("the last name {string}")
    public void the_last_name(String string) {
        throw new io.cucumber.java.PendingException();
    }
    @Given("the country {string}")
    public void the_country(String string) {
        throw new io.cucumber.java.PendingException();
    }

    @Given("the state\\/province {string}")
    public void the_state_province(String string) {
        throw new io.cucumber.java.PendingException();
    }

    @Given("the email address {string}")
    public void the_email_address(String string) {
        throw new io.cucumber.java.PendingException();
    }

    @Given("the repeat password {string}")
    public void the_repeat_password(String string) {
        throw new io.cucumber.java.PendingException();
    }

    @When("click on the CREATE AN ACCOUNT button")
    public void click_on_the_button_register(String string) {

    }

    @Then("redirected to the profile page")
    public void redirected_to_the_profile_page() {
        throw new io.cucumber.java.PendingException();
    }

}
