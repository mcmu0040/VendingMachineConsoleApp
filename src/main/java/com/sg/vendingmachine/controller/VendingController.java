/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachingPersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.service.Administrator;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.MachineServiceLayer;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.ui.MachineViewer;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcmu0
 */
public class VendingController {

    private MachineViewer view;
    private MachineServiceLayer service;
    private BigDecimal cash = new BigDecimal("0.00");

    public VendingController(MachineViewer view, MachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;

        welcome();

        try {
            while (keepGoing) {
                displayInvetory();

                displayMoney();
                addMoney();
                displayMoney();

                String selection = getSelction();
                //valid selection, verified in getSelection()

                getItem(selection);

                if (getAnotherItem()) {
                    keepGoing = true;
                } else {
                    keepGoing = false;
                }
            }
        } catch (VendingMachingPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

        //upon leaving calculate and display change
        displayChange();
    }

    private void addMoney() throws VendingMachingPersistenceException {
        String money;
        boolean notANum = true;
        do {
            money = view.getMoney();
            //first do admin check, then check for a NAN
            if (money.equals("admin")) {
                //enter admin mode
                //restock items
                //add new items
                //after admin is done, ask for money again and continue program normally
                //assumes some competence from admin user
                adminMode();
                //after exiting admin mode, display inventory again so it's ready for the next user
                
                view.whiteSpace();
                displayInvetory();
                money = view.getMoney();
            }
            //if validation check, maybe admin attempt didn't type correct string, so give another chance
            //could add a couner if we want to limit chances
            if (service.validateMoney(money)) {
                //return true if is a number
                notANum = false;
            } else {
                notANum = true;
            }
        } while (notANum);
        cash = cash.add(new BigDecimal(money));
    }

    private void displayMoney() {
        view.displayMoney(cash);
    }

    private void displayInvetory() throws VendingMachingPersistenceException {
        view.displayInventory(service.getInventory());
    }

    private String getSelction() throws VendingMachingPersistenceException {
        String selection = null;
        boolean valid;
        do {
            try {
                selection = view.getSelectionPrompt();
                valid = service.validateSelection(selection);
            } catch (NoItemInventoryException ex) {
                view.displayErrorMessage(ex.getMessage());
                valid = false;
            }
        } while (!valid);
        return selection;
    }

    private void getItem(String sel) throws VendingMachingPersistenceException {
        boolean valid;
        do {
            try {
                //expected valid item selection at this point
                //update the inventory that 1 item is sold

                cash = cash.subtract(service.soldItem(sel, cash));
                view.dispenseItem(sel);
                valid = true;
            } catch (InsufficientFundsException ex) {
                view.displayErrorMessage(ex.getMessage());
                //ask user if they want to add money to machince
                //if yes, allow them to add money and automatically try purchase again
                //if anything besides "y" or "Y", exit out of method, return to run() 
                //and let them make another choise
                view.displayCurrentCashBanner();
                view.displayMoney(cash);
                String add = view.addMoney();
                if (add.equalsIgnoreCase("y")) {
                    addMoney();
                    valid = false;
                } else {
                    valid = true;
                    //set valid to true to exit getItem without doing any other work
                }
            }
        } while (!valid);

    }

    private void displayChange() {
        view.displayChangeBanner();
        Change change = new Change(cash);
        view.displayCoins(change.getChange(Change.Type.QUARTERS), "Quarters");
        view.displayCoins(change.getChange(Change.Type.DIMES), "Dimes");
        view.displayCoins(change.getChange(Change.Type.NICKELS), "Nickels");
        view.displayCoins(change.getChange(Change.Type.PENNIES), "Pennies");
    }

    private void welcome() {
        view.displayWelcomeMessage();
    }

    private boolean getAnotherItem() {
        return view.getAnotherItem();
    }

    private void adminMode() throws VendingMachingPersistenceException {
        Administrator admin = new Administrator();
        admin.adminMode();
    }
}
