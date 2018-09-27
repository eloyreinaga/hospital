package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Proveedores;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecepcionMedControlador {

    private final MiFacade mi;

    public RecepcionMedControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecepcionMed.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        String id_proveedor = request.getParameter("id_proveedor");
        String id_farmacia = request.getParameter("id_farmacia");
        String nombre = request.getParameter("nombres");
        String tipo = request.getParameter("tipo");
        String diaq1 = request.getParameter("mesq1");
        String mesq1 = request.getParameter("mesq1");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("rol", Integer.toString(cliente.getId_rol2()));
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
        modelo.put("area", cliente.getArea());
        modelo.put("tipodato", "0");
        modelo.put("programa", "progra");
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);

        if (datoestab.getCod_esta() == 200010) {
            modelo.put("id_programa", "13");
            modelo.put("expedido", "P");
            modelo.put("tipodato", "4");
        } else {
            modelo.put("id_programa", "6");
        }
        if ("".equals(tipo) || tipo == null) {
            tipo = "0";
        }

        modelo.put("nombres", nombre);
        modelo.put("num_cladoc", "0");
        modelo.put("orden", "0");
        modelo.put("id_historial", "0");
        modelo.put("id_proveedor", id_proveedor);
        modelo.put("id_farmacia", id_farmacia);
        modelo.put("fecha1", fecha1);
        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);
        modelo.put("tipodato", tipo);
        modelo.put("id_farmaciauser", Integer.toString(cliente.getId_farmacia()));
        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        datofar.setId_persona(cliente.getId_persona());
        datofar.setTipo("A");
        datofar.setId_estado("A");
        List listarFarmaciaAsig = this.mi.getListarFarmaciasAsig(datofar); // getListarFarmaciasAsig
        modelo.put("listafarAsig", listarFarmaciaAsig);

        Proveedores listaprov = new Proveedores();
        listaprov.setCod_esta(cliente.getCod_esta());
        List listarprov = this.mi.getListarProveedores(listaprov);
        modelo.put("listarprov", listarprov);

        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());
        dato.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(dato);
        modelo.put("datoItem", datoItem);

        if ("entregarya2".equals(accion)) {
            String expedido = request.getParameter("expedido");
            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            String orden = request.getParameter("orden");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");

            int iAnio1 = Integer.parseInt(anioi) - 1900;
            int iMes1 = Integer.parseInt(mesi) - 1;
            int iDia1 = Integer.parseInt(diai);

            Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);

            modelo.put("expedido", expedido);
            modelo.put("nombres", nombre);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("orden", orden);
            modelo.put("fecha1", dFechaini1);
        }

        if ("Siguiente".equals(accion2)) {
            String id_pedido = request.getParameter("id_pedido");
            String id_programa = request.getParameter("id_programa");
            String expedido = request.getParameter("expedido");
            String nombres = request.getParameter("nombres");
            String num_cladoc = request.getParameter("num_cladoc");
            String orden = request.getParameter("orden");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");

            if("".equals(nombres) || nombres == null) {
                return new ModelAndView("Aviso", "mensaje", "Debe escribir una descripcion en Nombres");
            }
            if("".equals(id_programa) || id_programa == null) {
                id_programa = "0";
            }
            if("".equals(id_farmacia) || id_farmacia == null) {
                id_farmacia = "0";
            }

            int iAnio1 = Integer.parseInt(anioi) - 1900;
            int iMes1 = Integer.parseInt(mesi) - 1;
            int iDia1 = Integer.parseInt(diai);
            int ihora1 = fecha1.getHours();
            int iminuto1 = fecha1.getMinutes();
            int isegundo1 = fecha1.getSeconds();
            Date Fecha1 = new Date(iAnio1, iMes1, iDia1, ihora1, iminuto1, isegundo1);
            long fechaInicial = Fecha1.getTime();
            long fechaFinal = fecha1.getTime();
            long diferencia = fechaFinal - fechaInicial;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            if (fechaInicial > fechaFinal) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta, esta adelantada a HOY");
            }
            if (!"C.S. DISP. JESUS DE NAZARETH".equals(cliente.getEstablecimiento())) {
                if (diass > 90) {
                    return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar, NO puede ser anterior a 90 dias");
                }
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setId_dispensa(cliente.getId_persona());
            paciente.setId_proveedor(Integer.parseInt(id_proveedor));
            paciente.setId_persona(Integer.parseInt(id_programa)); //se guarda el programa solo en los ingresos, ajustes
            paciente.setNit(orden);
            paciente.setId_paciente(0);
            paciente.setNum_cladoc(num_cladoc);
            paciente.setId_estado("R");
            paciente.setId_rubro(1);
            paciente.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            paciente.setTipo(expedido);
            paciente.setId_tipo_far(1);
            paciente.setCadena1("E");
            if ("1".equals(tipo)) {
                paciente.setId_tipo_far(2);  ///ajuste positivo
                paciente.setCadena1("A"); ////ajuste positivo
            }
            if ("-1".equals(tipo)) {
                paciente.setId_tipo_far(3);
                paciente.setCadena1("A"); ////ajuste negativo
            }
            if ("4".equals(tipo)) { /////traspasos entre farmacias
                paciente.setId_tipo_far(4);
                paciente.setCadena1("T"); ////traspasos entre farmacias
            }
            if ("6".equals(tipo)) {  ///// ingresos caja chica
                paciente.setId_tipo_far(1);////caja chica
                paciente.setCadena1("C"); ////caja chica
            }

            ///paciente.setTipo("C");  /////setCrearPeedidoCNS
            paciente.setCadena2("00");
            paciente.setCadena3("000");
            paciente.setCadena4("00");
            paciente.setCadena5("00");
            paciente.setCadena6("00");
            paciente.setCadena7("00");
            paciente.setCadena8("00");

            paciente.setFec_registro(Fecha1);
            paciente.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(paciente);
            id_pedido = Integer.toString(this.mi.getNumPedido(paciente));

            modelo.put("expedido", expedido);
            modelo.put("tipodato", tipo);
            modelo.put("programa", "progra");
            modelo.put("nombres", nombre);
            modelo.put("num_cladoc", num_cladoc);
            modelo.put("orden", orden);
            modelo.put("recepcion", "no");
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_programa", id_programa);
            modelo.put("id_farmacia", id_farmacia);
            modelo.put("datos", paciente);
            return new ModelAndView("administrarfarmacias/RecepcionMedicamento", modelo);
        }

        //return new ModelAndView("administrarfarmacias/RecepcionMed2", modelo);  //para el anterior donde se mete todo
        return new ModelAndView("administrarfarmacias/RecepcionMed", modelo);

    }
}
