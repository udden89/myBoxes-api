package app;

import app.controllers.BoxController;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {

    static private final String username = "root";
    static private final String password = "admin";
    static private final String database = "myboxes";

    public static Connection connection = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

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
