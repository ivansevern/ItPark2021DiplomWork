package ElectricalCalculatesForCables2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CableDto {
    private String numberOfWire;
    private Double crossSection;
    private Integer power;
}