package com.cars.auction.service;

import com.cars.auction.dto.ItemDTO;
import com.cars.auction.entity.Item;

public interface ItemService {

    Item create(ItemDTO item);
}
