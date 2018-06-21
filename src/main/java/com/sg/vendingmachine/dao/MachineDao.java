/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mcmu0
 */
public interface MachineDao {
    
    //adds item to inventroy
    //should be used in back ground when loading inventory
    //may be useful if implementing a restock option
    Item addItem(String name, Item item) throws VendingMachingPersistenceException;
    
    //removes an item from inventory
    //used a background item
    //may be useful if implementing a restock option
    Item removeItem(String name) throws VendingMachingPersistenceException;
    
    //gets List of all Items in inventory
    //needed in service layer
    List<Item> getInventory() throws VendingMachingPersistenceException ;
    
    //updates the inventory of name but removing 1 from quantity
    //needed in service layer
    void soldItem(String name)throws VendingMachingPersistenceException;

    public BigDecimal getPrice(String selection) throws VendingMachingPersistenceException;
}
