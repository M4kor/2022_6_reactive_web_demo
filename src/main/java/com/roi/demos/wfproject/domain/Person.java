package com.roi.demos.wfproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("person")
public class Person {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
}
