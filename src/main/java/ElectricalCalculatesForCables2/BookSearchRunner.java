package ElectricalCalculatesForCables2;

import ElectricalCalculatesForCables2.dto.MaterialAndCable;
import ElectricalCalculatesForCables2.jdbc.CableDb;

import java.util.List;
import java.util.Scanner;
import java.util.ResourceBundle;

public class BookSearchRunner {
//    private static final String EXIT_PHRASE = "выход";
    //
    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("messages");
    private static final String APP_CLOSE = RESOURCES.getString("app.close");
    private static final String EXIT_PHRASE = RESOURCES.getString("app.exit");

    public static void main(String[] args) {
        System.out.println(RESOURCES.getString("app.title"));
        // System.out.println("Введите одно из: \n - сечение искомого кабеля \n - материал жил кабеля \n - число жил кабеля");
//        System.out.printf("или введите %s для завершения работы программы:\n", EXIT_PHRASE);
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            final String text = scanner.nextLine();
            if (EXIT_PHRASE.equalsIgnoreCase(text)){
                System.out.println(RESOURCES.getString("app.close"));
                return;
            }
            final List<MaterialAndCable> materialAndCables = CableDb.searchByCrossSectionOrMaterialName(text);
            System.out.println("Подходящие кабели:");
            System.out.println(materialAndCables);
            //System.out.println(RESOURCES.getString(app.nextPhrase));
        }
    }
}
