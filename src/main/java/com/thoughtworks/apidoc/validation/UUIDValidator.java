package com.thoughtworks.apidoc.validation;

import java.util.function.Predicate;
import java.util.regex.Pattern;


public class UUIDValidator {

    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    private static final Predicate<String> UUID_CHECKER = ((Predicate<String>) x -> x != null).and(UUID_REGEX_PATTERN.asPredicate());

    public boolean validate(String input) {
        return UUID_CHECKER.test(input);
    }
}
