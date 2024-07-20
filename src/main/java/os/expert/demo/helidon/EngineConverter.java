package os.expert.demo.helidon;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.nosql.AttributeConverter;

import java.util.logging.Logger;

public class EngineConverter implements AttributeConverter<Engine, String> {

    private static final Logger LOGGER = Logger.getLogger(EngineConverter.class.getName());

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public String convertToDatabaseColumn(Engine attribute) {
        if (attribute == null) {
            return null;
        }
        LOGGER.info("Converting Engine to JSON: " + attribute);
        return JSONB.toJson(attribute);
    }

    @Override
    public Engine convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return JSONB.fromJson(dbData, Engine.class);
    }
}
