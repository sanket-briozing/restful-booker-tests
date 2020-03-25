package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.RestBookingsHelper;
import com.briozing.automation.models.BookingDetailsDTO;
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

    @DataProvider(name = "booking-id-dp")
    public Object[][] bookingIdDP() {

        return new Object[][]{
                {"1"},{"2"}
        };
    }

    @DataProvider(name = "name-dp")
    public Object[][] nameDP() {

        return new Object[][]{
                {"Eric","Smith"}
        };
    }hit

    @Test(groups = {"smoke","getall"})
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

    @Test(groups={"smoke","getbyid"},dataProvider = "booking-id-dp")
    public void verify_get_booking_by_id(String id) {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_BOOKING_BY_ID.name(), true);
            validateGetBookingByIdTest(testSteps, id);
            logger.info("--------------Test Ended -------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting Booking by Id");
        }
    }

    @Test(groups={"smoke","getbyname"},dataProvider = "name-dp")
    public void verify_get_id_name(String first,String last) {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ID_BY_NAME.name(), true);
            validateGetIdByName(testSteps, first,last);
            logger.info("--------------Test Ended -------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting Id by name");
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

    private void validateGetBookingByIdTest(Map<String, Boolean> testSteps, String id) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_BOOKING_BY_ID.name()) && testSteps.get(TestSteps.STEP_GET_BOOKING_BY_ID.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_BOOKING_BY_ID.name());
            final BookingDetailsDTO response = restbookingsHelper.getAllBookingId(id,200)
                    .getBody().as(BookingDetailsDTO.class);
            validationHelper.verify_get_booking_by_id(response);
        }
    }

    private void validateGetIdByName(Map<String, Boolean> testSteps, String first,String last) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ID_BY_NAME.name()) && testSteps.get(TestSteps.STEP_GET_ID_BY_NAME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ID_BY_NAME.name());
            final BookingIdDTO[] response = restbookingsHelper.getIdByName(first,last,200)
                    .getBody().as(BookingIdDTO[].class);
            validationHelper.verify_get_id_by_name(response);
        }
    }
}