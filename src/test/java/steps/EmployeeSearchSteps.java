package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {

    @Given("user is navigated to HRMS")
    public void user_is_navigated_to_hrms() {
        openBrowser();
    }

    @Given("user is logged in with valid admin credentials")
    public void user_is_logged_in_with_valid_admin_credentials() {
        LoginPage loginPage = new LoginPage();
       sendText(loginPage.usernameBox, ConfigReader.getPropertyValue("username"));
       sendText(loginPage.passwordBox, ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);
    }

    @And("user is navigated to employee list page")
    public void userIsNavigatedToEmployeeListPage() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.pimOption);
        click(dash.employeeListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        EmployeeListPage employeeListPage = new EmployeeListPage();
        sendText(employeeListPage.idEmployee, "20119000");
    }


    @When("clicks on search button")
    public void clicks_on_search_button() {
        EmployeeListPage emp = new EmployeeListPage();
        click(emp.searchButton);
    }

    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        System.out.println("info is displayed");

    }
    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        EmployeeListPage emp = new EmployeeListPage();
        sendText(emp.employeeNameField, "Ricky");
    }

}
