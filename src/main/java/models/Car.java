package models;

public class Car {

    private long id;
    private String make;
    private String model;
    private String color;
    private int year;

    public Car(long id, String make, String model, String color, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
