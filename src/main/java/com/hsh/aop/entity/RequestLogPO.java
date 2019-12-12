package com.hsh.aop.entity;

import lombok.Data;

@Data
public class RequestLogPO {

    private Integer id;
    private String url;
    private String type;
    private Long respTime;
    private Integer status;
    private String callTime;
    private String method;
    private String remoteIP;

}