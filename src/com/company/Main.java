package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatosDeEntrada().setVisible(true);
            }
        });

    }
}
