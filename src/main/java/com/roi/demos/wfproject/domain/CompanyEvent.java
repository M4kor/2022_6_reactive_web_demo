package com.roi.demos.wfproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEvent {
    private UUID id;
    private String eventTitle;
    private String location;
    private int duration;
    private List<Person> attendees;

}
