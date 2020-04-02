package com.jipengcheng.entity.Entity;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
    private String phone;
    private Integer type;
    private Integer userType;
}
