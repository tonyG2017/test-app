package org.tony.kafka.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@Slf4j
@JsonDeserialize(builder = SimpleKafkaEvent.SimpleKafkaEventBuilder.class)
public class SimpleKafkaEvent implements Serializable {
    private String msg;
    private String date;
    @JsonPOJOBuilder(withPrefix = "")
    public static class SimpleKafkaEventBuilder{};
    public  String toJsonString(){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonString = objectMapper.writeValueAsString(this);
            log.info("Response {}", jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            log.info("Exception formatting JSON response. Ouputting the raw Java object instead.", e);
            log.info("Response {}", this);
            return "";
        }
    }
    public static SimpleKafkaEvent jsonString2SimpleKafkaEvent(String jsonStr){
        SimpleKafkaEvent simpleKafkaEvent =null;
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            simpleKafkaEvent = objectMapper.readValue(jsonStr, SimpleKafkaEvent.class);
            log.info("simpleKafkaEvent=> {}", simpleKafkaEvent);
        } catch (Exception  e) {
            log.info("{}",e);
        }
        return simpleKafkaEvent;
    }

}
