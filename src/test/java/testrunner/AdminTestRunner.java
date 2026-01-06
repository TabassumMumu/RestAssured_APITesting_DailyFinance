package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import controller.AdminController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;

public class AdminTestRunner extends Setup {

    AdminController adminController;
    @BeforeClass
    public void myAdminController(){
        adminController = new AdminController(prop);
    }

    //Login by admin (email: admin@test.com, password: admin123)
    @Test (priority = 1, description = "Verify Admin can login with Valid email and password")
    public void adminLogin() throws ConfigurationException {

        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");
        Response res =  adminController.doAdminLogin(userModel);
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(),200 );

        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        Utils.setEnv("adminToken", token);
    }

    //Get user list
    //@Test (priority = 2,description = "Verify if admin can see full user list")
    public void getUserList(){
        Response res = adminController.getUserList();
        System.out.println(res.asString());
        Assert.assertEquals( res.getStatusCode() , 200 );
    }

    //search the new user by user id
    @Test (priority = 3,description = "Verify if admin can search user by userID")
    public void searchUserByID(){
        Response res = adminController.searchUserByID(prop.getProperty("userID"));
        System.out.println(res.asString());
        Assert.assertEquals( res.getStatusCode() , 200 );
    }

    //edit the user info (e.g. firstname, phonenumber)
    @Test (priority = 4, description = "Verify Admin can edit user info")
    public void userEdit() throws ConfigurationException {

        Faker faker = new Faker();

        UserModel userModel = new UserModel();
        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(prop.getProperty("lastName"));
        userModel.setEmail(prop.getProperty("email"));
        userModel.setPassword("12345");
        userModel.setPhoneNumber("012" + Utils.randomNumber(9999999, 1000000));
        userModel.setAddress(prop.getProperty("address"));
        userModel.setGender("Female");
        userModel.setTermsAccepted(true);
        Response res = adminController.editUser(userModel, prop.getProperty("userID"));

        JsonPath jsonPath = res.jsonPath();
        String firstName = jsonPath.get("firstName");

        Utils.setEnv("firstName", firstName);

        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
