package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.models.BookingDetailsDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.json.*;

import java.io.FileInputStream;
import java.io.IOException;

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

    public Response getAllBookingId(String id, int status) {
        final Response response = given(requestSpecification)
                .pathParam("booking-id", id)
                .get("/booking/{booking-id}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getIdByName(String first,String last, int status) {
        final Response response = given(requestSpecification)
                .queryParam("firstname",first)
                .queryParam("lastname",last)
                .get("/booking");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response getIdByDate(String checkIn,String checkOut, int status) {
        final Response response = given(requestSpecification)
                .queryParam("checkin",checkIn)
                .queryParam("checkout",checkOut)
                .get("/booking");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response createBooking(JSONObject requestParams, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post("/booking");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response postJson(BookingDetailsDTO bookingDetailsDTO, int status) throws IOException {
        System.out.println("DTO in :" + bookingDetailsDTO.toString());
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(bookingDetailsDTO)
                .post("/booking");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

}