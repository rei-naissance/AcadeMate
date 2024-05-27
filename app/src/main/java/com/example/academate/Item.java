package com.example.academate;

public class Item {
    private String username;
    private String itemName;
    private String itemDescription;
    private String email;

    public String getEmail() {
        return email;
    }

    public Item() {
    }

    public Item(String username, String itemName, String itemDescription, String email) {
        this.username = username;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
