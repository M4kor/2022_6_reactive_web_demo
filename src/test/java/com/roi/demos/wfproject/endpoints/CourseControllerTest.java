package com.roi.demos.wfproject.endpoints;

import com.roi.demos.wfproject.domain.Course;
import com.roi.demos.wfproject.service.CourseManagementSvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseControllerTest {

    @Autowired
    ApplicationContext ctx;
    WebTestClient client;
    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToApplicationContext(ctx).build();
    }

    @AfterEach
    void tearDown() {
        client = null;
    }

    @Test
    void getCurrentCourses() {
    }

    @Test
    void getCurrentCoursesNDStream() {
    }

    @Test
    void getCurrentCoursesStream() {
        ParameterizedTypeReference<ServerSentEvent<Course>> rtnType
                = new ParameterizedTypeReference<ServerSentEvent<Course>>() {};

        client.get().uri("/course/txt-stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(rtnType)
                .getResponseBody()
                .subscribe(System.out::println);
    }
}