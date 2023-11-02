package com.example.sms.repository;

import com.example.sms.entity.ApiRequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApiRequestRepo extends JpaRepository<ApiRequestInfo,Long> {
}
