package ElectricalCalculatesForCables.jdbc;


import ElectricalCalculatesForCables.DbRunner;
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

    private final Properties DB_Settings = new Properties();

    static {
        try {
            DB_Settings.load(DbRunner.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows(SQLException.class)
    public void dropCableIfExists() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement dropIfExistsTableCables = connection.prepareStatement("drop table if exists cable");
//            final PreparedStatement dropIfExistsTableCables = connection.prepareStatement("drop table if exists cables");
            dropIfExistsTableCables.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public void createCable() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement dropIfExistsTableCables = connection.prepareStatement(
                    """
                            create table cable
                            (                          
                                  id           int auto_increment
                                      primary key,
                                  material     varchar(10) not null,
                                  numberOfWire varchar(10) not null,
                                  crossSection double      not null,
                                  power        int         not null                          
                            );
                            """
            );
            dropIfExistsTableCables.execute();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_Settings.getProperty("jdbc.url"),
                DB_Settings.getProperty("dbCable.login"),
                DB_Settings.getProperty("dbCable.password"));
    }
}
