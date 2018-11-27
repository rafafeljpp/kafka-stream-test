
package ve.com.sigis.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;


public class ProducerText {

    public static void main(final String[] args) {


        // Propiedades del productor
        Properties p = new Configurator().Producer();

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(p);



        System.out.print("Iniciando la publicación");
        Random rand = new Random();
        float  console = rand.nextInt(10);



        for (int i = 0; i < 500; i++) {

            String jsonString = "{" +
                    "    \"origin\": \"186.167.248.89:10369\",\n" +
                    "    \"inputDate\": \"2018-05-18 10:32:52\",\n" +
                    "    \"consoleCode\": \""+i+"\",\n" +
                    "    \"deviceType\": \"Teltonika\",\n" +
                    "    \"dataByte\": [3, 158, 202, 254, 1, 75, 0, 15, 51, 53, 50, 48, 57, 52, 48, 56, 55, 51, 49, 50, 52, 56, 49, 8, 7, 0, 0, 1, 99, 115, 131, 175, 64, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 251, 30, 10, 239, 1, 240, 1, 80, 1, 21, 5, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 251, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 87, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 0, 0, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 38, 18, 0, 76, 19, 3, 243, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 115, 126, 33, 96, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 251, 30, 10, 239, 1, 240, 1, 80, 1, 21, 5, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 251, 0, 14, 181, 0, 0, 182, 0, 0, 66, 48, 90, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 0, 0, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 39, 18, 0, 77, 19, 3, 243, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 115, 121, 43, 216, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 251, 30, 10, 239, 1, 240, 1, 80, 1, 21, 4, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 251, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 88, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 0, 0, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 37, 18, 0, 78, 19, 3, 242, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 111, 181, 30, 248, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 9, 239, 1, 240, 1, 80, 1, 21, 5, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 83, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 15, 156, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 59, 18, 0, 31, 19, 3, 243, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 111, 181, 148, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 9, 239, 1, 240, 1, 80, 1, 21, 4, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 88, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 15, 156, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 61, 18, 0, 31, 19, 3, 243, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 111, 182, 9, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 9, 239, 1, 240, 1, 80, 1, 21, 4, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 90, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 15, 156, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 62, 18, 0, 32, 19, 3, 244, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 0, 0, 1, 99, 111, 182, 126, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 9, 239, 1, 240, 1, 80, 1, 21, 4, 200, 0, 69, 2, 1, 1, 179, 0, 10, 1, 14, 181, 0, 0, 182, 0, 0, 66, 48, 88, 24, 0, 0, 205, 58, 144, 206, 11, 196, 67, 15, 156, 68, 0, 0, 9, 0, 86, 13, 0, 0, 17, 0, 61, 18, 0, 34, 19, 3, 242, 15, 0, 0, 4, 241, 0, 1, 30, 186, 199, 0, 0, 0, 0, 16, 0, 0, 0, 23, 12, 0, 0, 83, 226, 2, 11, 0, 0, 0, 0, 53, 100, 219, 38, 14, 0, 0, 0, 1, 166, 5, 24, 229, 7],\n" +
                    "    \"aplicationName\": \"Socket Server\",\n" +
                    "    \"consoleName\": \"Teltonika\"\n" +
                    "}";
            System.out.print(i+"\n");
            ProducerRecord<String, String> record =  new ProducerRecord<String, String>("sigis-iot", "78185545877|2018-07-05 10:07:25.25", jsonString);
            producer.send(record);

        }

        System.out.print("Finalizado...");

        producer.close();
    }
}

