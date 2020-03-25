package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author Sanket Khanorkar
 */
public class RestBookingsHelper {

    private RequestSpecification requestSpecification;

    public RestBookingsHelper() {
        requestSpecification = with()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(Configuration.apiServer);
    }

    public Response getAllBookingId(int status) {
        final Response response = given(requestSpecification)
                .get("/booking");
        response.then().assertThat().statusCode(status);
        return response;
    }


}
