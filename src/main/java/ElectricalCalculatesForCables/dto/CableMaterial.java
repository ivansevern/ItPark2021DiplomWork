package ElectricalCalculatesForCables.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class CableMaterial { //AuthorAndBook
    @CsvBindByName(column = "id")
    private Integer id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "typeId")
    private Integer typeId;
}
