package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.RestBookingsHelper;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.BookingIdDTO;
import com.briozing.automation.models.CreateBookingDTO;
import com.briozing.automation.utils.*;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.*;
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
                {TestConstants.bookingId}
        };
    }

    @DataProvider(name = "name-dp")
    public Object[][] nameDP() {

        return new Object[][]{
                {"Sally","Jackson"}
        };
    }

    @DataProvider(name = "date-dp")
    public Object[][] dateDP() {

        return new Object[][]{
                {"2017-08-24","2020-01-17"}
        };
    }

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

    @Test(dependsOnMethods = "verify_create_booking",groups={"smoke","getbyid","demo"},dataProvider = "booking-id-dp")
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
    public void verify_get_id_by_name(String first,String last) {
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

    @Test(groups={"smoke","getbydate"},dataProvider = "date-dp")
    public void verify_get_id_by_date(String checkIn,String checkOut) {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_GET_ID_BY_DATE.name(), true);
            validateGetIdByDate(testSteps, checkIn,checkOut);
            logger.info("--------------Test Ended -------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure getting Id by name");
        }
    }

    @Test(groups={"smoke","createbooking","demo"})
    public void verify_create_booking() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("firstname", "sandeep");
        requestParams.put("lastname", "khanorkar");
        requestParams.put("totalprice", 2000);
        requestParams.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2015-08-18");
        bookingDates.put("checkout","2019-02-05");

        requestParams.put("bookingdates", bookingDates);
        requestParams.put("additionalneeds","Alcohol");

        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_CREATE_BOOKING.name(), true);
            validateCreateBooking(testSteps,requestParams);
            logger.info("--------------Test Ended -------------");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure Creating booking id");
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

    private void validateGetIdByDate(Map<String, Boolean> testSteps, String checkIn,String checkOut) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_GET_ID_BY_DATE.name()) && testSteps.get(TestSteps.STEP_GET_ID_BY_DATE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_GET_ID_BY_DATE.name());
            final BookingIdDTO[] response = restbookingsHelper.getIdByDate(checkIn,checkOut,200)
                    .getBody().as(BookingIdDTO[].class);
            validationHelper.verify_get_id_by_date(response);
        }
    }

    private void validateCreateBooking(Map<String, Boolean> testSteps,JSONObject requestParams) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_CREATE_BOOKING.name()) && testSteps.get(TestSteps.STEP_CREATE_BOOKING.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CREATE_BOOKING.name());
            final CreateBookingDTO response = restbookingsHelper.createBooking(requestParams,200)
                    .getBody().as(CreateBookingDTO.class);
            TestConstants.bookingId=response.getBookingid().toString();
            validationHelper.verify_create_booking(response,requestParams);
        }
    }
}