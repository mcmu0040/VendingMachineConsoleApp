/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.MachineDao;
import com.sg.vendingmachine.dao.VendingMachingPersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcmu0
 */
public class MachineDaoStubImpl implements MachineDao {
    
    Item onlyItem;
    List<Item> itemList = new ArrayList<>();

    public MachineDaoStubImpl() {
        onlyItem = new Item("Snickers", new BigDecimal("1.50"));
        onlyItem.setQuantity(1);
        
        itemList.add(onlyItem);
    }
    
    

    @Override
    public Item addItem(String name, Item item) throws VendingMachingPersistenceException {
        if (item.getName().equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item removeItem(String name) throws VendingMachingPersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getInventory() throws VendingMachingPersistenceException {
        return itemList;
    }

    @Override
    public void soldItem(String name) throws VendingMachingPersistenceException {
        itemList.get(0).itemSold();
    }

    @Override
    public BigDecimal getPrice(String selection) throws VendingMachingPersistenceException {
        return itemList.get(0).getPrice();
    }
    
}
