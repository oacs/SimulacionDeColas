/**
 * Etapa
 *
 * Se establecio el constructor y funcion para calcular tiempo menor de servicio
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
import java.util.function.Predicate;

/**
 * La clase etapaa es controlada e inicializada por la clase Sistema
 * su utilidad es que lleva el control de los clientes mientras se
 * encuentran en alguna etapa correspondiente al sistema
 */
public class Etapa {

    public int identificador; // Numero de la Etapa
    public int cantidadTotalDeServidores; // Cantidad de servidores en paralelo
    public ArrayList < Cliente > clientesEnCola; // Clientes que se encuentran en cola
    public Servidor clientesEnServicio[]; // Clientes que se encuentran en algun servidor
    // public ArrayList<Cliente> clientesAtendidos;            // Clientes que ya fueron atendidos
    public float tiempoPromedioEnServicio; // Tiempo promedio de un cliente en servicio
    public float tiempoPromedioEnCola; // Tiempo promedio de un cliente en cola
    private float totalTiemposEnServicio; // Suma de todos los tiempos de servicio
    private float totalTiemposEnCola; // Suma de todos los tiempos de cola
    private GeneradorDeTiempos generadorTiemposServicio; // Genera los tiempos de servicio de los clientes
    public int clientesSinEspera = 0;
    public int clientesConEspera = 0;
    public double probEsperar; // Probabilidad de esperar.
    public double promCola; // Promedio de un cliente en cola.
    public int totalCantidadClientesEspera = 0;
    public int totalCantidadClientesServicio = 0;


    /** Constructor de Etapa
     * @param cantidadTotalDeServidores: Cantidad de servidores en paralelo que posee la etapa.
     * @param identificador: Numero de la etapa.
     */
    public Etapa(int cantidadTotalDeServidores, int identificador, ArrayList < Integer > minutos, ArrayList < Float > probabilidades) {
        this.cantidadTotalDeServidores = cantidadTotalDeServidores;
        this.identificador = identificador;
        this.clientesEnCola = new ArrayList < Cliente > ();
        this.clientesEnServicio = new Servidor[cantidadTotalDeServidores];
        for (int i = 0; i < cantidadTotalDeServidores; i++) {
            this.clientesEnServicio[i] = new Servidor();
        }
        // this.clientesAtendidos = new ArrayList<Cliente>();
        this.tiempoPromedioEnCola = 0;
        this.tiempoPromedioEnServicio = 0;
        this.totalTiemposEnCola = 0;
        this.totalTiemposEnServicio = 0;
        this.generadorTiemposServicio = new GeneradorDeTiempos(minutos, probabilidades);
    }

    /**  calcularTiempoProximoEvento - Busca el menor tiempo restante para
     *   culminar un servicio.
     *
     *   Devuelve el menor tiempo para que ocurra un evento
     *   de culminacion de servicio, revisa cada uno de los
     *   clientes almacenados en clientesEnServicio
     *   y los compara para saber el menor tiempo restante
     *   de los clientes en servicio para salir de la etapa.
     *
     * @return tiempo menor para que un cliente salga de servicio
     */
    public int calcularTiempoProximoEvento() {
        int tiempoMenorParaProximoEvento = 99999;
        for (Servidor servidor: clientesEnServicio) {
            if (servidor.clienteEnServicio != null && tiempoMenorParaProximoEvento > servidor.clienteEnServicio.tiempoEnServicio) {
                tiempoMenorParaProximoEvento = servidor.clienteEnServicio.tiempoEnServicio;
            }
        }
        return tiempoMenorParaProximoEvento;
    }

    /** avanzarTiempo - transcurre tantas unidades de tiempo como se le
     * indique
     *
     * Hace llamada dos funciones que ajustan los tiempos de los clientes en cola
     * y los clientes que estan siendo atendidos
     *
     * @param tiempoTranscurrido: unidades de tiempo a transcurrir dentro de la etapa
     */
    public void avanzarTiempo(int tiempoTranscurrido) {
        aumentarTiemposDeCola(tiempoTranscurrido);
        disminuirTiemposEnServicio(tiempoTranscurrido);
    }

