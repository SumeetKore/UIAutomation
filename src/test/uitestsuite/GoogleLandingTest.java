package test.uitestsuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import test.util.CaptureSnapshot;
import test.webpages.pages.GoogleLandingPage;

import static test.webpages.commons.ElementsUtil.isTextPresent;

public class GoogleLandingTest{
    WebDriver driver;
    String baseUrl = "https://www.google.com";
    GoogleLandingPage page;

    @BeforeSuite
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get(baseUrl);
        page = new GoogleLandingPage(driver);
    }

    @Test
    public void googleLandingTest() {
        page.enterQueryToSearchFor("toptal").doTheSearchAfterQueryEntered();
        Assert.assertTrue(isTextPresent(driver,"toptal"));

    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }
}
