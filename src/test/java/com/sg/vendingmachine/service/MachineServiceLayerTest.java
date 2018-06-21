/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.MachineDao;
import com.sg.vendingmachine.dao.MachineDaoStubImpl;
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
public class MachineServiceLayerTest {
    
    private MachineServiceLayer service;
    
    public MachineServiceLayerTest() {
        MachineDao dao = new MachineDaoStubImpl();
        
        service = new MachineServiceLayer(dao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInventory method, of class MachineServiceLayer.
     */
    @Test
    public void testGetInventory() throws Exception {
        assertEquals(1, service.getInventory().size());
    }

    /**
     * Test of validateSelection method, of class MachineServiceLayer.
     */
    @Test
    public void testValidateSelection() throws Exception {
        String sel1 = "Snickers";
        String sel2 = "Not Snickers";
        
        //test good item
        assertTrue(service.validateSelection(sel1));
        
        //test for bad item
        assertFalse(service.validateSelection(sel2));
        
        //sell item with enough "cash" to pay for it
        service.soldItem(sel1, new BigDecimal("3.00"));
        //test to ensure inventory updated and item won't be allowd to be sold due to no quantity
        try {
            assertFalse(service.validateSelection(sel1));
            fail("Expected NoItemInventoryException.");
        } catch (NoItemInventoryException e) {
            //success
            return;
        }
    }

    /**
     * Test of soldItem method, of class MachineServiceLayer.
     */
    @Test
    public void testSoldItem() throws Exception {
        List<Item> item = service.getInventory();
        try {
            service.soldItem(item.get(0).getName(), BigDecimal.ZERO);
            fail("Expected InsufficientFundsException.");
        } catch(InsufficientFundsException e) {
            //success
            return;
        }
        
        assertEquals(item.get(0).getPrice(), service.soldItem(item.get(0).getName(), item.get(0).getPrice()));
    }
    
    //do not test add or remove items in service since it is not implemented in the service.
    //only used in dao for testing and holders for future functions (ie. restock)
    
}
