package com.example.sms.service;

import com.example.sms.entity.ApiRequestInfo;


public interface InterceptorService {
    public ApiRequestInfo saveEntity(ApiRequestInfo apiRequestInfo);
}
