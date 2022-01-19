package ua.com.alevel.persistence.entity;

import groovy.transform.EqualsAndHashCode;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Operation extends BaseEntity {

    @Column(name = "category_name")
    @Getter
    @Setter
    private String categoryName;

    @Column(name = "category_income_expense")
    @Getter
    @Setter
    private boolean categoryIncomeExpense;

    public Operation(String categoryName, boolean categoryIncomeExpense) {
        this.categoryName = categoryName;
        this.categoryIncomeExpense = categoryIncomeExpense;
    }

    public Operation() {
    }
}