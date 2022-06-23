package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "money_type")
@Data
public class MoneyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public MoneyType() {
    }

    public MoneyType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MoneyType(String name) {
        this.name = name;
    }

}
