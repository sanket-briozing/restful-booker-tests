package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.GetTokenDTO;
import com.briozing.automation.models.PatchRequestDTO;
import com.briozing.automation.utils.TestConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public Response getBookingById(String id, int status) {
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

    public Response getToken(GetTokenDTO getTokenDTO, int status) throws IOException {
        System.out.println("DTO in :" + getTokenDTO.toString());
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(getTokenDTO)
                .post("/auth");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        TestConstants.token=response.jsonPath().get("token");
        return response;
    }

    public Response updateBooking(BookingDetailsDTO bookingDetailsDTO,String ids, int status) throws IOException {
        System.out.println("DTO in :" + bookingDetailsDTO.toString());
        System.out.println("Token :" + TestConstants.token);
        Map<String,String> headers= getHeaders();
        System.out.println(" Header : " + headers);

        System.out.println(" Ids : " + ids);
        final Response response = given(requestSpecification)
                .pathParam("booking-ids", ids)
//                .header("Content-Type", "application/json")
//                .header("Cookie","token="+TestConstants.token)
                .headers(headers)
                .body(bookingDetailsDTO)
                .put("/booking/{booking-ids}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response partialUpdateBooking(PatchRequestDTO patchRequestDTO, String ids, int status) throws IOException {
        System.out.println("DTO in :" + patchRequestDTO.toString());
        System.out.println("Token :" + TestConstants.token);
        Map<String,String> headers= getHeaders();
        System.out.println(" Header : " + headers);
        System.out.println(" Ids : " + ids);
        final Response response = given(requestSpecification)
                .pathParam("booking-ids", ids)
//                .header("Content-Type", "application/json")
//                .header("Cookie","token="+TestConstants.token)
                .headers(headers)
                .body(patchRequestDTO)
                .patch("/booking/{booking-ids}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response deleteBooking(String ids, int status) throws IOException {
        Map<String,String> headers= getHeaders();
        System.out.println(" Header : " + headers);
        System.out.println(" Ids : " + ids);
        final Response response = given(requestSpecification)
                .pathParam("booking-ids", ids)
                .headers(headers)
                .delete("/booking/{booking-ids}");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        return response;
    }

    public static Map<String, String> getHeaders(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Cookie", "token="+TestConstants.token);
        return headerMap;
    }

}