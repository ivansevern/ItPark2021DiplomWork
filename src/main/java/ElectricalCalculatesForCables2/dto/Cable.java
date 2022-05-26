package ElectricalCalculatesForCables2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cable {
    private Integer id;
    private String name;
    private String numberOfWire;
    private Double crossSection;
    private String material;
    private Integer power;
    private String cableName;
}