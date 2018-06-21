/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author mcmu0
 */
public class VendingMachingPersistenceException extends Exception {

    public VendingMachingPersistenceException(String string) {
        super(string);
    }

    public VendingMachingPersistenceException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
