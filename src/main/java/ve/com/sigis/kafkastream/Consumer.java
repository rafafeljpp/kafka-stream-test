package ve.com.sigis.kafkastream;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;

import java.util.*;

public class Consumer {
    private static final Logger log = Logger.getLogger(Consumer.class);


    public static void main(String[] args) throws Exception {

        String topic = "parser.nexo.receiver";
        List<String> topicList = new ArrayList<String>();
        topicList.add(topic);
        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", "172.22.20.109:9091, 172.22.20.109:9092");
        consumerProperties.put("group.id", "Demo_Group");
        consumerProperties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        consumerProperties.put("enable.auto.commit", "true");
        consumerProperties.put("auto.commit.interval.ms", "1000");
        consumerProperties.put("session.timeout.ms", "30000");

        KafkaConsumer<String, String> demoKafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);

        demoKafkaConsumer.subscribe(topicList);

        log.info("Subscribed to topic " + topic);
        int i = 0;
        try {
            while (true) {
                ConsumerRecords<String, String> records = demoKafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> record : records) {
                    log.info("offset = " + record.offset() + "key =" + record.key() + "value =" + record.value());
                    System.out.println(record.value().toString());
                }
                //TODO : Do processing for data here 
                demoKafkaConsumer.commitAsync(new OffsetCommitCallback() {
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        System.out.println(map.toString());

                    }
                });

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                demoKafkaConsumer.commitSync();

            } finally {
                demoKafkaConsumer.close();
            }
        }
    }
}