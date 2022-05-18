package ElectricalCalculatesForCables.csv;

import ElectricalCalculatesForCables.Cable;
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
public class CsvMapper {

    public List<Cable> getCableInfo() {
//public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvMapper.class.getResourceAsStream("/Cable.csv")))) {
            CsvToBean<Cable> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Cable.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

//            final List<Cable> cables = csvToBean.stream().collect(Collectors.toList());
//            System.out.println(cables);
     return csvToBean.stream().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
     return Collections.emptyList();
    }
}

