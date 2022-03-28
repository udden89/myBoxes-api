package app.repository;

/*import static app.db.MySQLStartup.executePreparedStatement;*/

import app.models.BoxModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

import static app.Application.connection;

public class BoxRepository {

    Logger logger = LoggerFactory.getLogger(BoxRepository.class);

     public BoxRepository() {
         /*createDB();*/
         createBoxTable();
    }

  /*  public void createDB(){
        executeStatement("CREATE DATABASE IF NOT EXISTS " + database);
    }*/

    public void createBoxTable(){
        String sql = "CREATE TABLE IF NOT EXISTS `myboxes`.`boxes` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `receiver` VARCHAR(45) NOT NULL," +
                "  `weight` DECIMAL NOT NULL," +
                "  `color` VARCHAR(45) NOT NULL," +
                "  `destinationCountry` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`id`));";
        executeStatement(sql);
    }

    public void saveBox(BoxModel box){
        String sql = "INSERT INTO " +
                "boxes(receiver, weight, color, destinationCountry) VALUES" +
                "('" + box.getReceiver() +
                "', " + box.getWeight() +
                ", '" + box.getColor() +
                "', '" + box.getDestinationCountry() + "')";
        System.out.println(sql);
        executePreparedStatement(sql);
    }

    public ArrayList<BoxModel> getAllBoxes() throws SQLException {
        String sql = "SELECT * FROM boxes ";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<BoxModel> boxModels = new ArrayList<BoxModel>();

        while (rs.next()){
            BoxModel boxModel = new BoxModel();
            boxModel.setReceiver(rs.getString("receiver"));
            boxModel.setWeight(rs.getDouble("weight"));
            boxModel.setColor(rs.getString("color"));
            boxModel.setDestinationCountry(rs.getString("destinationCountry"));
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
