package golf.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static golf.utilities.Constant.*;
import static golf.utilities.Constant.CLIENT_PASSWORD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ApiHelper {
    public static String getTokensAfterSignIn(String login, String password) {
        JsonPath jsonPath =
                given()
                        .baseUri(BASE_URI)
                        .basePath(SIGN_IN_BASE_PATH)
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"" + CLIENT_LOGIN + "\",\"password\":\"" + CLIENT_PASSWORD + "\"}")
                        .filter(new AllureRestAssured())
                        .when().post()
                        .then()
                        .statusCode(200)
                        .body("token", notNullValue())
                        .extract().jsonPath();

        return jsonPath.get("token");
    }
}
