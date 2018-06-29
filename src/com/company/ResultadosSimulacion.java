
package com.company;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultadosSimulacion extends javax.swing.JFrame {
    private DefaultTableModel modeloEventos;
    private DefaultTableModel modeloEstadisticas;
    private static int contadorEventos; 
    private static int cantidadEstaciones;
    private  ArrayList<Float[]> estadisticasAlmacenadas;
    
    public ResultadosSimulacion( int cantidadEstaciones) {
        initComponents();
        contadorEventos = 0;
        modeloEventos = new DefaultTableModel( new Object [][] {},new String [] {"N° Evento", "Tipo de Evento","ID Cliente","TM","SS","WL","AT","DT"});
        tablaEventos.setModel(modeloEventos);
        modeloEstadisticas = new DefaultTableModel( new Object [][] {},new String [] {"Cant. que no esperan","Clientes no atendidos","Prob. de Esperar","Promedio Clientes(Cola)","Promedio Clientes(Sistema)","Promedio Tiempo (Cola)","Promedio tiempo despues de cierre"});
        tablaEstadisticas.setModel(modeloEstadisticas);
        estadisticasAlmacenadas = new ArrayList<Float[]>();
        inicializarComboBox(cantidadEstaciones);
    }
    
    public void ingresarEvento(String tipoEvento,int idCliente,int tm,int ss, int wl,String at,String dt){
        DefaultTableModel aux = (DefaultTableModel) tablaEventos.getModel();
        if ( Integer.parseInt(at) > 99999) {
            at = "99999";
        }
        if ( Integer.parseInt(dt) > 99999) {
            dt = "99999";
        }
        aux.addRow(new Object[]{contadorEventos,tipoEvento,idCliente,tm,ss,wl,at,dt});
        contadorEventos++;
    }
    
    public void inicializarComboBox(int cantidad){
        cantidadEstaciones = cantidad;
        for(int i = 1 ; i < cantidadEstaciones+1 ; i++)
            comboBox.insertItemAt("Estacion "+i, i-1);
    }
    
    
    /* 
    arg1: Cantidad de clientes que no esperan
    arg2: Cantidad de clientes que se van sin ser atendidos
    arg3: Probabilidad de esperar
    arg4: Cantidad promedio de clientes en cola
    arg5: Cantidad promedio de clientes en sistema
    arg6: Tiempo promedio de espera del cliente que hace cola
    arg7: Tiempo promedio adicional que trabaja el comedor despues de cerrar
    */
    public void actualizarEstadisticas(int target,float arg1,float arg2,float arg3, float arg4, float arg5,float arg6,float arg7){ 
        // target = 0 Sistema ; 1 <= target Estacion
        Float[] auxiliar = {arg1,arg2,arg3,arg4,arg5,arg6,arg7};
        estadisticasAlmacenadas.set(target,auxiliar);
    }
    
    public void rellenarTablaEstadisticas(int target){
        DefaultTableModel aux = (DefaultTableModel) tablaEstadisticas.getModel();
        Float[] estadisticas = estadisticasAlmacenadas.get(target);
        aux.addRow(new Object[]{estadisticas[0],estadisticas[1],estadisticas[2],estadisticas[3],estadisticas[4],estadisticas[5],estadisticas[6]});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Resultados de la simulación");

        tablaEventos.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaEventos);

        tablaEstadisticas.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaEstadisticas);

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Estadísticas de:");

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1061, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged

        if(evt.getStateChange() == ItemEvent.SELECTED)
            rellenarTablaEstadisticas(comboBox.getSelectedIndex());
    }//GEN-LAST:event_comboBoxItemStateChanged

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResultadosSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadosSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadosSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadosSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultadosSimulacion(6).setVisible(true);
            }
        });
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEstadisticas;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables
}
