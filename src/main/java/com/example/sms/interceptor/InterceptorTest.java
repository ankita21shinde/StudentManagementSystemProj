package com.example.sms.interceptor;

import com.example.sms.entity.ApiRequestInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class InterceptorTest implements HandlerInterceptor {
    private long startTime;




    Date requestTime = new Date(); // Capture the current date and time
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = System.currentTimeMillis();
        Date requestTime = new Date(); // Capture the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Request Time: " + dateFormat.format(requestTime));
        request.setAttribute("startTime", startTime);
        return true;}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {



        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        Date responseTime = new Date(); // Capture the current date and time for response
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ApiRequestInfo apiRequestInfo = new ApiRequestInfo();

        //for error trace
        String errorStackTrace = null;
        if (ex != null) {
            // Capture the exception stack trace in a variable
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            errorStackTrace = sw.toString();
            System.out.println(" error trace : " + errorStackTrace);
        }

        //for response
        ContentCachingResponseWrapper wrapper;
        if (response instanceof ContentCachingResponseWrapper) {
            wrapper = (ContentCachingResponseWrapper) response;
        } else {
            wrapper = new ContentCachingResponseWrapper(response);
        }
        String responseContent = getResponse(wrapper);


//        ContentCachingResponseWrapper wrapper = (ContentCachingResponseWrapper) response;
//        String responseContent = getResponse(wrapper);



        System.out.println("Response Time: " + dateFormat.format(responseTime));
        System.out.println("Response Time: " + dateFormat.format(responseTime));
        System.out.println("Status Code :" + response.getStatus());
        System.out.println("Time Taken : " + timeTaken + " ms");
        System.out.println("Context path : " + request.getRequestURI());
        System.out.println("Method Used : " + request.getMethod());
//        System.out.println("Header Name : " + request.);
        System.out.println("Content Type : " + request.getContentType());
        System.out.println("Request ID : " + request.getRequestId());
        System.out.println("Host Name : " + request.getServerName());
        System.out.println("Response Content: " + responseContent);


        //for storing into database
      apiRequestInfo.setRequestTime(dateFormat.format(requestTime));
       apiRequestInfo.setResponseTime(dateFormat.format(responseTime));
        apiRequestInfo.setStatusCode(response.getStatus());
       apiRequestInfo.setTimeTaken(String.valueOf(timeTaken));
       apiRequestInfo.setRequestURI(request.getRequestURI());
        apiRequestInfo.setRequestMethod(request.getMethod());
       apiRequestInfo.setRequestHeaderName(getRequestHeaderNames(request));
     apiRequestInfo.setContentType(request.getContentType());
       apiRequestInfo.setRequestID(request.getRequestId()); // Replace with the actual request ID
        apiRequestInfo.setHostName(request.getServerName());
//        starEntity.setResponse(getResponse(new ContentCachingResponseWrapper(response)));
        apiRequestInfo.setResponse(responseContent);


        apiRequestInfo.setErrorTrace(errorStackTrace);


        starService.saveEntity(apiRequestInfo);


    }


    private String getRequestHeaderNames(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerNamesStr = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerNamesStr.append(headerName).append(", ");
        }
        return headerNamesStr.toString();
    }

    private String getResponse(ContentCachingResponseWrapper contentCachingResponseWrapper) {

        String response = IOUtils.toString(contentCachingResponseWrapper.getContentAsByteArray(), contentCachingResponseWrapper.getCharacterEncoding());
        return response;
    }






}
}
