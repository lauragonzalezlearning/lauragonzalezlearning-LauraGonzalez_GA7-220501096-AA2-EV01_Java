/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

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
            Class.forName("com.mysql.jdbc.Driver");
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
   }catch (Exception e) {
       
        JOptionPane.showMessageDialog(null, "NO es posible cerrar conexión");
   }
}
}
    
    
    
    

