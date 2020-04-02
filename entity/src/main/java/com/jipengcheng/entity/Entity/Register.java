package com.jipengcheng.entity.Entity;

import lombok.Data;

@Data
public class Register {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String salt;
    private Integer sex;
    private String emil;
    private String cardId;
    private Integer type;
    private Integer userType;
    private String address;
    private String verificationCode;
}
