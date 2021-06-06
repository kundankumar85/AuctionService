package com.cars.auction.exceptions;

import javax.validation.constraints.NotNull;

public class MultipleAuctionExistsException extends RuntimeException {
    public MultipleAuctionExistsException(@NotNull String message) {
        super(message);
    }
}
