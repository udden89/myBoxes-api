package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static app.Application.dotenv;

public class MySQLConnector {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    private static final String DATABASE_NAME = dotenv.get("DB_NAME");
    private static String urlDB = "jdbc:mysql://localhost/";

    private static Connection connection;
    private static boolean isDBInitialized = false;

    public static Connection getConnection(){
        try{
            Class.forName(DRIVER_NAME);
            try{
                connection = DriverManager.getConnection(urlDB, USERNAME, PASSWORD);
                if(!isDBInitialized){
                    initDB();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            System.out.println("Driver not found.");
        }
        return connection;
    }

    public static void initDB () {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            st.close();
            urlDB += " " + DATABASE_NAME;
            isDBInitialized = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
