package onetoone;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import onetoone.Users.LoginRequest;
import onetoone.Users.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;


public class Usercontrollertest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    // Test to get all users
    @Test
    public void getAllUsersTest() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0)); // Assuming there can be zero or more users
    }

    // Test to get user by ID
    @Test
    public void getUserByIdTest() {
        int userId = 3; // Set a valid user ID
        given()
                .when()
                .get("/users/{id}", userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    // Test to update user password
    @Test
    public void updatePasswordTest() {
        int userId = 3; // Set a valid user ID
        String newPassword = "newPassword123";

        given()
                .pathParam("id", userId)
                .pathParam("newPassword", newPassword)
                .when()
                .put("/users/{id}/up/{newPassword}")
                .then()
                .statusCode(200)
                .body("password", equalTo(newPassword));
    }

    // Test to update user email
    @Test
    public void updateEmailTest() {
        int userId = 3; // Set a valid user ID
        String newEmail = "newemail@example.com";

        given()
                .pathParam("id", userId)
                .body(newEmail)
                .when()
                .put("/users/{id}/ue/Email")
                .then()
                .statusCode(200)
                .body("email", equalTo(newEmail));
    }

    // Test to create a new user
    @Test
    public void createUserTest() {
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmail("john.doe@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body(equalTo("{\"message\":\"success\"}"));
    }

    @Test
    public void loginSuccessTest() {
        // Assuming you have a valid user's email and password for successful login
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("chan");
        loginRequest.setPassword("chan");

        String responseBody = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().asString(); // Extract the response body as a string

        // Assert the response body
        assertThat(responseBody.trim().toLowerCase()).isEqualTo("login successful");
    }

    @Test
    public void loginFailureTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("invalid_email@example.com");
        loginRequest.setPassword("invalid_password");

        String responseBody = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract().asString(); // Extract the response body as a string

        // Assert the response body
        assertThat(responseBody.trim().toLowerCase());

    }
    // Test to delete a user
    @Test
    public void deleteUserTest() {
        int userId = 10; // Set a valid user ID

        given()
                .pathParam("id", userId)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(200)
                .body(equalTo("{\"message\":\"success\"}"));
    }





}
