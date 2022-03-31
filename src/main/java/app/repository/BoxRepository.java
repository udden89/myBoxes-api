package app.repository;

import app.models.BoxModel;
import java.sql.*;
import java.util.ArrayList;


import static app.db.MySQLConnector.getConnection;

public class BoxRepository {

     public BoxRepository() {
         createBoxTable();
    }

    public void createBoxTable(){
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

    public boolean saveBox(BoxModel box){

        String sql = "INSERT INTO boxes(receiver, weight, color, destination_country, shipping_cost) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

            preparedStatement.setString(1, box.getReceiver());
            preparedStatement.setDouble(2, box.getWeight());
            preparedStatement.setString(3, box.getColor());
            preparedStatement.setString(4, box.getDestinationCountry());
            preparedStatement.setDouble(5,box.getShippingCost());

            int a = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (a == 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<BoxModel> getAllBoxes() throws SQLException {
        String sql = "SELECT * FROM boxes ";

        ArrayList<BoxModel> boxModels = new ArrayList<BoxModel>();

        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            BoxModel boxModel = new BoxModel();
                boxModel.setReceiver(rs.getString("receiver"));
                boxModel.setWeight(rs.getDouble("weight"));
                boxModel.setColor(rs.getString("color"));
                boxModel.setDestinationCountry(rs.getString("destination_country"));
                boxModel.setShippingCost(rs.getDouble("shipping_cost"));
            boxModels.add(boxModel);
        }
        statement.close();
        return boxModels;
    }

    private void executeStatement(String sqlStatement){
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate(sqlStatement);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
