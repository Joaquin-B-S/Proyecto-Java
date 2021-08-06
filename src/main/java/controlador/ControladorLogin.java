/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CrudDAOable;
import dao.UsuarioDAOImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Excepcion;
import modelo.Usuario;
import vista.Login;
import vista.Menu;
import vista.Vista;

/**
 *
 * @author leo
 * Mi Controlador...
 */
public class ControladorLogin {
    //private Vista vista;
    Login vista;
    private Usuario modelo;
   
    /*
    public Controlador(Vista v, Usuario m)
    {
        this.vista = v;
        this.modelo = m;
    }
    */
    
    public ControladorLogin(Login v, Usuario m)
    {
        this.vista = v;
        this.modelo = m;        
    }
    
    /*
    public void logearse() throws InstantiationException, IllegalAccessException, Exception
    {
        modelo = vista.pedirLogin();
        if (modelo.logearse())
        {
            vista.imprimirExito();
        }
        else
        {
            vista.imprimirFracaso();
        }
    }
    */
    
    public void inicializar() throws InstantiationException, IllegalAccessException, Exception
    {   
        this.vista.getjButton1().addActionListener(e-> {
            try {
                login();
            } catch (Exception ex) {
                Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.vista.setVisible(true);        
    }
     
    public void login() throws IllegalAccessException, Exception
    {
        String login = this.vista.getjTextField1().getText();
        String pass = this.vista.getjTextField2().getText();

        try
        {
           this.modelo.setLogin(login);
           this.modelo.setPassword(pass);
           if (this.modelo.logearse())
           {
               this.vista.setVisible(false);
               new Menu().setVisible(true);
           }
           else
           {
               this.vista.imprimirFracaso();
           }   
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
