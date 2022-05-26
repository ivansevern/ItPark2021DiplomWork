package ElectricalCalculatesForCables2;

import ElectricalCalculatesForCables2.dto.Cable;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class DbRunner {
    private static final Properties DB_SETTINGS = new Properties();

    static {
        try {
            DB_SETTINGS.load(DbRunner.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println(getCableById(1));
    }

    private static List<Cable> getCableById(Integer id) throws SQLException {
        try (final Connection connection = DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("db.login"),
                DB_SETTINGS.getProperty("db.password"));
             final Statement st = connection.createStatement();
             final PreparedStatement statement = connection.prepareStatement(
                     """
                             select c.cab_id, c.cab_name, c.numberOfWire, c.crossSection, c.material,
                             c.power, c1.name cable_name 
                             from cable c 
                             left join cable c1 
                             on c.cable_id = c1.id 
                             where c.cab_id = ?
                             """) // -1 or (d)
        ) {
            connection.setAutoCommit(false);
            Savepoint beforeCreate = connection.setSavepoint("beforeCreate");

            st.execute("insert into cable (id, name) values (156, 'New')");

            Savepoint beforeUpdate = connection.setSavepoint("beforeUpdate");

            st.execute("update cable set name = 'New2' where id = 156");

            statement.setInt(1, id);
            List<Cable> cables = new ArrayList<>();
            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cable cable = new Cable(resultSet.getInt("cab_id"), resultSet.getString("cab_name"),
                            resultSet.getString("numberOfWire"), resultSet.getDouble("crossSection"),
                            resultSet.getString("material"), resultSet.getInt("power"),
                            resultSet.getString("cable_name"));
                    cables.add(cable);
                    if (new Random().nextBoolean()) {
                        connection.rollback(beforeUpdate);
                    }
                }
            }
            connection.commit();
            return cables;
        }
    }
}
