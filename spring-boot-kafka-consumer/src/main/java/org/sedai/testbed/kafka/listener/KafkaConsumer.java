package org.sedai.testbed.kafka.listener;

import org.sedai.testbed.kafka.model.StockDetails;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "KafkaExample", group = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "KafkaStockMessage", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(StockDetails sd) {
        System.out.println("Consumed JSON Message: " + sd);
    }
}
