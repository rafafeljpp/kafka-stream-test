package ve.com.sigis.kafka;


import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class Configurator {
    private  Properties prop;


    public Configurator(){
        prop =new Properties();
        prop.put("bootstrap.servers", "192.168.196.172:9092,192.168.196.172:9093,192.168.196.172:9094");

    }

    public Properties Producer(){
        Properties p = prop;
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("partitioner.class", "ve.com.sigis.kafka.IOTPartitioner");
        p.put("acks", "all");
        p.put("retries", 1);
        p.put("batch.size", 20000);
        p.put("linger.ms", 1);
        p.put("client.id", "parser-source");
        p.put("buffer.memory", 24568545);
        return p;

    }

    public  Properties Streamer(){
        Properties p = prop;

        p.put(StreamsConfig.APPLICATION_ID_CONFIG, "Kafka-Stream");
        p.put("partitioner.class", "ve.com.sigis.kafka.IOTPartitioner");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");


        return p;

    }
    public  Properties StreamerText(){
        Properties p = prop;

        p.put(StreamsConfig.APPLICATION_ID_CONFIG, "Kafka-Stream");
        p.put("partitioner.class", "ve.com.sigis.kafka.IOTPartitioner");
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        return p;

    }
}
