package me.practice.restapiwithspring.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        // field error
        errors.getFieldErrors().stream().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("field", e.getField());
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());

                Object rejectValue = e.getRejectedValue();
                if (rejectValue != null) {
                    gen.writeStringField("rejectValue", rejectValue.toString());
                }

                gen.writeEndObject();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        errors.getGlobalErrors().stream().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("Code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeEndObject();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        gen.writeEndArray();
    }
}
