package com.epam.jdi.mobile.ui.html.asserts;

import com.epam.jdi.mobile.asserts.generic.SelectedAssert;
import com.epam.jdi.mobile.asserts.generic.UIAssert;
import com.epam.jdi.mobile.common.JDIAction;
import com.epam.jdi.mobile.ui.html.elements.common.Checkbox;
import org.hamcrest.Matchers;

import static com.epam.jdi.mobile.asserts.core.SoftAssert.jdiAssert;
import static org.hamcrest.Matchers.is;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class CheckboxAssert extends UIAssert<CheckboxAssert, Checkbox>
        implements SelectedAssert<CheckboxAssert> {
    @JDIAction("Assert that '{name}' is selected")
    public CheckboxAssert selected() {
        jdiAssert(getIsSelected(), Matchers.is("selected"));
        return this;
    }
    @JDIAction("Assert that '{name}' is not selected")
    public CheckboxAssert deselected() {
        jdiAssert(getIsSelected(), Matchers.is("not selected"));
        return this;
    }

    //protected
    protected String getIsSelected() {
        return element.isSelected() ? "selected" : "not selected";
    }
}