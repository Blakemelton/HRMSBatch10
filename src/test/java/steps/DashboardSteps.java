package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashBoardPage;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("user verifies the dashboard options available on the page")
    public void user_verifies_the_dashboard_options_available_on_the_page(DataTable dataTable) {
        List<String> expectedTabs = dataTable.asList();

        DashBoardPage dashBoardPage= new DashBoardPage();
        List<String> actualList = new ArrayList<>();

        for(WebElement ele:dashBoardPage.dashboardTabs){
            actualList.add(ele.getText());

        }
        Assert.assertTrue(expectedTabs.equals(actualList));
    }

}
