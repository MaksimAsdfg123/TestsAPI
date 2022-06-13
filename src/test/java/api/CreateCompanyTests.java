package api;

import api.pojo.Company;
import api.pojo.CompanyReq;
import api.spec.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateCompanyTests {
    private final static String URL = "http://users.bugred.ru/";
    private String nameCompany = "test";

    @Test
    public void createCompany(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        CompanyReq companyReq = new CompanyReq(nameCompany, "ООО", new String[]{"test_cu_11@mail.com", "sdfsd@gmail.com"}, "aa+1@mail.com");
        Response response = given()
                .body(companyReq)
                .when()
                .post("tasks/rest/createcompany")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Integer id_company = jsonPath.get("id_company");
        Assertions.assertNotNull(id_company);
    }

    @Test
    public void createCompanyWithoutName(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        CompanyReq companyReq1 = new CompanyReq("", "ООО", new String[]{"test_cu_11@mail.com", "sdfsd@gmail.com"}, "aa+1@mail.com");
        Response response = given()
                .body(companyReq1)
                .when()
                .post("tasks/rest/createcompany")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assertions.assertEquals(" company_name некорректный", message);
    }

    @Test
    public void createCompanyWithoutUsers(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        CompanyReq companyReq2 = new CompanyReq(nameCompany, "ООО", new String[]{""}, "aa+1@mail.com");
        Response response = given()
                .body(companyReq2)
                .when()
                .post("tasks/rest/createcompany")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assertions.assertEquals(" company_users  не указаны сотрудники", message);
    }

    @Test
    public void createCompanyWithoutOwner(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        CompanyReq companyReq3 = new CompanyReq(nameCompany, "ООО", new String[]{"test_cu_11@mail.com", "sdfsd@gmail.com"}, "");
        Response response = given()
                .body(companyReq3)
                .when()
                .post("tasks/rest/createcompany")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assertions.assertEquals("Пользователь не найден c email_owner ", message);
    }
}
