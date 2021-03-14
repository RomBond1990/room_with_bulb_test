package com.rbondarovich.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomBean {

    private Long id;
    private String name;
    private Boolean bulb;
    private UserBean owner;
    private CountryBean country;
    private List<UserBean> visitors = new ArrayList<>();
}
