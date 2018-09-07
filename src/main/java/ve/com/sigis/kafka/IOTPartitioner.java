package ve.com.sigis.kafka;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class IOTPartitioner implements Partitioner {


    public IOTPartitioner() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        int partition = 2;

        return partition;
    }

    @Override
    public void close() {

    }

}