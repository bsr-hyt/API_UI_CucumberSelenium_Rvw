package com.exlab.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Map<String,Object> getToken(String email, String password){  //Neden map kullandık; bu emtodu çağırırken .headers("token", Authorization.getToken(email, password)) de "token" yazmamak için
        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email) //Multipart olduğu için formParam yazdık.
                .and()
                .formParam("password", password)
                .when().log().all()
                .post("/allusers/login");

        Map<String,Object> map = new HashMap<>();
        map.put("token",response.path("token"));

        return map;
    }
}
