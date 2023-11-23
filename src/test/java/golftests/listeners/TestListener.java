package golftests.listeners;

import golftests.ui.uibase.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class TestListener implements TestLifecycleListener {


    public void beforeTestStop(TestResult result) {

        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (BaseTest.getDriver() != null) {
                Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }
    }
}
