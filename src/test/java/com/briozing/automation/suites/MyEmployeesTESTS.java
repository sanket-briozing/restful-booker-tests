package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.MyEmployeesHelper;
import com.briozing.automation.helpers.RestBookingsHelper;
import com.briozing.automation.models.BookingDetailsDTO;
import com.briozing.automation.models.CreateBookingDTO;
import com.briozing.automation.models.EmployeeRequestVO;
import com.briozing.automation.models.EmployeeResponseVO;
import com.briozing.automation.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class MyEmployeesTESTS {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private MyEmployeesHelper myEmployeesHelper;
    private TestValidationHelper validationHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        myEmployeesHelper = new MyEmployeesHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups={"smoke","addEmployee"})
    public void verify_add_employee() {
        try {
            FileInputStream fileInputStream= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/AddEmployee.json"));
            ObjectMapper mapper = new ObjectMapper();
            EmployeeRequestVO employeeRequestVO = mapper.readValue(fileInputStream, EmployeeRequestVO.class);
            System.out.println("DTO : " + employeeRequestVO.toString());
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.STEP_ADD_EMPLOYEE.name(), true);
            validateAddEmployee(testSteps, employeeRequestVO);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure adding employee");
        }
    }

    private void validateAddEmployee(Map<String, Boolean> testSteps,EmployeeRequestVO employeeRequestVO) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_ADD_EMPLOYEE.name()) && testSteps.get(TestSteps.STEP_ADD_EMPLOYEE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_ADD_EMPLOYEE.name());
            final EmployeeResponseVO response = myEmployeesHelper.addEmployee(employeeRequestVO,200)
                    .getBody().as(EmployeeResponseVO.class);
            validationHelper.verify_add_employee(response,employeeRequestVO);
        }
    }
}
