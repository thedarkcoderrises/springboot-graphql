package com.tdcr.graphql.dao.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode
@Document(collection = "sequence")
public class SequenceId {
    @Id
    private String id;

    private long seq;
}
