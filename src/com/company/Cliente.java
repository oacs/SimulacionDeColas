package com.company;

import java.util.ArrayList;

public class Cliente {
    public int identificador;
    public int tiempoDeLlegada;
    public int tiempoEnCola;
    public int tiempoEnServicio;

    public Cliente(int identificador) {
        this.identificador = identificador;
    }

    public Cliente(int identificador, int tiempoDeLlegada) {
        this.identificador = identificador;
        this.tiempoDeLlegada = tiempoDeLlegada;
    }
}
