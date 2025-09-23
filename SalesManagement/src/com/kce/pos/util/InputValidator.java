package com.kce.pos.util;

import com.kce.pos.exception.InvalidInputException;

public class InputValidator {
    public static int validatePositiveInt(int value) throws InvalidInputException {
        if (value <= 0) throw new InvalidInputException("Value must be positive.");
        return value;
    }

    public static double validatePositiveDouble(double value) throws InvalidInputException {
        if (value <= 0) throw new InvalidInputException("Value must be positive.");
        return value;
    }
}
