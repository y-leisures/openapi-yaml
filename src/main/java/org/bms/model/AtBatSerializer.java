package org.bms.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

class AtBatSerializer extends StdSerializer<Double> {
    public AtBatSerializer() {
        super(Double.class);
    }

    @Override
    public void serialize(Double aDouble, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (aDouble == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(String.format("%.2f", aDouble));
        }
    }
}
