package org.tony.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.tony.kafka.config.SimpleKafkaEvent;

@Slf4j
@Component
public class SimpleKafkaConsumer {
    @KafkaListener(topics = "testapp-events")
    public void simplyConsume(ConsumerRecord<String, Object> record ) {
        log.info("SimpleKafkaConsumer is consuming,topic= {} ,content = {}",record.topic(),record.value());
        SimpleKafkaEvent simpleKafkaEvent =SimpleKafkaEvent.jsonString2SimpleKafkaEvent(record.value().toString());
        System.out.println("Re-constructed SimpleKafkaEvent:"+simpleKafkaEvent);
    }
}
