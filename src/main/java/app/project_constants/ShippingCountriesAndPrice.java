package app.project_constants;

public enum ShippingCountriesAndPrice {

    //IF ADDING MORE COUNTRIES, REMEMBER TO CHANGE IN BOXSERVICE AS WELL
    SWEDEN(1.3),
    CHINA(4),
    BRAZIL(8.6),
    AUSTRALIA(7.2);

    public final double price;

    ShippingCountriesAndPrice(double price) {
        this.price = price;
    }
}
