package com.example.service.user.service;

import com.example.service.user.entity.Hotel;
import com.example.service.user.entity.Rating;
import com.example.service.user.entity.User;
import com.example.service.user.exception.ResourceNotFoundException;
import com.example.service.user.external.service.HotelService;
import com.example.service.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImplUserService implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger=LoggerFactory.getLogger(ImplUserService.class);
    @Override
    public User saveUser(User user) {
       String randomUserId= UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //get single user
    @Override
    public User getUser(String userId) {
        //get user from user repository
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User With Given Id Not Found On Server"+userId));
        //fetch rating of the above user
        //http://localhost:8083/ratings/users/bcf48da1-9300-4e5b-829d-ebb267d4d5ae
      Rating[] ratingOfUser=  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);
      List<Rating> ratings=  Arrays.stream(ratingOfUser).toList();


      List<Rating>ratingList=  ratings.stream().map(rating ->{
            //api call to hotel service to get the hotel
         // localhost:8082/hotels/a6899a74-a1e2-4157-aef0-258f279f215a
       // ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

        Hotel hotel=hotelService.getHotel(rating.getHotelId());
       // logger.info("response status code: {}",forEntity.getStatusCode());
            //set the hotel to rating
          rating.setHotel(hotel);
            //return rating
            return rating;
        }).collect(Collectors.toList());
      user.setRatingList(ratingList);
        return user;
    }
}
