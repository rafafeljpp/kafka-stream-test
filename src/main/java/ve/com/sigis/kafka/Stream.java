package ve.com.sigis.kafka;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import ve.com.sigis.avro.AvroHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Stream {


    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }


    public static void main(final String[] args) {
        Properties KafkaStreamProperties = new Configurator().Streamer();

        final StreamsBuilder builder = new StreamsBuilder();

        Schema schema = AvroHandler.loadSchemaFromFile("src/main/resources/nexo.avsc");
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        Consumed.with(
                Serdes.String(),
                Serdes.ByteArray()
        );

        KStream<String,  byte[]> sb = builder.stream(
        "sigis-iot",
        Consumed.with(
                Serdes.String(),
                Serdes.ByteArray()
        ));

        KStream<String, byte[]> x = sb.flatMap(
                (key, value) -> {
                    List<KeyValue<String, byte[]>> result = new LinkedList<>();
                    try{
                        GenericRecord r = recordInjection.invert(value).get();

                        System.out.print(r.get("consoleName") + "  "+ r.get("consoleCode")+" ");

                    }catch (Exception e){
                        System.out.print(e.toString());
                    }



                    //result.add(KeyValue.pair(record.get("inputDate").toString(), record.get("consoleName").toString()));

                    return result;
                }
        );





        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, KafkaStreamProperties);

        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }

        System.exit(0);
    }

}
