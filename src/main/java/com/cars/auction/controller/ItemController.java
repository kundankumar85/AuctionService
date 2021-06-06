package com.cars.auction.controller;

import com.cars.auction.dto.ItemDTO;
import com.cars.auction.entity.Item;
import com.cars.auction.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;


    @PostMapping
    public ResponseEntity<Item> create(@RequestBody @Valid ItemDTO itemDto){
        log.info("Create item");
        return ResponseEntity.status(HttpStatus.OK).body(itemService.create(itemDto));
    }
}
