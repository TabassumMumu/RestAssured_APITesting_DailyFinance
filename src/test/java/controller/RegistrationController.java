package controller;

import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RegistrationController {

    Properties prop;
    public RegistrationController(Properties prop){
        this.prop = prop;
    }

    public Response doRegistration(UserModel userModel){
        Response res = given().contentType("application/json")
                .body(userModel).when().post("/api/auth/register");
        return res;
    }


}
