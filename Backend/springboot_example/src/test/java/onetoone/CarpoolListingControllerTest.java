package onetoone;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import onetoone.carpooling.CarpoolListing;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class CarpoolListingControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

//    @Test
//    public void testGetAllCarpoolListings() {
//        given()
//                .when()
//                .get("/listers")
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body("size()", greaterThan(0));
//    }

    @Test
    public void testGetCarpoolListingById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/listers/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1));
    }

    @Test
    public void testGetListingsByUserId() {
        given()
                .pathParam("userId", 2)
                .when()
                .get("/listings/byUser/{userId}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    //    this is expected to get a failure message
    @Test
    public void createPersonTest() {
        // Define user and travel history details
        CarpoolListing carpool = new CarpoolListing("John2", "John2", 25, "", 2);

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(carpool)
                .when()
                .post("/listers");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Extract "message" from the response and compare with expected value
        String message = response.jsonPath().getString("message");
        assertEquals("failure", message);
    }@Test
    public void deleteCarpoolListingTest() {
        given()
                .contentType("application/json") // Set the appropriate content type
                .pathParam("id", 2)
                .when()
                .delete("/listers/{id}")
                .then()
                .statusCode(200)
                .body(equalTo("{\"message\":\"success\"}")); // Adjust this based on the actual response

        // If the response is 'text/plain'
        // String responseBody = given().when().delete("/carpool/listing/{listingId}").asString();
        // Manually parse the response as needed
        // Assert.assertEquals("success", parsePlainTextResponse(responseBody));
    }
}
