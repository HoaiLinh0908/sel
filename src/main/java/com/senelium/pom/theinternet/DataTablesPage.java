package com.senelium.pom.theinternet;

import com.senelium.constant.SortOrder;
import com.senelium.element.Table;
import com.senelium.model.Person;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class DataTablesPage {
    private final Table tableOne;
    private final Table tableTwo;

    public DataTablesPage() {
        this.tableOne = new Table(By.id("table1"));
        this.tableTwo = new Table(By.id("table2"));
    }

    public void sortTableOneByHeader(String name, SortOrder order) {
        var header = this.tableOne.getHeader(name);
        var orderClassName = "header" + switch (order) {
            case ASCENDING -> " headerSortDown";
            case DESCENDING -> " headerSortUp";
            case UNSORTED -> "";
        };

        while (!header.getProperty("className").equals(orderClassName)) {
            header.click();
        }
    }

    public List<Person> getExampleOneTableData() {
        var data = this.tableOne.getTableData();
        System.out.println(data);
        return data.stream().map(person -> new Person(
                person.get("Last Name"),
                person.get("First Name"),
                person.get("Email"),
                person.get("Due"),
                person.get("Web Site"))
        ).collect(Collectors.toList());
    }
}
