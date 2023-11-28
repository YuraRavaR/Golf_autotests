package golf.utilities;

public class Constant {
    public static String BASE_URI = getProperty("baseURI");
    public static String ADMIN_LOGIN = getProperty("adminLogin");
    public static String ADMIN_PASSWORD = getProperty("adminPassword");
    public static String CLIENT_LOGIN = getProperty("clientLogin");
    public static String CLIENT_PASSWORD = getProperty("clientPassword");

    public static String CLIENT_NEW_PASSWORD = getProperty("clientNewPassword");
    public static String CLIENT_MAIL_PASSWORD_831 = getProperty("clientMailPassword831");

    public static String SIGN_UP_BASE_PATH = getProperty("signUpBasePath");
    public static String BASE_URL_UI = getProperty("baseUrlUI");
    public static String xlsxFileWithTestData = getProperty("xlsxFileWithTestData");

    private static String getProperty(String key) {
        return PropertiesCache.getInstance().getProperty(key);
    }
}
