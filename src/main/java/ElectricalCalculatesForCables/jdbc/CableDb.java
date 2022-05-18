package ElectricalCalculatesForCables.jdbc;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
//    @SneakyThrows(SQLException.class)
//    public void dropIfExists() {
//        try (final Connection connection = getConnection()) {
//            final PreparedStatement dropIfEsistsTableCables = connection.prepareStatement("drop  if exists  table cables");
////            final PreparedStatement dropIfEsistsTableCables = connection.prepareStatement("drop  if exists  table cables");
//            dropIfEsistsTableCables.execute();
//        }
//    }
//
//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                DB_Settings.getProperty("jdbc.url"),
//                DB_Settings.getProperty("dbCable.login"),
//                DB_Settings.getProperty("dbCable.password"));
//    }
}
