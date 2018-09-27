package org.ayaic.web.administrarcuadernos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.springframework.stereotype.Controller;

@Controller
public class ConexionBDDControlador {

    private String password = "ctrlaltsuprfdc";
    //nombre de la base de datos Acces con extension *.mdb o *.accdb
    private String dbName = "dbSalmi.mdb";
    //direccion de la base de datos
    //private String bd = System.getProperty("D:\\SNS\\SALMI\\") + dbName + ";PWD=" + password;
    private String bd = "D:/SALMI/" + dbName + ";PWD=" + password;
    //driver para base de datos Access 2000, 2003, 2007, 2010     192.168.90.21sybase
    private String url = "jdbc:odbc:Driver=Microsoft Access Driver(*.mdb, *.accdb);DBQ=" + bd;
    private Connection conn = null;

    private static Connection cn = null;
    private static String URLAccess = "jdbc:odbc:cuatro";
    private static String usuario = "usuarioaccess";
    private static String contrasena = "ctrlaltsuprfdc";
    /*
    public static Connection ConexionSalmiOdbc() throws SQLException, ClassNotFoundException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        cn = DriverManager.getConnection(URLAccess,usuario,contrasena);
        return cn;
    }
     */

    private static Connection cnsql = null;
    private static String URLSql = "jdbc:odbc:snismorbi";
    private static String usuariosql = "usuariosql";
    private static String contrasenasql = "sa";

    public static Connection ConexionSqlSoaps() throws SQLException, ClassNotFoundException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

        cnsql = DriverManager.getConnection(URLSql, usuariosql, contrasenasql);
        return cnsql;
    }

    public Connection conectarSalmi() {//////////////Se conecta directamente
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/SALMI/bdSalmi.mdb;PWD=ctrlaltsuprfdc";
            conn = DriverManager.getConnection(strConect, "", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } finally {
            return conn;
        }
    }

    public Connection conectar() {//////////////Se conecta directamente
        //Connection conn = null;
        try {
            //obtenemos el driver para Access 
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //obtenemos la conexion 
            //conn = DriverManager.getConnection(url);
            //si la conexion tuvo exito
            String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/SALMI/bdSalmi.mdb;PWD=ctrlaltsuprfdc";
            conn = DriverManager.getConnection(strConect, "", "");
            //JOptionPane.showMessageDialog(null, "Conectado: ");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } finally {
            return conn;
        }
    }

    public Connection conectarSnis() {
        //Connection conn = null;
        try {
            //obtenemos el driver para Access 
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=C:/SNIS2014/snis2014.mdb;PWD=96541237";
            conn = DriverManager.getConnection(strConect, "", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } finally {
            return conn;
        }
    }

    /*
    public Connection conectarSyBase(){
    //Connection conn = null;
        
    try{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        url = "jdbc:jtds:sybase://sybasehost:2638/siscnsr";
        System.out.println(url);
        java.sql.Connection conn = DriverManager.getConnection(url, "dba", "sql");
        System.out.println("Connection successful!");
    }
    catch(SQLException se){
        se.printStackTrace();
        System.out.println("Connection unsuccessful");
    }
    catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
        System.out.println("Class not found");
    }
     
    return conn;
         
  }

     */
    public void desconectar() {
        conn = null;
        System.out.println("Desconexion a base de datos listo...");
    }

    public Connection conectarSoap() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=DBestadistica;user=sa;password=;";
            Connection conn = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        } finally {
            return conn;
        }
    }

    /*
     try {
   //import driver
   Class.forName("net.sourceforge.jtds.jdbc.Driver");
   //connection to database
   Connection conn=DriverManager.getConnection("jdbc:jtds:sqlserver://NVTANMARB:1433;databaseName=Student;user=sa;password=1234567");
   Statement state=conn.createStatement();
   String sql="select * from TableOfStudents";
   ResultSet result=state.executeQuery(sql);
   while (result.next())
   {
    System.out.println(result.getString("ID")+"\t"+result.getString("Name"));
   }
  } catch (ClassNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } 
 }
}
     */
}
