package ElectricalCalculatesForCables;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvMapper {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CsvMapper.class.getResourceAsStream("/CableAlMany.csv")))) {
            CsvToBean<CableAlMany> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CableAlMany.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            final List<CableAlMany> cable = csvToBean.stream().collect(Collectors.toList());
            System.out.println(cable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

