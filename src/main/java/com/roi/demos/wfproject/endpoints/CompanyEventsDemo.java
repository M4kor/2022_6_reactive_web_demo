package com.roi.demos.wfproject.endpoints;

import com.roi.demos.wfproject.domain.CompanyEvent;
import com.roi.demos.wfproject.repository.CompanyEventRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "r2h2")
public class CompanyEventsDemo {

    private final CompanyEventRepository corpRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<CompanyEvent>> getCurrentEvents(){
        return ResponseEntity.ok()
                .body(this.corpRepo.findAll());
    }
}
