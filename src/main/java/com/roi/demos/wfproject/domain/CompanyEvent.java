package com.roi.demos.wfproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("corpevents")
public class CompanyEvent {
    @Id
    private UUID id;
    private String eventTitle;
    private String location;
    private int duration;
    private List<Person> attendees;

}
