package com.company;

import java.util.ArrayList;

public class Servidor {
    public Cliente clienteEnServicio;      // Clientes que se encuentran en algun servidor
    public int clientesAtendidos;           // Clientes que ya fueron atendidos

    public float getPorcentajeDeUtilizacion ( float acum ) {
        return (this.clientesAtendidos/acum);
    }
}