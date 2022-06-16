package com.roi.demos.wfproject.marshalling;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.roi.demos.wfproject.domain.Author;
import com.roi.demos.wfproject.domain.Course;

import java.io.IOException;
import java.util.UUID;

public class CourseDeserializer extends StdDeserializer<Course> {

    public CourseDeserializer(){
        this(null);
    }
    public CourseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Course deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode crsNode = jsonParser.readValueAsTree();
        return Course.builder()
                .id(UUID.fromString(crsNode.get("id").asText()))
                .catalogTitle(crsNode.get("catalogTitle").asText())
                .description(crsNode.get("description").asText())
                .author(
                        Author.builder()
                                .id(UUID.fromString(crsNode.get("author").get("id").asText()))
                                .firstName(crsNode.get("author").get("firstName").asText())
                                .lastName(crsNode.get("author").get("lastName").asText())
                                .emailAddress(crsNode.get("author").get("emailAddress").asText())
                                .build()
                )
                .build();
    }
}
