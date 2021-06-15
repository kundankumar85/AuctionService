package com.cars.auction.exceptions;

import javax.validation.constraints.NotNull;

public class BidNotValidException extends RuntimeException {
    public BidNotValidException(@NotNull String message) {
        super(message);
    }
}
