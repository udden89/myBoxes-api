package app.controllers;

import app.models.BoxModel;
import app.services.BoxService;
import io.javalin.http.Handler;

public class BoxController {

    static BoxService boxService = new BoxService();

    public BoxController() {
    }

    public static Handler getAllBoxes = ctx -> {
        ctx.status(200).json(boxService.getAllBoxes());
    };

    public static Handler createNewBox = ctx -> {
        boolean ok = boxService.createNewBox(ctx.bodyAsClass(BoxModel.class));
        if(ok) {
            ctx.status(200).json("Saved successfully!");
        }else {
            ctx.status(500).json("Could not save");
        }
    };

}
