package ElectricalCalculatesForCables2.jdbc;

import ElectricalCalculatesForCables.DbRunner;
import ElectricalCalculatesForCables2.dto.MaterialAndCable;
import ElectricalCalculatesForCables2.dto.CableDto;
import ElectricalCalculatesForCables2.dto.MaterialDto;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@UtilityClass
public class CableDb {

    private final Properties DB_SETTINGS = new Properties();

    static {
        try {
            DB_SETTINGS.load(DbRunner.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows(SQLException.class)
    public void dropCablesAndMaterialsIfExists() {
        try (final Connection connection = getConnection();
            final PreparedStatement dropIfExistsTableCables = connection.prepareStatement("drop table if exists cables");
            final PreparedStatement dropIfExistsTableMaterials = connection.prepareStatement("drop table if exists materials")) {
             dropIfExistsTableCables.execute();
             dropIfExistsTableMaterials.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public void createCablesAndMaterials() {
        try (final Connection connection = getConnection();
            final PreparedStatement dropIfExistsTableCables = connection.prepareStatement(
                    """
                           create table materials
                           (
                               id int auto_increment,
                               material varchar(10) not null,
                               constraint materials_pk
                                   primary key (id)
                           );
                           """
            );
            final PreparedStatement dropIfExistsTableMaterials = connection.prepareStatement(
                    """
                        create table cables
                                  (
                                      id int auto_increment,
                                      numberOfWire varchar(10) not null,
                                      crossSection double not null,
                                      power int not null,
                                      material_id  int null,
                                      constraint cables_pk
                                          primary key (id),
                                      constraint cables_materials_id_fk
                                          foreign key (material_id) references materials (id)
                                  );
                        """
            )) {
            dropIfExistsTableCables.execute();
            dropIfExistsTableMaterials.execute();
        }
    }

    @SneakyThrows(SQLException.class)
    public Optional<Integer> insertMaterial(MaterialDto material) {
        final String materialName = material.getMaterialName();
        if (StringUtils.isBlank(materialName)) {
            return Optional.empty();
        }

        try (final Connection connection = getConnection();
             final PreparedStatement materialStatement = connection.prepareStatement("insert into materials(material) values(?)");
             final PreparedStatement searchMaterialStatement = connection.prepareStatement("select id from materials where material = ?")
        ) {
            materialStatement.setString(1, materialName);
            materialStatement.execute();
            searchMaterialStatement.setString(1, materialName);
            final ResultSet resultSet = searchMaterialStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(resultSet.getInt("id"));
            }
        }
        return Optional.empty();
    }

    @SneakyThrows(SQLException.class)
    public void insertCable(Integer materialId, CableDto cable) {
        try (final Connection connection = getConnection();
             final PreparedStatement fullCableStatement = connection.prepareStatement(
                     """
                             insert into cables(numberOfWire, crossSection, power, material_id)
                             values (?, ?, ?, ?)
                             """
             );
             final PreparedStatement cableStatementWithoutMaterial = connection.prepareStatement(
                     """
                             insert into cables(numberOfWire, crossSection, power)
                             values (?, ?, ?)
                             """
             )
        ) {
            if (materialId != null) {
                fullCableStatement.setString(1, cable.getNumberOfWire());
                fullCableStatement.setDouble(2, cable.getCrossSection());
                fullCableStatement.setInt(3, cable.getPower());
                fullCableStatement.setInt(4, materialId);
                fullCableStatement.execute();
            } else {
                cableStatementWithoutMaterial.setString(1, cable.getNumberOfWire());
                cableStatementWithoutMaterial.setDouble(2, cable.getCrossSection());
                cableStatementWithoutMaterial.setInt(3, cable.getPower());
                cableStatementWithoutMaterial.execute();
            }
        }
    }

 /*   @SneakyThrows(SQLException.class)
    public List<MaterialAndCable> searchByCrossSectionOrMaterialName(String text) {
        try(final Connection connection = getConnection();
            final PreparedStatement searchStatement = connection.prepareStatement("""
                select c.numberOfWire, c.crossSection, c.power, m.material from cables c
                left join materials m
                on c.material_id = m.id
                where lower(c.crossSection) like ? or lower(m.material) like ?
                order by c.id
            """)
        ) {
            searchStatement.setString(1, "%" + text + "%");
            searchStatement.setString(2, "%" + text + "%");
            final ResultSet resultSet = searchStatement.executeQuery();
            List<MaterialAndCable> result = new ArrayList<>();
            while(resultSet.next()) {
                result.add(new MaterialAndCable(
                                        resultSet.getString("numberOfWire"),
                                        resultSet.getDouble("crossSection"),
                        resultSet.getString("material"),
                        resultSet.wasNull() ? null : resultSet.getInt("power")));
            }
            return result;
        }
    }*/

    @SneakyThrows(SQLException.class)
    public List<MaterialAndCable> searchByCrossSectionOrMaterialName(String text) {
        try(final Connection connection = getConnection();
            final PreparedStatement searchStatement = connection.prepareStatement("""
                select c.numberOfWire, c.crossSection, c.power, m.material from cables c
                left join materials m
                on c.material_id = m.id
                where lower(c.crossSection) like ? or lower(m.material) like ? or lower(c.numberOfWire) like ?
                order by c.id
            """)
        ) {
            searchStatement.setString(1, "%" + text + "%");
            searchStatement.setString(2, "%" + text + "%");
            searchStatement.setString(3, "%" + text + "%");
            final ResultSet resultSet = searchStatement.executeQuery();
            List<MaterialAndCable> result = new ArrayList<>();
            while(resultSet.next()) {
                result.add(new MaterialAndCable(
                        resultSet.getString("numberOfWire"),
                        resultSet.getDouble("crossSection"),
                        resultSet.getString("material"),
                        resultSet.wasNull() ? null : resultSet.getInt("power")));
            }
            return result;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_SETTINGS.getProperty("jdbc.url"),//.crossSection
                DB_SETTINGS.getProperty("dbCable.login"),
                DB_SETTINGS.getProperty("dbCable.password"));
    }
}
