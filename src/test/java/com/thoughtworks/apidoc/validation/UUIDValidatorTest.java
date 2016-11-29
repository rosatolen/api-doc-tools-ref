package com.thoughtworks.apidoc.validation;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UUIDValidatorTest {

    public static final String VALID_UUID = UUID.randomUUID().toString();
    UUIDValidator validator = new UUIDValidator();

    @Test
    public void shouldBeFalseForNull() {
        assertThat(validator.validate(null), is(false));
    }

    @Test
    public void shouldBeFalseForEmptyString() {
        assertThat(validator.validate(""), is(false));
    }

    @Test
    public void shouldBeTrueForValidUUID() {
        assertThat(validator.validate(UUID.randomUUID().toString()), is(true));
    }

    @Test
    public void shouldBeFalseForPartialUUID() {
        assertThat(validator.validate(VALID_UUID.substring(5)), is(false));
    }

}