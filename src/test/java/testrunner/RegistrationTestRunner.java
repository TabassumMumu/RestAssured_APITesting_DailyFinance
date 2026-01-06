package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import controller.RegistrationController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import utils.Utils;

public class RegistrationTestRunner extends Setup {

    RegistrationController registrationController;
    @BeforeClass
    public void myRegistrationController(){
        registrationController = new RegistrationController(prop);
    }

    //Register a new user
    @Test (priority = 1, description = "Verify User can Register successfully")
    public void userRegistration() throws ConfigurationException {
        Faker faker = new Faker();

        UserModel userModel = new UserModel();
        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail("TestUser +"+ Utils.randomNumber(100,10) +"@gmail.com");
        userModel.setPassword("12345");
        userModel.setPhoneNumber("012"+ Utils.randomNumber(9999999,1000000));
        String address = faker.country().capital();
        userModel.setAddress(address);
        userModel.setGender("Female");
        userModel.setTermsAccepted(true);
        Response res = registrationController.doRegistration(userModel);

        JsonPath jsonPath = res.jsonPath();
        String userID = jsonPath.get("_id");
        String firstName = jsonPath.get("firstName");
        String lastName = jsonPath.get("lastName");
        String email = jsonPath.get("email");

        Utils.setEnv("userID",userID);
        Utils.setEnv("firstName",firstName);
        Utils.setEnv("lastName",lastName);
        Utils.setEnv("email",email);
        Utils.setEnv("address", address);

        System.out.println(res.asString());
        Assert.assertEquals( res.getStatusCode() , 201 );

    }

}
