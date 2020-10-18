package org.tony.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder

@Document(indexName="user")
public class User implements Serializable {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Field(type = FieldType.Text)
    private String fullName;

    @Field(type = FieldType.Text)
    private String jobTitle;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    Use this annotation to hide the password in serialization
    @Field(type = FieldType.Text)
    private String password;

    private int salary;
    private int version;
}
