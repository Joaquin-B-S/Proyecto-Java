/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Camionero;
import modelo.Ruta;
import modelo.Excepcion;
import servicios.FactoriaServiciosImpl;
import vista.CamioneroModificar;

/**
 *
 * @author alumno
 */
public class ControladorCamioneroModificar {
    //private Vista vista;
    CamioneroModificar vista;
    private Camionero modelo;

    public ControladorCamioneroModificar(CamioneroModificar v, Camionero m)
    {
        this.vista = v;
        this.modelo = m;        
    }
    
    
    public void inicializar() throws InstantiationException, IllegalAccessException, Exception
    {     
        Ruta mc = new Ruta();
        ArrayList rutas = mc.read();
        this.vista.cargarRutas(rutas,this.modelo.getRutaByRut(this.modelo.getRut()) );
        this.vista.setearCamionero(modelo.getCamionero(modelo));
        
        this.vista.getjButton1().addActionListener( e-> {
            try {
                System.out.println("Agregando listener y evento");
                update();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.vista.setVisible(true);  
    }
    

    public void update() 
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " entrando a update()");
        }

        try
        {
            //tomando valores de los controles para despues crear objeto
            String rut = this.vista.getjTextField1().getText();
            String nombre = this.vista.getjTextField2().getText();
            String genero = null;
            if (this.vista.getjRadioButton1().isSelected())
            {
                genero = "M";
            }
            else
            {
                genero = "F";
            }
            int edad = Integer.parseInt(this.vista.getjTextField4().getText());
            float estatura = Float.parseFloat(this.vista.getjTextField5().getText());
            boolean jubilado=false;
            if (this.vista.getjCheckBox1().isSelected())
            {
                jubilado = true;
            }
            String rutaSeleccionada = null;
            int codigoRuta = 0;
            String nombreRuta = null;
            if (this.vista.getjComboBox1().getModel().getSize()!=0)
            {
                rutaSeleccionada = (String)this.vista.getjComboBox1().getSelectedItem();
                String corcheteAbiertoADerecha="[";
                String corcheteAbiertoAIzquierda="]";
                
                int dondeCorcheteAbiertoADerecha = rutaSeleccionada.indexOf(corcheteAbiertoADerecha);
                int dondeCorcheteAbiertoAIzquierda = rutaSeleccionada.indexOf(corcheteAbiertoAIzquierda);
                
                String codigoDentroDeLosCorchetes = rutaSeleccionada.substring(dondeCorcheteAbiertoADerecha+1, dondeCorcheteAbiertoAIzquierda);
                codigoRuta = Integer.parseInt(codigoDentroDeLosCorchetes);
                
                nombreRuta = rutaSeleccionada.substring(0, dondeCorcheteAbiertoADerecha).trim();
            }
           
            //Creamos el objeto
            Camionero c = new Camionero();
            c.setRut(rut);
            c.setNombre(nombre);
            c.setGenero(genero);
            c.setEdad(edad);
            c.setEstatura(estatura);
            c.setJubilado(jubilado);
            c.setIdRuta(codigoRuta);
            c.setRuta(nombreRuta);
        
            this.modelo.update(c);
           
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
        
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
    
}
