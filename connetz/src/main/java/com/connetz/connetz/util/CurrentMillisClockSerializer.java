package com.connetz.connetz.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.api.core.CurrentMillisClock;

import java.io.IOException;

public class CurrentMillisClockSerializer extends JsonSerializer<CurrentMillisClock> {

    @Override
    public void serialize(CurrentMillisClock value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        long timestamp = value.millisTime();

        gen.writeNumber(timestamp);
    }
}
