/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author mcmu0
 */
public class MachineViewer {
    private UserIO io = new SimpleIO();

    public MachineViewer(UserIO io) {
        this.io = io;
    }

   
    
    public String getMoney() {
        return io.readString("Add money to the machine: ");
    }

    public void displayMoney(BigDecimal cash) {
        io.print("Cash: $" + cash.setScale(2, RoundingMode.HALF_UP));
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }

    public void displayInventory(List<Item> inventory) {
        io.print("Item/Price/Quantity");
        inventory.forEach((inv) -> {
            io.print(inv.getName()
                    + ": $" + inv.getPrice().setScale(2, RoundingMode.HALF_UP) 
                    + " QTY: " + inv.getQuantity());
        });
    }

    public String getSelectionPrompt() {
        return io.readString("What would you like to get?");
    }

    public void displayChangeBanner() {
        io.print("Thank you for using McMullen family vedning machines. Please take your change below.");
    }

    public void displayCoins(int numCoins, String coin) {
        io.print(coin + ": " + numCoins);
    }

    public void displayWelcomeMessage() {
        io.print("Welcome to the McMullen Family Vending machine simulator.");
        io.print("Please review our current inventory and make selection.");
    }

    public boolean getAnotherItem() {
        String choice = io.readString("Would you like to make another selection? (Y/N)");
        if (choice.equalsIgnoreCase("y")) {
            return true;
        } else { //user enters anything besides "y" or "Y", exit
            return false;
        }
    }

    public void dispenseItem(String selection) {
        io.print("**Machine Noises**");
        io.print("Kerplunk");
        io.print(selection.toUpperCase() + " has magicaly appeared.");
        io.print("Thank you for your purchase.");
    }

    public String addMoney() {
        return io.readString("Would you like to add more money? (Y/N)");
    }

    public void displayCurrentCashBanner() {
        io.print("The current balance on the machine is:");
    }

    public void whiteSpace() {
        //give extra white space after exiting adminMode
        io.print("\n\n\n\n\n\n");
    }
    
}
