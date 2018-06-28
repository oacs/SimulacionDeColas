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
    public boolean end;



    public Sistema(ArrayList<Etapa> etapas, int cantidadClientes, ArrayList<Integer> minutos, ArrayList<Float> probabilidades, int tiempoCierre) {
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
        this.resultadosSimulacion = new ResultadosSimulacion();
        this.resultadosSimulacion.setVisible(true);
        this.tiempoCierre = tiempoCierre;
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
     *
     */
    public void ejecutarTransicion(){
        if(this.tiempoActual >= this.tiempoCierre && clientesEnSistema <= 0) {
            System.out.println("Se acabo la simulacion");
            this.end = true;
            return;
        }
        if(this.tiempoActual >= this.tiempoCierre) {
            demandantes.clear();
        }
        if((demandantes.size()>0 || clientesEnSistema > 0) ||  this.tiempoActual < this.tiempoCierre) {
            int tiempoMenor;

            if (demandantes.size() > 0 && this.tiempoActual < this.tiempoCierre) {
                tiempoMenor = demandantes.get(0).tiempoDeLlegada;
                tiempoMenor = calcularTiempoMenorEtapas(tiempoMenor);
                if (tiempoMenor < demandantes.get(0).tiempoDeLlegada) {
                    demandantes.get(0).tiempoDeLlegada -= tiempoMenor;
                } else {
                    tiempoMenor = demandantes.get(0).tiempoDeLlegada;
                    etapas.get(0).clientesEnCola.add(demandantes.get(0)); // Cliente encolado
                    if( etapas.get(0).servidoresOcupados() < etapas.get(0).cantidadTotalDeServidores) {
                        etapas.get(0).servirClientes(); // Cliente en servicio

                        for (Servidor servidor: etapas.get(0).clientesEnServicio) {
                            if( servidor.clienteEnServicio != null && servidor.clienteEnServicio.identificador == demandantes.get(0).identificador) {
                                this.clientesSinEspera++;
                                this.clientesEnSistema++;
                                demandantes.remove(0);
                                this.manejarEventoLlegada(servidor.clienteEnServicio, tiempoMenor);


                            }
                        }
                    } else {
                        this.clientesEnSistema++;
                        int idCliente = demandantes.get(0).identificador;
                        demandantes.remove(0);
                        if (demandantes.size() > 0) {
                            demandantes.get(0).tiempoDeLlegada = generadorTiemposLlegada.obtenerTiempo();
                            this.resultadosSimulacion.ingresarEvento(
                                    "Llegada C", idCliente,
                                    this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                                    etapas.get(0).clientesEnCola.get(0).tiempoEnCola, demandantes.get(0).tiempoDeLlegada+"",
                                    etapas.get(0).calcularTiempoProximoEvento()+""
                            );
                        } else {
                            this.resultadosSimulacion.ingresarEvento(
                                    "Llegada C", idCliente,
                                    this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                                    etapas.get(0).clientesEnCola.get(0).tiempoEnCola, "---",
                                    etapas.get(0).calcularTiempoProximoEvento()+""
                            );
                        }
                    }
                }
            } else {
                tiempoMenor = calcularTiempoMenorEtapas(99999);
            }


            tiempoActual += tiempoMenor;

            int index = 0;
            for (Etapa etapa : etapas) {
                etapa.avanzarTiempo(tiempoMenor);
                if (index + 1 < etapas.size()) {
                    for (Cliente clientesServidos : etapa.sacarClientes()) {
                        etapas.get(index + 1).clientesEnCola.add(clientesServidos);
                        if (demandantes.size() > 0) {
                            demandantes.get(0).tiempoDeLlegada = generadorTiemposLlegada.obtenerTiempo();
                            if( etapa.clientesEnCola.size()>0){
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida"+ etapa.identificador, clientesServidos.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        etapa.clientesEnCola.get(0).tiempoEnCola, demandantes.get(0).tiempoDeLlegada+"",
                                        clientesServidos.tiempoEnServicio+""
                                );
                            } else {
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida"+ etapa.identificador, clientesServidos.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        0, demandantes.get(0).tiempoDeLlegada+"",
                                        clientesServidos.tiempoEnServicio+""
                                );

                            }
                        } else {
                            if( etapa.clientesEnCola.size()>0){
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida"+ etapa.identificador, clientesServidos.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        etapa.clientesEnCola.get(0).tiempoEnCola, "---",
                                        clientesServidos.tiempoEnServicio+""
                                );
                            } else {
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida"+ etapa.identificador, clientesServidos.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        0, "---",
                                        clientesServidos.tiempoEnServicio+""
                                );

                            }

                        }
                    }
                } else {
                    for (Cliente cliente : etapa.sacarClientes()) {
                        System.out.println("Evento salida del sistema del cliente " + cliente.identificador);
                        this.clientesEnSistema--;
                        if (this.tiempoActual < this.tiempoCierre) {
                            demandantes.add(new Cliente(cliente.identificador, 0));
                            if(demandantes.size()==1){
                                demandantes.get(0).tiempoDeLlegada = generadorTiemposLlegada.obtenerTiempo();
                            }
                        }
                        if (demandantes.size() > 0) {
                            demandantes.get(0).tiempoDeLlegada = generadorTiemposLlegada.obtenerTiempo();
                            if( etapa.clientesEnCola.size()>0){
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida "+ etapa.identificador, cliente.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        etapa.clientesEnCola.get(0).tiempoEnCola, demandantes.get(0).tiempoDeLlegada+"",
                                        cliente.tiempoEnServicio+""
                                );
                            } else {
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida "+ etapa.identificador, cliente.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        0, demandantes.get(0).tiempoDeLlegada+"",
                                        cliente.tiempoEnServicio+""
                                );

                            }
                        } else {
                            if( etapas.get(0).clientesEnCola.size()>0 && etapa.clientesEnCola.size() >0){
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida "+ etapa.identificador, cliente.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        etapa.clientesEnCola.get(0).tiempoEnCola, "---",
                                        cliente.tiempoEnServicio+""
                                );
                            } else {
                                this.resultadosSimulacion.ingresarEvento(
                                        "Salida "+ etapa.identificador, cliente.identificador,
                                        this.tiempoActual, etapa.servidoresOcupados(),
                                        0, "---",
                                        cliente.tiempoEnServicio+""
                                );

                            }

                        }

                    }
                }
                etapa.servirClientes();
                index++;
            }
        }
    }

    public void mostrarDemandantes() {
        for( Cliente demandante: demandantes){
            System.out.println(demandante.identificador + "- Tiempo de llegada: " + demandante.tiempoDeLlegada);
        }
    }

    public void manejarEventoLlegada(Cliente cliente, int tiempoMenor){
        if (demandantes.size() > 0) {
            demandantes.get(0).tiempoDeLlegada = generadorTiemposLlegada.obtenerTiempo();
            if( etapas.get(0).clientesEnCola.size()>0){
                this.resultadosSimulacion.ingresarEvento(
                        "Llegada al Sistema", cliente.identificador,
                        this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                        etapas.get(0).clientesEnCola.get(0).tiempoEnCola, demandantes.get(0).tiempoDeLlegada+"",
                        cliente.tiempoEnServicio+""
                );
            } else {
                this.resultadosSimulacion.ingresarEvento(
                        "Llegada al Sistema", cliente.identificador,
                        this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                        0, demandantes.get(0).tiempoDeLlegada+"",
                        cliente.tiempoEnServicio+""
                );

            }
        } else {
            if( etapas.get(0).clientesEnCola.size()>0){
                this.resultadosSimulacion.ingresarEvento(
                        "Llegada al Sistema", cliente.identificador,
                        this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                        etapas.get(0).clientesEnCola.get(0).tiempoEnCola, "---",
                        cliente.tiempoEnServicio+""
                );
            } else {
                this.resultadosSimulacion.ingresarEvento(
                        "Llegada al Sistema", cliente.identificador,
                        this.tiempoActual+tiempoMenor, etapas.get(0).servidoresOcupados(),
                        0, "---",
                        cliente.tiempoEnServicio+""
                );

            }

        }

    }
}
