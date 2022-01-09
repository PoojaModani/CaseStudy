package Demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test1 {
    public String accessToken;
    public int count;
    public String id;
    public String product_name;
    public String update;
    public String delete;
	@Test (priority = 0)
	public void login() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		String requestbody ="{\r\n"
        		+ "	\"email\": \"pqr176@gmail.com\",\r\n"
        		+ "	\"password\": \"pqr@123\"\r\n"
        		+ "}\r\n"
        		+ "";
		
		Response response = given().
				header("content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		JsonPath data = new JsonPath(body);
		
		accessToken = data.get("accessToken");
		System.out.println(accessToken);
	}
	
	@Test(priority = 1)
	public void get_all_product() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		
		Response response = given().
				header("content-Type","application/json")
				 .header("Authorization","bearer "+accessToken)
				
				.when()
				.get("/products")
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		//System.out.println(body);
		JsonPath data =  new JsonPath(body);
		count = data.get("count");
		System.out.println(count);
	}
	
	@Test(priority = 2)
	public void create_product() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		
		String requestbody = "{\r\n"
				+ "	\"name\": \"agyvd\",\r\n"
				+ "	\"price\": 17000\r\n"
				+ "}\r\n"
				+ "";

		
		Response response = given().
				header("content-Type","application/json")
				 .header("Authorization","bearer "+accessToken)
				 .body(requestbody)
				
				.when()
				.post("/products")
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		//System.out.println(body);
		JsonPath data =  new JsonPath(body);
		id = data.get("product._id");
		System.out.println(id);
	}
	
	@Test(priority = 3)
	public void get_product_by_id() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		
		Response response = given().
				header("content-Type","application/json")
				 .header("Authorization","bearer "+accessToken)
				
				.when()
				.get("/products/"+id)
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		//System.out.println(body);
		JsonPath data =  new JsonPath(body);
		product_name = data.get("name");
		System.out.println(product_name);
	}
	
	@Test(priority = 4)
	public void update_product_by_id() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		
		String requestbody = "{\r\n"
				+ "	\"name\": \"abckl\",\r\n"
				+ "	\"price\": 17000\r\n"
				+ "}\r\n"
				+ "";

		
		Response response = given().
				header("content-Type","application/json")
				 .header("Authorization","bearer "+accessToken)
				 .body(requestbody)
				
				.when()
				.patch("/products/"+id)
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		//System.out.println(body);
		JsonPath data =  new JsonPath(body);
		update = data.get("message");
		System.out.println(update);
	}
	
	@Test(priority = 5)
	public void delete() {
		
		RestAssured.baseURI = "https://ecommerceservice.herokuapp.com";
		
		Response response = given().
				header("content-Type","application/json")
				 .header("Authorization","bearer "+accessToken)
				
				.when()
				.delete("/products/"+id)
				
				.then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON)
				
				.extract().response();
		String body = response.asString();
		//System.out.println(body);
		JsonPath data =  new JsonPath(body);
		delete = data.get("message");
		System.out.println(delete);
	}
	
}

	
