package com.cars.auction.controller;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.entity.Auction;
import com.cars.auction.service.AuctionService;
import com.cars.auction.timer.AuctionTimerTask;
import com.cars.auction.validators.impls.AuctionValidatorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;
import java.util.Timer;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionValidatorImpl auctionValidator;
    private final AuctionService auctionService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping
    public ResponseEntity<Auction> startAuction(@RequestBody @Valid AuctionDetailDTO auctionDetailDTO){
        auctionValidator.validate(auctionDetailDTO);
        Auction auction = auctionService.startAuction(auctionDetailDTO);

        AuctionTimerTask auctionTimerTask = applicationContext.getBean(AuctionTimerTask.class);
        auctionTimerTask.setAuctionId(auction.getId());
        Timer timer = new Timer();
        timer.schedule(auctionTimerTask, Duration.between(auction.getStartTime(),auction.getEndTime()).toMillis());

        return ResponseEntity.ok(auction);
    }
}
