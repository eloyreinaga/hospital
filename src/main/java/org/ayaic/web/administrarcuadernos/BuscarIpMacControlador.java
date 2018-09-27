package org.ayaic.web.administrarcuadernos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BuscarIpMacControlador {

    private String password = "ctrlaltsuprfdc";
    //nombre de la base de datos Acces con extension *.mdb o *.accdb
    private String dbName = "dbSalmi.mdb";
    //direccion de la base de datos
    //private String bd = System.getProperty("D:\\SNS\\SALMI\\") + dbName + ";PWD=" + password;
    private String bd = "D:/SALMI/" + dbName + ";PWD=" + password;
    //driver para base de datos Access 2000, 2003, 2007, 2010
    private String url = "jdbc:odbc:Driver=Microsoft Access Driver(*.mdb, *.accdb);DBQ=" + bd;
    private Connection conn = null;

    public Connection conectar() {
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

}
