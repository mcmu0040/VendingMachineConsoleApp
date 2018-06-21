/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author mcmu0
 */
public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String string) {
        super(string);
    }

    public NoItemInventoryException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
