package com.roi.demos.wfproject.config;

import com.roi.demos.wfproject.handlers.CompanyEventHandler;
import com.roi.demos.wfproject.handlers.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunctionalRouteConfig {

    @Bean
    public RouterFunction<ServerResponse> buildFunctionalRoutes(CompanyEventHandler corpEvents,
                                                                PersonHandler people){
        return RouterFunctions.route()
                .path("events", e -> e.nest(accept(MediaType.APPLICATION_JSON),
                 p -> p.GET("{days:\\d}", corpEvents::getEventsByDays)
                 .GET("{locale}", corpEvents::getEventsByLocation)
                .GET("",corpEvents::getCurrentEvents)
                .POST("",corpEvents::createNewEvent)
                .POST("group",corpEvents::createEvents)))
                .path("person", n -> n.nest( accept(MediaType.APPLICATION_JSON).and(contentType(MediaType.APPLICATION_JSON)),
                p->p.GET("",people::getPeoples)
                .POST("",people::addPerson)
                .POST("email",people::getPersonByEmail)
                .POST("phone",people::getPersonByPhone)))
                .build();
    }
}
