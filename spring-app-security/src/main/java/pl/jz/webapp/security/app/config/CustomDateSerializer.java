package pl.jz.webapp.security.app.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateSerializer extends StdSerializer<LocalDateTime> {

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value,
                          JsonGenerator gen,
                          SerializerProvider arg2) throws IOException {
        gen.writeString(value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
    }

}
