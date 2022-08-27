package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcommerceAPI {
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		//Login code
		
		RequestSpecification reqspec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginRequest loginreq = new LoginRequest();
		
		loginreq.setUserEmail("sharathpatil125@gmail.com");
		loginreq.setUserPassword("Smayan@2022");
		
		RequestSpecification reqLogin = given().log().all().spec(reqspec).body(loginreq);
		
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		
		String token = loginResponse.getToken();
		
		String userid = loginResponse.getUserId();	
		
		
		//Add Product code
		
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization", token).build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Shirt")
		.param("productAddedBy", userid).param("productCategory", "fashion")
		.param("productSubCategory","Clothing").param("productPrice", "1500")
		.param("productDescription", "Peter England").param("productFor", "Men")
		.multiPart("productImage", new File("C:\\Users\\User\\Desktop\\Separation_Docs\\GPS_Screenshot.jpg"));
		// we use multipart method to upload any file using REST ASSURED
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		
		JsonPath j = new JsonPath(addProductResponse);
		
		String productId = j.get("productId");
		
		System.out.println(productId);
		
		
		//create an order
		
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON).build();
		OrderDetails orderDetail = new OrderDetails();
		
		orderDetail.setCountry("India");
		
		orderDetail.setProductOrderId(productId);
		
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		
		orderDetailsList.add(orderDetail);
		
		Orders order = new Orders();
		
		order.setOrders(orderDetailsList);
		
		RequestSpecification createOrderReq = given().spec(createOrderBaseReq).when().body(order);
		
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		
		System.out.println(responseAddOrder);
		
		
		//delete product
		
		RequestSpecification  deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization",token).setContentType(ContentType.JSON).build();
		
		RequestSpecification deleteProductReq = given().log().all().when().spec(deleteProductBaseReq).pathParam("productId", productId);
		
		//Note: For passing path params in http requests we use {} like shown below.
		
		String deleteProductResponse = deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all()
				.extract().response().asString();
		
		JsonPath js = new JsonPath(deleteProductResponse);
		
		System.out.println(js.get("message"));
		
		
		Assert.assertEquals("Product Deleted Successfully", js.get("message"));
		
		
	}
}
