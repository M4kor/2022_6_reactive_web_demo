package com.roi.demos.wfproject.service;

import com.roi.demos.wfproject.domain.Course;
import com.roi.demos.wfproject.persistence.CourseDataService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CourseMgmtImpl implements CourseManagementSvc{

    private final CourseDataService crsData;

    public CourseMgmtImpl(CourseDataService crsData) {
        this.crsData = crsData;
    }

    @Override
    public Flux<Course> getCurrentCourses() {
        return this.crsData.findAll();
    }

    @Override
    public Mono<Course> findCourseByTitle(String title) {
        if (title.contains("svcfaux")){
            throw new IllegalArgumentException("illegal title characters");
        }
        return crsData.findCourseByTitle(title);
    }

    @Override
    public void save(Course nueCourse) {
        this.crsData.save(nueCourse);
    }

    @Override
    public void saveAll(List<Course> nueCourses) {
        this.crsData.saveAll(nueCourses);
    }

    @Override
    public Flux<Course> findCoursesByTitleContaining(String phrase) {
        return this.crsData.findCourseByTitleContaining(phrase);
    }
}
