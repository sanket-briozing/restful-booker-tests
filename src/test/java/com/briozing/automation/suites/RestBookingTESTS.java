package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.RestBookingsHelper;
import com.briozing.automation.models.BookingIdDTO;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import com.briozing.automation.utils.TestSteps;
import com.briozing.automation.utils.TestValidationHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestBookingTESTS {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    private RestBookingsHelper restbookingsHelper;
    private TestValidationHelper validationHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        restbookingsHelper = new RestBookingsHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups = {"smoke"})
    public void verify_get_all_booking_id() {
        try {
            logger.info("-------------Test Started ------------");

            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ALL_BOOKING_ID.name(), true);
            validateGetAllIdTest(testSteps);

            logger.info("--------------Test Ended -------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting all the Booking Ids");
        }
    }

    private void validateGetAllIdTest(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ALL_BOOKING_ID.name()) && testSteps.get(TestSteps.STEP_GET_ALL_BOOKING_ID.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ALL_BOOKING_ID.name());
            final BookingIdDTO[] response = restbookingsHelper.getAllBookingId(200)
                    .getBody().as(BookingIdDTO[].class);
            validationHelper.verify_get_all_booking_id(response);
        }
    }
}