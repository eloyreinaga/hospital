package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenerarCodigoControlador {

    private final MiFacade mi;

    public GenerarCodigoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CodigoControl.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sw = request.getParameter("sw");

        String id_paciente = request.getParameter("id_paciente");
        String nombres = request.getParameter("nombres");
        String medico = request.getParameter("medico");
        String carnet = request.getParameter("carnet");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");
        String num_cladoc = request.getParameter("num_cladoc");
        String id_pedido = request.getParameter("id_pedido");
        String precio_total = request.getParameter("precio_total");
        String nit = request.getParameter("nit");
        String ciu = request.getParameter("ciu");
        String swenfer = request.getParameter("swenfer");
        String swe = request.getParameter("swe");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));

        String fech = request.getParameter("fec");
        String sumatot1 = request.getParameter("precio");
        long na = Long.parseLong(datoestab.getNum_auto().trim());
        long fact = datoestab.getNum_fact();
        long nit2 = Long.parseLong(datoestab.getNit());
        String llave = datoestab.getLlave();

        Cuadernos datocod = new Cuadernos();
        datocod.setTipo_egreso(1);
        List codcontrol = this.mi.getCodigoControl(datocod);  ////getCodigoControl

        for (int i = 0; i < codcontrol.size(); i++) {

            Cuadernos datopac = (Cuadernos) codcontrol.get(i);

            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }
            if (dia1 < 10) {
                dia11 = "0" + dia11;
            }
            String ff = anio11 + mes11 + dia11;

            long fec = Long.parseLong(ff);
            long facts = datopac.getNum_auto2();
            int total = (int) (Math.round(((float) (datopac.getDs1()))));
            long carne = datopac.getNum_auto();
            codigoControl numer22 = new codigoControl(na, facts, carne, fec, total, llave);
            String codigo = numer22.verCodigoControl();

            Cuadernos dato = new Cuadernos();
            dato.setAccion("2");
            dato.setSuma1(datopac.getSuma1());
            dato.setSuma2(datopac.getSuma2());
            dato.setAspecto(codigo);
            this.mi.setModificarCuaderno1(dato);   ////setModificarCodigo

        }

        return new ModelAndView("Aviso", "mensaje", "Correcto Jeton");
    }
}
