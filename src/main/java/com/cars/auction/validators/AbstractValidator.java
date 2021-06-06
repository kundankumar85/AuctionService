package com.cars.auction.validators;

import com.cars.auction.entity.Item;
import com.cars.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public abstract class AbstractValidator<S, T> implements Validator<S, T> {

    @Autowired
    private ItemRepository itemRepository;

    protected Item findItem(String itemCode) {
        Optional<Item> itemOptional = itemRepository.findById(itemCode);
        return itemOptional.orElse(null);
    }

    protected boolean itemCodeExists(String itemCode) {
        return itemRepository.existsById(itemCode);
    }

}