    /** aumentarTiemposDeCola - Le agrega el tiempo transcurrido al tiempo que llevan
     * esperando los clientes en cola
     *
     * @param tiempoTranscurrido: unidades de tiempo a aumentar dentro de los clientes en cola
     */
    private void aumentarTiemposDeCola(int tiempoTranscurrido) {
        for (Cliente cliente: clientesEnCola) {
            cliente.tiempoEnCola += tiempoTranscurrido;
            totalCantidadClientesEspera += tiempoTranscurrido * clientesEnCola.size();
        }
    }

    /** DisminuirTiemposEnServicio - Le sustrae el tiempo transcurrido al tiempo restante
     * a los clientes en servicio
     *
     * @param tiempoTranscurrido: unidades de tiempo a disminuir dentro de los clientes en servicio
     */
    private void disminuirTiemposEnServicio(int tiempoTranscurrido) {
        for (Servidor servidor: clientesEnServicio) {
            if (servidor.clienteEnServicio != null) {
                servidor.clienteEnServicio.tiempoEnServicio -= tiempoTranscurrido;
                totalTiemposEnServicio += tiempoTranscurrido;
                totalCantidadClientesServicio += tiempoTranscurrido * servidoresOcupados();
            }

        }
    }

    /** sacarClientes - toma todos los clientes que su tiempo de servicio culmino y los saca de los
     * servidores
     *
     * @return Devuelve una lista con todos los clientes que deben pasar a otra etapa
     */
    public ArrayList < Cliente > sacarClientes() {
        ArrayList < Cliente > clientesSaliendo = new ArrayList < Cliente > ();
        for (Servidor servidor: clientesEnServicio) {
            if (servidor.clienteEnServicio != null && servidor.clienteEnServicio.tiempoEnServicio == 0) {
                // this.clientesAtendidos.add(servidor.clienteEnServicio);
                servidor.clientesAtendidos++;
                clientesSaliendo.add(new Cliente(servidor.clienteEnServicio.identificador));
            }
        }
        for (int i = 0; i < this.clientesEnServicio.length; i++) {
            if (this.clientesEnServicio[i].clienteEnServicio != null && this.clientesEnServicio[i].clienteEnServicio.tiempoEnServicio == 0) {
                this.clientesEnServicio[i].clienteEnServicio = null;
            }
        }
        return clientesSaliendo;
    }

    /** servirClientes - Agrega los clientes que estaban
     *
     * Desencola a los clientes que puedan entrar si alguno/algunos de los servidores esta disponible
     *
     */
    public void servirClientes() {
        while ((servidoresDisponibles() > 0) && (clientesEnCola.size() > 0)) {
            this.clientesEnCola.get(0).tiempoEnServicio = generadorTiemposServicio.obtenerTiempo();
            for (Servidor servidor: this.clientesEnServicio) {
                if (servidor.clienteEnServicio == null) {
                    servidor.clienteEnServicio = this.clientesEnCola.get(0);
                    this.totalTiemposEnCola += this.clientesEnCola.get(0).tiempoEnCola;
                    break;
                }
            }
            this.clientesEnCola.remove(0);
        }
    }

    /** mostrarEtapa - imprime por consola el estado actual de los clientes
     */
    public void mostrarEtapa() {
        System.out.println("Etapa " + this.identificador);
        System.out.print("Clientes en cola: ");
        for (Cliente cliente: clientesEnCola) {
            System.out.print(cliente.identificador + "<- ");
        }
        System.out.println("\n");
        System.out.print("Clientes en servicio: ");
        for (Servidor servidor: clientesEnServicio) {
            if (servidor.clienteEnServicio != null) {
                System.out.print(servidor.clienteEnServicio.identificador + "(" + servidor.clienteEnServicio.tiempoEnServicio + "), ");
            }
        }
        System.out.println("\n");
    }

    /** servirClientes - Agrega los clientes que estaban
     *
     * Desencola a los clientes que puedan entrar si alguno/algunos de los servidores esta disponible
     *
     */
    /* public float getTiempoPromedioEnServicio(){
        return  totalTiemposEnServicio/clientesAtendidos.size();
    }*/

