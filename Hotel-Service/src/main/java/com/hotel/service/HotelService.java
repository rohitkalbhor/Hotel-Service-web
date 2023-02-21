package com.hotel.service;

import com.hotel.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    //getAll Hotel
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
