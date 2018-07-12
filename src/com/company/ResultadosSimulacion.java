package com.company;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ResultadosSimulacion extends javax.swing.JFrame {
    private DefaultTableModel modeloEventos;
    private DefaultTableModel modeloEstadisticas;;
    private DefaultTableModel modeloPorcentajes;
    private static int contadorEventos;
    private static int cantidadEstaciones;
    private ArrayList < Integer > cantidadServidores;
    private ArrayList < ArrayList < Float[] >> estadisticasAlmacenadas;
    private ArrayList < Float[][] > porcentajesAlmacenados;
    Float[] ax = {
        1F,
        2F,
        3F,
        4F,
        5F,
        6F,
        7F
    };

    public ResultadosSimulacion(int cantidadEstaciones, ArrayList < Integer > cantidadServidores) {
        initComponents();
        contadorEventos = 0;
        modeloEventos = new DefaultTableModel(new Object[][] {}, new String[] {
            "Dia",
            "N° Evento",
            "Tipo de Evento",
            "ID Cliente",
            "TM",
            "SS 1",
            "SS 2",
            "SS 3",
            "SS 4",
            "WL 1",
            "WL 2",
            "WL 3",
            "WL 4",
            "AT",
            "DT"
        });
        tablaEventos.setModel(modeloEventos);
        modeloEstadisticas = new DefaultTableModel(new Object[][] {}, new String[] {
            "Cant. que no esperan",
            "Clientes no atendidos",
            "Prob. de Esperar",
            "Promedio Clientes(Cola)",
            "Promedio Clientes(Sistema)",
            "Tiempo Promedio (Cola)",
            "Tiempo Promedio (Sistema)",
            "Tiempo Promedio con espera",
            "Promedio tiempo despues de cierre"
        });
        modeloPorcentajes = new DefaultTableModel(new Object[][] {}, new String[] {
            "Porcentaje de Utilización"
        });
        tablaEstadisticas.setModel(modeloEstadisticas);
        tablaEstadisticas1.setModel(modeloPorcentajes);
        estadisticasAlmacenadas = new ArrayList < ArrayList < Float[] >> ();
        porcentajesAlmacenados = new ArrayList < Float[][] > ();
        this.cantidadServidores = cantidadServidores;
        inicializarComboBox(cantidadEstaciones);
        //inicializarComboBox1(cantidadServidores);
        // ingresarEstadistica(2,0,0,0,0,0,0);
        for (int i = 0; i < cantidadEstaciones; i++) {
            for (int j = 0; j < 7; j++) {
                actualizarEstadisticas(i, j, i + 1F, i + 2F, i + 3F, i + 4F, i + 5F, i + 6F, i + 7F, 0, 0);
            }
        }
        /*
                for (int i=0; i<cantidadEstaciones; i++){
                    for (Integer ax1: cantidadServidores){
                        this.porcentajesAlmacenados.add(new Float[7][7]);
                        for (int j=0; j<7; j++){
                            this.porcentajesAlmacenados.get(i)[ax1][j] = 0F;
                        }
                    }
                }*/
        for (int i = 0; i < cantidadEstaciones; i++) {
            this.porcentajesAlmacenados.add(new Float[this.cantidadServidores.get(i)][7]);
            for (int ax1 = 0; ax1 < this.cantidadServidores.get(i); ax1++) {
                for (int j = 0; j < 7; j++) {
                    this.porcentajesAlmacenados.get(i)[ax1][j] = 0F;
                }
            }
        }
    }

    public void ingresarEvento(int dia, String tipoEvento, int idCliente, int tm, int ss1, int ss2, int ss3, int ss4, int wl1, int wl2, int wl3, int wl4, String at, String dt) {
        DefaultTableModel aux = (DefaultTableModel) tablaEventos.getModel();
        if (Integer.parseInt(at) > 99999) {
            at = "99999";
        }
        if (Integer.parseInt(dt) > 99999) {
            dt = "99999";
        }
        aux.addRow(new Object[] {
            dia,
            contadorEventos,
            tipoEvento,
            idCliente,
            tm,
            ss1,
            ss2,
            ss3,
            ss4,
            wl1,
            wl2,
            wl3,
            wl4,
            at,
            dt
        });
        contadorEventos++;
    }

    public void ingresarEstadistica(float cantidadSinEsperar, float cantidadNoAtendidos, float probEspera,
        float promedioCola, float promedioSistema, float promedioTiempoEnCola,
        float promedioTiempo) {
        Float[] auxiliar = new Float[7];
        auxiliar[0] = cantidadSinEsperar;
        auxiliar[1] = cantidadNoAtendidos;
        auxiliar[2] = probEspera;
        auxiliar[3] = promedioCola;
        auxiliar[4] = promedioSistema;
        auxiliar[5] = promedioTiempoEnCola;
        auxiliar[6] = promedioTiempo;
        DefaultTableModel aux = (DefaultTableModel) tablaEstadisticas.getModel();
        aux.addRow(auxiliar);
    }

    public void inicializarComboBox(int cantidad) {
        cantidadEstaciones = cantidad;

        comboBox.insertItemAt("Sistema ", 0);
        for (int i = 1; i < cantidadEstaciones + 1; i++)
            comboBox.insertItemAt("Estacion " + i, i);
    }

    /*public void inicializarComboBox1(ArrayList<Integer> cantidad){
        int k = 0;
        for (int i=0; i<cantidad.size(); i++){
            comboBox1.insertItemAt("Estacion "+(i+1),i);
        }
    }*/

    public void inicializarComboBox2(int cantidad) {
        comboBox2.removeAllItems();
        for (int i = 0; i < cantidad; i++) {
            comboBox2.insertItemAt("Servidor " + (i + 1), i);
        }
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
    public void actualizarEstadisticas(int target, int day, float cantidadSinEsperar, float cantidadNoAtendidos, float probEspera,
        float promedioCola, float promedioSistema, float promedioTiempoEnCola, float promedioTiempoEnSistema, float promedioTiempoEspera,
        float tiempoAdicional) {

        // target = 0 Sistema ; 1 <= target Estacion
        Float[] auxiliar = {
            cantidadSinEsperar,
            cantidadNoAtendidos,
            probEspera,
            promedioCola,
            promedioSistema,
            promedioTiempoEnCola,
            promedioTiempoEnSistema,
            promedioTiempoEspera,
            tiempoAdicional
        };
        if (estadisticasAlmacenadas.size() > target)
            if (estadisticasAlmacenadas.get(target).size() > day)
                estadisticasAlmacenadas.get(target).set(day, auxiliar);
            else
                estadisticasAlmacenadas.get(target).add(day, auxiliar);
        else {
            ArrayList < Float[] > diaNuevo = new ArrayList < Float[] > ();
            diaNuevo.add(auxiliar);
            estadisticasAlmacenadas.add(diaNuevo);
        }
    }

    public void actualizarPorcentajes(int target, int servidor, int dia, float valor) {
        // target = 0 Sistema ; 1 <= target Estacion
        this.porcentajesAlmacenados.get(target)[servidor][dia] = valor;
        //System.err.println(valor);
        /*Float [] aux = this.porcentajesAlmacenados.get(target);
        aux[servidor]=valor;
        // this.porcentajesAlmacenados.get(target*5+servidor);
        this.porcentajesAlmacenados.get(target)[dia]=valor;*/
    }


    public void rellenarTablaEstadisticas(int target) {
        DefaultTableModel aux = (DefaultTableModel) tablaEstadisticas.getModel();
        ArrayList < Float[] > ax = estadisticasAlmacenadas.get(target);
        Float[] estadisticas;
        limpiarTablaEstadisticas(1);
        for (int i = 0; i < 7; i++) {
            estadisticas = ax.get(i);
            aux.addRow(new Object[] {
                estadisticas[0], estadisticas[1], estadisticas[2], estadisticas[3], estadisticas[4], estadisticas[5], estadisticas[6], estadisticas[7], estadisticas[8]
            });
        }
    }

    public void rellenarTablaEstadisticas1(int target, int servidor) {
        DefaultTableModel aux = (DefaultTableModel) tablaEstadisticas1.getModel();
        Float ax2;
        if (target >= 0) {
            limpiarTablaEstadisticas(2);
            for (int i = 0; i < 7; i++) {
                ax2 = this.porcentajesAlmacenados.get(target)[servidor][i];
                aux.addRow(new Object[] {
                    ax2
                });
            }
        }
    }

    public void limpiarTablaEstadisticas(int opc) {
        DefaultTableModel aux;
        if (opc == 1) {
            aux = (DefaultTableModel) tablaEstadisticas.getModel();
        } else {
            aux = (DefaultTableModel) tablaEstadisticas1.getModel();
        }
        int ax = aux.getRowCount() - 1;
        if (ax != 0) {
            for (int i = ax; i >= 0; i--) {
                aux.removeRow(aux.getRowCount() - 1);
            }
        }
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
        comboBox = new javax.swing.JComboBox < > ();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaEstadisticas1 = new javax.swing.JTable();
        comboBox2 = new javax.swing.JComboBox < > ();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Resultados de la simulación");

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }
            },
            new String[] {
                "Title 1",
                "Title 2",
                "Title 3",
                "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaEventos);

        tablaEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }
            },
            new String[] {
                "Title 1",
                "Title 2",
                "Title 3",
                "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaEstadisticas);

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Estadísticas de:");

        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Porcentajes de:");

        tablaEstadisticas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }, {
                    null,
                    null,
                    null,
                    null
                }
            },
            new String[] {
                "Title 1",
                "Title 2",
                "Title 3",
                "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaEstadisticas1);

        comboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBox2ItemStateChanged(evt);
            }
        });
        comboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox2ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1061, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 129, Short.MAX_VALUE))
                                    .addComponent(comboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
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
    } // </editor-fold>//GEN-END:initComponents

    private void comboBoxItemStateChanged(ItemEvent evt) { //GEN-FIRST:event_comboBoxItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            rellenarTablaEstadisticas(comboBox.getSelectedIndex());
            int i = comboBox.getSelectedIndex() - 1;
            this.comboBox2.removeAllItems();
            this.limpiarTablaEstadisticas(2);
            if (i >= 0) {
                this.inicializarComboBox2(this.cantidadServidores.get(i));
            }
        }
    } //GEN-LAST:event_comboBoxItemStateChanged

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
    } //GEN-LAST:event_comboBoxActionPerformed

    private void comboBox2ItemStateChanged(java.awt.event.ItemEvent evt) { //GEN-FIRST:event_comboBox2ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            rellenarTablaEstadisticas1(comboBox.getSelectedIndex() - 1, comboBox2.getSelectedIndex());
        }
    } //GEN-LAST:event_comboBox2ItemStateChanged

    private void comboBox2ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_comboBox2ActionPerformed
        // TODO add your handling code here:
    } //GEN-LAST:event_comboBox2ActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
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
                //new ResultadosSimulacion(6, 3).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox < String > comboBox;
    private javax.swing.JComboBox < String > comboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaEstadisticas;
    private javax.swing.JTable tablaEstadisticas1;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables
}