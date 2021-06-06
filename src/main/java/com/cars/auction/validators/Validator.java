package com.cars.auction.validators;

public interface Validator<S,T> {
    T validate(S source);
}
