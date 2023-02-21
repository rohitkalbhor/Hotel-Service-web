package com.Icwd.rating.controller;

import com.Icwd.rating.entity.Rating;
import com.Icwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create ratings
    @PostMapping
    public ResponseEntity<Rating>create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }
    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRating(){
        return  ResponseEntity.ok(ratingService.getRating());
    }

    //get all
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable  String hotelId){
        return  ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
