package org.example.antraproject1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<String> getTeacherStudentData() {
        // Call teacher/student service asynchronously
        CompletableFuture<String> teacherStudentData = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject("http://teacher-student-service/teacher-student", String.class);
        });

        // Call details service asynchronously
        CompletableFuture<String> detailsData = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject("http://details-service/details/port", String.class);
        });

        // Merge the results and combine them into a general response
        return teacherStudentData.thenCombine(detailsData, (teacherStudentResponse, detailsResponse) -> {
            // Merge the responses here, you can customize the response format
            String mergedResponse = "general response(code=200, timestamp=" + System.currentTimeMillis() +
                    ", data=" + teacherStudentResponse + ", " + detailsResponse + ")";
            return mergedResponse;
        });
    }
}
