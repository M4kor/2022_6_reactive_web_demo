package com.roi.demos.wfproject.handlers;

import com.roi.demos.wfproject.domain.Person;
import com.roi.demos.wfproject.persistence.PeopleDataSvc;
import com.roi.demos.wfproject.service.CourseManagementSvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonHandlerTest {

    WebTestClient client;
    @Autowired
    ApplicationContext ctx;
    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToApplicationContext(ctx).build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPeoples() {
        Flux<Person> personFlux =
        client.get().uri("/person")
                .exchange().expectStatus().isOk()
                .returnResult(Person.class).getResponseBody();
//        StepVerifier.create(personFlux).expectNextSequence()
    }

    @Test
    void addPerson() {
    }

    @Test
    void getPersonByEmail() {
    }

    @Test
    void getPersonByPhone() {
    }
}