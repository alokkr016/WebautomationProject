package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.JavaScriptAlertsPage;
import tests.base.BaseTest;

public class JavaScriptAlertsTest extends BaseTest {

    @Test
    public void testJSAlert() {
        JavaScriptAlertsPage alertPage = new JavaScriptAlertsPage();
        alertPage.triggerJSAlert();
        Assert.assertEquals(alertPage.getResultMessage(), "You successfully clicked an alert");
    }

    @Test
    public void testJSConfirmAccept() {
        JavaScriptAlertsPage alertPage = new JavaScriptAlertsPage();
        alertPage.triggerJSConfirm(true);
        Assert.assertEquals(alertPage.getResultMessage(), "You clicked: Ok");
    }

    @Test
    public void testJSConfirmDismiss() {
        JavaScriptAlertsPage alertPage = new JavaScriptAlertsPage();
        alertPage.triggerJSConfirm(false);
        Assert.assertEquals(alertPage.getResultMessage(), "You clicked: Cancel");
    }

    @Test
    public void testJSPromptAccept() {
        JavaScriptAlertsPage alertPage = new JavaScriptAlertsPage();
        alertPage.triggerJSPrompt("Hello", true);
        Assert.assertEquals(alertPage.getResultMessage(), "You entered: Hello");
    }

    @Test
    public void testJSPromptDismiss() {
        JavaScriptAlertsPage alertPage = new JavaScriptAlertsPage();
        alertPage.triggerJSPrompt("Ignored", false);
        Assert.assertEquals(alertPage.getResultMessage(), "You entered: null");
    }
}