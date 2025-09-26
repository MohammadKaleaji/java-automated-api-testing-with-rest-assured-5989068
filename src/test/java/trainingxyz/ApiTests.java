package trainingxyz;

import org.junit.jupiter.api.Test;

import models.Product;

import static io.restassured.RestAssured.given;

public class ApiTests {

    private final String BASE_URL = "http://localhost:8888"; // ✅ Correct port from docker-compose

    /*
     * @Test
     * public void getCategories() {
     * String endpoint = BASE_URL + "/category/read.php"; // ✅ Singular 'category'
     * var response = given()
     * .when().get(endpoint)
     * .then().statusCode(200)
     * .extract().response().asString(); //✅ Extract the response as a string
     * System.out.println("API Response of getCategories is: " + response); //✅
     * Print the response
     * }
     */

    @Test // 1. Get Categories
    public void getCategories() {
        System.out.println("\n*** GET CATEGORIES:");
        String endpoint = BASE_URL + "/category/read.php"; // ✅ Singular 'category'
        var response = given()
                .when().get(endpoint)
                .then().statusCode(200);
        response.log().body(); // ✅ Log the response body
        // System.out.println("API Response of getCategories is: " + response);
    }

    @Test // 2. Get Product
    public void getProduct() {
        System.out.println("\n*** GET PRODUCT:");
        String endpoint = BASE_URL + "/product/read_one.php";

        var response = given().queryParam("id", 2) // ✅ Set the query parameter
                .when().get(endpoint) // ✅ Send the request
                .then().statusCode(200)
                .extract().response().asString(); // ✅ Extract the response as a string

        System.out.println("API Response of getProduct is: " + response); // ✅ Print the response
    }

    @Test // 3. Create Product
    public void createProduct() {
        System.out.println("\n*** CREATE PRODUCT:");
        String endpoint = BASE_URL + "/product/create.php";
        String body = """
                {
                    "name": "Test Product",
                    "description": "Test Description",
                    "price": 999,
                    "category_id": 19
                }
                """;
        var response = given().body(body). // ✅ Set the body of the request
                when().post(endpoint). // ✅ Send the request
                then().statusCode(201); // ✅ Check the status code
        response.log().body(); // ✅ Extract the response as a string
    }

    @Test // 4. Update Product
    public void updateProduct() {
        System.out.println("\n*** UPDATE PRODUCT:");
        String endpoint = BASE_URL + "/product/update.php";
        String body = """
                {
                    "id": 19,
                    "name": "Test Product Updated",
                    "description": "Test Description Updated",
                    "price": 999,
                    "category_id": 19
                }
                """;
        var response = given().body(body). // ✅ Set the body of the request
                when().post(endpoint). // ✅ Send the request
                then().statusCode(200); // ✅ Check the status code
        response.log().body(); // ✅ Extract the response as a string
    }

    @Test // 5. Delete Product
    public void deleteProduct() {
        System.out.println("\n*** DELETE PRODUCT:");
        String endpoint = BASE_URL + "/product/delete.php";
        String body = """
                {
                    "id": 30
                }
                """;
        var response = given().body(body). // ✅ Set the body of the request
                when().post(endpoint). // ✅ Send the request
                then().statusCode(200); // ✅ Check the status code
        response.log().body(); // ✅ Extract the response as a string
    }

    @Test // 6. Create Serialized Product
    public void createSerializedProduct() {
        System.out.println("\n*** CREATE SERIALIZED PRODUCT:");
        String endpoint = BASE_URL + "/product/create.php";
        Product product = new Product(
                0,
                "Test Product",
                "Test Description",
                1299,
                51);
        var response = given().body(product).when().post(endpoint).then().statusCode(201);
        response.log().body();
    }
}
