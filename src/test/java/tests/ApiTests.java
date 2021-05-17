package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests {
    private final ApiSteps apiSteps = new ApiSteps();
    private static final String NAME = "testusername";
    private static final String JOB = "janitor";
    private static final String NEW_JOB= "president";
    private static final String ID = "2";

    @BeforeEach
    public void setBaseUri() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @DisplayName("Create user from API")
    public void createUserFromAPI() {
        final DataUser created = apiSteps.createUser(NAME, JOB);
        assertThat(created.getId()).isNotNull();
    }

    @Test
    @DisplayName("Update user data from API")
    public void updateUserDataFromAPI() {
        final DataUser updated = apiSteps.updateUserData(NEW_JOB, ID);
        assertThat(updated.getJob()).isEqualTo(NEW_JOB);
    }
}
