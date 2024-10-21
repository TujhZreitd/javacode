package coretask.localdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

@JsonSerialize(using = DrawLocalDateSerializer.class)
public class DrawLocalDate {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd'##'HH:mm:ss:SSS",
            locale = "ru"
    )
    private final LocalDateTime localDateTime;

    public DrawLocalDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return localDateTime.toString();
    }
}

class Main {
    public static void main(String[] args) throws JsonProcessingException {
        DrawLocalDate drawLocalDate = new DrawLocalDate(LocalDateTime.now());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String formatterData = objectMapper.writeValueAsString(drawLocalDate);
        System.out.println(formatterData);
    }
}