    /** getTiempoPromedioEnCola - Agrega los clientes que estaba
     *
     */
    /* public float getTiempoPromedioEnCola(){
         float tiempoPromedioEnCola=0f;
         for(Cliente cliente: clientesAtendidos) {
            tiempoPromedioEnCola+= cliente.tiempoEnCola;
         }
         tiempoPromedioEnCola /= clientesAtendidos.size();
         System.out.println(tiempoPromedioEnCola);
         return  tiempoPromedioEnCola;
     }*/

    public int servidoresDisponibles() {
        int contador = 0;
        for (Servidor servidor: this.clientesEnServicio) {
            if (servidor.clienteEnServicio == null) {
                contador++;
            }
        }
        return contador;
    }

    public int servidoresOcupados() {
        int contador = 0;
        for (Servidor servidor: this.clientesEnServicio) {
            if (servidor.clienteEnServicio != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     *
     * @param cliente
     * @return 1= En servicio, 0= en cola
     */
    public int agregarCliente(Cliente cliente) {
        if (servidoresDisponibles() > 0) {
            for (Servidor servidor: this.clientesEnServicio) {
                if (servidor.clienteEnServicio == null) {
                    servidor.clienteEnServicio = cliente;
                    servidor.clienteEnServicio.tiempoEnServicio = this.generadorTiemposServicio.obtenerTiempo();
                    // System.out.println(cliente.tiempoEnCola);
                    if (cliente.tiempoEnCola == 0) {
                        this.clientesSinEspera += 1;
                    }
                    return 1;
                }
            }
        }
        this.clientesConEspera += 1;
        this.clientesEnCola.add(cliente);
        return 0;
    }

    public int getClientesAtendidos() {
        return  this.clientesConEspera + this.clientesSinEspera;
    }

    public float[] getPorcentajeDeUtilizacionEtapas() {
        float[] porcentajeUtilizacionEtapas = new float[this.cantidadTotalDeServidores];
        int i = 0;
        for (Servidor servidor: this.clientesEnServicio) {
            porcentajeUtilizacionEtapas[i++] = servidor.getPorcentajeDeUtilizacion(this.getClientesAtendidos());
        }
        return porcentajeUtilizacionEtapas;
    }

    public float getProbabilidadDeEsperar() {
        return  (float)(this.clientesConEspera / this.getClientesAtendidos());
    }

    public float getTiempoPromedioEnCola() {
        return (float)(this.totalTiemposEnCola/this.getClientesAtendidos());
    }

    public float getTiempoPromedioClienteHaceCola() {
        return (float)((this.clientesConEspera > 0) ? (this.totalTiemposEnCola / this.clientesConEspera) : 0);
    }
    
    /**
     * imprime estadisticas del sistema.
     */
    public void estadisticas() {
        double acum = this.clientesConEspera + this.clientesSinEspera;
        System.out.println("\nEtapa: " + this.identificador);
        System.out.println("Clientes Atendidos: " + acum);
        System.out.println("Cantidad de Clientes que No Esperan: " + this.clientesSinEspera);
        System.out.println("Cantidad de Clientes que Esperan: " + this.clientesConEspera);
        // System.out.println("totalTiemposEnCola: " + this.totalTiemposEnCola);
        System.out.println("Porcentaje de utilizacion en Etapa " + this.identificador + ":\n");
        int i = 1;
        for (Servidor servidor: this.clientesEnServicio) {
            // acum+=servidor.clientesAtendidos;
           /* System.out.println(
                "\tPorcentaje de utilizacion en Etapa "+ i + ": " + String.format("%2.f", servidor.getPorcentajeDeUtilizacion(acum)*100) + "\n"
            );*/
            System.out.printf("\tPorcentaje de utilizacion en Etapa %d: %.2f\n", i, servidor.getPorcentajeDeUtilizacion((float)acum)*100 );
        }
        double ax = (this.clientesConEspera / acum);
        this.probEsperar = (this.clientesConEspera / acum);
        System.out.println("Probabilidad de Esperar: " + this.probEsperar);
        this.promCola = this.totalTiemposEnCola / acum;
        System.out.println("Tiempo Promedio Cliente en Cola: " + this.promCola);
        ax = (this.clientesConEspera > 0) ? (this.totalTiemposEnCola / this.clientesConEspera) : 0;
        System.out.println("Tiempo de Espera de un Cliente que hace Cola: " + ax);
    }

}