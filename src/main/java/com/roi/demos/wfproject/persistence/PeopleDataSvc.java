package com.roi.demos.wfproject.persistence;

import com.roi.demos.wfproject.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PeopleDataSvc {
    Flux<Person> findAll();
    Mono<Person> findByEmailAddress(String email);
    Mono<Person> findByPhone(String phone);
    void addPerson(Person peep);
    void addGroup(List<Person> people);

}
