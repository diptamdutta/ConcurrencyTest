/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twopirad.concurrent;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author diptam
 */
public class DecThread implements Runnable {

    private String name;
    private Semaphore sem;

    DecThread(Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Starting :" + name);
        try {
            System.out.println(name + " awaiting permit");
            sem.acquire();
            System.out.println(name + " get permit");
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
// Now, allow a context switch -- if possible.
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DecThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name + " releases the permit.");
        sem.release();
    }

}
