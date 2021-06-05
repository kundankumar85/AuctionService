package com.cars.auction.service.impl;

import com.cars.auction.dto.ItemDTO;
import com.cars.auction.entity.Item;
import com.cars.auction.repository.ItemRepository;
import com.cars.auction.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    @Override
    public Item create(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getItemCode(),itemDTO.getItemName());
        return itemRepository.save(item);
    }
}
