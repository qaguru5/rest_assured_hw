package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests {
    private final ApiSteps apiSteps = new ApiSteps();

    private static final String NAME = "monk";
    private static final String JOB = "janitor";
    private static final String NEW_JOB = "president";
    private static final String EMAIL = "eve.holt@reqres.in";
    private static final String REGISTER_PASSWORD = "pistol";
    private static final String LOGIN_PASSWORD = "cityslicka";
    private static final String TOKEN = "QpwL5tke4Pnpja7X4";
    private static final int MISSING_USER_ID = 23;
    private static final int STATUS_NOT_FOUND = 404;
    private static final int STATUS_SUCCESS = 200;

    @BeforeEach
    public void setBaseUri() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    @DisplayName("Create user from API")
    public void createUserFromAPI() {
        Response createUserResponse = apiSteps.createUser(NAME, JOB);
        assertThat(createUserResponse.jsonPath().getString("id")).isNotNull();
    }

    @Test
    @DisplayName("Update user's job from API with PATCH")
    public void updateUserJobWithPatch() {
        Response updateDataResponse = apiSteps.updateUserDataWithPatch(NAME, NEW_JOB);
        assertThat(updateDataResponse.jsonPath().getString("job")).isEqualTo(NEW_JOB);
    }

    @Test
    @DisplayName("Get response status code ${NOT_FOUND_STATUS} from data of missing user")
    public void getStatusNotFoundForMissingUser() {
        Response getStatusResponse = apiSteps.getUserData(MISSING_USER_ID, STATUS_NOT_FOUND);
        assertThat(getStatusResponse.getStatusCode()).isEqualTo(STATUS_NOT_FOUND);
    }

    @Test
    @DisplayName("Successful user registration")
    public void successfulUserRegistration() {
        Response registerUserResponse = apiSteps.registerUser(EMAIL, REGISTER_PASSWORD, STATUS_SUCCESS);
        assertThat(registerUserResponse.jsonPath().getString("token")).isEqualTo(TOKEN);
    }

    @Test
    @DisplayName("Authorization")
    public void successfulLogin() {
        Response loginUserResponse = apiSteps.login(EMAIL, LOGIN_PASSWORD, STATUS_SUCCESS);
        assertThat(loginUserResponse.jsonPath().getString("token")).isEqualTo(TOKEN);
    }
}
