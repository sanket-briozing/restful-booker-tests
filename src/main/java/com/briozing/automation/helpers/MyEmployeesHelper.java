package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.EmployeeRequestVO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class MyEmployeesHelper {

    private RequestSpecification requestSpecification;

    public MyEmployeesHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(Configuration.apiServer);
        System.out.println("RequestSpecification object Created");
    }

    public Response addEmployee(EmployeeRequestVO employeeRequestVO, int status) throws IOException {
        System.out.println("DTO in :" + employeeRequestVO.toString());
        System.out.println("API URL : " + Configuration.apiServer );
        System.out.println("Request Spec : " + requestSpecification.toString());
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(employeeRequestVO)
                .post("/employee/addEmployee");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }
}
