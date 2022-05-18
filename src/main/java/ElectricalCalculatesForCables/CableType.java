package ElectricalCalculatesForCables;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CableType {
    @CsvBindByName(column = "id")
    private Integer id;
    @CsvBindByName(column = "name")
    private String name;
}
