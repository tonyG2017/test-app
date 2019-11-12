package org.tony.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class User implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String fullName;
    private String jobTitle;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    Use this annotation to hide the password in serialization
    private String password;
}
