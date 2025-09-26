package trainingxyz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.everyItem;

import static io.restassured.RestAssured.given;
import models.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // ✅ Add this line
public class ProductCycle {
    private final String BASE_URL = "http://localhost:8888";
    private int createdProductId; // This will now persist across tests

    @Test // 1. Create Product
    @Order(1)
    public void createProduct() {
        System.out.println("\n*** CREATE PRODUCT:");
        String endpoint = BASE_URL + "/product/create.php";

        Product product = new Product(
                0,
                "Sweatband_Test_" + System.currentTimeMillis(), // Make it unique
                "Sweatband Description",
                5.99,
                3);

        var response = given()
                .contentType("application/json")
                .body(product)
                .when().post(endpoint)
                .then().assertThat().statusCode(201);

        response.log().body();

        // DEBUG: Let's see what the read.php returns
        System.out.println("\n🔍 DEBUG - All products response:");
        String allProductsResponse = given()
                .when().get(BASE_URL + "/product/read.php")
                .then().statusCode(200)
                .extract().body().asString();

        System.out.println("All Products: " + allProductsResponse);

        // Extract the ID of the first product (most recent)
        createdProductId = given()
                .when().get(BASE_URL + "/product/read.php")
                .then().statusCode(200)
                .extract().jsonPath().getInt("records[0].id");

        System.out.println("✅ Extracted Product ID: " + createdProductId);

        // DEBUG: Let's also get the name to verify
        String productName = given()
                .when().get(BASE_URL + "/product/read.php")
                .then().statusCode(200)
                .extract().jsonPath().getString("records[0].name");

        System.out.println("✅ Product Name: " + productName);
    }

    @Test // 2. Read Product
    @Order(2)
    public void readProduct() {
        System.out.println("\n*** READ PRODUCT:");
        String endpoint = BASE_URL + "/product/read_one.php";

        var response = given()
                .queryParam("id", createdProductId)
                .when().get(endpoint)
                .then().assertThat().statusCode(200)
                .body("description", equalTo("Sweatband Description"));

        response.log().body();
    }

    @Test // 3. Update Product Price
    @Order(3)
    public void updateProductPrice() {
        System.out.println("\n*** UPDATE PRODUCT PRICE:");
        String endpoint = BASE_URL + "/product/update.php";

        // Create JSON body manually since the API expects specific format
        String updateBody = String.format("""
                {
                    "id": %d,
                    "name": "Sweatband_Updated",
                    "description": "Updated Sweatband Description",
                    "price": "10.99",
                    "category_id": "3"
                }
                """, createdProductId);

        var response = given()
                .contentType("application/json") // ✅ Add content type
                .body(updateBody) // ✅ Use JSON string
                .when().post(endpoint) // ✅ Use POST, not PUT
                .then().assertThat().statusCode(200)
                .body("price", equalTo(10.99));

        response.log().body();
    }

    @Test // 4. Read Updated Product
    @Order(4)
    public void readUpdatedProduct() {
        System.out.println("\n*** READ UPDATED PRODUCT:");
        String endpoint = BASE_URL + "/product/read_one.php";

        var response = given()
                .queryParam("id", createdProductId)
                .when().get(endpoint)
                .then().assertThat().statusCode(200);

        response.log().body();
    }

    @Test // 5. Delete Product
    @Order(5)
    public void deleteProduct() {
        System.out.println("\n*** DELETE PRODUCT:");
        String endpoint = BASE_URL + "/product/delete.php";

        // Delete expects JSON body with ID, not query param
        String deleteBody = String.format("""
                {
                    "id": %d
                }
                """, createdProductId);

        var response = given()
                .contentType("application/json") // ✅ Add content type
                .body(deleteBody) // ✅ Use JSON body, not query param
                .when().post(endpoint) // ✅ Use POST, not DELETE
                .then().assertThat().statusCode(200);

        response.log().body();
    }

    @Test // 6. Get Products
    @Order(6)
    public void getProducts() {
        System.out.println("\n*** GET PRODUCTS:");
        String endpoint = BASE_URL + "/product/read.php";
        
        var response = given()
            .when().get(endpoint)
            .then().assertThat().statusCode(200)
            .body("records.id", notNullValue())  // Check records exists
            .body("records.size()", greaterThan(0));  // Use size() method instead of .size
        
        response.log().body();
    }
}