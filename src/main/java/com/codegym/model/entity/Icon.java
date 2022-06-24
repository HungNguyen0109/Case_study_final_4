package com.codegym.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Icon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String linkIcon;

    public Icon() {
    }

    public Icon(Long id, String linkIcon) {
        this.id = id;
        this.linkIcon = linkIcon;
    }
}
