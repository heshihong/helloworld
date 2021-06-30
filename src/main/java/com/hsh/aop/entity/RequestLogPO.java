package com.hsh.aop.entity;

import lombok.Data;

@Data
public class RequestLogPO {
    private Integer id;
    private String url;
    private String type;
    private String callTime;
    private Long respTime;
    private String method;
    private String exceptionMessage;
    private String remoteIP;
}