package ElectricalCalculatesForCables;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CableAlMany {
    @CsvBindByName(column = "section")
    private Double section;
    @CsvBindByName(column = "Amp")
    private Integer amp;
}
