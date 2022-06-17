package com.roi.demos.wfproject.persistence;

import com.roi.demos.wfproject.domain.Course;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class FauxDataSvc implements CourseDataService {
    private List<Course> crsList;

    public FauxDataSvc() {
        this.crsList = new ArrayList<>();
    }

    @Override
    public Flux<Course> findAll(){
        return Flux.fromIterable(this.crsList);
    }

    @Override
    public void save(Course nueCourse){
        crsList.add(nueCourse);
    }

    @Override
    public void saveAll(List<Course> nueCourses){
        this.crsList.addAll(nueCourses);
    }

    @Override
    public Flux<Course> findCourseByTitleContaining(String phrase){
        return Flux.fromStream(
        crsList.stream().filter(course -> course.getCatalogTitle().contains(phrase)));
    }

    @Override
    public Mono<Course> findCourseByTitle(String title) {
        return Mono.justOrEmpty(
                crsList.stream().filter(c->c.getCatalogTitle().equalsIgnoreCase(title)).findFirst()
        );
    }
}
