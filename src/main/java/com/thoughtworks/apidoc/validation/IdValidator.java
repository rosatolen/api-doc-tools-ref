package com.thoughtworks.apidoc.validation;

import java.util.function.Predicate;
import java.util.regex.Pattern;


public class IdValidator {

    private static final Pattern ID_REGEX_PATTERN = Pattern.compile("^[0-9]+$");

    private static final Predicate<String> ID_CHECKER = ((Predicate<String>) x -> x != null).and(ID_REGEX_PATTERN.asPredicate());

    public boolean validate(String input) {
        return ID_CHECKER.test(input);
    }
}
