package onetoone;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class reservationcontrollertest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }


    @Test
    public void getBookingByIdTest() {
        // Write test cases to get a booking by ID
        int bookingId = 4; // Modify with an existing booking ID
        given()
                .when()
                .get("/Bookings/" + bookingId)
                .then()
                .statusCode(200) // Assuming 200 is the success status code
                .body("id", equalTo(bookingId)); // Validate specific fields
    }


    @Test
    public void getBookingsByTypeTest() {
        String serviceType = "Cleaning"; // Modify with an existing service type
        given()
                .when()
                .get("/Bookings/type/" + serviceType)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0)); // Validate that the list is not empty
    }


    @Test
    public void deleteBookingTest() {
        int bookingId =6; // Modify with an existing booking ID
        given()
                .when()
                .delete("/Bookings/" + bookingId)
                .then()
                .statusCode(200)
                .body(equalTo("success")); // Modify with appropriate response message
    }







}
//    @Test
//    public void createBookingTest() {
//        // Create a request body for a new reservation
//        String requestBody = "{\"serviceType\": \"Plumbing\", \"timeslot\": \"2024-01-01\", \"user\": {\"id\": 1}}";
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/Bookings")
//                .then()
//                .statusCode(200)
//                .body(equalTo("Booking created successfully")); // Modify with appropriate response message
//    }

//@Test
//    public void updateBookingTest() {
//        int bookingId = 2; // Modify with an existing booking ID
//        // Create a request body for updating the reservation
//        String requestBody = "{\"serviceType\": \"Updated Type\", \"timeslot\": \"2024-01-02\"}";
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .put("/Bookings/" + bookingId)
//                .then()
//                .statusCode(200)
//                .body("serviceType", equalTo("Updated Type")); // Validate updated field

