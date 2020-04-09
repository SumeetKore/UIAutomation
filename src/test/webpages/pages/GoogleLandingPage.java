package test.webpages.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import test.webpages.pages.Page;

import java.util.List;

import static test.util.CaptureSnapshot.captureSnapshotForAllure;
import static test.webpages.commons.ElementsUtil.waitForElementToBeClickable;
import static test.webpages.commons.ElementsUtil.waitForPageLoaded;

public class GoogleLandingPage extends Page {
    public GoogleLandingPage(WebDriver driver) {
        super(driver);
    }

    private final static String SEARCH_INPUT_CSS = "input[name=\"q\"]";
    private final static String GOOGLE_SEARCH_RESULT_LIST_CSS = "div[id*=\"ires\"] div.rc>div.r>a[href]";

    @FindBy(css = SEARCH_INPUT_CSS)
    private WebElement searchInput;
    @FindBy(css = GOOGLE_SEARCH_RESULT_LIST_CSS)
    private List<WebElement> googleSearchResults;

    public GoogleLandingPage enterQueryToSearchFor(String googleQuery) {
        log.info("entering query to search in google input");
        waitForPageLoaded(driver);
        waitForElementToBeClickable(driver, searchInput);
        searchInput.clear();
        searchInput.sendKeys(googleQuery);
        captureSnapshotForAllure(driver);
        return this;
    }

    public GoogleLandingPage doTheSearchAfterQueryEntered() {
        log.info("sending 'Enter' key");
        searchInput.sendKeys(Keys.ENTER);
        waitForPageLoaded(driver);
        return new GoogleLandingPage(driver);
    }

    @Step("analyzing found results and dumping screenshot of the page")
    public int getNumberOfFoundResults() {
        captureSnapshotForAllure(driver);
        log.info("getting the length of list representing search results found");
        return googleSearchResults.size();
    }
}
