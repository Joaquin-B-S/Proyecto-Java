/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Scanner;
import modelo.Excepcion;
import modelo.Usuario;

/**
 *
 * @author leo
 */
public class Vista {
    
    private Scanner sc = new Scanner(System.in);
    
    public Usuario pedirLogin() throws Excepcion
    {
        System.out.println("LoginApp");
        System.out.println("Ingrese su login:");
        String login = sc.nextLine();
        System.out.println("Ingrese su password:");
        String pass = sc.nextLine();
        
        Usuario u = new Usuario(login, pass);
        
        return u;
    }
    
    public void imprimirExito()
    {
        System.out.println("Logeo Exitoso");
    }
    
    public void imprimirFracaso()
    {
        System.out.println("Logeo Incorrecto");
    }
    
    
}
