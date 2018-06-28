package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DatosDeEntrada datosDeEntrada = new DatosDeEntrada();
        datosDeEntrada.setVisible(true);
        System.out.println("Iniciando Servidor");
        ArrayList<Etapa> etapas = new ArrayList<Etapa>();
        ArrayList<Integer> minutos = new ArrayList<Integer>();
        ArrayList<Float> probabilidades = new ArrayList<Float>();
        ArrayList<Integer> minutos2 = new ArrayList<Integer>();
        ArrayList<Float> probabilidades2 = new ArrayList<Float>();
        minutos.add(2);
        minutos.add(3);
        minutos.add(4);
        probabilidades.add(0.5f);
        probabilidades.add(0.3f);
        probabilidades.add(0.2f);
        minutos2.add(12);
        minutos2.add(10);
        minutos2.add(7);
        probabilidades2.add(0.5f);
        probabilidades2.add(0.3f);
        probabilidades2.add(0.2f);
        etapas.add(new Etapa(1,1, minutos, probabilidades));
        etapas.add(new Etapa(2,2, minutos, probabilidades2));
        Sistema sistema = new Sistema(etapas, 10, minutos, probabilidades, 40);

        for (int i = 0; i < 10; i++) {
            sistema.mostrarDemandantes();
            sistema.ejecutarTransicion();
            System.out.println("\n Realizando la Iteracion "+ i+"\n \n");
            sistema.etapas.get(0).mostrarEtapa();
            sistema.etapas.get(1).mostrarEtapa();
        }


    }
}
