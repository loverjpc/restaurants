package com.jipengcheng.commont.vo;

import lombok.Data;

@Data
public class ResponseEntity {
    private String code;
    private boolean charge;
    private int remain;
    private String msg;
    private Result result;
}
