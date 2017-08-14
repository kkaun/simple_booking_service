package com.kirak.to;

/**
 * Created by Kir on 14.08.2017.
 */
public class ApartmentTo {

    private final Double price;

    private final Short personNum;

    private final String category;

    private final String bedsArrangement;

    public ApartmentTo(Double price, Short personNum, String category, String bedsArrangement) {
        this.price = price;
        this.personNum = personNum;
        this.category = category;
        this.bedsArrangement = bedsArrangement;
    }

    public Double getPrice() {
        return price;
    }

    public Short getPersonNum() {
        return personNum;
    }

    public String getCategory() {
        return category;
    }

    public String getBedsArrangement() {
        return bedsArrangement;
    }

    @Override
    public String toString() {
        return "ApartmentTo{" +
                "price=" + price +
                ", personNum=" + personNum +
                ", category='" + category + '\'' +
                ", bedsArrangement='" + bedsArrangement + '\'' +
                '}';
    }
}
