package com.cars.auction.controller;

import com.cars.auction.dto.AuctionDetailDTO;
import com.cars.auction.dto.BidDetailDTO;
import com.cars.auction.dto.CurrentAuctionDTO;
import com.cars.auction.dto.CurrentAuctionResponse;
import com.cars.auction.entity.Auction;
import com.cars.auction.service.AuctionService;
import com.cars.auction.service.BidService;
import com.cars.auction.timer.AuctionTimerTask;
import com.cars.auction.validators.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.util.List;
import java.util.Timer;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final Validator<AuctionDetailDTO,Boolean> auctionValidator;
    private final Validator<BidDetailDTO,Boolean> bidDetailValidator;
    private final AuctionService auctionService;
    private final BidService bidService;

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping
    public ResponseEntity<Auction> startAuction(@RequestBody @Valid AuctionDetailDTO auctionDetailDTO){
        auctionValidator.validate(auctionDetailDTO);
        Auction auction = auctionService.startAuction(auctionDetailDTO);

        AuctionTimerTask auctionTimerTask = applicationContext.getBean(AuctionTimerTask.class);
        auctionTimerTask.setAuctionId(auction.getAuctionId());
        Timer timer = new Timer();
        timer.schedule(auctionTimerTask, Duration.between(auction.getStartTime(),auction.getEndTime()).toMillis());

        return ResponseEntity.ok(auction);
    }

    @GetMapping
    public ResponseEntity<CurrentAuctionResponse> auctions(@RequestParam(name="status") String status){
        List<CurrentAuctionDTO> currentAuctionDTOList = auctionService.findAuctions(status);
        CurrentAuctionResponse currentAuctionResponse = new CurrentAuctionResponse();
        currentAuctionResponse.setCurrentAuctionDTOList(currentAuctionDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(currentAuctionResponse);
    }

    @PostMapping("/{itemCode}/bid")
    public ResponseEntity<String> bid(@PathVariable(name="itemCode") String itemCode,@RequestBody @Valid BidDetailDTO bidDetailDTO){
        bidDetailDTO.setItemCode(itemCode);
        bidDetailValidator.validate(bidDetailDTO);
        bidService.placeBid(bidDetailDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("bid accepted");
    }


}
