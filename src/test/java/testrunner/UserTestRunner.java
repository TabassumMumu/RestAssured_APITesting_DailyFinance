package testrunner;

import com.github.javafaker.Faker;
import config.CostModel;
import config.Setup;
import config.UserModel;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class UserTestRunner extends Setup {

    UserController userController;
    @BeforeClass
    public void myUserController(){
        userController = new UserController(prop);
    }

    //Login by any user
    @Test (priority = 1, description = "Verify User can login successfully with valid email and password")
    public void doUserLogin() throws ConfigurationException {

        UserModel userModel = new UserModel();
        userModel.setEmail("mumutabassum74@gmail.com");
        //userModel.setEmail(prop.getProperty("email"));
        userModel.setPassword("123456");
        Response res = userController.doLogin(userModel);

        Assert.assertEquals(res.getStatusCode(),200 );

        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        Utils.setEnv("UserToken", token);
    }

    //Add any item
    @Test (priority = 2, description = "Verify user can add cost with valid data")
    public void addCostItem() throws ConfigurationException {
        Faker faker = new Faker();

        CostModel costModel = new CostModel();
        costModel.setItemName(faker.commerce().productName());
        costModel.setAmount(String.valueOf(faker.random().nextInt(600,1000)));
        costModel.setQuantity(String.valueOf(faker.random().nextInt(1,100)));
        costModel.setPurchaseDate(new SimpleDateFormat("MM/dd/yyyy")
                .format(faker.date().future(10, TimeUnit.DAYS)));
        costModel.setMonth(Month.of(faker.number().numberBetween(1, 13)).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        costModel.setRemarks(faker.lorem().sentence());
        Response res = userController.addCost(costModel);

        JsonPath jsonPath = res.jsonPath();
        String costID = jsonPath.get("_id");
        String itemName = jsonPath.get("itemName");
        String amount = jsonPath.get("amount");
        String quantity = jsonPath.get("quantity");
        String purchaseDate = jsonPath.get("purchaseDate");
        String month = jsonPath.get("month");
        String remarks = jsonPath.get("remarks");

        Utils.setEnv("costID",costID);
        Utils.setEnv("itemName",itemName);
        Utils.setEnv("quantity",quantity);
        Utils.setEnv("purchaseDate",purchaseDate);
        Utils.setEnv("month", month);
        Utils.setEnv("remarks", remarks);
        Utils.setEnv("amount", amount);

        System.out.println(res.asString());
        Assert.assertEquals( res.getStatusCode() , 201 );

    }

    // Edit any item name
    @Test (priority = 3, description = "Verify if user can edit cost")
    public void editCostItem() throws ConfigurationException {
        Faker faker = new Faker();

        CostModel costModel = new CostModel();
        costModel.setItemName(faker.commerce().productName());
        costModel.setAmount(prop.getProperty("amount"));
        costModel.setQuantity(prop.getProperty("quantity"));
        costModel.setPurchaseDate(prop.getProperty("purchaseDate"));
        costModel.setMonth(prop.getProperty("month"));
        costModel.setRemarks(prop.getProperty("remarks"));
        Response res = userController.editCost(costModel, prop.getProperty("costID"));

        JsonPath jsonPath = res.jsonPath();
        String itemName = jsonPath.get("itemName");

        Utils.setEnv("itemName",itemName);

        Assert.assertEquals( res.getStatusCode() , 200 );

    }

    @Test (priority = 4, description = "Verify if user can see all the costlist")
    public void getCostList(){
        Response res = userController.getCostList();
        System.out.println(res.asString());
        Assert.assertEquals( res.getStatusCode() , 200 );
    }

    @Test (priority = 5, description = "Verify if User can delete cost successfully")
    public void deleteCostitem(){
        Response res = userController.deleteCost(prop.getProperty("costID"));
        Assert.assertEquals( res.getStatusCode() , 200 );
    }
}
