package com.cars.auction.service.impl;

import com.cars.auction.dto.ItemDTO;
import com.cars.auction.entity.Item;
import com.cars.auction.repository.ItemRepository;
import com.cars.auction.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void create() {
        Item item = new Item("A1","BMW");
        ItemDTO itemDTO = new ItemDTO("A1","BMW");
        BDDMockito.given(itemRepository.save(any())).willReturn(item);
        Item response = itemService.create(itemDTO);
        then(response.getItemCode()).isEqualTo(itemDTO.getItemCode());
    }
}