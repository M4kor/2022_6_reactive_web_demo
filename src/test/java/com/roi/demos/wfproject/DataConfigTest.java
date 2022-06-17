package com.roi.demos.wfproject;

import com.roi.demos.wfproject.persistence.FauxCompanyEventsImpl;
import com.roi.demos.wfproject.persistence.FauxDataSvc;
import com.roi.demos.wfproject.persistence.FauxPeoplesSvcImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import reactor.test.subscriber.TestSubscriber;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataConfigTest {

    @Autowired
    private FauxDataSvc dataSvc;
    @Autowired
    private FauxCompanyEventsImpl companySvc;
    @Autowired
    private FauxPeoplesSvcImpl peopleSvc;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void verifyDataInitialization(){
        StepVerifier.create(dataSvc.findAll())
                .expectNextCount(2000)
                .verifyComplete();
    }

    @Test
    void initializeFauxRepository() {
    }

    @Test
    void initialFauxDataSvc() {
    }

    @Test
    void initializeFunctionalDataEntities() {
        AtomicInteger eventCount = new AtomicInteger();
        companySvc.getCurrentEvents().subscribe(c->{eventCount.getAndIncrement();});
        Assertions.assertEquals(1000, eventCount.get());

        AtomicInteger peopleCount = new AtomicInteger();
        peopleSvc.findAll().subscribe(p->peopleCount.getAndIncrement());
        Assertions.assertEquals(3000,peopleCount.get());
    }
}