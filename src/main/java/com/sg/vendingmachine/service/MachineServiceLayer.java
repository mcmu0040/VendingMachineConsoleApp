/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.MachineDao;
import com.sg.vendingmachine.dao.VendingMachingPersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mcmu0
 */
public class MachineServiceLayer {

    private MachineDao dao;

    public MachineServiceLayer(MachineDao dao) {
        this.dao = dao;
    }

    public List<Item> getInventory() throws VendingMachingPersistenceException {
        return dao.getInventory();
    }

    public boolean validateSelection(String selection) throws VendingMachingPersistenceException, 
                                                                NoItemInventoryException {
        boolean valid = false;
        List<Item> items = getInventory();
        for (Item item : items) {
            if (selection.equalsIgnoreCase(item.getName())) {
                if (item.getQuantity() > 0) {
                    valid = true;
                } else {
                    throw new NoItemInventoryException("OOPS: That item is out of inventory");
                }
            }
        }
        return valid;
    }

    public BigDecimal soldItem(String selection, BigDecimal cash) throws VendingMachingPersistenceException, 
                                                                    InsufficientFundsException {
        //check if cash available is greater than or equal to the items price

        if (cash.compareTo(dao.getPrice(selection)) >= 0) {
            dao.soldItem(selection);
            return dao.getPrice(selection);
        } else {
            throw new InsufficientFundsException("OOPS: You don't have enough money for that.");
        }
    }

    public boolean validateMoney(String money) {
        try {
            Double.parseDouble(money);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
