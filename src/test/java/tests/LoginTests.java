package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.base.BaseTest;
import utils.AwsParameterStore;


public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        String username = AwsParameterStore.getParameter("LT_USERNAME");
        String password = AwsParameterStore.getParameter("LT_PASSWORD");
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        String username = AwsParameterStore.getParameter("LT_USERNAME");
        loginPage.login(username, "invalidPass");
        Assert.assertTrue(loginPage.getMessage().contains("Your password is invalid!"));
    }
}



