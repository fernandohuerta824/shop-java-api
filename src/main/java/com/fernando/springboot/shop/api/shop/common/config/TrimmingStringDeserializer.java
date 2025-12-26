package com.fernando.springboot.shop.api.shop.common.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TrimmingStringDeserializer extends JsonDeserializer<String> {
    
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JacksonException {
        String value = p.getValueAsString();

        if(value == null) {
            return null;
        }

        value = value.trim();
        return value.isBlank() ? null : value;
    }
}
