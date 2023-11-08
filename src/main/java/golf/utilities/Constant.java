package golf.utilities;

public class Constant {
    public static String BASE_URI = PropertiesCache.getInstance().getProperty("baseURI");
    public static String ADMIN_LOGIN = PropertiesCache.getInstance().getProperty("adminLogin");
    public static String ADMIN_PASSWORD = PropertiesCache.getInstance().getProperty("adminPassword");
    public static String CLIENT_LOGIN = PropertiesCache.getInstance().getProperty("clientLogin");
    public static String CLIENT_PASSWORD = PropertiesCache.getInstance().getProperty("clientPassword");
    public static String SIGN_UP_BASE_PATH = PropertiesCache.getInstance().getProperty("signUpBasePath");
    public static String BASE_URL_UI = PropertiesCache.getInstance().getProperty("baseUrlUI");
    public static String xlsxFileWithTestData = PropertiesCache.getInstance().getProperty("xlsxFileWithTestData");
}
