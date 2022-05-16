package jdbc;

import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class CableDb {

    private final Properties DB_Settings = new Properties();

    static {
        try {
            DB_Settings.load(DBRunner.class.getResourceAsStream("/db.properties"));

        }
    }
}
