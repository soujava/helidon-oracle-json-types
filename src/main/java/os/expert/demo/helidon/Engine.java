package os.expert.demo.helidon;

import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;
import jakarta.json.bind.annotation.JsonbVisibility;

import java.util.Objects;

@JsonbTypeInfo(
        key = "type",
        value = {
                @JsonbSubtype(alias = "gas", type = GasEngine.class),
                @JsonbSubtype(alias = "electric", type = ElectricEngine.class)
        }
)
@JsonbVisibility(FieldAccessStrategy.class)
public abstract class Engine {

    protected int horsepower;

    public abstract String getFuelType();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return horsepower == engine.horsepower &&
                getFuelType().equals(engine.getFuelType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(horsepower, getFuelType());
    }
}