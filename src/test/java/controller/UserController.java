package controller;

import config.CostModel;
import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController {

    Properties prop;
    public UserController(Properties prop){
        this.prop = prop;
    }

    public Response doLogin(UserModel userModel){
        Response res = given().contentType("application/json").body(userModel)
                .when().post("/api/auth/login");
        return res;
    }

    public Response addCost(CostModel costModel){
        Response res = given().contentType("application/json").body(costModel)
                .header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .post("/api/costs");
        return res;
    }

    public Response editCost(CostModel costModel, String costID){
        Response res  = given().contentType("application/json").body(costModel)
                .header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .put("/api/costs/" + costID);
        return res;
    }

    public Response getCostList(){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .get("/api/costs");
        return res;
    }

    public Response deleteCost(String costID){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("UserToken"))
                .delete("/api/costs/" + costID);
        return res;
    }
}
