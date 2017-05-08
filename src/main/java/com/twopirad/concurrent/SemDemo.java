/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twopirad.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author diptam
 */
public class SemDemo {

    public static void main(String args[]) {
        Semaphore sem = new Semaphore(1);
        new IncThread(sem, "A");
        new DecThread(sem, "B");
    }
}

