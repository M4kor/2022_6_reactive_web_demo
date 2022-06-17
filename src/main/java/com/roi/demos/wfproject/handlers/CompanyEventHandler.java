package com.roi.demos.wfproject.handlers;

import com.roi.demos.wfproject.domain.CompanyEvent;
import com.roi.demos.wfproject.persistence.CorpEventsDataSvc;
import com.roi.demos.wfproject.persistence.FauxCompanyEventsImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class CompanyEventHandler {

    private final CorpEventsDataSvc dataSvc;

    public CompanyEventHandler(CorpEventsDataSvc dataSvc) {
        this.dataSvc = dataSvc;
    }

    public Mono<ServerResponse> getCurrentEvents(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(dataSvc.getCurrentEvents(), CompanyEvent.class);
    }

    public Mono<ServerResponse> getEventsByLocation(ServerRequest request){
        return ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.getEventsByLocation(
                request.pathVariable("locale")),
                CompanyEvent.class);
    }

    public Mono<ServerResponse> getEventsByDays(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.dataSvc.getEventsByDuration(
                Integer.valueOf(request.pathVariable("days")))
                ,CompanyEvent.class);
    }

    public Mono<ServerResponse> createNewEvent(ServerRequest request){
        return ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                .body(request.bodyToMono(CompanyEvent.class)
                .flatMap(dataSvc::addEvent),CompanyEvent.class);
    }
    public Mono<ServerResponse> createEvents(ServerRequest request){
        ArrayList<CompanyEvent> rtn = new ArrayList<CompanyEvent>();
        request.bodyToFlux(CompanyEvent.class)
        .subscribe(e->{this.dataSvc.addEvent(e); rtn.add(e);});

        return ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
        .body(Flux.fromIterable(rtn),CompanyEvent.class);
    }
}
