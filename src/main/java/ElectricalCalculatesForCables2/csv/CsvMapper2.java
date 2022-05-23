package ElectricalCalculatesForCables2.csv;

import ElectricalCalculatesForCables2.dto.MaterialAndCable;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CsvMapper2 {
        //1
/*    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvMapper2.class.getResourceAsStream("/cables.csv")))) {
            CsvToBean<CablesAndMaterials> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CablesAndMaterials.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            final List<CablesAndMaterials> cables = csvToBean.stream().collect(Collectors.toList());
            System.out.println(cables);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    //2
    public List<MaterialAndCable> getCableInfo() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvMapper2.class.getResourceAsStream("/cables.csv")))) {
            CsvToBean<MaterialAndCable> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(MaterialAndCable.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.stream().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
