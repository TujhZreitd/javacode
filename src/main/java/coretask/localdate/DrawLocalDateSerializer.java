package coretask.localdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DrawLocalDateSerializer extends JsonSerializer<DrawLocalDate> {

    @Override
    public void serialize(DrawLocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd'##'HH:mm:ss:SSS", Locale.forLanguageTag("ru"));
        gen.writeString(value.getLocalDateTime().format(formatter));
    }
}
