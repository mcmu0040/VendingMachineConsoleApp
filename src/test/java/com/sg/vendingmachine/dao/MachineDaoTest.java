/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mcmu0
 */
public class MachineDaoTest {
    
    private MachineDao dao = new MachineDaoImpl();
    
    public MachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        //get dao in known state
        //remove all items
        List<Item> itemList = dao.getInventory();
        for(Item item : itemList) {
            dao.removeItem(item.getName());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class MachineDao.
     */
    @Test
    public void testAddItem() throws Exception {
        List<Item> inv = dao.getInventory();
        //will pass if removeItem works in setup()
        assertEquals(0, inv.size());
        
        Item item1 = new Item("Snickers", new BigDecimal("1.50"));
        item1.setQuantity(1);
        dao.addItem(item1.getName(), item1);
        
        Item fromDao = dao.removeItem(item1.getName());
        
        assertEquals(item1, fromDao);
    }

    /**
     * Test of removeItem method, of class MachineDao.
     */
    @Test
    public void testRemoveItem() {
        //no further testing needed, proved in setup and testAddItem
    }

    /**
     * Test of getInventory method, of class MachineDao.
     */
    @Test
    public void testGetInventory() throws Exception {
        Item item1 = new Item("Snickers", new BigDecimal("1.50"));
        item1.setQuantity(1);
        dao.addItem(item1.getName(), item1);
        
        Item item2 = new Item("Butter Finger", new BigDecimal("2.50"));
        item2.setQuantity(1);
        dao.addItem(item2.getName(), item2);
        
        dao.removeItem(item2.getName());
        
        assertEquals(1, dao.getInventory().size());
        
        dao.removeItem(item1.getName());
        
        assertEquals(0, dao.getInventory().size());
    }

    /**
     * Test of soldItem method, of class MachineDao.
     */
    @Test
    public void testSoldItem() throws Exception {
        Item item1 = new Item("Snickers", new BigDecimal("1.50"));
        item1.setQuantity(1);
        dao.addItem(item1.getName(), item1);
        
        dao.soldItem(item1.getName());
        
        assertEquals(0, item1.getQuantity());
    }

    /**
     * Test of getPrice method, of class MachineDao.
     */
    @Test
    public void testGetPrice() throws Exception {
        Item item1 = new Item("Snickers", new BigDecimal("1.50"));
        item1.setQuantity(1);
        dao.addItem(item1.getName(), item1);
        
        assertEquals(new BigDecimal("1.50"), dao.getPrice(item1.getName()));
    }

    
    
}
