package com.roi.demos.wfproject;

import com.roi.demos.wfproject.persistence.FauxDataSvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import reactor.test.subscriber.TestSubscriber;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataConfigTest {

    @Autowired
    private FauxDataSvc dataSvc;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void verifyDataInitialization(){
        StepVerifier.create(dataSvc.findAll())
                .expectNextCount(2000)
                .verifyComplete();
    }
}