package io.github.com.sections;

import com.epam.jdi.mobile.elements.complex.dropdown.Dropdown;
import com.epam.jdi.mobile.elements.composite.Form;
import com.epam.jdi.mobile.elements.interfaces.complex.IsCombobox;
import com.epam.jdi.mobile.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.mobile.ui.html.elements.common.Button;
import com.epam.jdi.mobile.ui.html.elements.common.Checkbox;
import com.epam.jdi.mobile.ui.html.elements.common.TextArea;
import com.epam.jdi.mobile.ui.html.elements.common.TextField;
import io.github.com.entities.Contacts;

public class ContactForm extends Form<Contacts> {
	TextField name, lastName, position, passportNumber, passportSeria;

	Dropdown gender;
	IsCombobox religion;

	Checkbox passport, acceptConditions;
	TextArea description;

	@UI("['Submit']") public Button submit;
}