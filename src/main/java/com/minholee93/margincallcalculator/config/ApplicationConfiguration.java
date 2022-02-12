package com.minholee93.margincallcalculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Bean
    @Qualifier("customObjectMapper")
    public ObjectMapper customObjectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {

        DateTimeFormatter defaultDateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return jackson2ObjectMapperBuilder
                .serializers(new LocalDateTimeSerializer(defaultDateTimeFormatter))
                .deserializers(new LocalDateTimeDeserializer(defaultDateTimeFormatter))
                .build();
    }
}
