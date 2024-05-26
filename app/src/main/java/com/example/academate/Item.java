package com.example.academate;

public class Item {
    private String username;
    private String itemName;
    private String itemDescription;

    public Item() {
    }

    public Item(String username, String itemName, String itemDescription) {
        this.username = username;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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
