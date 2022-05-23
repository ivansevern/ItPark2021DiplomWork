package ElectricalCalculatesForCables2;

import ElectricalCalculatesForCables2.dto.MaterialAndCable;
import ElectricalCalculatesForCables2.jdbc.CableDb;

import java.util.List;
import java.util.Scanner;

public class BookSearchRunner {
    private static final String EXIT_PHRASE = "выход";

    public static void main(String[] args) {
        System.out.println("Введите наименование искомого кабеля или имя материала");
        System.out.printf("или введите %s для завершения работы программы:\n", EXIT_PHRASE);
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            final String text = scanner.nextLine();
            if (EXIT_PHRASE.equalsIgnoreCase(text)){
                return;
            }
            final List<MaterialAndCable> materialAndCables = CableDb.searchByCrossSectionOrMaterialName(text);
            System.out.println("Подходящие книги с информацией об авторе:");
            System.out.println(materialAndCables);
        }
    }
}
