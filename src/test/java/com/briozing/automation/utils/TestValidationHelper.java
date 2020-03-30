package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.BookingIdDTO;
import com.briozing.automation.models.CreateBookingDTO;
import com.briozing.automation.models.PatchRequestDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.*;

import java.io.FileInputStream;
import java.util.Map;

public class TestValidationHelper {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    public TestValidationHelper() {
    }

    public void verify_get_all_booking_id(BookingIdDTO[] actualResponse) {
        int countId = actualResponse.length;
        logger.info("Total number of booking id: " + countId);
        for (BookingIdDTO id : actualResponse) {
            logger.info("Booking Id = " + id.getBookingid());
            AppAssert.assertTrue(id.getBookingid() != null, "Booking id is not null");
        }
    }

    public void verify_get_booking_by_id(BookingDetailsDTO actualResponse) {
        logger.info("First name :- " + actualResponse.getFirstname());
        AppAssert.assertTrue(actualResponse.getFirstname() != null, "First name is not null");
        logger.info("Last name :- " + actualResponse.getLastname());
        AppAssert.assertTrue(actualResponse.getLastname() != null, "Last name is not null");
        logger.info("Total price :- " + actualResponse.getTotalprice());
        AppAssert.assertTrue(actualResponse.getTotalprice() != null, "Total price is not null");
        logger.info("Deposit Paid? " + actualResponse.getDepositpaid());
        AppAssert.assertTrue(actualResponse.getDepositpaid() != null, "Deposit paid is not null");
    }

    public void verify_get_id_by_name(BookingIdDTO[] actualResponse) {
        for (BookingIdDTO bookingId : actualResponse) {
            logger.info("Booking id:- " + bookingId.getBookingid());
            AppAssert.assertTrue(bookingId.getBookingid() != null, "Booking id is not null");
        }
    }

    public void verify_get_id_by_date(BookingIdDTO[] actualResponse) {
        for (BookingIdDTO bookingId : actualResponse) {
            logger.info("Booking id:- " + bookingId.getBookingid());
            AppAssert.assertTrue(bookingId.getBookingid() != null, "Booking id is not null");
        }
    }

    public void verify_create_booking(CreateBookingDTO actualResponse, JSONObject requestParams) {
        logger.info("Booking id :- " + actualResponse.getBookingid());
        AppAssert.assertTrue(actualResponse.getBookingid() != null, "Booking is not null");
        logger.info("Actual First Name :- " + actualResponse.getBooking().getFirstname());
        logger.info("Expected First Name :- " + requestParams.getString("firstname"));
        AppAssert.assertEqual(actualResponse.getBooking().getFirstname(), requestParams.getString("firstname"),"First Name :");
        AppAssert.assertEqual(actualResponse.getBooking().getLastname(), requestParams.getString("lastname"),"Last Name :");
        AppAssert.assertEqual(actualResponse.getBooking().getDepositpaid(), requestParams.getBoolean("depositpaid"),"Deposit Paid :");
        AppAssert.assertEqual(actualResponse.getBooking().getTotalprice(), requestParams.getInt("totalprice"),"Total Price :");
        AppAssert.assertEqual(actualResponse.getBooking().getBookingdates().getCheckin(), requestParams.getJSONObject("bookingdates").getString("checkin") ,"CheckIn Date :");
        AppAssert.assertEqual(actualResponse.getBooking().getBookingdates().getCheckout(), requestParams.getJSONObject("bookingdates").getString("checkout") ,"CheckOut Date :");
        AppAssert.assertEqual(actualResponse.getBooking().getAdditionalneeds(), requestParams.getString("additionalneeds") ,"Additional needs :");
    }

    public void verify_post_json(CreateBookingDTO actualResponse, BookingDetailsDTO bookingDetailsDTO) {
        logger.info("Booking id :- " + actualResponse.getBookingid());
        AppAssert.assertTrue(actualResponse.getBookingid() != null, "Booking is not null");
        logger.info("Actual First Name :- " + actualResponse.getBooking().getFirstname());
        logger.info("Expected First Name :- " + bookingDetailsDTO.getFirstname());
        AppAssert.assertEqual(actualResponse.getBooking().getFirstname(), bookingDetailsDTO.getFirstname(),"First Name :");
        AppAssert.assertEqual(actualResponse.getBooking().getLastname(), bookingDetailsDTO.getLastname(),"Last Name :");
        AppAssert.assertEqual(actualResponse.getBooking().getDepositpaid(), bookingDetailsDTO.getDepositpaid(),"Deposit Paid :");
        AppAssert.assertEqual(actualResponse.getBooking().getTotalprice(), bookingDetailsDTO.getTotalprice(),"Total Price :");
        AppAssert.assertEqual(actualResponse.getBooking().getBookingdates().getCheckin(), bookingDetailsDTO.getBookingdates().getCheckin(),"CheckIn Date :");
        AppAssert.assertEqual(actualResponse.getBooking().getBookingdates().getCheckout(), bookingDetailsDTO.getBookingdates().getCheckout() ,"CheckOut Date :");
        AppAssert.assertEqual(actualResponse.getBooking().getAdditionalneeds(), bookingDetailsDTO.getAdditionalneeds() ,"Additional needs :");
    }

    public void verify_get_token(Response actualResponse) {
        logger.info("Token :- " + actualResponse.jsonPath().get("token"));
        AppAssert.assertTrue(actualResponse.jsonPath().get("token") != null, "Token is not null");
    }

    public void verify_update_booking(BookingDetailsDTO actualResponse, BookingDetailsDTO bookingDetailsDTO) {
        logger.info("Requested First Name :- " + bookingDetailsDTO.getFirstname());
        logger.info("Response First Name :- " + actualResponse.getFirstname());
        AppAssert.assertEqual(actualResponse.getFirstname(), bookingDetailsDTO.getFirstname(),"First Name :");
        AppAssert.assertEqual(actualResponse.getLastname(), bookingDetailsDTO.getLastname(),"Last Name :");
        AppAssert.assertEqual(actualResponse.getDepositpaid(), bookingDetailsDTO.getDepositpaid(),"Deposit Paid :");
        AppAssert.assertEqual(actualResponse.getTotalprice(), bookingDetailsDTO.getTotalprice(),"Total Price :");
        AppAssert.assertEqual(actualResponse.getBookingdates().getCheckin(), bookingDetailsDTO.getBookingdates().getCheckin(),"CheckIn Date :");
        AppAssert.assertEqual(actualResponse.getBookingdates().getCheckout(), bookingDetailsDTO.getBookingdates().getCheckout() ,"CheckOut Date :");
        AppAssert.assertEqual(actualResponse.getAdditionalneeds(), bookingDetailsDTO.getAdditionalneeds() ,"Additional needs :");
    }

    public void verify_partial_update_booking(BookingDetailsDTO actualResponse, PatchRequestDTO patchRequestDTO) {
        logger.info("Actual First Name :- " + actualResponse.getFirstname());
        logger.info("Expected First Name :- " + patchRequestDTO.getFirstname());
        logger.info("Actual Last Name :- " + actualResponse.getLastname());
        logger.info("Expected Last Name :- " + patchRequestDTO.getLastname());
        AppAssert.assertEqual(actualResponse.getFirstname(), patchRequestDTO.getFirstname(),"First Name :");
        AppAssert.assertEqual(actualResponse.getLastname(), patchRequestDTO.getLastname(),"Last Name :");
    }
}



