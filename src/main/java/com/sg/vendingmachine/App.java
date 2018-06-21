/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingController;
import com.sg.vendingmachine.dao.MachineDao;
import com.sg.vendingmachine.dao.MachineDaoImpl;
import com.sg.vendingmachine.service.MachineServiceLayer;
import com.sg.vendingmachine.ui.MachineViewer;
import com.sg.vendingmachine.ui.SimpleIO;
import com.sg.vendingmachine.ui.UserIO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mcmu0
 */
public class App {
    public static void main(String[] args) {
//        UserIO io = new SimpleIO();
//        MachineViewer view = new MachineViewer(io);
//        MachineDao dao = new MachineDaoImpl();
//        MachineServiceLayer service = new MachineServiceLayer(dao);
//        VendingController controller = new VendingController(view, service);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingController controller = ctx.getBean("controller", VendingController.class);
        
        controller.run();
    }
}
