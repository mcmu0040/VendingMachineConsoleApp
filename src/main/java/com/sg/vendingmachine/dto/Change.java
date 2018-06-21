/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author mcmu0
 */
public class Change {
    //recieves money in pennies
    //able to return change as quarters, dimes, nickles and pennies
    
    //pennies will hold the total change, then update as we move value from pennies to other coins
    private int pennies;
    private int quarters;
    private int dimes;
    private int nickels;
    
    public Change(BigDecimal change) {
        this.pennies = change.multiply(new BigDecimal("100")).intValueExact();
        //mult by 100 to get pennies, then convert to int
        
        calculateChange();
    }
    
    private void calculateChange() {
        quarters = pennies / 25; //calc total quarters in all of pennies
        pennies = pennies % 25; //update pennies to remainder
        
        dimes = pennies / 10;
        pennies = pennies % 10;
        
        nickels = pennies / 5;
        pennies = pennies % 5;
    }
    
    public enum Type {
        QUARTERS,
        DIMES,
        NICKELS,
        PENNIES
    }
    
    //only use getters since this will calc and store the required change when instantiated

    public int getChange(Type type) {
        switch (type) {
            case QUARTERS:
                return quarters;
            case DIMES:
                return dimes;
            case NICKELS:
                return nickels;
            case PENNIES:
                return pennies;
        }
        return 0;
    }
    
    public int getPennies() {
        return pennies;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }
    
}
