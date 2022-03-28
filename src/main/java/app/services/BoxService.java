package app.services;

import app.models.BoxModel;
import app.repository.BoxRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BoxService {

    static BoxRepository boxRepository = new BoxRepository();

    public BoxService() {

    }

    public void createNewBox(BoxModel box){
        System.out.println(box.toString());
        boxRepository.saveBox(box);
    }

    public ArrayList<BoxModel> getAllBoxes() throws SQLException {
        return boxRepository.getAllBoxes();
    }
}
