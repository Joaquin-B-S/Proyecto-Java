/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Camionero;
import modelo.Excepcion;
import servicios.FactoriaServiciosImpl;
import vista.CamioneroModificar;
import vista.Camioneros;

/**
 *
 * @author alumno
 */
public class ControladorCamioneros {
    //private Vista vista;
    Camioneros vista;
    private Camionero modelo;

    public ControladorCamioneros(Camioneros v, Camionero m)
    {
        this.vista = v;
        this.modelo = m;        
    }
    
    
    public void inicializar() throws InstantiationException, IllegalAccessException, Exception
    { 
        this.vista.getjButton2().addActionListener( e-> {
            try {
                System.out.println("Agregando listener y evento");
                read();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        this.vista.getjButton5().addActionListener( e-> {
            try {
                System.out.println("Agregando listener y evento");
                update();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.vista.getjButton6().addActionListener( e-> {
            try {
                System.out.println("Agregando listener y evento");
                delete();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
        
        this.read();
        
        this.vista.setVisible(true);
    }
    

    public void read()
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " entrando a read()");
        }

        try
        {
            ArrayList al = null;
            if (this.vista.getjTextField1().getText().isEmpty())
            {
                al = this.modelo.read();
            }
            else
            {
                String nombre = this.vista.getjTextField1().getText().trim();
                al = this.modelo.readConWhere(" where cam.nombre like '%" + nombre + "%'");
            } 
            this.vista.llenarJTable(al);
        }
        /*
        catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException ex)
        {
            JOptionPane.showMessageDialog(null, "Posible falla del servidor BD, puede que no este dando el servicio o hay un problema con la red. Intente nuevamente o llame a soporte. ");
        }
        */
        catch (Excepcion ex)
        {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        catch (Exception ex)
        {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }  
    }
    
    public void update() 
    {
        DefaultTableModel m = (DefaultTableModel) this.vista.getjTable1().getModel();
        int filas = m.getRowCount();
        if (filas>0)
        {
            if (this.vista.getjTable1().getSelectedRow()==-1)
            {
                JOptionPane.showMessageDialog(null, "Debe elejir una fila", "Informacion", JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION);
            }
            else
            {
                int fila = this.vista.getjTable1().getSelectedRow();
                
                String rut = (String) m.getValueAt(fila, 0);
 
                CamioneroModificar unaVista = new CamioneroModificar(null, true, rut); 
                Camionero camModelo = new Camionero();
                camModelo.setRut(rut);
                ControladorCamioneroModificar c = new ControladorCamioneroModificar(unaVista, camModelo);        
                try {
                    c.inicializar();
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ControladorCamioneros.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorCamioneros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No hay registros", "Información", JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION);    
        }        
    }
    
    public void delete() 
    {
        DefaultTableModel m = (DefaultTableModel) this.vista.getjTable1().getModel();
        int filas = m.getRowCount();
        if (filas>0)
        {
            if (this.vista.getjTable1().getSelectedRow()==-1)
            {
                JOptionPane.showMessageDialog(null, "Debe elejir una fila", "Informacion", JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION);
            }
            else
            {
                int fila = this.vista.getjTable1().getSelectedRow();
                
                String rut = (String) m.getValueAt(fila, 0);
 
                CamioneroModificar unaVista = new CamioneroModificar(null, false, rut); 
                Camionero camModelo = new Camionero();
                camModelo.setRut(rut);

                try                
                {
                    if (JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar rut=" + camModelo.getRut()+"?", "Delete", JOptionPane.WARNING_MESSAGE +  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        camModelo.delete(camModelo);
                        this.read();
                    }
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ControladorCamioneros.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorCamioneros.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No hay registros", "Información", JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION);    
        }        

    }
    
}
