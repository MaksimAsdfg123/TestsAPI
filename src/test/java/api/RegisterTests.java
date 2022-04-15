package api;

import api.registration.Register;
import api.registration.UnSuccessUserReg;
import api.spec.Specifications;
import api.registration.SuccessUserReg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class  RegisterTests {
    private final static String URL = "https://reqres.in/";

    @Test
    public void successRegisterUser(){
        int userId = 4;
        String userToken = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in","pistol");
        SuccessUserReg successUserReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessUserReg.class);
        Assertions.assertNotNull(successUserReg.getId());
        Assertions.assertNotNull(successUserReg.getToken());
        Assertions.assertEquals(userId, successUserReg.getId());
        Assertions.assertEquals(userToken, successUserReg.getToken());
    }

    @Test
    public void unSuccessRegisterUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecError400());
        Register peopleSecond = new Register("sydney@fife","");
        UnSuccessUserReg unSuccessUserReg = given()
                .body(peopleSecond)
                .when()
                .post("/api/register")
                .then()
                .log().body()
                .extract().as(UnSuccessUserReg.class);
        Assertions.assertNotNull(unSuccessUserReg.getError());
        Assertions.assertEquals("Missing password", unSuccessUserReg.getError());
    }
}
