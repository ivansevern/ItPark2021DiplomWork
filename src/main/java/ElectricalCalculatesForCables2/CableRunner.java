package ElectricalCalculatesForCables2;

import ElectricalCalculatesForCables2.csv.CsvMapper2;
import ElectricalCalculatesForCables2.dto.CableDto;
import ElectricalCalculatesForCables2.dto.MaterialDto;
import ElectricalCalculatesForCables2.jdbc.CableDb;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class CableRunner {
    public static void main(String[] args) {
        System.out.println("Сперва удалим таблицы Cables и их Materials, если таковые имеются");
        CableDb.dropCablesAndMaterialsIfExists();
        System.out.println("Создадим таблицы Cables и их Materials");
        CableDb.createCablesAndMaterials();
        System.out.println("Наполним таблицы данными из ресурсного файла");
        CsvMapper2.getCableInfo().stream().map(materialAndCable -> Pair.of(
                new MaterialDto(materialAndCable.getMaterial()),
                new CableDto(materialAndCable.getNumberOfWire(),
                        materialAndCable.getCrossSection(),
                        materialAndCable.getPower()))).forEach(pair ->
                {
                    final Optional<Integer> material = CableDb.insertMaterial(pair.getLeft());
                    CableDb.insertCable(material.orElse(null), pair.getRight());
                }
        );
        System.out.println("Выполнение программы завершено");

    }
}
