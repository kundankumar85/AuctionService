package com.cars.auction.controller;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.service.AuctionService;
import com.cars.auction.validators.impls.AuctionValidatorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionValidatorImpl auctionValidator;
    private final AuctionService auctionService;

    @PostMapping
    ResponseEntity<Auction> startAuction(@RequestBody @Valid AuctionDetailDTO auctionDetailDTO){
        auctionValidator.validate(auctionDetailDTO);
        return ResponseEntity.ok(auctionService.startAuction(auctionDetailDTO));
    }
}
