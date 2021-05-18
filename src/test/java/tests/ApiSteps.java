package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiSteps {

    @Step("Create user")
    public Response createUser(String name, String job) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body("{ \"name\": \""+name+"\", " + "\"job\": \""+job+"\" }")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract().response();
    }

    @Step("Update user data with PATCH")
    public Response updateUserDataWithPatch(String name, String job) {
       return given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body("{ \"name\": \""+name+"\", " + "\"job\": \""+job+"\" }")
                .when()
                .patch("/api/users/2")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Step("GET user's data")
    public Response getUserData(int userId, int status) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .when()
                .get("/api/users/" + userId)
                .then()
                .statusCode(status)
                .extract().response();
    }

    @Step("Register new user")
    public Response registerUser(String email, String password, int statusCode) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body("{ \"email\": \""+email+"\", " + "\"password\": \""+password+"\" }")
                .when()
                .post("/api/register")
                .then()
                .statusCode(statusCode)
                .extract().response();
    }

    @Step("Authorization")
    public Response login(String email, String password, int statusCode) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body("{ \"email\": \""+email+"\", " + "\"password\": \""+password+"\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(statusCode)
                .extract().response();
    }
}
