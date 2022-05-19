package ElectricalCalculatesForCables.jdbc;

import ElectricalCalculatesForCables.DBRunner;
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
            DB_Settings.load(DBRunner.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SneakyThrows(SQLException.class)
    public void dropCableIfExists() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement dropIfEsistsTableCables = connection.prepareStatement("drop table if exists cable");
//            final PreparedStatement dropIfEsistsTableCables = connection.prepareStatement("drop table if exists cables");
            dropIfEsistsTableCables.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public void createCable() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement dropIfEsistsTableCables = connection.prepareStatement(
                    """
                        create table cable
                        (
                        id int auto_increment,
                        material varchar(10) not null,
                        cross double,
                        type varchar(10),
                        power int,
                        primary key (id)
                        );
                        """
            );
            dropIfEsistsTableCables.execute();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_Settings.getProperty("jdbc.url"),
                DB_Settings.getProperty("dbCable.login"),
                DB_Settings.getProperty("dbCable.password"));
    }
}
