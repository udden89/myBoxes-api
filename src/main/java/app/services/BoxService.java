package app.services;

import app.models.Box;
import app.project_constants.ShippingCountriesAndPrice;
import app.repository.BoxRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BoxService {

    static BoxRepository boxRepository = new BoxRepository();

    public BoxService() {
    }

    public boolean createNewBox(Box box) {
        box.setShippingCost( calculateShippingPrice(box) );
        if(box.getShippingCost() < 0) return false;
        return boxRepository.saveBox(box);
    }

    public ArrayList<Box> getAllBoxes() throws SQLException {
        return boxRepository.getAllBoxes();
    }

    private double calculateShippingPrice(Box box) {

        return switch (box.getDestinationCountry().toUpperCase()) {
            case "SWEDEN" -> ShippingCountriesAndPrice.SWEDEN.price * box.getWeight();
            case "CHINA" -> ShippingCountriesAndPrice.CHINA.price * box.getWeight();
            case "BRAZIL" -> ShippingCountriesAndPrice.BRAZIL.price * box.getWeight();
            case "AUSTRALIA" -> ShippingCountriesAndPrice.AUSTRALIA.price * box.getWeight();
            default -> -1;
        };
    }
}
