package com.polarbookshop.catalogservice.domain;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book("1234567891", "Title", "Author", 100.00);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var book = new Book("", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(2);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The book ISBN must be defined.")
                .contains("The ISBN format must be valid.");
    }
}
