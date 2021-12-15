package utils;

public class APIPayloadConstants {

    public static String CreateEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"Blake\",\n" +
                "  \"emp_lastname\": \"Melton\",\n" +
                "  \"emp_middle_name\": \"Edward\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-12-11\",\n" +
                "  \"emp_status\": \"Active\",\n" +
                "  \"emp_job_title\": \"API Tester\"\n" +
                "}";

        return createEmployeePayload;
    }
}
