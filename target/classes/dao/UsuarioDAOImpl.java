/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Camionero;
import modelo.Usuario;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author leo
 * DAO Data Access Object
 */
public class UsuarioDAOImpl implements CrudDAOable<Usuario> {

    @Override
    public void create(Usuario t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        String sql = "insert into usuario (login, password) values (?, ?)";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getLogin());
        ps.setString(2, t.getPassword());
        ps.executeUpdate();
    }
    
    
    @Override
    public boolean read(Usuario t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        boolean existe = false;
        String sql = "select login from usuario where login=? and password=? limit 1";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        //https://dev.mysql.com/doc/relnotes/connector-j/8.0/en/news-8-0-20.html
        //ps.setFetchDirection(ResultSet.FETCH_REVERSE);
        ps.setString(1, t.getLogin());
        ps.setString(2, t.getPassword());
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            existe = true;
        }
        return existe;
        
        /*
        rs.last();
        int size = rs.getRow();
        rs.beforeFirst();
        if (size==1)
        {
            return true;
        }
        else
        {
            return false;
        }
        */
    }    
    
    
    @Override
    public ArrayList<Usuario> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        ArrayList<Usuario> al = new ArrayList();
        String sql = "select * from usuario;";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            String login = rs.getNString("login");
            String pass = rs.getString("password");
            
            Usuario usuario = new Usuario(login, pass);
            al.add(usuario);
        }        
        return al;
    }


    @Override
    public ArrayList<Usuario> readWhere(String sqlFilter) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        ArrayList<Usuario> al = new ArrayList();
        String sql = "select * from usuario " + sqlFilter + ";";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            String login = rs.getNString("login");
            String pass = rs.getString("password");
            
            Usuario usuario = new Usuario(login, pass);
            al.add(usuario);
        }        
        return al;      
    }

    
    @Override
    public void update(Usuario t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        String sql = "update usuario set password=? where login=?;";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getPassword());
        ps.setString(2, t.getLogin());
        ps.executeUpdate();
    }

    
    @Override
    public void delete(Usuario t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    {
        String sql = "delete from usuario where login=?;";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getLogin());
        ps.executeUpdate();        
    }   
}
