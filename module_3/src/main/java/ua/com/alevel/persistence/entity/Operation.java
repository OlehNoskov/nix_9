package ua.com.alevel.persistence.entity;

import groovy.transform.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Operation extends BaseEntity {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_income_expense")
    private boolean categoryIncomeExpense;

    public Operation(String categoryName, boolean categoryIncomeExpense) {
        this.categoryName = categoryName;
        this.categoryIncomeExpense = categoryIncomeExpense;
    }

    public Operation() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryIncomeExpense() {
        return categoryIncomeExpense;
    }

    public void setCategoryIncomeExpense(boolean categoryIncomeExpense) {
        this.categoryIncomeExpense = categoryIncomeExpense;
    }
}