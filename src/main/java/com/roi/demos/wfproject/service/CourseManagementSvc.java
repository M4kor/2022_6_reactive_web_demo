package com.roi.demos.wfproject.service;

import com.roi.demos.wfproject.domain.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseManagementSvc {
    Flux<Course> getCurrentCourses();
    Mono<Course> findCourseByTitle(String title);
    void save(Course nueCourse);

    void saveAll(List<Course> nueCourses);

    Flux<Course> findCoursesByTitleContaining(String phrase);
}
