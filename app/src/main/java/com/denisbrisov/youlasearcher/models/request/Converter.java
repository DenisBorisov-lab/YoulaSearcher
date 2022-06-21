// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//
//
// Import this package:
//
//     import com.denisbrisov.youlasearcher.models.request.Converter;
//
// Then you can deserialize a JSON string with
//
//     Request data = Converter.fromJsonString(jsonString);

package com.denisbrisov.youlasearcher.models.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
    // Date-time helpers

    private static final String[] DATE_TIME_FORMATS = {
            "yyyy-MM-dd'T'HH:mm:ss.SX",
            "yyyy-MM-dd'T'HH:mm:ss.S",
            "yyyy-MM-dd'T'HH:mm:ssX",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SX",
            "yyyy-MM-dd HH:mm:ss.S",
            "yyyy-MM-dd HH:mm:ssX",
            "yyyy-MM-dd HH:mm:ss",
            "HH:mm:ss.SZ",
            "HH:mm:ss.S",
            "HH:mm:ssZ",
            "HH:mm:ss",
            "yyyy-MM-dd",
    };
    private static ObjectReader reader;
    private static ObjectWriter writer;

    public static Date parseAllDateTimeString(String str) {
        for (String format : DATE_TIME_FORMATS) {
            try {
                return new SimpleDateFormat(format).parse(str);
            } catch (Exception ex) {
                // Ignored
            }
        }
        return null;
    }

    public static String serializeDateTime(Date datetime) {
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").format(datetime);
    }
    // Serialize/deserialize helpers

    public static String serializeDate(Date datetime) {
        return new SimpleDateFormat("yyyy-MM-dd").format(datetime);
    }

    public static String serializeTime(Date datetime) {
        return new SimpleDateFormat("hh:mm:ssZ").format(datetime);
    }

    public static Request fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(Request obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseAllDateTimeString(value);
            }
        });
        module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseAllDateTimeString(value);
            }
        });
        module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseAllDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(Request.class);
        writer = mapper.writerFor(Request.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}
