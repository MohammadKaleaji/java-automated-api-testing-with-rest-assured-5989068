package trainingxyz;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    private final String BASE_URL = "http://localhost:8888"; // ✅ Correct port from docker-compose

    /*@Test
    public void getCategories() {
        String endpoint = BASE_URL + "/category/read.php"; // ✅ Singular 'category'
        var response = given()
                .when().get(endpoint)
                .then().statusCode(200)
                .extract().response().asString(); //✅ Extract the response as a string 
        System.out.println("API Response of getCategories is: " + response); //✅ Print the response
    }*/

    @Test
    public void getCategories() {
        System.out.println("\n* GET CATEGORIES:");
        String endpoint = BASE_URL + "/category/read.php"; // ✅ Singular 'category'
        var response = given()
                .when().get(endpoint)
                .then().statusCode(200);
                response.log().body(); //✅ Log the response body
        //System.out.println("API Response of getCategories is: " + response);
    }

    @Test
    public void getProduct() {
        System.out.println("\n* GET PRODUCT:");
        String endpoint = BASE_URL + "/product/read_one.php";

        var response = given().queryParam("id", 2)
                .when().get(endpoint)
                .then().statusCode(200)
                .extract().response().asString();

        System.out.println("API Response of getProduct is: " + response);
    }

    @Test
    public void createProduct() {
        System.out.println("\n* CREATE PRODUCT:");
        String endpoint = BASE_URL + "/product/create.php";
        String body = """
                {
                    "name": "Test Product",
                    "description": "Test Description",
                    "price": 999,
                    "category_id": 19
                }
                """;
        
        var response = given().body(body).  //✅ Set the body of the request
        when().post(endpoint). //✅ Send the request
        then().statusCode(201); //✅ Check the status code
        response.log().body(); //✅ Extract the response as a string

        
    }
}
