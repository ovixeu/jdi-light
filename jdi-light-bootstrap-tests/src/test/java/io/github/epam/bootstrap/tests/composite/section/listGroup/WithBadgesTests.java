package io.github.epam.bootstrap.tests.composite.section.listGroup;

import io.github.epam.TestsInit;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.listGroupWithBadges;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class WithBadgesTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
    }

    String text1 = "Cras justo odio";
    String text2 = "Dapibus ac facilisis in";
    String text3 = "Morbi leo risus";

    @DataProvider
    public Object[][] listData() {
        return new Object[][]{
                {1, text1}, {2, text2}, {3, text3}
        };
    }

    @Test
    public void initTests() {
        listGroupWithBadges.listGroup.is().size(3);
        listGroupWithBadges.badge.is().size(3);
    }

    @Test (dataProvider = "listData")
    public void listGroupTests(int num, String text) {
        listGroupWithBadges.listGroup.get(num).is()
                .text(containsString(text))
                .css("font-size", is("14px"))
                .hasClass("list-group-item d-flex justify-content-between align-items-center");
    }

    @Test
    public void badgeTests() {
        listGroupWithBadges.badge.get(1).is()
                .text("14")
                .css("font-size", is("10.5px"))
                .hasClass("badge badge-primary badge-pill")
                .tag(is("span"));
        listGroupWithBadges.badge.get(2).is()
                .text("2")
                .css("font-size", is("10.5px"))
                .hasClass("badge badge-primary badge-pill")
                .tag(is("span"));
        listGroupWithBadges.badge.get(3).is()
                .text("1")
                .css("font-size", is("10.5px"))
                .hasClass("badge badge-primary badge-pill")
                .tag(is("span"));
    }



}
