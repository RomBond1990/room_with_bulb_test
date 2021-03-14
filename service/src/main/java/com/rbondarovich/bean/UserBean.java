package com.rbondarovich.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBean {

    private Long id;
    private String name;
    private CountryBean country;

}
