package onetoone;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class bookingcontrollertest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    public void postControllerTest() {
        // Perform the POST request and validate the response
        given()
                .contentType(ContentType.JSON)

                .queryParam("userId", 4)  // Change to queryParam
                .queryParam("carpoolingId", 3)  // Change to queryParam
                .when()
                .post("/bookings/add")
                .then()
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("user.id", equalTo(4))  // Add more constraints for user and carpooling
                .body("carpooling.id", equalTo(3))
                .body("status", equalTo("CONFIRMED"))
                .log().body(); // Log the response body for debugging


    }


    @Test
    public void createBookingAndRetrieveTest() {
        // Create a booking and extract the booking ID
        Long bookingId = given()
                .contentType(ContentType.JSON)
                .queryParam("userId", 4)
                .queryParam("carpoolingId", 3)
                .when()
                .post("/bookings/add")
                .then()
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("user.id", equalTo(4))
                .body("carpooling.id", equalTo(3))
                .body("status", equalTo("CONFIRMED"))
                .log().body()
                .extract().jsonPath().getLong("id"); // Extract the booking ID

        // Retrieve the specific booking by ID and validate its details
        given()
                .when()
                .get("/bookings/book/{bookingId}", bookingId)
                .then()
                .contentType(ContentType.JSON)
                .body("id", equalTo(bookingId.intValue())) // Convert to int for comparison
                .body("user.id", equalTo(4))
                .body("carpooling.id", equalTo(3))
                .body("status", equalTo("CONFIRMED"))
                .log().body();

    }

    @Test
    public void bookTutoringAndRetrieveTest() {
        // Book a tutoring service and extract the booking ID
        Long bookingId = given()
                .contentType(ContentType.JSON)
                .queryParam("userId", 4)
                .queryParam("tutoringId", 3)
                .when()
                .post("/bookings/bookForTutoring")
                .then()
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("user.id", equalTo(4))
                .body("status", equalTo("CONFIRMED"))
                .log().body()
                .extract().jsonPath().getLong("id"); // Extract the booking ID

        // Retrieve the specific booking by ID and validate its details
        given()
                .when()
                .get("/bookings/book/{bookingId}", bookingId)
                .then()
                .contentType(ContentType.JSON)
                .body("id", equalTo(bookingId.intValue())) // Convert to int for comparison
                .body("user.id", equalTo(4))
                .body("status", equalTo("CONFIRMED"))
                .log().body();
    }

    @Test
    public void retrieveBookingDetails() {
        // Assuming the response JSON you provided
        given()
                .when()
                .get("/bookings/book/{bookingId}", 3) // Replace with the actual booking ID
                .then()
                .contentType(ContentType.JSON)
                .body("id", equalTo(3))
                .body("user.id", equalTo(72))
                .body("user.name", equalTo("seeker1"))
                .body("user.email", equalTo("seeker"))
                .body("user.status", equalTo("SEEKER"))
                .body("bookingDate", notNullValue())
                .body("status", equalTo("CONFIRMED"))
                .body("tutor", nullValue()) // Assuming tutor should be null for tutoring bookings
                .log().body();
    }


}