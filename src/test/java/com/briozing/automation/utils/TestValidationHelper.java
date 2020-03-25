package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
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


}
