package ElectricalCalculatesForCables;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBRunner {
    private static final Properties DB_SETTINGS = new Properties();;

    static {
        try {
            DB_SETTINGS.load(DBRunner.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        try (final Connection connection = DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),
                DB_SETTINGS.getProperty("dbCable.login"),
                DB_SETTINGS.getProperty("dbCable.password"));
             final PreparedStatement statement = connection.prepareStatement(
                     """
//                            select id, material, numberOfWire, crossSection, power
//                            from cable
//                            left join type t
//                            on t.typeId = t.id
//                            where m.typeId = ?
                    """)
             ) {
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("ID: %d Name: %s CableType: %s\n",
                        resultSet.getInt("typeId")
                        );
            }
            }
    }


}
