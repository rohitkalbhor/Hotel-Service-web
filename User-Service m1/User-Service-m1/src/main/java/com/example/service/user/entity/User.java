package com.example.service.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "microuser")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL",length = 40)
    private String email;
    @Column(name = "ABOUT",length = 40)
    private  String about;

    @Transient
   private List<Rating> ratingList=new ArrayList<>();

}
