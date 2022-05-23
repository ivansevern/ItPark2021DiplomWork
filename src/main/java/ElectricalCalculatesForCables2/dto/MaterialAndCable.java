package ElectricalCalculatesForCables2.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAndCable {
    @CsvBindByName(column = "NumberOfWire")
    private String numberOfWire;
    @CsvBindByName(column = "CrossSection")
    private Double crossSection;
    @CsvBindByName(column = "Material")
    private String material;
    @CsvBindByName(column = "Power")
    private Integer power;
}