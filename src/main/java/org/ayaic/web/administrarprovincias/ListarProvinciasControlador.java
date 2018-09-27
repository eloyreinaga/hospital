package org.ayaic.web.administrarprovincias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarProvinciasControlador {

    private final MiFacade mi;

    public ListarProvinciasControlador(MiFacade mi) {
        this.mi = mi;
    }
        
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/rp";
        String user = "postgres";
        String password = "san_juan123";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectString, user, password);        
        return conn;
    }

    @RequestMapping("/ListarProvincias.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse reponse) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        List listarProvincias = this.mi.getListarProvincias();
        modelo.put("listarProvincias", listarProvincias);

        return new ModelAndView("administrarprovincias/ListarProvincias", modelo);

    }
}
