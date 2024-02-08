package model;

public class Guitar {
    private String serialNumber;
    private double price;
    private Builder builder;
    private String model;
    private Type type;
    private Wood backWood;
    private Wood topWood;

    public Guitar(){

    }

    public Guitar(Builder b, String m, Type t, Wood bw, Wood tw, String sn, double p){
        this.serialNumber = sn;
        this.price = p;
        this.builder = b;
        this.model = m;
        this.type = t;
        this.backWood = bw;
        this.topWood = tw;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float f){
        this.price = f;
    }

    public Builder getBuilder() {
        return builder;
    }

    public String getModel() {
        return model;
    }

    public Type getType() {
        return type;
    }

    public Wood getBackWood() {
        return backWood;
    }

    public Wood getTopWood() {
        return topWood;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setBackWood(Wood backWood) {
        this.backWood = backWood;
    }

    public void setTopWood(Wood topWood) {
        this.topWood = topWood;
    }
}
