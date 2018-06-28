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
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * La clase etapaa es controlada e inicializada por la clase Sistema
 * su utilidad es que lleva el control de los clientes mientras se
 * encuentran en alguna etapa correspondiente al sistema
 */
public class Etapa {

    public int identificador;                       // Numero de la Etapa
    public int cantidadTotalDeServidores;           // Cantidad de servidores en paralelo
    public ArrayList<Cliente> clientesEnCola;       // Clientes que se encuentran en cola
    public ArrayList<Cliente> clientesEnServicio;   // Clientes que se encuentran en algun servidor
    public ArrayList<Cliente> clientesAtendidos;    // Clientes que ya fueron atendidos
    public float tiempoPromedioEnServicio;          // Tiempo promedio de un cliente en servicio
    public float tiempoPromedioEnCola;              // Tiempo promedio de un cliente en cola
    private float totalTiemposEnServicio;             // Suma de todos los tiempos de servicio
    private float totalTiemposEnCola;                 // Suma de todos los tiempos de cola
    private GeneradorDeTiempos generadorTiemposServicio; // Genera los tiempos de servicio de los clientes

    /** Constructor de Etapa
     * @param cantidadTotalDeServidores: Cantidad de servidores en paralelo
     *                                 que posee la etapa.
     * @param identificador: Numero de la etapa.
     */
    public Etapa(int cantidadTotalDeServidores, int identificador, ArrayList<Integer> minutos, ArrayList<Float> probabilidades) {
        this.cantidadTotalDeServidores = cantidadTotalDeServidores;
        this.identificador = identificador;
        this.clientesEnCola = new ArrayList<Cliente>();
        this.clientesEnServicio = new ArrayList<Cliente>();
        this.clientesAtendidos = new ArrayList<Cliente>();
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
        for (Cliente cliente: clientesEnServicio){
            if(tiempoMenorParaProximoEvento > cliente.tiempoEnServicio) {
                tiempoMenorParaProximoEvento = cliente.tiempoEnServicio;
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
    public void avanzarTiempo(int tiempoTranscurrido){
        aumentarTiemposDeCola(tiempoTranscurrido);
        disminuirTiemposEnServicio(tiempoTranscurrido);
    }

    /** aumentarTiemposDeCola - Le agrega el tiempo transcurrido al tiempo que llevan
     * esperando los clientes en cola
     *
     * @param tiempoTranscurrido: unidades de tiempo a aumentar dentro de los clientes en cola
     */
    private void aumentarTiemposDeCola(int tiempoTranscurrido) {
        for( Cliente cliente: clientesEnCola) {
            cliente.tiempoEnCola += tiempoTranscurrido;
        }
    }

    /** DisminuirTiemposEnServicio - Le sustrae el tiempo transcurrido al tiempo restante
     * a los clientes en servicio
     *
     * @param tiempoTranscurrido: unidades de tiempo a disminuir dentro de los clientes en servicio
     */
    private void disminuirTiemposEnServicio(int tiempoTranscurrido) {
        for( Cliente cliente: clientesEnServicio) {
            cliente.tiempoEnServicio -= tiempoTranscurrido;
            totalTiemposEnServicio+= tiempoTranscurrido;
        }
    }

    /** sacarClientes - toma todos los clientes que su tiempo de servicio culmino y los saca de los
     * servidores
     *
     * @return Devuelve una lista con todos los clientes que deben pasar a otra etapa
     */
    public ArrayList<Cliente> sacarClientes() {
        ArrayList<Cliente> clientesSaliendo = new ArrayList<Cliente>();
        for( Cliente cliente: clientesEnServicio) {
            if( cliente.tiempoEnServicio == 0){
                this.clientesAtendidos.add(cliente);
                clientesSaliendo.add(new Cliente(cliente.identificador));
            }
        }
        this.clientesEnServicio.removeIf(new Predicate<Cliente>() {
            @Override
            public boolean test(Cliente cliente) {
                if(cliente.tiempoEnServicio == 0) {
                    return true;
                }
                return false;
            }
        });
        return clientesSaliendo;
    }

    /** servirClientes - Agrega los clientes que estaban
     *
     * Desencola a los clientes que puedan entrar si alguno/algunos de los servidores esta disponible
     *
     */
    public void servirClientes() {
        while( (this.clientesEnServicio.size() < this.cantidadTotalDeServidores) && (clientesEnCola.size()>0)) {
            this.clientesEnCola.get(0).tiempoEnServicio = generadorTiemposServicio.obtenerTiempo();
            this.clientesEnServicio.add(this.clientesEnCola.get(0));
            this.clientesEnCola.remove(0);
        }
    }

    public void mostrarEtapa() {
        System.out.println("Etapa " + this.identificador);
        System.out.print("Clientes en cola: ");
        for ( Cliente cliente: clientesEnCola) {
            System.out.print(cliente.identificador + "<- ");
        }
        System.out.println("\n");
        System.out.print("Clientes en servicio: ");
        for ( Cliente cliente: clientesEnServicio) {
            System.out.print(cliente.identificador + "(" + cliente.tiempoEnServicio + "), " );
        }
        System.out.println("\n");
    }

    public float getTiempoPromedioEnServicio(){
        return  totalTiemposEnServicio/clientesAtendidos.size();
    }
    public float getTiempoPromedioEnCola(){
        float tiempoPromedioEnCola=0f;
        for(Cliente cliente: clientesAtendidos) {
           tiempoPromedioEnCola+= cliente.tiempoEnCola;
        }
        tiempoPromedioEnCola /= clientesAtendidos.size();
        System.out.println(tiempoPromedioEnCola);
        return  tiempoPromedioEnCola;
    }


}
