package com.hsh.aop.mapper;

import com.hsh.aop.entity.RequestLogPO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogMapper {
    Integer insertRequestLog(RequestLogPO requestLogPO);
}
