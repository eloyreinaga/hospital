package org.ayaic.web.administrarpacientes;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Controller
public class ConexionBDControlador {

    private Connection conexion;
    private static Connection cnsql = null;
    //jdbc.url=jdbc:postgresql://localhost:5432/obrero
    //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/SALMI/bdSalmi.mdb;PWD=ctrlaltsuprfdc";
    private static String URLSql = "jdbc:odbc:cn33s";
    private static String usuariosql = "usuariosql";
    private static String contrasenasql = "sa";

    public static Connection ConexionSqlCNS() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnsql = DriverManager.getConnection(URLSql, usuariosql, contrasenasql);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Se conecto a la base de datos");
        }
        return cnsql;
    }/////otra conexion que me sirvio

    public void cerrarConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
        };
    }

}
