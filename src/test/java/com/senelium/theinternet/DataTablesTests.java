package com.senelium.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.constant.SortOrder;
import com.senelium.model.Person;
import com.senelium.pom.theinternet.DataTablesPage;
import org.testng.annotations.Test;

import java.util.Comparator;

public class DataTablesTests extends TheInternetTestBase {

    @Test(description = "Verify the sorting 'Example 1' table")
    void testDataTables() {
        homePage.openPage("Sortable Data Tables");
        var dataTablesPage = new DataTablesPage();
        var dataBeforeSorting = dataTablesPage.getExampleOneTableData();
        dataBeforeSorting.sort(Comparator.comparing(Person::lastName));
        dataTablesPage.sortTableOneByHeader("Last Name", SortOrder.ASCENDING);
        var dataAfterSorting = dataTablesPage.getExampleOneTableData();
        SelAssert.assertEqual(dataAfterSorting, dataBeforeSorting, "Verify the table is sorted");
    }
}
