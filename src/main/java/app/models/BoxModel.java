package app.models;

public class BoxModel {

    String receiver = "";
    double weight = 0;
    String color = "";
    String destinationCountry = "";
    double shippingCost = 0;

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    @Override
    public String toString() {
        return "BoxModel{" +
                "receiver='" + receiver + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", shippingCost=" + shippingCost +
                '}';
    }
}
