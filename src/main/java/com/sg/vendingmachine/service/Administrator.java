/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.MachineDao;
import com.sg.vendingmachine.dao.MachineDaoImpl;
import com.sg.vendingmachine.dao.VendingMachingPersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.ui.SimpleIO;
import com.sg.vendingmachine.ui.UserIO;
import java.math.BigDecimal;

/**
 *
 * @author mcmu0
 */
public class Administrator {
    
    //class used to restock or remove items
    //must enter a password to continue
    
    UserIO io = new SimpleIO();
    MachineDao dao = new MachineDaoImpl();

    public void adminMode() throws VendingMachingPersistenceException {
        io.print("\nWelcome to ADMIN MODE");
        boolean done = false;
        String itemChoice;
        int quantity;
        Item item;
        String password = io.readString("Please enter the password");
        if (password.endsWith("12345")) {
            dao.getInventory();
            io.print("That's a combination only an idiot would use on his luggage.");
            
            do {
                String action = io.readString("Restock, remove, add or exit?").toLowerCase();
                switch (action) {
                    case "restock":
                        //remove, update quantity only, then add it back
                        itemChoice = io.readString("Restock which item?");
                        item = dao.removeItem(itemChoice);
                        quantity = io.readInt("Add how many?", 0, 15); 
                        quantity += item.getQuantity();
                        item.setQuantity(quantity);
                        dao.addItem(item.getName(), item);
                        break;
                    case "remove":
                        itemChoice = io.readString("Remove which item?");
                        dao.removeItem(itemChoice);
                        break;
                    case "add":
                        itemChoice = io.readString("Add which item?");
                        String price = io.readString("Enter Items's price");
                        quantity = io.readInt("Add how many?", 0, 15);
                        
                        item = new Item(itemChoice, new BigDecimal(price));
                        item.setQuantity(quantity);
                        
                        dao.addItem(itemChoice.toLowerCase(), item);
                        break;
                    case "exit":
                        done = true;
                        break;
                    default:
                        io.print("That is not a valid choice.");
                        io.print("Please do better next time. :(");
                }
                
            } while (!done);
            
            
            io.print("Exiting ADMIN MODE");
        } else {
            io.print("Incorrect password");
            io.print("Exiting ADMIN MODE");
        }
    }
    
}
