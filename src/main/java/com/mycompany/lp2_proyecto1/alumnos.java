/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lp2_proyecto1;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Acer
 */
public class alumnos {
    int id;
    String nombre;
    String apellido;
    String edad;
    String direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void InsertarAlumno(JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion){
        
            setNombre(paramnombre.getText());
            setApellido(paramapellido.getText());
            setEdad(paramedad.getText());
            setDireccion(paramdireccion.getText());
            conexion objetoConexion = new conexion();
            String Consulta ="INSERT INTO alumnos(nombre,apellido,edad,direccion) VALUES(?,?,?,?);";
            try{
               CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(Consulta);
               cs.setString(1,getNombre()); 
               cs.setString(2,getApellido());
               cs.setString(3,getEdad());
               cs.setString(4,getDireccion());
               cs.execute();
               JOptionPane.showMessageDialog(null,"Se creo correctamente!");
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null,"Hubo un error al intentar guardar en la base de datos!,error:"+e.toString());
            }
            
        
    }
}
