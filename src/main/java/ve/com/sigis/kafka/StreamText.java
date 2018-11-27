package ve.com.sigis.kafka;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class StreamText {


    public static void main(final String[] args) {
        Properties KafkaStreamProperties = new Configurator().StreamerText();

        final StreamsBuilder builder = new StreamsBuilder();

        Consumed.with(
                Serdes.String(),
                Serdes.String()
        );

        KStream<String, String> sb = builder.stream(
        "sigis-iot",
        Consumed.with(
                Serdes.String(),
                Serdes.String()
        ));




        KStream<String, String> x = sb.flatMap(
                (key, value) -> {
                    List<KeyValue<String, String>> result = new LinkedList<>();
                    try{
                        NexoMessage data = new Gson().fromJson(value, NexoMessage.class);
                        System.out.print(data.consoleCode + "-> " + data.deviceType +"\n");

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
