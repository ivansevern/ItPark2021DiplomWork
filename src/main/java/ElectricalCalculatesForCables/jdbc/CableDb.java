package ElectricalCalculatesForCables.jdbc;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@UtilityClass
public class CableDb {

//    private final Properties DB_Settings = new Properties();
//
//    static {
//        try {
//            DB_Settings.load(DBRunner.class.getResourceAsStream("/db.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @SneakyThrows(SQLException.class)
//    public void dropBooksAndAuthorsIfExists() {
//        try (final Connection connection = getConenection();
//             final PreparedStatement dropIfExistsTableCableDb = connection.prepareStatement("drop table if exists cables");
//             final PreparedStatement dropIfExistsTableCableCu = connection.prepareStatement("drop table if exists cables");
//    }
}
