package app.repository;

import app.models.Box;

import java.sql.*;
import java.util.ArrayList;


import static app.db.MySQLConnector.getConnection;

public class BoxRepository {

    public BoxRepository() {
        createBoxTable();
    }

    public void createBoxTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `myboxes`.`boxes` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `receiver` VARCHAR(45) NOT NULL," +
                "  `weight` DOUBLE NOT NULL," +
                "  `color` VARCHAR(45) NULL," +
                "  `destination_country` VARCHAR(45) NOT NULL," +
                "  `shipping_cost` DOUBLE NULL," +
                "  PRIMARY KEY (`id`));";
        executeStatement(sql);
    }

    public boolean saveBox(Box box) {

        String sql = "INSERT INTO boxes(receiver, weight, color, destination_country, shipping_cost) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

            preparedStatement.setString(1, box.getReceiver());
            preparedStatement.setDouble(2, box.getWeight());
            preparedStatement.setString(3, box.getColor());
            preparedStatement.setString(4, box.getDestinationCountry());
            preparedStatement.setDouble(5, box.getShippingCost());

            int success = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (success == 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Box> getAllBoxes() throws SQLException {
        String sql = "SELECT * FROM boxes";

        ArrayList<Box> boxes = new ArrayList<Box>();

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Box box = new Box.BoxBuilder()
                    .color(resultSet.getString("color"))
                    .destinationCountry(resultSet.getString("destination_country"))
                    .receiver(resultSet.getString("receiver"))
                    .shippingCost(resultSet.getDouble("shipping_cost"))
                    .weight(resultSet.getDouble("weight"))
                    .build();

            boxes.add(box);
        }
        statement.close();
        return boxes;
    }

    private void executeStatement(String sqlStatement) {
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate(sqlStatement);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
