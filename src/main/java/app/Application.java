package app;

import app.controllers.BoxController;
import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Application {

    public static Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        app.routes(() -> {
           get("box", BoxController.getAllBoxes);
           post("box", BoxController.createNewBox);
        });

    }
}
