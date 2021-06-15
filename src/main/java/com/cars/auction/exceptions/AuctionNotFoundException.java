package com.cars.auction.exceptions;

import javax.validation.constraints.NotNull;

public class AuctionNotFoundException extends RuntimeException {
    public AuctionNotFoundException(@NotNull String message) {
        super(message);
    }
}
