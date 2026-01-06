package controller;

import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class AdminController {

    Properties prop;
    public AdminController(Properties prop){
        this.prop = prop;
    }

    public Response doAdminLogin(UserModel userModel){
        Response res = given().contentType("application/json").body(userModel)
                .when().post("/api/auth/login");
        return res;
    }

    public Response getUserList(){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().get("/api/user/users");
        return res;
    }

    public Response searchUserByID(String userID){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().get("/api/user/" + userID);
        return res;
    }

    public Response editUser(UserModel userModel, String userID){
        Response res = given().contentType("application/json").body(userModel)
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when().put("/api/user/" + userID);
        return res;
    }
}
