package com.weather.beans;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomTimeSerializer extends StdSerializer<Date> {

    private static SimpleDateFormat formatter =
            new SimpleDateFormat("h:mma");

    public CustomTimeSerializer() {
        this(null);
    }

    public CustomTimeSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value).replace("AM","am").replace("PM","pm"));
    }
}
