/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Laugo
 */
public class CConexion {
   
    Connection conectar = null;
    
    String usuario="root";
    String contrasenia="";
    String bd="bdusuarios1";
    String ip="localhost";
    String puerto="3306";
    
    String cadena ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null,"Se conectó correctamente a la BD");
            
            
        }catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"NO se conectó correctamente a la BD");
        }
        return conectar;
         
    }
    
    public void cerrarConexion() {
        
        try {
    if (conectar!= null && !conectar.isClosed()) {
        conectar.close();
        JOptionPane.showMessageDialog(null, "Conexión cerrada");
        
    }
   }catch (HeadlessException | SQLException e) {
       
        JOptionPane.showMessageDialog(null, "NO es posible cerrar conexión");
   }}
    


    public void AgregarUsuario(JTextField Nombre,JTextField Apellido,JComboBox comboSexo,JTextField Edad,JDateChooser FNacimiento, File Foto) {
        
        CConexion objetoConexion = new CConexion();
        String consulta="insert into usuarios (Nombre,Apellido,comboSexo,Edad,FNacimiento,Foto) values (?,?,?,?,?,?);";
        
        try {
    
        FileInputStream fis = new FileInputStream(Foto);
        
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setString(1, Nombre.getText());
        cs.setString(2, Apellido.getText());
        
        int idSexo= (int) comboSexo.getClientProperty(comboSexo.getSelectedItem());
        
        cs.setInt(3, idSexo);
        cs.setInt(4, Integer.parseInt(Edad.getText()));
        
        Date fechaSeleccionada = FNacimiento.getDate();
        
        java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
        
        cs.setDate(5,fechaSQL);
        
        cs.setBinaryStream(6, fis,(int)Foto.length());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Se guardo correctamente el usuario");
        
      
         } catch (Exception e) {
                 
                  JOptionPane.showMessageDialog(null, "error al guardar, error: "+e.toString());
                
            }
    }
    
}


        
