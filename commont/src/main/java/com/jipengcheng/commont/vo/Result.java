package com.jipengcheng.commont.vo;

import lombok.Data;

@Data
public class Result {
    private String ReturnStatus;
    private String Message;
    private String RemainPoint;
    private String TaskID;
    private int SuccessCounts;
}
