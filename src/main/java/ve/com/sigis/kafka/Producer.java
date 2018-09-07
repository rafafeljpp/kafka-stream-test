
package ve.com.sigis.kafka;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ve.com.sigis.avro.AvroHandler;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;



public class Producer {

    public static void main(final String[] args) {



        // Avro Schema
        Schema.Parser parser = new Schema.Parser();
        Schema schema = AvroHandler.loadSchemaFromFile("src/main/resources/nexo.avsc");
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);

        // Propiedades del productor
        Properties p = new Configurator().Producer();

        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(p);




        Random rand = new Random();

        float  console = rand.nextInt(10);



        for (int i = 0; i < 100; i++) {
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("origin", "192.168.0.1:4878" );
            avroRecord.put("inputDate", LocalDateTime.now().toString());
            avroRecord.put("deviceType", "Type A" );
            avroRecord.put("dataByte",  new int[100].toString());
            avroRecord.put("applicationName", "APP" );
            avroRecord.put("consoleCode", console);
            avroRecord.put("consoleName", "ConsoleName" );

            byte[] bytes = recordInjection.apply(avroRecord);


            //ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("sigis-iot", "78185545877|2018-07-05 10:07:25.25", bytes);
            ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("sigis-iot", "78185545877|2018-07-05 10:07:25.25", bytes);

            //System.out.print(record.toString());
            producer.send(record);

            /*
            GenericRecord r = recordInjection.invert(bytes).get();
            System.out.print(r);
            */
        }



        producer.close();
    }
}

