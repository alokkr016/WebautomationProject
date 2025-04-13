package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.base.BaseTest;
import utils.AwsParameterStore;

public class LoginTests extends BaseTest {

    private String username;
    private String password;
    private String invalidPassword;

    @BeforeClass
    public void setupCredentials() {
        username = AwsParameterStore.getParameter("heruku.validUsername");
        password = AwsParameterStore.getParameter("heruku.validPassword");
        invalidPassword = AwsParameterStore.getParameter("heruku.invalidPassword");
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, invalidPassword);
        Assert.assertTrue(loginPage.getMessage().contains("Your password is invalid!"));
    }
}
