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
        System.out.println("Minutos        2, 4, 4");
        System.out.println("Probabilidades 0.5, 0.3, 0.2");
        GeneradorDeTiempos generador = new GeneradorDeTiempos(minutos, probabilidades);
        for (int i = 0; i < 10; i++) {
            generador.obtenerTiempo();
        }
        /*etapas.add(new Etapa(50, 1,1, minutos, probabilidades));
        etapas.add(new Etapa(50, 2,2, minutos, probabilidades2));
        Sistema sistema = new Sistema(50, etapas, 10, minutos, probabilidades, 40);

        for (int i = 0; i < 10; i++) {
            sistema.mostrarDemandantes();
            sistema.ejecutarTransicion();
            System.out.println("\n Realizando la Iteracion "+ i+"\n \n");
            sistema.etapas.get(0).mostrarEtapa();
            sistema.etapas.get(1).mostrarEtapa();
        }*/


    }
}
