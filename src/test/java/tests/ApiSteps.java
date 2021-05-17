package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    final DataUser user = new DataUser();

    @Step("Create user")
    public DataUser createUser(String name, String job) {
        user.setName(name);
        user.setJob(job);
        return given()
                    .filter(new AllureRestAssured())
                    .baseUri("https://reqres.in/api")
                    .body(user)
                .when()
                     .post("/users")
                .then()
                    .statusCode(201)
                .extract()
                .as(DataUser.class);
    }

    @Step("Update user data")
    public DataUser updateUserData(String job, String id) {
        user.setId(id);
        user.setJob(job);
        return given()
                .filter(new AllureRestAssured())
                .body(user)
                .when()
                .contentType(ContentType.JSON)
                .patch("/users/2")
        .then()
                .statusCode(200)
                .extract()
                .as(DataUser.class);
    }
}
