package onetoone;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import onetoone.HomeService.HomeServiceListing;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.aspectj.bridge.MessageUtil.fail;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeServiceListingControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    public void getAllListingsTest() {
        given()
                .when()
                .get("/listings")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                 // Assuming there are at least 2 listings in the database
    }

    @Test
    public void getListingByIdTest() {
        given()
                .when()
                .get("/listings/1") // Assuming the listing with ID 1 exists
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1)); // Assuming the ID of the retrieved listing is 1
    }


    @Test
    public void updateListingTest() {
        // Update an existing listing (assuming listing ID 1 exists)
        HomeServiceListing updatedListing = new HomeServiceListing();
        updatedListing.setServiceType("Updated Cleaning");
        updatedListing.setDescription("Updated test description");
        updatedListing.setPrice(60.0);

        given()
                .contentType(ContentType.JSON)
                .body(updatedListing)
                .when()
                .put("/listings/5")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("serviceType", equalTo("Updated Cleaning"))
                .body("description", equalTo("Updated test description"))
                .body("price", equalTo(60.0f)); // Assuming the price is a float value
    }

    @Test
    public void deleteListingTest() {
        Response response = given()
                .when()
                .delete("/listings/41"); // Assuming the listing with ID 3 exists

        // Check status code
        response.then().statusCode(500);

        // Check if error message exists in the response
        if (response.getBody().asString().contains("errorMessage")) {
            // Extract error message from the response and verify its non-nullness
            String errorMessage = response.jsonPath().getString("errorMessage");
            assertNotNull(errorMessage);
            assertEquals("Deletion failed", errorMessage); // Replace with the expected error message
        } else {
            fail("Error message not found in the response");
        }
    }

    @Test
    public void getListingsByListerIdTest() {
        int listerId = 3; // Replace with an existing lister ID
        given()
                .when()
                .get("/listingsByLister/" + listerId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                // Assuming the lister has at least 2 listings
    }

}




