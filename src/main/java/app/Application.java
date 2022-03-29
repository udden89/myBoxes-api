package app;

import app.controllers.BoxController;
import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {

    //TODO PUT THIS INTO OWN CLASS??
    public static Connection connection = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Dotenv dotenv = Dotenv.load();

        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        String database = dotenv.get("DB_NAME");

        Javalin app = Javalin.create().start(7070);

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost/" + database;
        connection = DriverManager.getConnection(url, username, password);

        app.routes(() -> {
           get("box", BoxController.getAllBoxes);
           post("box", BoxController.createNewBox);
        });
        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
