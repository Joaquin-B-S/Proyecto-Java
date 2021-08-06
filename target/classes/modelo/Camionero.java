/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.CamioneroDAOImpl;
import dao.CrudDAOable;
import java.util.ArrayList;
import java.util.Objects;
import servicios.FactoriaServiciosImpl;

/**
 *
 * @author alumno
 */
public class Camionero {
    private String rut;
    private String nombre;
    private String genero;
    private int edad;
    private float estatura;
    private boolean jubilado;
    private int idRuta;
    
    //para efectos de display
    private String ruta;

    public Camionero() {
    }

    public Camionero(String rut, String nombre, String genero, int edad, float estatura, boolean jubilado, int idRuta, String ruta) {
        this.setRut(rut);
        this.setNombre(nombre);
        this.setGenero(genero);
        this.setEdad(edad);
        this.setEstatura(estatura);
        this.setJubilado(jubilado);
        this.setIdRuta(idRuta);
        this.setRuta(ruta);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public boolean isJubilado() {
        return jubilado;
    }

    public void setJubilado(boolean jubilado) {
        this.jubilado = jubilado;
    }
    
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Camionero{" + "rut=" + rut + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad + ", estatura=" + estatura + ", jubilado=" + jubilado + ", idRuta=" + idRuta + ", ruta=" + ruta + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.rut);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Camionero other = (Camionero) obj;
        if (!Objects.equals(this.rut, other.rut)) {
            return false;
        }
        return true;
    }
   
    
    public void create(Camionero c) throws InstantiationException, IllegalAccessException, Exception
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a create");
        }

        CrudDAOable<Camionero> cDAO = new CamioneroDAOImpl();
        cDAO.create(c);           
    }
  
    public ArrayList<Camionero> read() throws InstantiationException, IllegalAccessException, Exception
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a read");
        }
        
        CrudDAOable<Camionero> cDAO = new CamioneroDAOImpl();
        return cDAO.read();   
    }
    
    public ArrayList<Camionero> readConWhere(String whereSQL) throws InstantiationException, IllegalAccessException, Exception
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a read");
        }
        
        CrudDAOable<Camionero> cDAO = new CamioneroDAOImpl();
        return cDAO.readWhere(whereSQL);
    }
    
    public void update(Camionero c) throws InstantiationException, IllegalAccessException, Exception
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a update");
        }

        CrudDAOable<Camionero> cDAO = new CamioneroDAOImpl();
        cDAO.update(c);           
    }    


    public void delete(Camionero c) throws InstantiationException, IllegalAccessException, Exception
    {
        if (FactoriaServiciosImpl.getFactoria().isDEBUG())
        {
            System.out.println(this.getClass().getCanonicalName() + " Entrando a delete");
        }

        CrudDAOable<Camionero> cDAO = new CamioneroDAOImpl();
        cDAO.delete(c);           
    }        
    
    public int getRutaByRut(String rut) throws IllegalAccessException, Exception
    {
        CamioneroDAOImpl cDAO = new CamioneroDAOImpl();
        return cDAO.getRutaByRut(rut);
    }
    
    public Camionero getCamionero(Camionero cli) throws InstantiationException, IllegalAccessException, Exception
    {
        CamioneroDAOImpl cDAO = new CamioneroDAOImpl();
        return cDAO.read(" where rut='" + cli.getRut()+ "'");
    }

    
}
