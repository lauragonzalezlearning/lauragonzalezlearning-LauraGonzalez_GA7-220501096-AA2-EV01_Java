/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Laugo
 */
public class CUsuario {
    
    int idSexo;

    public void estableceridSexo (int idSexo) {
        this.idSexo = idSexo;
    }
    
    
    
    public void MostrarSexoCombo(JComboBox comboSexo){
        
        Clases.CConexion objetoConexion = new Clases.CConexion();
        
        String sql = "select * from sexo";
        
        Statement st;
        
        try {
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            comboSexo.removeAllItems();
            
            while (rs.next()) {
                String nombreSexo = rs.getString("sexo");
                this.estableceridSexo(rs.getInt("id"));
                
                
                comboSexo.addItem(nombreSexo);
                comboSexo.putClientProperty(nombreSexo, idSexo);
                
            }
                    
        } catch (SQLException e) {
        
            JOptionPane.showMessageDialog(null, "Error al mostrar el sexo: "+e.toString());
            
        }
        finally {
            objetoConexion.cerrarConexion();
            
        
        }
        }
    }

