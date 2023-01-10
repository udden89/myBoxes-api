package app.controllers;

import app.models.Box;
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
        boolean ok = boxService.createNewBox(ctx.bodyAsClass(Box.class));
        if(ok) {
            ctx.status(200).json("Box saved successfully!");
        }else {
            ctx.status(500).json("Could not create the box");
        }
    };

}
