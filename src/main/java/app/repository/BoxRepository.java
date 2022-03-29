package app.repository;

import app.models.BoxModel;
import java.sql.*;
import java.util.ArrayList;

import static app.Application.connection;

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

    public void saveBox(BoxModel box){
        String sql = "INSERT INTO " +
                "boxes(receiver, weight, color, destination_country, shipping_cost) VALUES" +
                "('" + box.getReceiver() +
                "', " + box.getWeight() +
                ", '" + box.getColor() +
                "', '" + box.getDestinationCountry() +
                "', '" + box.getShippingCost() + "')";
        System.out.println(sql);
        executePreparedStatement(sql);
    }

    public ArrayList<BoxModel> getAllBoxes() throws SQLException {
        String sql = "SELECT * FROM boxes ";

        ArrayList<BoxModel> boxModels = new ArrayList<BoxModel>();

        Statement statement = connection.createStatement();
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
        return boxModels;
    }

    private void executeStatement(String sqlStatement){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(sqlStatement);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executePreparedStatement(String sqlStatement){
        try {
            PreparedStatement st = connection.prepareStatement(sqlStatement);
            st.executeUpdate(sqlStatement);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
