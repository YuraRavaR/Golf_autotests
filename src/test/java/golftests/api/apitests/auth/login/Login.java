package golftests.api.apitests.auth.login;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Login {
    private RequestSpecification requestSpecUser;

    @BeforeClass
    public void setupRequestSpecification() {
        requestSpecUser = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(SIGN_IN_BASE_PATH)
                .contentType(ContentType.JSON);
    }

    @Test
    public void loginValidCredentials() {
        given()
                .spec(requestSpecUser)
                .body("{\"email\":\"" + CLIENT_LOGIN + "\",\"password\":\"" + CLIENT_PASSWORD + "\"}")
                .filter(new AllureRestAssured())
                .when().post()
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
