/**
 * TiemposDeServicio
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
import java.util.Random;

/**
 * La clase TiemposDeServicio lleva el manejo de los tiempos de servicio
 * con sus respectivas proabilidades y seleccion.
 */
public class GeneradorDeTiempos {
    private ArrayList<Integer> minutos;
    private ArrayList<Float> probabilidades;
    private Random aleatorio;

    /** Constructor de TiemposDeServicio
     * @param minutos: minutos correspondientes al tiempo de servicio
     * @param probabilidades: probabilidad de que se escoja el tiempo de servicio
     */
    public GeneradorDeTiempos(ArrayList<Integer> minutos, ArrayList<Float> probabilidades) {
        this.minutos = minutos;
        this.probabilidades = probabilidades;
        this.aleatorio = new Random(System.currentTimeMillis());
    }


    /** obtenerTiempo - random que escoge los minutos de servicio
     *
     * @return Devuelve los minutos que dura un cliente en servicio
     */
    public int obtenerTiempo() {
        float probabilidad = this.aleatorio.nextFloat();
        float probabilidadTotal = 0;
        int indice = 0;
        for ( Float probabilidadTiempo: probabilidades ){
            probabilidadTotal += probabilidadTiempo;
            if( probabilidadTotal > probabilidad){
                // System.out.println(probabilidad + " ---> " + minutos.get(indice));
                return minutos.get(indice);
            }
            indice++;
        }
        return -1;
    }
}
