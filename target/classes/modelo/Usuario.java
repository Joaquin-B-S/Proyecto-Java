/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.CrudDAOable;
import dao.UsuarioDAOImpl;

/**
 *
 * @author leo
 */
public class Usuario {
    private String login;
    private String password;

    public Usuario() {
    }

    public Usuario(String login, String password) throws Excepcion {
        //this.login = login;
        //this.password = password;
        this.setLogin(login);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Excepcion {
        if (login.equalsIgnoreCase("leonardo"))
        {
            throw new Excepcion("No se acepta leo como usuario");
        }
        else
        {
            this.login = login;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", password=" + password + '}';
    }
    
    public boolean logearse() throws InstantiationException, IllegalAccessException, Exception
    {
        CrudDAOable<Usuario> uDAO = new UsuarioDAOImpl();
        return uDAO.read(this); 
    }
    
}
