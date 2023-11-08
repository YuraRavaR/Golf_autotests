package golftests.dataprovider;

import golf.utilities.Readxls;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class XlsDataProvider {
    @DataProvider(name = "ValidLoginCredentials")
    public static Object[][] excelDataValidLoginCredentials() throws IOException {
        return new Readxls().readValidLoginCredentials();
    }

    //receiving the object as dataProvider
    @DataProvider(name = "InvalidLoginCredentials")
    public static Object[][] excelDataInvalidLoginCredentials() throws IOException {
        return new Readxls().readInvalidLoginCredentials();
    }
    @DataProvider(name = "InvalidRegisterCredentials")
    public static Object[][] excelDataInvalidRegisterCredentials() throws IOException {
        return new Readxls().readInvalidRegisterCredentials();
    }
}
