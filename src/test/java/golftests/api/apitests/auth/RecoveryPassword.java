package golftests.api.apitests.auth;

import golf.utilities.EmailReceiver;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class RecoveryPassword {

    @Test
    public void recoveryPassword() {
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);

        String requestBody = String.format("{\"email\":\"%s\",\"tempPassword\":\"%s\",\"newPassword\":\"%s\"}",
                CLIENT_LOGIN, temporaryPassword, CLIENT_PASSWORD);

        RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(PASSWORD_RECOVERY_BASE_PATH)
                .contentType(ContentType.JSON).given()
                .body(requestBody)
                .filter(new AllureRestAssured())
                .when()
                .post()
                .then()
                .statusCode(200);
    }
}
