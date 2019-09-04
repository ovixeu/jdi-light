package io.github.epam.testng;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.tools.Safe;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.jdi.light.actions.ActionProcessor.provideExtraInfoOnFail;
import static com.epam.jdi.light.settings.WebSettings.TEST_NAME;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static java.lang.System.currentTimeMillis;

public class TestNGListener implements IInvokedMethodListener {

    private Safe<Long> start = new Safe<>(0L);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult iTestResult) {
        if (method.isTestMethod()) {
            Method testMethod = method.getTestMethod().getConstructorOrMethod().getMethod();
            if (testMethod.isAnnotationPresent(Test.class)) {
                TEST_NAME.set(iTestResult.getTestClass().getRealClass().getSimpleName() + "." + testMethod.getName());
                start.set(currentTimeMillis());
                logger.step("== Test '%s' START ==", TEST_NAME.get());
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult iTestResult) {
        if (method.isTestMethod()) {
            String result = getTestResult(iTestResult);
            logger.step("=== Test '%s' %s [%s] ===", TEST_NAME.get(), result,
                    new SimpleDateFormat("mm:ss.SS").format(new Date(currentTimeMillis() - start.get())));
            if (result.equals("FAILED")) {
                String methodName = method.getTestMethod().toString();
                String shortMethodName = methodName.substring(0, methodName.indexOf("("));
                provideExtraInfoOnFail(shortMethodName + "(..)");
                logger.error("ERROR: " + iTestResult.getThrowable().getMessage());
            }
        }
    }

    private String getTestResult(ITestResult iTestResult) {
        switch (iTestResult.getStatus()) {
            case ITestResult.SUCCESS:
                return "PASSED";
            case ITestResult.SKIP:
                return "SKIPPED";
            default:
                return "FAILED";
        }
    }

}
