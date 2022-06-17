package com.roi.demos.wfproject.endpoints;

import com.roi.demos.wfproject.domain.Course;
import com.roi.demos.wfproject.domain.RequestError;
import com.roi.demos.wfproject.service.CourseManagementSvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(path = "course")
public class CourseController {

    private final CourseManagementSvc crsCatalog;

    public CourseController(CourseManagementSvc crsCatalog) {
        this.crsCatalog = crsCatalog;
    }

    @GetMapping(path = "current", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Course> getCurrentCourses(){
        return this.crsCatalog.getCurrentCourses();
    }

    @GetMapping(path="nd-stream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Course> getCurrentCoursesNDStream(){
        return this.crsCatalog.getCurrentCourses();
    }

    @GetMapping(path="txt-stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Course> getCurrentCoursesStream(){
//        return this.crsCatalog.getCurrentCourses()
//                .map(c -> ServerSentEvent.builder()
//                .id(UUID.randomUUID().toString())
//                .event(c.getCatalogTitle())
//                .data(c)
//                .build());
        // OR
        return this.crsCatalog.getCurrentCourses()
                .delayElements(Duration.ofMillis(5));
    }

    @GetMapping(path = "{crsTitle:[\\w-\\s]+}")
    public Mono<Course> findCourseByTitle(@PathVariable("crsTitle") String title){
        if (title.contains("ctlfaux")){
            throw new IllegalArgumentException("Generating exception message for demo");
        }
        return this.crsCatalog.findCourseByTitle(title);
    }
    @GetMapping(path = "topic/{phrase:[\\w-\\s]+}")
    public Flux<Course> findCoursesByTopic(@PathVariable("phrase") String title){
        return this.crsCatalog.findCoursesByTitleContaining(title);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity submitNewCourse(@RequestBody Course nueCourse){

        if (nueCourse.getCatalogTitle().isEmpty())
            return ResponseEntity.badRequest()
                    .body(new RequestError(HttpStatus.NOT_ACCEPTABLE.value(),
                            "Course title must present for new course submissions"));
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(nueCourse);
    }

}
