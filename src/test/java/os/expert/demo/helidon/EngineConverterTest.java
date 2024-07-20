package os.expert.demo.helidon;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EngineConverterTest {

    private EngineConverter converter;
    private Jsonb jsonb;

    @BeforeEach
    public void setUp() {
        converter = new EngineConverter();
        jsonb = JsonbBuilder.create();
    }

    @Test
    void shouldConvertToDatabaseColumn_GasEngine() {
        GasEngine gasEngine = new GasEngine(300);
        String expectedJson = jsonb.toJson(gasEngine);

        String actualJson = converter.convertToDatabaseColumn(gasEngine);

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    void shouldConvertToDatabaseColumn_ElectricEngine() {
        ElectricEngine electricEngine = new ElectricEngine(200);
        String expectedJson = jsonb.toJson(electricEngine);

        String actualJson = converter.convertToDatabaseColumn(electricEngine);

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    void shouldConvertToDatabaseColumn_Null() {
        String actualJson = converter.convertToDatabaseColumn(null);

        assertThat(actualJson).isNull();
    }

    @Test
    void shouldConvertToEntityAttribute_GasEngine() {
        GasEngine expectedEngine = new GasEngine(300);
        String json = jsonb.toJson(expectedEngine);

        Engine actualEngine = converter.convertToEntityAttribute(json);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualEngine).isInstanceOf(GasEngine.class);
        softly.assertThat(actualEngine.getHorsepower()).isEqualTo(expectedEngine.getHorsepower());
        softly.assertThat(actualEngine.getFuelType()).isEqualTo(expectedEngine.getFuelType());
        softly.assertAll();
    }

    @Test
    void shouldConvertToEntityAttribute_ElectricEngine() {
        ElectricEngine expectedEngine = new ElectricEngine(200);
        String json = jsonb.toJson(expectedEngine);

        Engine actualEngine = converter.convertToEntityAttribute(json);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualEngine).isInstanceOf(ElectricEngine.class);
        softly.assertThat(actualEngine.getHorsepower()).isEqualTo(expectedEngine.getHorsepower());
        softly.assertThat(actualEngine.getFuelType()).isEqualTo(expectedEngine.getFuelType());
        softly.assertAll();
    }

    @Test
    void shouldConvertToEntityAttribute_Null() {
        Engine actualEngine = converter.convertToEntityAttribute(null);
        assertThat(actualEngine).isNull();
    }

    @Test
    void shouldConvertToEntityAttribute_EmptyString() {
        Engine actualEngine = converter.convertToEntityAttribute("");
        assertThat(actualEngine).isNull();
    }

}