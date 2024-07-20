package os.expert.demo.helidon;


import jakarta.json.bind.annotation.JsonbVisibility;

@JsonbVisibility(FieldAccessStrategy.class)
public class ElectricEngine extends Engine {

    @Override
    public String getFuelType() {
        return "Electric";
    }

    @Override
    public String toString() {
        return "ElectricEngine{" +
                "horsepower=" + horsepower +
                '}';
    }
}