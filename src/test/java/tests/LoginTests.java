package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.base.BaseTest;
import utils.AwsParameterStore;


public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String username = AwsParameterStore.getParameter("LT_USERNAME");
        String password = AwsParameterStore.getParameter("LT_PASSWORD");
        System.out.println("Here is the data " + username + " " + password);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
//        String username = AwsParameterStore.getParameter("LT_USERNAME");
        loginPage.login("tomsmith", "invalidPass");
        Assert.assertTrue(loginPage.getMessage().contains("Your username is invalid!"));
    }
}



