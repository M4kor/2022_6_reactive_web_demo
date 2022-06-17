package com.roi.demos.wfproject.endpoints;

import com.roi.demos.wfproject.domain.Author;
import com.roi.demos.wfproject.domain.Course;
import com.roi.demos.wfproject.service.CourseManagementSvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClientConfigurer;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
//@AutoConfigureWebTestClient(timeout = "50000")
class CourseControllerTest {

    @Autowired
    ApplicationContext ctx;
    WebTestClient client;
    CourseManagementSvc crsMockSvc = mock(CourseManagementSvc.class);

    List<Course> mockData;
    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToApplicationContext(ctx)
                .build();
        mockData  = Arrays.asList(
                Course.builder().id(UUID.randomUUID()).catalogTitle("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).catalogTitle("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).catalogTitle("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build(),
                Course.builder().id(UUID.randomUUID()).catalogTitle("Webflux rocks project reactor")
                        .description("amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim")
                        .author(
                                Author.builder().id(UUID.randomUUID()).firstName("Gabrielle").lastName("marquez")
                                        .emailAddress("gabby@outlook.com").build()
                        )
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        client = null;
    }

    @Test
    void getCurrentCourses() {
        WebTestClient wt = WebTestClient.bindToController(new CourseController(crsMockSvc))
                .configureClient().baseUrl("/course").build();
        when(crsMockSvc.getCurrentCourses()).thenReturn(Flux.fromIterable(mockData));
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
                .returnResult(Course.class)
                .getResponseBody()
                .parallel(8)
                .runOn(Schedulers.parallel())
                .subscribe(System.out::println);

    }
}