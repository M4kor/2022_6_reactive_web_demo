package com.roi.demos.wfproject.handlers;

import com.roi.demos.wfproject.domain.Search;
import com.roi.demos.wfproject.domain.Person;
import com.roi.demos.wfproject.persistence.PeopleDataSvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class PersonHandler {
    private final PeopleDataSvc dataSvc;

    public PersonHandler(PeopleDataSvc pSvc) {
        this.dataSvc = pSvc;
    }

    public Mono<ServerResponse> getPeoples(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.findAll(), Person.class);
    }

    public Mono<ServerResponse> addPerson(ServerRequest request){
        Mono<Person> p = request.bodyToMono(Person.class);
        return p.flatMap(np -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                        .body(dataSvc.addPerson(np),Person.class));
    }

    public Mono<ServerResponse> getPersonByEmail(ServerRequest request){
       Mono<Search> seek = request.bodyToMono(Search.class);
       return seek.flatMap(s->
           ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON)
           .body(this.dataSvc.findByEmailAddress(s.getTerm()),Person.class)
                   .switchIfEmpty(ServerResponse.notFound().build())
       );
    }

    public Mono<ServerResponse> getPersonByPhone(ServerRequest request){
        Mono<Search> seek = request.bodyToMono(Search.class);
        return seek.flatMap(s->
                ServerResponse.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON)
                        .body(this.dataSvc.findByPhone(s.getTerm()),Person.class)
                        .switchIfEmpty(ServerResponse.notFound().build())
        );
    }
}
