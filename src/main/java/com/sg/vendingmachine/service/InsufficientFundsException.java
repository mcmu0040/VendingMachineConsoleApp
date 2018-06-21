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
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String string) {
        super(string);
    }

    public InsufficientFundsException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
