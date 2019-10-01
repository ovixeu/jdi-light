package io.github.epam.bootstrap.tests.composite.section.navs;

import io.github.epam.TestsInit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.elements.common.WindowsManager.closeWindow;
import static com.epam.jdi.light.elements.common.WindowsManager.switchToNewWindow;
import static com.epam.jdi.light.elements.composite.WebPage.getTitle;
import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.navsBaseA;
import static io.github.com.pages.BootstrapPage.navsBaseLi;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.hamcrest.CoreMatchers.is;
import static org.testng.Assert.assertEquals;

/**
 * Created by Dmitrii Pavlov on 30.09.2019
 * Email: delnote@gmail.com; Skype: Dmitrii Pavlov
 */

public class BaseTests extends TestsInit {

    private static final String JS_SCROLL_TO_ELEMENT = "arguments[0].scrollIntoView(true);";
    private static final String HEADER_RIGHT_PART = "#nav-base-li";
    private String link1 = "Active";
    private String link2 = "JDI Docs";
    private String link3 = "JDI - testing tool";
    private String link4 = "Disabled";
    private String pageTitle1 = "Home Page";
    private String pageTitle2 = "JDI · GitHub";
    private String pageTitle3 = "JDI Testing tools · GitHub";

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
        ((JavascriptExecutor) getDriver()).executeScript(JS_SCROLL_TO_ELEMENT, getDriver().findElement(By.cssSelector(HEADER_RIGHT_PART)));
    }

    @DataProvider
    public Object[][] listData() {
        return new Object[][]{
                {1, link1}, {2, link2}, {3, link3}, {4, link4}
        };
    }

    @DataProvider
    public Object[][] clickValidate() {
        return new Object[][]{
                {1, pageTitle1}, {2, pageTitle2}, {3, pageTitle3}
        };
    }

    @Test
    public void isValidationTests() {
        navsBaseLi.navItem.is()
                .size(4);
        navsBaseA.navItemLink.is()
                .size(4);
        navsBaseLi.is()
                .displayed()
                .enabled()
                .core()
                .hasClass("nav");
        navsBaseA.is()
                .displayed()
                .enabled()
                .core()
                .hasClass("nav");
        navsBaseLi.navItemLink.get(1)
                .is()
                .core()
                .hasClass("active");
        navsBaseLi.navItemLink.get(4)
                .is()
                .core()
                .hasClass("disabled");
    }

    @Test(dataProvider = "listData")
    public void itemsIsValidationTests(int index, String linkText) {
        navsBaseLi.navItem.get(index).is()
                .core()
                .hasClass("nav-item")
                .text(is(linkText));
        navsBaseLi.navItemLink.get(index).is()
                .core()
                .hasClass("nav-link")
                .text(is(linkText));
        navsBaseA.navItemLink.get(index).is()
                .core()
                .hasClass("nav-link")
                .text(is(linkText));
    }

    @Test(dataProvider = "clickValidate")
    public void linkClickableLiTests(int index, String pageTitle) {
        navsBaseLi.navItem.get(index).highlight();
        navsBaseLi.navItem.get(index).click();
        newWindowTitleCheck(pageTitle);
        navsBaseLi.navItem.get(index).unhighlight();
    }

    @Test(dataProvider = "clickValidate")
    public void linkClickableATests(int index, String pageTitle) {
        navsBaseA.navItemLink.get(index).highlight();
        navsBaseA.navItemLink.get(index).click();
        newWindowTitleCheck(pageTitle);
        navsBaseA.navItemLink.get(index).unhighlight();
    }

    private void newWindowTitleCheck(String pageTitle) {
        switchToNewWindow();
        assertEquals(getTitle(), pageTitle);
        closeWindow();
    }
}
