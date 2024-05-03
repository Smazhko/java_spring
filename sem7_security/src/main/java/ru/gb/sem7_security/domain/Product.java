package ru.gb.sem7_security.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer count;
}
