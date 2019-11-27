package io.github.epam.properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.security.InvalidParameterException;
import java.util.Properties;

import static com.epam.jdi.light.common.Property.BROWSER_SIZE;
import static com.epam.jdi.light.common.Property.TIMEOUT_WAIT_ELEMENT;
import static com.epam.jdi.light.common.PropertyValidationUtils.validateProperties;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TimeoutWaitElementTests {

    @DataProvider
    public static Object[] negativeData() {
        return new Object[]{"-1", "1000", "01", "", "1.1", "10,11", "ten"};
    }

    @DataProvider
    public static Object[] positiveData() {
        return new Object[]{"x", "MAXIMIZE", "99x8", "999"};
    }

    @Test(dataProvider = "negativeData")
    public void negativeTest(String value) {
        Properties properties = new Properties();
        properties.setProperty(TIMEOUT_WAIT_ELEMENT.getName(), value);
        try {
            validateProperties(properties);
            fail("Value '" + value + "' should not be valid for this test.");
        } catch (InvalidParameterException exp) {
            String expMessage = exp.getMessage();
            assertEquals(expMessage, TIMEOUT_WAIT_ELEMENT.getExMsg() + " See example: https://jdi-docs.github.io/jdi-light/#driver-settings");
        }
    }

    @Test(dataProvider = "positiveData")
    public void positiveTest(String value) {
        Properties properties = new Properties();
        properties.setProperty(BROWSER_SIZE.getName(), value);
        validateProperties(properties);
    }

}
