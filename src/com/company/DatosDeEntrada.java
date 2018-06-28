package com.company;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class DatosDeEntrada extends javax.swing.JFrame {
    private TableModel modeloEstaciones;
    private TableModel modeloLlegadas;
    private TableModel modeloServicio;
    private int capacidadSistema;

    /* Data de columnas de la tabla de Estaciones / N° servidores */
    public ArrayList<Integer> dataCantidadServidores; //El index+1 identifica a que estacion pertenece
    
    /* Data de columnas de la tabla de Tiempos de Llegada */
    public  ArrayList<Integer> dataTiemposLlegada;
    public ArrayList<Float> dataProbabilidadesTiemposLlegada;
    
    /* Data de columnas de la tabla de Tiempos de Servicio */
    public ArrayList<Integer> dataEstacionTiemposServicio;
    public ArrayList<Integer> dataTiemposServicio;
    public ArrayList<Float> dataProbabilidadesTiemposServicio;

    public DatosDeEntrada() {
        initComponents();
        initTables();  
        inputCapacidad.setTransferHandler(null);
        capacidadSistema = 0;
        
        dataCantidadServidores = new ArrayList<Integer>();
        
        dataTiemposLlegada = new ArrayList<Integer>();
        dataProbabilidadesTiemposLlegada = new ArrayList<Float>();
        
        dataEstacionTiemposServicio = new ArrayList<Integer>();
        dataTiemposServicio = new ArrayList<Integer>();
        dataProbabilidadesTiemposServicio = new ArrayList<Float>();

        prueba();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        panelEstaciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstaciones = new javax.swing.JTable();
        panelTiemposServicio = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaTiemposServicio = new javax.swing.JTable();
        panelTiemposLlegada = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTiemposLlegada = new javax.swing.JTable();
        labelTiemposLlegada = new javax.swing.JLabel();
        labelTiemposServicio = new javax.swing.JLabel();
        labelEstaciones = new javax.swing.JLabel();
        botonNuevaEstacion = new javax.swing.JButton();
        botonEliminarEstacion = new javax.swing.JButton();
        botonNuevoTiempoLlegada = new javax.swing.JButton();
        botonEliminarTiempoLlegada = new javax.swing.JButton();
        botonNuevoTiempoServicio = new javax.swing.JButton();
        botonEliminarTiempoServicio = new javax.swing.JButton();
        labelGuardarCambios = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonVaciarTablas = new javax.swing.JButton();
        inputCapacidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        checkCapacidad = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelFondo.setBackground(new java.awt.Color(204, 204, 204));

        panelEstaciones.setBackground(new java.awt.Color(153, 153, 153));

        tablaEstaciones.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tablaEstaciones);

        javax.swing.GroupLayout panelEstacionesLayout = new javax.swing.GroupLayout(panelEstaciones);
        panelEstaciones.setLayout(panelEstacionesLayout);
        panelEstacionesLayout.setHorizontalGroup(
            panelEstacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEstacionesLayout.setVerticalGroup(
            panelEstacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTiemposServicio.setBackground(new java.awt.Color(153, 153, 153));

        tablaTiemposServicio.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane3.setViewportView(tablaTiemposServicio);

        javax.swing.GroupLayout panelTiemposServicioLayout = new javax.swing.GroupLayout(panelTiemposServicio);
        panelTiemposServicio.setLayout(panelTiemposServicioLayout);
        panelTiemposServicioLayout.setHorizontalGroup(
            panelTiemposServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiemposServicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTiemposServicioLayout.setVerticalGroup(
            panelTiemposServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiemposServicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTiemposLlegada.setBackground(new java.awt.Color(153, 153, 153));

        tablaTiemposLlegada.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane2.setViewportView(tablaTiemposLlegada);

        javax.swing.GroupLayout panelTiemposLlegadaLayout = new javax.swing.GroupLayout(panelTiemposLlegada);
        panelTiemposLlegada.setLayout(panelTiemposLlegadaLayout);
        panelTiemposLlegadaLayout.setHorizontalGroup(
            panelTiemposLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiemposLlegadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTiemposLlegadaLayout.setVerticalGroup(
            panelTiemposLlegadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTiemposLlegadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelTiemposLlegada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelTiemposLlegada.setForeground(new java.awt.Color(51, 51, 51));
        labelTiemposLlegada.setText("Tiempos de Llegada");

        labelTiemposServicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelTiemposServicio.setForeground(new java.awt.Color(51, 51, 51));
        labelTiemposServicio.setText("Tiempos de Servicio");

        labelEstaciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelEstaciones.setForeground(new java.awt.Color(51, 51, 51));
        labelEstaciones.setText("Estaciones / N° Servidores");

        botonNuevaEstacion.setText("Nueva estación");
        botonNuevaEstacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaEstacionActionPerformed(evt);
            }
        });

        botonEliminarEstacion.setText("Eliminar estacion");
        botonEliminarEstacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarEstacionActionPerformed(evt);
            }
        });

        botonNuevoTiempoLlegada.setText("Añadir nuevo");
        botonNuevoTiempoLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoTiempoLlegadaActionPerformed(evt);
            }
        });

        botonEliminarTiempoLlegada.setText("Eliminar tiempo");
        botonEliminarTiempoLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarTiempoLlegadaActionPerformed(evt);
            }
        });

        botonNuevoTiempoServicio.setText("Añadir nuevo");
        botonNuevoTiempoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoTiempoServicioActionPerformed(evt);
            }
        });

        botonEliminarTiempoServicio.setText("Eliminar tiempo");
        botonEliminarTiempoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarTiempoServicioActionPerformed(evt);
            }
        });

        labelGuardarCambios.setForeground(new java.awt.Color(51, 51, 51));
        labelGuardarCambios.setText("Para comenzar con la simulación deben estar llenas todas las celdas de cada fila de las tablas de entrada, de no ser así no podrá guardar cambios");

        botonGuardar.setText("Guardar y comenzar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonVaciarTablas.setText("Vaciar tablas");
        botonVaciarTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVaciarTablasActionPerformed(evt);
            }
        });

        inputCapacidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputCapacidad.setToolTipText("Ingrese la capacidad del sistema");
        inputCapacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCapacidadKeyTyped(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Capacidad del sistema ");

        titulo.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        titulo.setForeground(new java.awt.Color(51, 51, 51));
        titulo.setText("Simulador de Líneas de Espera");

        checkCapacidad.setText("Capacidad infinita");
        checkCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCapacidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(titulo))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(542, 542, 542)
                        .addComponent(checkCapacidad))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(541, 541, 541)
                        .addComponent(jLabel1))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addGap(490, 490, 490)
                                .addComponent(inputCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelGuardarCambios)
                                        .addGap(134, 134, 134))
                                    .addGroup(panelFondoLayout.createSequentialGroup()
                                        .addComponent(botonNuevaEstacion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonEliminarEstacion, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonNuevoTiempoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonEliminarTiempoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botonNuevoTiempoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonEliminarTiempoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelFondoLayout.createSequentialGroup()
                                        .addGap(381, 381, 381)
                                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonVaciarTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator2))
                                .addGroup(panelFondoLayout.createSequentialGroup()
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelEstaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelEstaciones))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelTiemposLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelTiemposLlegada))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelFondoLayout.createSequentialGroup()
                                            .addComponent(labelTiemposServicio)
                                            .addGap(250, 250, 250))
                                        .addComponent(panelTiemposServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkCapacidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTiemposLlegada)
                        .addComponent(labelEstaciones))
                    .addComponent(labelTiemposServicio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelEstaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTiemposLlegada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTiemposServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevaEstacion)
                    .addComponent(botonEliminarEstacion)
                    .addComponent(botonNuevoTiempoLlegada)
                    .addComponent(botonEliminarTiempoLlegada)
                    .addComponent(botonNuevoTiempoServicio)
                    .addComponent(botonEliminarTiempoServicio))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGuardarCambios)
                .addGap(18, 18, 18)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonVaciarTablas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void initTables() { //Inicializa los modelos de las tablas de entrada
        /* Hacemos no editable el numero de estaciones para que sea asignado segun la cuenta de estaciones añadidas */
        modeloEstaciones = new DefaultTableModel( new Object [][] {},new String [] {"Estacion", "N° Servidores"}){
            @Override
            public boolean isCellEditable(int row, int column) { return column == 1 ? true : false; }
        };
        modeloLlegadas = new DefaultTableModel( new Object [][] {},new String [] {"Tiempo", "Probabilidad"});
        modeloServicio = new DefaultTableModel( new Object [][] {},new String [] {"Estación","Tiempo", "Probabilidad"});
        
        /* Aplicando modelos */
        tablaEstaciones.setModel(modeloEstaciones);
        tablaTiemposLlegada.setModel(modeloLlegadas);
        tablaTiemposServicio.setModel(modeloServicio);
    } 
    
    public void obtenerData() { //Guarda la data de cada tabla en variables del programa
        
        /* Verificamos que las tablas no esten vacías al momento de guardar */
        if(tablaEstaciones.getRowCount() != 0 && tablaTiemposLlegada.getRowCount() != 0 && tablaTiemposServicio.getRowCount() != 0){    
            if(!buscarCeldasVacias()){
                /* Tabla Estaciones / N°Servidores */
                for(int i = 0; i < tablaEstaciones.getRowCount(); i++) 
                    dataCantidadServidores.add(Integer.parseInt(tablaEstaciones.getValueAt(i, 1).toString()));

                /* Tabla Tiempos de Llegada */
                for(int i = 0; i < tablaTiemposLlegada.getRowCount(); i++) {
                    dataTiemposLlegada.add(Integer.parseInt(tablaTiemposLlegada.getValueAt(i, 0).toString()));
                    dataProbabilidadesTiemposLlegada.add(Float.parseFloat(tablaTiemposLlegada.getValueAt(i, 1).toString()));
                }

                /* Tabla Tiempos de Servicio */
                for(int i = 0; i < tablaTiemposServicio.getRowCount(); i++) {
                    dataEstacionTiemposServicio.add(Integer.parseInt(tablaTiemposServicio.getValueAt(i, 0).toString()));
                    dataTiemposServicio.add(Integer.parseInt(tablaTiemposServicio.getValueAt(i,1).toString()));
                    dataProbabilidadesTiemposServicio.add(Float.parseFloat(tablaTiemposServicio.getValueAt(i, 2).toString()));
                }

                /* Capacidad del sistema */
                if(checkCapacidad.isSelected()) 
                    capacidadSistema = -1;
                else 
                    if(!inputCapacidad.getText().isEmpty())
                        capacidadSistema = Integer.parseInt(inputCapacidad.getText());
            } else 
                JOptionPane.showMessageDialog(this, "Error: No se permiten celdas vacías en las tablas de entrada");
        }
    }

    private boolean buscarCeldasVacias() { // Busca celdas vacias en las tres tablas, retorna true si consigue alguna        
        boolean tiene = false;
        /* Busqueda */
        for(int i = 0; i < tablaEstaciones.getRowCount(); i++)
            for(int j = 0 ; j < tablaEstaciones.getColumnCount() ; j++)
                if(tablaEstaciones.getValueAt(i, j) == null)
                    tiene = true;
        
        for(int i = 0; i < tablaTiemposLlegada.getRowCount(); i++)
            for(int j = 0 ; j < tablaTiemposLlegada.getColumnCount() ; j++)
                if(tablaTiemposLlegada.getValueAt(i, j) == null)
                    tiene = true;
        
        for(int i = 0; i < tablaTiemposServicio.getRowCount(); i++)
            for(int j = 0 ; j < tablaTiemposServicio.getColumnCount() ; j++)
                if(tablaTiemposServicio.getValueAt(i, j) == null)
                    tiene = true;
        
        /* Si no consigue */
        return tiene;
    }
    
    private boolean validarEstaciones() {
        for(int i = 0 ; i < tablaTiemposServicio.getRowCount(); i++){
            int numeroEstacion = Integer.parseInt(tablaTiemposServicio.getValueAt(i, 0).toString());
            if(numeroEstacion >= 1 && numeroEstacion <= tablaEstaciones.getRowCount())
                return true;
        }
        return false;
    }
    
    private boolean validarProbabilidades(){
        float probabilidadLlegada = 0;
                
        for(Float f : dataProbabilidadesTiemposLlegada)
            probabilidadLlegada += f;
       
        if(probabilidadLlegada == 1)
            return true;
        return false;
    }
    
    private void botonNuevaEstacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaEstacionActionPerformed
       /* Añadiendo una fila vacía */
       DefaultTableModel aux = (DefaultTableModel) tablaEstaciones.getModel();
       aux.addRow(new Object[]{tablaEstaciones.getRowCount()+1,""});
    }//GEN-LAST:event_botonNuevaEstacionActionPerformed

    private void botonNuevoTiempoLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoTiempoLlegadaActionPerformed
        /* Añadiendo una fila vacía */        
        DefaultTableModel aux = (DefaultTableModel) tablaTiemposLlegada.getModel();
        aux.addRow(new Object[]{"",""});
    }//GEN-LAST:event_botonNuevoTiempoLlegadaActionPerformed

    private void botonNuevoTiempoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoTiempoServicioActionPerformed
        /* Añadiendo una fila vacía */
        DefaultTableModel aux = (DefaultTableModel) tablaTiemposServicio.getModel();
        aux.addRow(new Object[]{"",""});
    }//GEN-LAST:event_botonNuevoTiempoServicioActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        obtenerData();
        /*
        if(!validarEstaciones())
            JOptionPane.showMessageDialog(this, "Error: Numero de estación errado en la tabla de tiempos de servicio");
        if(!validarProbabilidades())
            JOptionPane.showMessageDialog(this, "Error: Las probabilidades introducidas estan erradas (deben sumar 1)");
        /*else
            // Crea una instancia de sistema pasandole los datos*/
        ArrayList<Etapa> etapas = new ArrayList<Etapa>();
        int indice = 0;
        for(Integer servidores: this.dataCantidadServidores) {
            ArrayList<Integer> minutos = new ArrayList<Integer>();
            ArrayList<Float> probabilidades = new ArrayList<Float>();
            int indiceEstacion= 0;
            for ( Integer estacion: this.dataEstacionTiemposServicio){
                if (indice+1 == estacion){
                    probabilidades.add(this.dataProbabilidadesTiemposServicio.get(indiceEstacion));
                    minutos.add(this.dataTiemposServicio.get(indiceEstacion));
                }
                indiceEstacion++;
            }
            etapas.add(new Etapa(servidores, indice+1, minutos, probabilidades));
            indice++;
        }
        Sistema sistema = new Sistema(etapas, this.capacidadSistema, this.dataTiemposLlegada, this.dataProbabilidadesTiemposLlegada, 20);
        System.out.println(sistema.clientesEnSistema+sistema.demandantes.size());
        for (int i = 0; !sistema.end; i++) {
            System.out.println("//////////////////////////////////////////////////////////////");
            sistema.mostrarDemandantes();
            sistema.ejecutarTransicion();
            System.out.println("\n Realizando la Iteracion "+ i+" tiempo "+sistema.tiempoActual+" \n\n");
            sistema.etapas.get(0).mostrarEtapa();
            sistema.etapas.get(1).mostrarEtapa();
            sistema.etapas.get(2).mostrarEtapa();
            sistema.etapas.get(3).mostrarEtapa();

        }
        for(Etapa etapa: sistema.etapas){
            System.out.println("Tiempo promedio de servicio en la etapa " + etapa.identificador + " = " + etapa.getTiempoPromedioEnServicio());
            etapa.getTiempoPromedioEnCola();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonEliminarEstacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarEstacionActionPerformed
        /* Eliminando las filas seleccionadas */
        DefaultTableModel aux = (DefaultTableModel) tablaEstaciones.getModel();
        int numRows = tablaEstaciones.getSelectedRows().length;
        for(int i = 0; i < numRows ; i++ ) {
            aux.removeRow(tablaEstaciones.getSelectedRow());
        }
        /* Reorganizando los indices de estaciones tras la eliminación */
        for(int i = 0; i < tablaEstaciones.getRowCount() ; i++ ) {
            tablaEstaciones.setValueAt(i+1, i, 0);
        }
    }//GEN-LAST:event_botonEliminarEstacionActionPerformed

    private void botonEliminarTiempoLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarTiempoLlegadaActionPerformed
        /* Eliminando las filas seleccionadas */
        DefaultTableModel aux = (DefaultTableModel) tablaTiemposLlegada.getModel();
        int numRows = tablaTiemposLlegada.getSelectedRows().length;
        for(int i=0; i<numRows ; i++ ) {
            aux.removeRow(tablaTiemposLlegada.getSelectedRow());
        }
    }//GEN-LAST:event_botonEliminarTiempoLlegadaActionPerformed

    private void botonEliminarTiempoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarTiempoServicioActionPerformed
        /* Eliminando las filas seleccionadas */
        DefaultTableModel aux = (DefaultTableModel) tablaTiemposServicio.getModel();
        int numRows = tablaTiemposServicio.getSelectedRows().length;
        for(int i = 0; i < numRows ; i++ ) {
            aux.removeRow(tablaTiemposServicio.getSelectedRow());
        }
    }//GEN-LAST:event_botonEliminarTiempoServicioActionPerformed

    private void checkCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCapacidadActionPerformed
        if(checkCapacidad.isSelected()) 
            inputCapacidad.setEnabled(false);
        else 
            inputCapacidad.setEnabled(true);        
    }//GEN-LAST:event_checkCapacidadActionPerformed

    private void botonVaciarTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVaciarTablasActionPerformed
        /* Vaciado de la tabla de estaciones */
        DefaultTableModel modeloTabla1 = (DefaultTableModel) tablaEstaciones.getModel();
        modeloTabla1.setRowCount(0);
        
        /* Vaciado de la tabla de tiempos de llegada */        
        DefaultTableModel modeloTabla2 = (DefaultTableModel) tablaTiemposLlegada.getModel();
        modeloTabla2.setRowCount(0);
        
        /* Vaciado de la tabla de tiempos de servicio */
        DefaultTableModel modeloTabla3 = (DefaultTableModel) tablaTiemposServicio.getModel();
        modeloTabla3.setRowCount(0);
        
        /* Vaciando data almacenada en memoria */
        dataCantidadServidores.clear();
        dataTiemposLlegada.clear();
        dataProbabilidadesTiemposLlegada.clear();
        dataEstacionTiemposServicio.clear();
        dataTiemposServicio.clear();
        dataProbabilidadesTiemposServicio.clear();
    }//GEN-LAST:event_botonVaciarTablasActionPerformed

    private void inputCapacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCapacidadKeyTyped

    }//GEN-LAST:event_inputCapacidadKeyTyped

    public void prueba() {
        DefaultTableModel aux = (DefaultTableModel) tablaEstaciones.getModel();
        aux.addRow(new Object[]{tablaEstaciones.getRowCount()+1,"3"});
        aux.addRow(new Object[]{tablaEstaciones.getRowCount()+1,"2"});
        aux.addRow(new Object[]{tablaEstaciones.getRowCount()+1,"3"});
        aux.addRow(new Object[]{tablaEstaciones.getRowCount()+1,"4"});
        aux = (DefaultTableModel) tablaTiemposServicio.getModel();
        aux.addRow(new Object[]{"1","5","0.5"});
        aux.addRow(new Object[]{"1","4","0.3"});
        aux.addRow(new Object[]{"1","3","0.2"});
        aux.addRow(new Object[]{"2","4","0.5"});
        aux.addRow(new Object[]{"2","3","0.5"});
        aux.addRow(new Object[]{"3","3","0.5"});
        aux.addRow(new Object[]{"3","2","0.5"});
        aux.addRow(new Object[]{"4","3","0.5"});
        aux.addRow(new Object[]{"4","2","0.5"});
        aux = (DefaultTableModel) tablaTiemposLlegada.getModel();
        aux.addRow(new Object[]{"1","0.5"});
        aux.addRow(new Object[]{"1","0.3"});
        aux.addRow(new Object[]{"1","0.2"});
        this.inputCapacidad.setText("20");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatosDeEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarEstacion;
    private javax.swing.JButton botonEliminarTiempoLlegada;
    private javax.swing.JButton botonEliminarTiempoServicio;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonNuevaEstacion;
    private javax.swing.JButton botonNuevoTiempoLlegada;
    private javax.swing.JButton botonNuevoTiempoServicio;
    javax.swing.JButton botonVaciarTablas;
    private javax.swing.JCheckBox checkCapacidad;
    private javax.swing.JTextField inputCapacidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelEstaciones;
    private javax.swing.JLabel labelGuardarCambios;
    private javax.swing.JLabel labelTiemposLlegada;
    private javax.swing.JLabel labelTiemposServicio;
    private javax.swing.JPanel panelEstaciones;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelTiemposLlegada;
    private javax.swing.JPanel panelTiemposServicio;
    private javax.swing.JTable tablaEstaciones;
    private javax.swing.JTable tablaTiemposLlegada;
    private javax.swing.JTable tablaTiemposServicio;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
