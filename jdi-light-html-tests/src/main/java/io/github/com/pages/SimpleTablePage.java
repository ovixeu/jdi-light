package io.github.com.pages;

import com.epam.jdi.mobile.elements.complex.table.DataTable;
import com.epam.jdi.mobile.elements.complex.table.Table;
import com.epam.jdi.mobile.elements.composite.WebPage;
import io.github.com.entities.Furniture;

public class SimpleTablePage extends WebPage {
	public static Table usersTable;
	public static DataTable<?, Furniture> products;
	public static DataTable<?, Furniture> furniture;

}