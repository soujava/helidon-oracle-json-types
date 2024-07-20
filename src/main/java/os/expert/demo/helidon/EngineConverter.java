package os.expert.demo.helidon;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.nosql.AttributeConverter;


public class EngineConverter implements AttributeConverter<Engine, String> {

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public String convertToDatabaseColumn(Engine attribute) {
        if (attribute == null) {
            return null;
        }
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
