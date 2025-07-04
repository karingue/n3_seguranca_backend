package br.com.diegoplaninscheck.n3_seguranca_backend.model;

public class Product {
    private String name;
    private String color;
    private String size;

    public Product(String name, String color, String size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
