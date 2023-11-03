package com.example.sms.service;

import com.example.sms.entity.ApiRequestInfo;
import com.example.sms.repository.ApiRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterceptorServiceImpl implements InterceptorService{

    @Autowired
    private ApiRequestRepo apiRequestRepo;
    @Override
    public ApiRequestInfo saveEntity(ApiRequestInfo apiRequestInfo) {
        return apiRequestRepo.save(apiRequestInfo);
    }
}
