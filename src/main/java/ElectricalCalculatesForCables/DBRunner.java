package ElectricalCalculatesForCables;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBRunner {
    private static Properties DB_SETTINGS;

    static {
        try {
            DB_SETTINGS = new Properties();
            DB_SETTINGS.load(DBRunner.class.getResourceAsStream("/dbCable.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        try (final Connection connection = DriverManager.getConnection(
                DB_SETTINGS.getProperty("ElectricalCalculatesForCables.jdbc.url"),
                DB_SETTINGS.getProperty("dbCable.login"),
                DB_SETTINGS.getProperty("dbCable.password"));
             final PreparedStatement statement = connection.prepareStatement(
                     """
                            select m.id, m.name, m.typeId, t.id, t.name, t.cableId
                            from material m
                            left join type t
                            on t.typeId = t.id
                            where m.typeId = ?
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
