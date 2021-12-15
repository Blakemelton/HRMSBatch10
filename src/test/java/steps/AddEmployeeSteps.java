package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dashBoardPage= new DashBoardPage();
        click(dashBoardPage.addEmployeeButton);
    }
    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage addEmployeePage= new AddEmployeePage();
        sendText(addEmployeePage.firstName, "Rambo");
        sendText(addEmployeePage.middleName, "Spooky");
        sendText(addEmployeePage.lastName, "Jackson");
    }
    @When("user deletes employee id")
    public void user_deletes_employee_id() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        addEmployeePage.employeeID.clear();
    }
    @When("user selects checkbox")
    public void user_selects_checkbox() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        click(addEmployeePage.createLoginCheckBox);
    }
    @When("user enters username password and confirmpassword")
    public void user_enters_username_password_and_confirmpassword() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.createUsername, "Rambo123953");
        sendText(addEmployeePage.createPassword, "Hum@nhrm123");
        sendText(addEmployeePage.rePassword, "Hum@nhrm123");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
       AddEmployeePage addEmployeePage = new AddEmployeePage();
       click(addEmployeePage.saveBtn);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dashBoardPage = new DashBoardPage();
        click(dashBoardPage.pimOption);
    }
    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage= new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @And("user enters {string} {string} and {string} for an employee")
    public void userEntersAndForAnEmployee(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage= new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("I add multiple employees and verify them that user has been added successfully")
    public void iAddMultipleEmployeesAndVerifyThemThatUserHasBeenAddedSuccessfully(DataTable employees) throws InterruptedException {
        List<Map<String, String>> employeeNames = employees.asMaps();
        for(Map<String, String> employeeName: employeeNames){
            String valueFirstName =employeeName.get("firstName");
            String valueMiddleName = employeeName.get("middleName");
            String valueLastName = employeeName.get("lastName");

            AddEmployeePage addEmployeePage= new AddEmployeePage();
            sendText(addEmployeePage.firstName, valueFirstName);
            sendText(addEmployeePage.middleName, valueMiddleName);
            sendText(addEmployeePage.lastName, valueLastName);
            click(addEmployeePage.saveBtn);

            DashBoardPage dashBoardPage = new DashBoardPage();
            click(dashBoardPage.addEmployeeButton);
            Thread.sleep(3000);

        }
    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the added employee")
    public void userAddsMultipleEmployeesFromExcelFileUsingSheetAndVerifyTheAddedEmployee(String sheetName) {
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,sheetName);
        DashBoardPage dash = new DashBoardPage();
        AddEmployeePage add = new AddEmployeePage();

        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()){
            Map<String,String> mapNewEmp = it.next();
            sendText(add.firstName, mapNewEmp.get("FirstName"));
            sendText(add.middleName, mapNewEmp.get("MiddleName"));
            sendText(add.lastName, mapNewEmp.get("LastName"));
            click(add.saveBtn);

            click(dash.addEmployeeButton);
        }
    }
}
