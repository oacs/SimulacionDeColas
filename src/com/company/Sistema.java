/**
 * Sistema
 *
 * Se establecio el constructor y funcion para calcular tiempo de servicio
 *
 * 15/06/2018
 *
 * @author Oscar Castillejo
 * @author Antonio Cestari
 *
 * Copyright
 */
package com.company;

import java.util.ArrayList;

public class Sistema {
    public ArrayList < Etapa > etapas;
    public ArrayList < Cliente > demandantes;
    public GeneradorDeTiempos generadorTiemposLlegada;
    public ResultadosSimulacion resultadosSimulacion;
    public int clientesEnSistema;
    public int clientesPerdidos;
    public int tiempoActual;
    public int tiempoCierre;
    public int at, dt;
    public boolean end;
    public int capacidadDeClientes;
    public int dia;
    public ArrayList<Integer> Servidores;


    public Sistema(int capacidadDeClientes, ArrayList < Etapa > etapas, int cantidadClientes, ArrayList < Integer > minutos, ArrayList < Float > probabilidades, int tiempoCierre) {
        Servidores = new ArrayList<Integer>();
        /*for (int i = 0; i<etapas.size(); i++){
            Servidores.add(7);
        }*/
        for (Etapa etapa: etapas ) {
            Servidores.add(etapa.cantidadTotalDeServidores);
        }
        this.resultadosSimulacion = new ResultadosSimulacion(etapas.size(), Servidores);
        this.capacidadDeClientes = capacidadDeClientes;
        this.resultadosSimulacion.setVisible(true);
        this.etapas = etapas;
        this.generadorTiemposLlegada = new GeneradorDeTiempos(minutos, probabilidades);
        this.demandantes = new ArrayList < Cliente > ();
        for (int i = 0; i < cantidadClientes; i++) {
            this.demandantes.add(new Cliente(i + 1, 0));
        }
        dia = 0;
        this.tiempoCierre = tiempoCierre;
        inicializacion();
    }

    public void inicializacion() {
        this.clientesPerdidos = 0;
        this.tiempoActual = 0;
        this.clientesEnSistema = 0;
        this.end = false;
        this.at = 0;
        this.dt = 99999;
        for (Etapa etapa: this.etapas
             ) {
            etapa.inicializar();
        }
        /* SE HACE LLAMADA A LA FUNCION PARA INGRESAR UN ROW */
        // this.resultadosSimulacion.ingresarEstadistica (0,0, 5, 6, 9, 3, 9);
        this.resultadosSimulacion.ingresarEvento(dia, "Inicializacion del Sistema", 0, this.tiempoActual, 0, 0, this.tiempoActual + at + "", this.tiempoActual + dt + "");
    }
    /** calcularTiempoMenorEtapas - busca el tiempo menor para un proximo evento de salida
     *
     * @param tiempoMenor: se le pasa el tiempo que falta para que un cliente llegue
     *                   para tenerlo de referencia como menor
     * @return devuelve el tiempo menor para que salga un cliente o el parametro con en el que
     * se llamo en caso de ser menor
     */
    public int calcularTiempoMenorEtapas(int tiempoMenor) {
        for (Etapa etapa: etapas) {
            if (etapa.calcularTiempoProximoEvento() < tiempoMenor)
                tiempoMenor = etapa.calcularTiempoProximoEvento();
        }
        return tiempoMenor;
    }

