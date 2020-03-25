package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.BookingIdDTO;
import org.apache.log4j.Logger;
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
        logger.info("Booking id:- " + actualResponse[0].getBookingid());
        AppAssert.assertTrue(actualResponse[0].getBookingid() != null, "Booking id is not null");
    }
}



