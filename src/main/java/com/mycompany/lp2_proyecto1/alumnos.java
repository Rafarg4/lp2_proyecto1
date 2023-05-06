/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lp2_proyecto1;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
      public void MostrarAlumnos(JTable paramtabla){
        conexion  objetoConexion = new conexion(); 
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");
        model.addColumn("Direccion");
        paramtabla.setModel(model);
        sql="select * from alumnos";
        String []  datos = new String[5];
        Statement st;
         try{
             st=objetoConexion.establecerConexcion().createStatement();
             ResultSet rs =st.executeQuery(sql);
             while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4); 
             datos[4]=rs.getString(5);
             model.addRow(datos);
         }
             paramtabla.setModel(model);
            
        } catch(Exception e ){
            JOptionPane.showMessageDialog(null,"Error al mostrar los registros"+e.toString());
        }       
    }
      public void SelecionarAlumno(JTable paramtabla,JTextField paramid,JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion){
          try{
          int fila = paramtabla.getSelectedRow();
          if(fila>=0){
           paramid.setText(paramtabla.getValueAt(fila,0).toString());
           paramnombre.setText(paramtabla.getValueAt(fila,1).toString());
           paramapellido.setText(paramtabla.getValueAt(fila,2).toString());
           paramedad.setText(paramtabla.getValueAt(fila,3).toString());
           paramdireccion.setText(paramtabla.getValueAt(fila,4).toString());
          }else{
           JOptionPane.showMessageDialog(null,"Error al seleccionar");
          }
          }catch(Exception e){
               JOptionPane.showMessageDialog(null,"Error al seleccionar,error:"+e.toString());
          }
        }
          public void Editar (JTextField paramid,JTextField paramnombre, 
            JTextField paramapellido, JTextField paramedad,
            JTextField paramdireccion){
            setId(Integer.parseInt(paramid.getText()));
            setNombre(paramnombre.getText());
            setApellido(paramapellido.getText());
            setEdad(paramedad.getText());
            setDireccion(paramdireccion.getText());
            conexion objetoConexion = new conexion();
            String editar="UPDATE alumnos set alumnos.nombre=?, alumnos.apellido=?,alumnos.edad=?,alumnos.direccion=? where alumnos.id=?";
            try{
       CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(editar);
       cs.setString(1,getNombre());
       cs.setString(2,getApellido());
       cs.setString(3,getEdad());
       cs.setString(4,getDireccion());
       cs.setInt(5,getId());
       cs.execute();
        JOptionPane.showMessageDialog(null,"Se edito correctamente");
            }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error al editar, error:"+e.toString());
                    }
          }
  public void eliminar(JTextField paramid){
       conexion objetoConexion = new conexion();
       setId(Integer.parseInt(paramid.getText()));
       String eliminar="DELETE FROM alumnos WHERE id= ?";
  try{
 CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(eliminar);
       cs.setInt(1,getId());
       cs.execute();
        JOptionPane.showMessageDialog(null,"Eliminado correctamente");
  }catch(Exception e){
       JOptionPane.showMessageDialog(null,"Error al eliminar, error:"+e.toString());
      } 
    }
  }

