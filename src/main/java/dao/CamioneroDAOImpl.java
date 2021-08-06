/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import modelo.Camionero;
import modelo.Usuario;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author alumno
 */
public class CamioneroDAOImpl implements CrudDAOable<Camionero> {

    @Override
    public void create(Camionero t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "insert into camionero values (?, ?, ?, ?, ?, ?, ?);";    
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getRut());
        ps.setString(2, t.getNombre());
        ps.setString(3, t.getGenero());
        ps.setInt(4, t.getEdad());
        ps.setFloat(5, t.getEstatura());
        ps.setBoolean(6, t.isJubilado());
        ps.setInt(7, t.getIdRuta());
        ps.executeUpdate();
    }

    @Override
    public boolean read(Camionero t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Camionero> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a read()");
        }
        
        ArrayList<Camionero> al = new ArrayList();
        String sql = "select cam.*, com.nombre as ruta "
                + " from camionero cam"
                + " inner join ruta com on cam.id_ruta = com.id;";
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            String rut = rs.getNString("rut");
            String nombre = rs.getString("nombre");
            String genero = rs.getString("genero");
            int edad = rs.getInt("edad");
            float estatura = rs.getFloat("estatura");
            boolean jubilado = rs.getBoolean("jubilado");
            int idRuta = rs.getInt("id_ruta");
            String ruta = rs.getNString("ruta");
            Camionero camionero = new Camionero();
           
            camionero.setRut(rut);
            camionero.setNombre(nombre);
            camionero.setGenero(genero);
            camionero.setEdad(edad);
            camionero.setEstatura(estatura);
            camionero.setJubilado(jubilado);
            camionero.setIdRuta(idRuta);
            camionero.setRuta(ruta);
            
            al.add(camionero);
        }        
        return al;
    }

    @Override
    public ArrayList<Camionero> readWhere(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Camionero> al = new ArrayList();
        String sql = "select cam.*, com.nombre as ruta "
        + " from camionero cam"
        + " inner join ruta com on cam.id_ruta = com.id "  + whereSQL + ";";
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a readWhere()");
            System.out.println(sql);
        }
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            String rut = rs.getNString("rut");
            String nombre = rs.getString("nombre");
            String genero = rs.getString("genero");
            int edad = rs.getInt("edad");
            float estatura = rs.getFloat("estatura");
            boolean jubilado = rs.getBoolean("jubilado");
            int idRuta = rs.getInt("id_ruta");
            String ruta = rs.getNString("ruta");
            
            Camionero camionero = new Camionero();
           
            camionero.setRut(rut);
            camionero.setNombre(nombre);
            camionero.setGenero(genero);
            camionero.setEdad(edad);
            camionero.setEstatura(estatura);
            camionero.setJubilado(jubilado);
            camionero.setIdRuta(idRuta);
            camionero.setRuta(ruta);
            
            al.add(camionero);
        }        
        return al; 
    }
    
    
    public Camionero read(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
    { 
        Camionero camionero = null;
    
        String sql = "select cam.*, com.nombre as ruta "
        + " from camionero cam"
        + " inner join ruta com on cam.id_ruta = com.id "  + whereSQL + ";";
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a read(String whereSQL)");
            System.out.println(sql);
        }
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            String rut = rs.getNString("rut");
            String nombre = rs.getString("nombre");
            String genero = rs.getString("genero");
            int edad = rs.getInt("edad");
            float estatura = rs.getFloat("estatura");
            boolean jubilado = rs.getBoolean("jubilado");
            int idRuta = rs.getInt("id_ruta");
            String ruta = rs.getNString("ruta");
            
            camionero = new Camionero();
           
            camionero.setRut(rut);
            camionero.setNombre(nombre);
            camionero.setGenero(genero);
            camionero.setEdad(edad);
            camionero.setEstatura(estatura);
            camionero.setJubilado(jubilado);
            camionero.setIdRuta(idRuta);
            camionero.setRuta(ruta);

        }        
        return camionero;
    }    

    @Override
    public void update(Camionero t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "update camionero set nombre=?, genero=?, edad=?, estatura=?, jubilado=?, id_ruta=? where rut=?;";    
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getNombre());
        ps.setString(2, t.getGenero());
        ps.setInt(3, t.getEdad());
        ps.setFloat(4, t.getEstatura());
        ps.setBoolean(5, t.isJubilado());
        ps.setInt(6, t.getIdRuta());
        ps.setString(7, t.getRut());
        ps.executeUpdate();        
    }

    @Override
    public void delete(Camionero t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "delete from camionero where rut=?;";    
        PreparedStatement ps = FactoriaServiciosImpl.getFactoria().getConexionDB().getConexion().prepareStatement(sql);
        ps.setString(1, t.getRut());
        ps.executeUpdate();          
    }
    
    public int getRutaByRut(String rut) throws InstantiationException, IllegalAccessException, Exception
    {
        Camionero camionero = this.read(" where rut='" + rut + "'");
        if (camionero==null)
        {
            return 0;
        }
        else
        {
            return camionero.getIdRuta();
        }  
    }

}
