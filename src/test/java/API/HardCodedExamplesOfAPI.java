package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamplesOfAPI {

    /*
    Given -pre-condition - prepare the request
    When - action sending the request / hitting the endpoint
    Then - result - verify response
     */

    //rest assured considers baseURL as baseURI

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzkwMTA1MzIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzOTA1MzczMiwidXNlcklkIjoiMzI0MyJ9.qZMcZK2waHLmKYC3uMFJ3w-ORYYF3LgX-VLSGExmR5U";
    static String employee_id;

    @Test
    public void dgetDetailsOfOneEmployee() {

        //Given part
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", "25598A");

        //When part - hitting the end point
       Response response= preparedRequest.when().get("/getOneEmployee.php");
        System.out.println(response.asString());

        //Then - result or assertion
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void acreateEmployee(){
        RequestSpecification preparedRequest =   given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"emp_firstname\": \"Bmelton\",\n" +
                                "  \"emp_lastname\": \"Mouff\",\n" +
                                "  \"emp_middle_name\": \"Inya\",\n" +
                                "  \"emp_gender\": \"M\",\n" +
                                "  \"emp_birthday\": \"1991-06-25\",\n" +
                                "  \"emp_status\": \"Employee \",\n" +
                                "  \"emp_job_title\": \"API Tester\"\n" +
                                "}");

        //when
        Response response = preparedRequest.when().post("/createEmployee.php");

        response.prettyPrint();
        //this pretty print does the same job as syso. // sout(response.asString());

        //jsonPath() we use this to get specific information from the json object
       employee_id= response.jsonPath().getString("Employee.employee_id");

       //then
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Bmelton"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
}

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpID = empID.contentEquals(employee_id);
        Assert.assertTrue(comparingEmpID);

        String firstName = response.jsonPath().getString("employee.emp_firstname");
        Assert.assertTrue(firstName.contentEquals("Bmelton"));

    }

    @Test
    public void cupdatedCreatedEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"BlakeThe\",\n" +
                        "  \"emp_lastname\": \"SnakeMelton\",\n" +
                        "  \"emp_middle_name\": \"SlipperySilent\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1991-06-25\",\n" +
                        "  \"emp_status\": \"Active\",\n" +
                        "  \"emp_job_title\": \"API Tester\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();

    }


    }
