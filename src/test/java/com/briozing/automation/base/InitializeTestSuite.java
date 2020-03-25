package com.briozing.automation.base;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class InitializeTestSuite {

    /**
     * Method to be invoked before launch of any Suite execution.
     */
    @BeforeSuite(alwaysRun = true)
    public final void init() {
        String envName = System.getProperty("envName");
        if (envName != null) {
            System.out.println("Environment Name is: " + envName);
            Log4JFactory.getCommonLogger().info("====== rest-assured-template execution started ======");
            Properties properties = new Properties();
            try {
                properties.load(MainUtils.loadProperties(envName + ".properties"));
                Configuration.apiServer = MainUtils.fetchProperty(properties, "api.server");
            } catch (Exception e) {
                e.printStackTrace();
                AppAssert.assertTrue(false, "Before suite failed due to exception");
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
    }
}
