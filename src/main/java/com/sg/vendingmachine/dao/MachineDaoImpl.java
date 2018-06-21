/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mcmu0
 */
public class MachineDaoImpl implements MachineDao {
    
    private Map<String, Item> inventory = new HashMap<>();
    private static final String INV_FILE = "inventory.txt";
    private static final String DELIMITER = "::";

    @Override
    public Item addItem(String name, Item item) throws VendingMachingPersistenceException{
        //function used for testing
        //may add administrative purpose later
        Item newItem = inventory.put(name.toLowerCase(), item);
        writeInventory();
        return newItem;
    }

    @Override
    public Item removeItem(String name) throws VendingMachingPersistenceException{
        //function used for testing
        //may add administrative purpose later
        Item removedItem = inventory.remove(name.toLowerCase());
        writeInventory();
        return removedItem;
    }

    @Override
    public List<Item> getInventory() throws VendingMachingPersistenceException {
        loadInventory();
        return new ArrayList<Item>(inventory.values());
    }

    @Override
    public void soldItem(String name) throws VendingMachingPersistenceException {
        inventory.get(name.toLowerCase()).itemSold();
        //after item sold, update memory
        writeInventory();
    }
    
    @Override
    public BigDecimal getPrice(String selection) throws VendingMachingPersistenceException {
        return inventory.get(selection.toLowerCase()).getPrice();
    }
    
//    //@Override
//    public void restock(String selection, int quantity) {
//        //quantity is meant to add items only
//        Item item = inventory.get(selection);
//        item.setQuantity(item.getQuantity() + quantity);
//    }
    
    private void loadInventory() throws VendingMachingPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(INV_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachingPersistenceException("-_- Could not load inventory data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        
        String[] currentTokens;
        // Go through INV_FILE line by line, decoding each line into a 
        // Item object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Item object and put it into the map of inventory
            Item currentItem = new Item(currentTokens[0], new BigDecimal(currentTokens[1]));
            // Set the remaining vlaues on currentItem manually
            currentItem.setQuantity(Integer.parseInt(currentTokens[2]));
            

            // Put currentItem into the map using name (in lowercase) as the key
            inventory.put(currentItem.getName().toLowerCase(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeInventory() throws VendingMachingPersistenceException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INV_FILE));
        } catch (IOException e) {
            throw new VendingMachingPersistenceException("-_- Could not load inventory data into memory.", e);
        }

        // Write out the Item objects to the inventory file.
        
        Collection<Item> inventoryList = inventory.values();
        for (Item item : inventoryList) {
            // write the Item object to the file
            out.println(item.getName() + DELIMITER
                    + item.getPrice() + DELIMITER
                    + item.getQuantity());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