    /**
     * SIMULACION
     */
    public void ejecutarTransicion() {
        // Se revisa si ya esta terminada la simulacion
        if (tiempoActual > tiempoCierre && clientesEnSistema <= 0) {
            System.out.println("Termino el sistema");
            this.end = true;
            for (Etapa etapa: this.etapas) {
                etapa.estadisticas();
            }

            return;
        }
        int tiempoMenor = 0;
        if (at <= dt) {
            tiempoMenor = at;
            int idTemporal = 0;
            if (demandantes.size() > 0) {
                idTemporal = demandantes.get(0).identificador;
                if (this.capacidadDeClientes > (this.etapas.get(0).clientesEnCola.size() + this.etapas.get(0).servidoresOcupados())) {
                    this.etapas.get(0).agregarCliente(this.demandantes.remove(0));
                    this.clientesEnSistema++;
                } else {
                    this.demandantes.remove(0);
                    this.clientesPerdidos++;
                }
            }
            if (demandantes.size() > 0)
                at = this.demandantes.get(0).tiempoDeLlegada = this.generadorTiemposLlegada.obtenerTiempo();
            else
                at = 99999;
            this.resultadosSimulacion.ingresarEvento(dia, "Llegada al Sistema", idTemporal, this.tiempoActual + tiempoMenor, this.etapas.get(0).servidoresOcupados(), this.etapas.get(0).clientesEnCola.size(), this.tiempoActual + at + "", this.tiempoActual + this.calcularTiempoMenorEtapas(99999) + "");
            // Evento llegada al Sistema
        } else {
            tiempoMenor = dt;
            if (demandantes.size() > 0) {
                this.demandantes.get(0).tiempoDeLlegada -= tiempoMenor;
            }
        }

        this.tiempoActual += tiempoMenor;

        for (Etapa etapa: this.etapas) {
            etapa.avanzarTiempo(tiempoMenor);

            for (Cliente cliente: etapa.sacarClientes()) {
                if (etapa.identificador == this.etapas.size()) {
                    // Evento salida del sistema
                    this.resultadosSimulacion.ingresarEvento(dia, "Salida del Sistema", cliente.identificador, this.tiempoActual, etapa.servidoresOcupados(), etapa.clientesEnCola.size(), this.tiempoActual + at + "", this.tiempoActual + dt + "");
                    this.demandantes.add(new Cliente(cliente.identificador, this.generadorTiemposLlegada.obtenerTiempo()));
                    this.clientesEnSistema--;
                } else {
                    // Evento salida de una etapa
                    if (this.capacidadDeClientes > (this.etapas.get(etapa.identificador).clientesEnCola.size() + this.etapas.get(etapa.identificador).servidoresOcupados())) {
                        this.resultadosSimulacion.ingresarEvento(dia, "Salida de etapa " + etapa.identificador, cliente.identificador, this.tiempoActual, etapa.servidoresOcupados(), etapa.clientesEnCola.size(), this.tiempoActual + at + "", this.tiempoActual + dt + "");
                        this.etapas.get(etapa.identificador).agregarCliente(new Cliente(cliente.identificador, this.generadorTiemposLlegada.obtenerTiempo()));
                    } else {
                        System.err.println("El sistema colapso");
                        // Todo evento de sistema colapsado
                        this.end = true;
                        return;
                    }
                }
            }
            etapa.servirClientes();
        }
        dt = calcularTiempoMenorEtapas(99999);
        if (tiempoActual > tiempoCierre)
            at = 99999;
    }

    public void mostrarDemandantes() {
        for (Cliente demandante: demandantes) {
            System.out.println(demandante.identificador + "- Tiempo de llegada: " + demandante.tiempoDeLlegada);
        }
    }

    public int getClientesAtendidos() {
        int clientesTotalesAtendidos = 0;
        for (Etapa etapa: this.etapas) {
            clientesTotalesAtendidos += etapa.getClientesAtendidos();
        }
        return clientesTotalesAtendidos;
    }

    public int getClientesEsperan() {
        int clientesEsperan = 0;
        for (Etapa etapa: this.etapas) {
            clientesEsperan += etapa.clientesConEspera;
        }
        return clientesEsperan;
    }

    public int getClientesSinEspera() {
        int clientesSinEspera = 0;
        for (Etapa etapa: this.etapas) {
            clientesSinEspera += etapa.clientesSinEspera;
        }
        return clientesSinEspera;
    }

    public float[] getPorcentajeDeUtilizacionEtapas() {
        float[] porcentajeDeUtilizacionEtapas = new float[this.etapas.size()];
        int i = 0;
        for (Etapa etapa: this.etapas) {
            porcentajeDeUtilizacionEtapas[i++] = ((float)(etapa.getClientesAtendidos())) / ((float) this.getClientesAtendidos());
        }
        return porcentajeDeUtilizacionEtapas;
    }

    public float getProbabilidadDeEsperar() {
        int clientesQueEsperan = 0;
        for (Etapa etapa: this.etapas) {
            clientesQueEsperan += etapa.clientesConEspera;
        }
        return ((float) clientesQueEsperan / (float) this.getClientesAtendidos());
    }

    public float getTiempoPromedioEnCola() {
        float promedioEnColaEtapas = 0;
        for (Etapa etapa: this.etapas) {
            promedioEnColaEtapas += etapa.getTiempoPromedioEnCola();
        }
        return promedioEnColaEtapas / this.etapas.size();
    }
    public float getTiempoPromedioEnSistema() {
        float promedioEnColaEtapas = 0;
        for (Etapa etapa: this.etapas) {
            promedioEnColaEtapas += etapa.getTiempoPromedioEnServicio();
        }
        return promedioEnColaEtapas / this.etapas.size();
    }

