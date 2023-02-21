package com.example.service.user.entity;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {
    private String id;
    private String name;
    private  String location;
    private String about;
}
