package com.rbondarovich.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBean {

    private Long id;
    private String name;
    private Boolean bulb;
    private String country;

}
