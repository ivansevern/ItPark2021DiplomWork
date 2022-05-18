package ElectricalCalculatesForCables;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Cable {
    @CsvBindByName(column = "id")
    private Integer id;
    @CsvBindByName(column = "material")
    private String material;
    @CsvBindByName(column = "numberOfWire")
    private String numberOfWire;
    @CsvBindByName(column = "crossSection")
    private Double crossSection;
    @CsvBindByName(column = "power")
    private Integer power;
}
