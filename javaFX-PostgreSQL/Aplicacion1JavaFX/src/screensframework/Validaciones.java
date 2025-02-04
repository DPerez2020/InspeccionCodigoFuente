package screensframework;

import screensframework.DBConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Validaciones {
    
    private Pattern p;
    private Matcher m;
    
    public Validaciones () {}
    
    
    /******* VALIDAR VACIOS ************/
    public boolean validarVacios(String datos, String nombreCampo) {
        
        if (datos.equals("")) {            
            JOptionPane.showMessageDialog(null, "El campo "+nombreCampo+" no puede estar vacio");
            return false;
        }
        
        return true;
    }
    
    public boolean validarVacios(int datos, String nombreCampo) {
        
        if (datos==-1) {
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento del combobox: "+nombreCampo);
            return false;
        }
        
        return true;
    }
    /*****VALIDA CARACTERES ESPECIALES********/
    public boolean ValidarCaracteresEspeciales(String cadena,String nombreCampo){
         p = Pattern.compile("[$%&|<>#='\":]");
         m=p.matcher(cadena);
        if (m.find()){
            JOptionPane.showMessageDialog(null, "El texto introducido: "+cadena+" tiene caracteres invalidos "
                    + " para el campo "+nombreCampo);
            return false;
        }
        return true;               
    }
    
    /********* VALIDAR LONGITUD ****************/
    public boolean validarMaximo(String datos, String nombreCampo, int maximo, int minimo) {
       
        if (!datos.isEmpty()) {
            if (datos.length() > maximo || datos.length() < minimo) {
                JOptionPane.showMessageDialog(null, "Debe ser entre "+minimo+" y "+maximo+" caracteres para "+nombreCampo);
                return false;
            }
        }
        return true;
    }
    
    /******* VALIDAR CORREO ************/
    public boolean validarCorreo(String datos) {
        
        p = Pattern.compile("^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$");
        m = p.matcher(datos);
        
        if (!datos.isEmpty()) {
            if (!m.find()) {
                JOptionPane.showMessageDialog(null, "La direccion de correo es correcta");
                return false;
            }
        }
        return true;
    }

    public boolean correoNoExiste(String correo)  {

        try{
            Connection connection =  DBConnection.getConnection();

            String sql = "SELECT * FROM USUARIOS WHERE correo = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,correo);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){


                JOptionPane.showMessageDialog(null,"Este correo ya existe");
                return false;
            }else{


                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
    
    /****** SOLO LETRAS *********/
    public boolean soloLetras(String datos) {
        
        p = Pattern.compile("^[a-zA-Z]*$");
        m = p.matcher(datos); 
        if (!datos.isEmpty()) {
            if (!m.find()) {
                JOptionPane.showMessageDialog(null, "Solo se admiten letras");
                return false;
            }
        }
        return true;
    }
    
    /****** SOLO NUMEROS *********/
    public boolean soloNumeros(String datos) {
        
        p = Pattern.compile("^[0-9A-Z]*$");
        m = p.matcher(datos); 
        if (!datos.isEmpty()) {
            if (!m.find()) {
                JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
                return false;
            }
        }
        return true;
    }
    
    /****** PASSWORD *********/
    public boolean validaPassword(String pass1, String pass2) {
        
        if (pass1 == null ? pass2 != null : !pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas deben ser iguales");
            return false;
        }
        return true;
    }
    
    public boolean validarPrecio(int precio){
   
            return precio > 0 && precio<2147483647;
        
        }
    
}
