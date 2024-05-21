package ru.gb.sem11_prometheus.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(Long id) {
        super("< <<  !!!  >> > Product with id " + id + " not found.");
    }
}
