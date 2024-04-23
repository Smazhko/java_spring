package ru.gb.sem5_jpa.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TaskNotFoundException extends EntityNotFoundException {
    public TaskNotFoundException(Long id) {
        super("< <<  !!!  >> > Task with id " + id + " not found.");
    }
}
