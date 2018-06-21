/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author mcmu0
 */
public class Item {
    private String name;
    private BigDecimal price;
    private int quantity;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //easy method to update quantity when item is sold
    //we will check in service layer if an item is available so no other logic is needed
    public void itemSold() {
        quantity--;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
    
}
