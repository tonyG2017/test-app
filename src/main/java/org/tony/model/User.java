package org.tony.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String fullName;
    private String jobTitle;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    Use this annotation to hide the password in serialization
    private String password;
}