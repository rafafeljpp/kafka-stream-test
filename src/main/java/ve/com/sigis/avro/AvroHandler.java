package ve.com.sigis.avro;

import org.apache.avro.Schema;

import java.io.File;

public class AvroHandler {
    public  AvroHandler(){

    }

    public static  Schema loadSchemaFromFile(String pathName) {
        Schema  schema =null;
        try {
            schema = new Schema.Parser().parse(new File(pathName));

        } catch (Exception e) {
            System.out.print("-> Error:" + e.toString());

        }
        return schema;
    }
}
