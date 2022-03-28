package app.controllers;

import app.models.BoxModel;
import app.services.BoxService;
import io.javalin.http.Handler;

public class BoxController {

    static BoxService boxService = new BoxService();

    public BoxController() {
    }

    public static Handler getAllBoxes = ctx -> {
        ctx.json(boxService.getAllBoxes());
    };

    public static Handler createNewBox = ctx -> {
        boxService.createNewBox(ctx.bodyAsClass(BoxModel.class));
    };

}
