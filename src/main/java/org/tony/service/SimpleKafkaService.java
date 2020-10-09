package org.tony.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.tony.kafka.config.SimpleKafkaEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class SimpleKafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public void sendMessage(String topic,String msg){

        log.info("The SimpleKafkaService producer is producing message:{}", msg);
        SimpleKafkaEvent simpleKafkaEvent= SimpleKafkaEvent.builder()
                .msg(msg)
                .date(sdf.format(new Date()))
                .build();


        kafkaTemplate.send(topic, simpleKafkaEvent.toJsonString()).addCallback(
                new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Failed to send message：{}",ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("Successfully sent message：{} - {} - {}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                }
        );
    }
}
