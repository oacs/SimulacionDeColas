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
    public ArrayList<Etapa> etapas;
    public ArrayList<Cliente> demandantes;
    public GeneradorDeTiempos generadorTiemposLlegada;
    public ResultadosSimulacion resultadosSimulacion;
    public int clientesEnSistema;
    public int clientesSinEspera;
    public int tiempoActual;
    public int tiempoCierre;
    public int at, dt;
    public boolean end;



    public Sistema(ArrayList<Etapa> etapas, int cantidadClientes, ArrayList<Integer> minutos, ArrayList<Float> probabilidades, int tiempoCierre) {
        this.resultadosSimulacion = new ResultadosSimulacion(etapas.size());
        this.resultadosSimulacion.setVisible(true);
        this.etapas = etapas;
        this.generadorTiemposLlegada = new GeneradorDeTiempos(minutos, probabilidades);
        this.demandantes = new ArrayList<Cliente>();
        for( int i = 0; i < cantidadClientes; i++ ){
            this.demandantes.add(new Cliente(i+1, 0));
        }
        this.tiempoActual = 0;
        this.clientesSinEspera = 0;
        this.clientesEnSistema=0;
        this.end=false;
        this.at= 0;
        this.dt = 99999;
        this.tiempoCierre = tiempoCierre;
        /* SE HACE LLAMADA A LA FUNCION PARA INGRESAR UN ROW */
        // this.resultadosSimulacion.ingresarEstadistica (0,0, 5, 6, 9, 3, 9);
        this.resultadosSimulacion.ingresarEvento("Inicializacion del Sistema", 0, this.tiempoActual, 0, 0, this.tiempoActual+ at+"", this.tiempoActual+dt+"");
    }

    /** calcularTiempoMenorEtapas - busca el tiempo menor para un proximo evento de salida
     *
     * @param tiempoMenor: se le pasa el tiempo que falta para que un cliente llegue
     *                   para tenerlo de referencia como menor
     * @return devuelve el tiempo menor para que salga un cliente o el parametro con en el que
     * se llamo en caso de ser menor
     */
    public int calcularTiempoMenorEtapas( int tiempoMenor) {
        for ( Etapa etapa: etapas) {
            if (etapa.calcularTiempoProximoEvento() < tiempoMenor)
                tiempoMenor = etapa.calcularTiempoProximoEvento();
        }
        return  tiempoMenor;
    }

    /**
     * SIMULACION
     */
    public void ejecutarTransicion(){
        if( tiempoActual > tiempoCierre && clientesEnSistema<=0){
            System.out.println("Termino el sistema");
            this.end = true;
            for ( Etapa etapa: etapas) {
                etapa.estadisticas();
            }

            return;
        }
            int tiempoMenor = 0;
            if ( at <= dt) {
                tiempoMenor = at;
                int idTemporal = 0;
                if( demandantes.size()>0){
                    idTemporal = demandantes.get(0).identificador;
                    this.etapas.get(0).agregarCliente(this.demandantes.remove(0));
                    this.clientesEnSistema++;
                }
                if( demandantes.size()>0)
                    at = this.demandantes.get(0).tiempoDeLlegada = this.generadorTiemposLlegada.obtenerTiempo();
                else
                    at = 99999;
                this.resultadosSimulacion.ingresarEvento("Llegada al Sistema", idTemporal, this.tiempoActual+tiempoMenor, this.etapas.get(0).servidoresOcupados(), this.etapas.get(0).clientesEnCola.size(), this.tiempoActual+ at+"", this.tiempoActual+this.calcularTiempoMenorEtapas(99999)+"");
                // Evento llegada al Sistema
            } else {
                tiempoMenor = dt;
                if( demandantes.size() > 0){
                    this.demandantes.get(0).tiempoDeLlegada-=tiempoMenor;
                }
            }

            this.tiempoActual+= tiempoMenor;

            for ( Etapa etapa: this.etapas) {
                etapa.avanzarTiempo(tiempoMenor);

                for (Cliente cliente: etapa.sacarClientes()){
                    if( etapa.identificador == this.etapas.size()){
                        // Evento salida del sistema
                        this.resultadosSimulacion.ingresarEvento("Salida del Sistema", cliente.identificador, this.tiempoActual, etapa.servidoresOcupados(), etapa.clientesEnCola.size(), this.tiempoActual+ at+"", this.tiempoActual+dt+"");
                        this.demandantes.add(new Cliente(cliente.identificador, this.generadorTiemposLlegada.obtenerTiempo()));
                        this.clientesEnSistema--;
                    } else {
                        // Evento salida de una etapa
                        this.resultadosSimulacion.ingresarEvento("Salida de etapa "+etapa.identificador, cliente.identificador, this.tiempoActual, etapa.servidoresOcupados(), etapa.clientesEnCola.size(), this.tiempoActual+ at+"", this.tiempoActual+dt+"");
                        this.etapas.get(etapa.identificador).agregarCliente(new Cliente(cliente.identificador, this.generadorTiemposLlegada.obtenerTiempo()));
                    }
                }
                etapa.servirClientes();
            }
            dt = calcularTiempoMenorEtapas(99999);
            if( tiempoActual > tiempoCierre)
                at = 99999;
    }

    public void mostrarDemandantes() {
        for( Cliente demandante: demandantes){
            System.out.println(demandante.identificador + "- Tiempo de llegada: " + demandante.tiempoDeLlegada);
        }
    }


}