    public float getTiempoPromedioClienteHaceCola() {
        float tiempoPromedioColaEtapas = 0;
        for (Etapa etapa: this.etapas) {
            tiempoPromedioColaEtapas += etapa.getTiempoPromedioClienteHaceCola();
        }
        return tiempoPromedioColaEtapas / this.etapas.size();
    }

    public void estadisticas() {
        if (this.tiempoActual < this.tiempoCierre) {
            System.err.println("El sistema no logro mantenerse estable");
        }
        System.out.println("\nSistema: ");
        System.out.println("Clientes Atendidos: " + this.getClientesAtendidos());
        System.out.println("Cantidad de Clientes que No Esperan: " + this.getClientesSinEspera());
        System.out.println("Cantidad de Clientes que Esperan: " + this.getClientesEsperan());
        float[] porcentajesDeUtilizacion = this.getPorcentajeDeUtilizacionEtapas();
        for (int i = 0; i < this.etapas.size(); i++) {
            System.out.println("Porcentaje de utilizacion en Etapa " + i + ":" + porcentajesDeUtilizacion[i]);
        }
        System.out.println("Probabilidad de Esperar: " + this.getProbabilidadDeEsperar());
        System.out.println("Tiempo Promedio Cliente en Cola: " + this.getTiempoPromedioEnCola());
        System.out.println("Tiempo Promedio Cliente en Cola: " + this.getTiempoPromedioEnSistema());
        System.out.println("Tiempo de Espera de un Cliente que hace Cola: " + this.getTiempoPromedioClienteHaceCola());
        float promedioEnCola = 0;
        float promedioEnServicio = 0;
        for (int i = 0; i < this.etapas.size(); i++) {
            System.out.println("Promedio de clientes en cola en Etapa " + (i + 1) + ":  " + ((float)(this.etapas.get(i).totalCantidadClientesEspera) / ((float)(this.tiempoActual))));
            //System.out.println("Promedio de clientes en cola en Etapa " + i + ":" + this.etapas.get(i).totalCantidadClientesEspera + "/" +this.tiempoActual );
            promedioEnCola += ((float)(this.etapas.get(i).totalCantidadClientesEspera) / ((float)(this.tiempoActual)));
        }
        for (int i = 0; i < this.etapas.size(); i++) {
            System.out.println("Promedio de clientes en servicio en Etapa " + (i + 1) + ":  " + ((float)(this.etapas.get(i).totalCantidadClientesServicio) / ((float)(this.tiempoActual))));
            //System.out.println("Promedio de clientes en cola en Etapa " + i + ":" + this.etapas.get(i).totalCantidadClientesEspera + "/" +this.tiempoActual );
            promedioEnServicio += ((float)(this.etapas.get(i).totalCantidadClientesServicio) / ((float)(this.tiempoActual)));
        }
        System.out.println("Clientes que se van sin ser atendidos: " + this.clientesPerdidos);
        this.resultadosSimulacion.actualizarEstadisticas(0, dia, getClientesSinEspera(), clientesPerdidos, getProbabilidadDeEsperar(), getTiempoPromedioEnCola(), (this.getTiempoPromedioEnCola() + this.getTiempoPromedioEnSistema()), promedioEnCola,  (promedioEnCola + promedioEnServicio), this.getTiempoPromedioClienteHaceCola(), (this.tiempoActual - this.tiempoCierre));
        int i = 1;
        for( Etapa etapa: this.etapas) {
            this.resultadosSimulacion.actualizarEstadisticas(i, dia,
                    etapa.clientesSinEspera, 0, etapa.getProbabilidadDeEsperar(),
                    etapa.getTiempoPromedioEnCola(), (etapa.getTiempoPromedioEnCola() + etapa.getTiempoPromedioEnServicio()),
                    ((float)(etapa.totalCantidadClientesEspera) / ((float)(this.tiempoActual))),  (((float)(etapa.totalCantidadClientesEspera) / ((float)(this.tiempoActual))) + ((float)(etapa.totalCantidadClientesServicio) / ((float)(this.tiempoActual)))), etapa.getTiempoPromedioClienteHaceCola(),
                    (this.tiempoActual - this.tiempoCierre));
            float [] buffer  = etapa.getPorcentajeDeUtilizacionEtapas();
            for (int j = 0; j < buffer.length; j++) {
                // System.err.println(buffer[j]);
                this.resultadosSimulacion.actualizarPorcentajes(i-1, j, this.dia, buffer[j]);
            }
            i++;
        }
    }
}