package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="audit_api")
@Getter
@Setter
@ToString

public class ApiRequestInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String requestTime;
    private String responseTime;
    private int StatusCode;
    private String timeTaken;
    private String requestURI;
    private String requestMethod;
    private String requestHeaderName;
    private String contentType;
    private String requestID;
    private String hostName;
    private String response;
    private String errorTrace;

}
