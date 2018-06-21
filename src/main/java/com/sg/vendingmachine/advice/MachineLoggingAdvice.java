/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.MachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachingPersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author mcmu0
 */
public class MachineLoggingAdvice {
    MachineAuditDao dao;

    public MachineLoggingAdvice(MachineAuditDao dao) {
        this.dao = dao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().toString() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            dao.writeAuditEntry(auditEntry);
        } catch (VendingMachingPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in MachineLoggingAdvice.");
        }
    }
    
    public void fundsExceptionLog(JoinPoint jp, InsufficientFundsException ex) {
        //System.out.println("Made it here");
        
        String auditEntry = "";
        
        Object[] args = jp.getArgs();
        auditEntry = jp.getSignature().toString() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        
        try {
            dao.writeAuditEntry("InsufficientFundsException: " + auditEntry);
        } catch (VendingMachingPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in MachineLoggingAdvice.");
        }
    }
    
    public void itemsExceptionLog(JoinPoint jp, NoItemInventoryException ex) {
        //System.out.println("Made it here");
        
        String auditEntry = "";
        
        Object[] args = jp.getArgs();
        auditEntry = jp.getSignature().toString() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        
        try {
            dao.writeAuditEntry("NoItemInventoryException: " + auditEntry);
        } catch (VendingMachingPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in MachineLoggingAdvice.");
        }
    }
}
