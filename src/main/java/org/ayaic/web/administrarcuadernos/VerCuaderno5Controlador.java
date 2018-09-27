package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerCuaderno5Controlador {

    private final MiFacade mi;

    public VerCuaderno5Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuaderno5.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("accion");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        if ("Cuaderno Internaciones".equals(sAccion)) {
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String id_sala = request.getParameter("id_sala");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_persona(cliente.getId_persona());
            dato.setTipo("C");
            dato.setAqv(Integer.parseInt(mes));
            dato.setAuto(Integer.parseInt(anio));
            dato.setBcg(Integer.parseInt(id_sala));
            List listaCobros = this.mi.getVerCuadernoInterServicio(dato);
            modelo.put("listaCobros", listaCobros);
            return new ModelAndView(new VerCuadernoG4PDF(), modelo);
        }

        if ("Cuaderno No 5".equals(sAccion) || "C-4.Internaciones".equals(sAccion) || "ExportarExcel".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);
                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("U");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);

//                if ("ExportarExcel".equals(sAccion)) {
//                    dato.setTipo("P");
//                    dato.setSuma1(1);
//                    dato.setSuma2(99999);
//                    if (cliente.getId_piso() != 0) {
//                        dato.setSuma1(cliente.getId_piso());
//                        dato.setSuma2(cliente.getId_piso());
//                    }
//                    List listaCobros = this.mi.getVerCuaderno5Piso(dato);
//                    modelo.put("listaCobros", listaCobros);
//
//                    return new ModelAndView(new excel(), modelo);
//                }

                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                    List listaCobros = this.mi.getVerCuaderno5Todos(dato);
                    modelo.put("listaCobros", listaCobros);
                }
                if ("C-4.Internaciones".equals(sAccion)) {
                    List listaCobros = this.mi.getVerCuaderno5(dato);
                    modelo.put("listaCobros", listaCobros);
                    return new ModelAndView(new VerCuadernoG4PDF(), modelo);
                } else {
                    List listaCobros = this.mi.getVerCuaderno5(dato);
                    modelo.put("listaCobros", listaCobros);
                    return new ModelAndView(new VerCuaderno5PDF(), modelo);
                }

            }
        }

        if ("CuadernoNo 5".equals(sAccion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("dato", cliente);

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("U");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);
                List listaCobros = this.mi.getVerCuaderno5(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                }
                List listaCobros2 = this.mi.getVerCuaderno5Todos(dato);
                modelo.put("listaCobros", listaCobros2);
                return new ModelAndView(new VerCuaderno5_2014PDF(), modelo);
            }
        }

        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }

//    private static class excel extends AbstractExcelView {
//
//        protected void buildExcelDocument(Map map, HSSFWorkbook hSSFWorkbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//
//            HSSFWorkbook objWB = new HSSFWorkbook();
//
//            HSSFSheet sheet = hSSFWorkbook.createSheet("internados");
//
//            Row fila = sheet.createRow(0);
//            Cell celda;
//
//            String[] titulos = {"registro", "fecha_ingreso", "nombrepaciente", "edad", "nombre medico", "sexo", "mes", "dia", "dias", "diagnostico"};
//            int i;
//
//            for (i = 0; i < titulos.length; i++) {
//                celda = fila.createCell(i);
//                celda.setCellValue(titulos[i]);
//            }
//            // Nueva fila
//            fila = sheet.createRow(1);
//            List listardat = (List) map.get("listaCobros");
//            for (i = 0; i < listardat.size(); i++) {
//                Cuadernos dato = (Cuadernos) listardat.get(i);
//                Row filas = sheet.createRow(i + 5);
//                for (int j = 0; j < 18; j++) {
//                    HSSFRow row = sheet.createRow(i + 1);
//                    row.createCell(0).setCellValue(dato.getCadena1());  ////registro
//                    row.createCell(1).setCellValue(dato.getCadena2());  ////fechap
//                    row.createCell(2).setCellValue(dato.getNombres());    ////nombres
//                    row.createCell(3).setCellValue(dato.getEdad());  ////edad
//                    row.createCell(4).setCellValue(dato.getNombre());  ////nombre medico
//                    row.createCell(5).setCellValue(dato.getCadena1());  ////sexo
//                    row.createCell(6).setCellValue(dato.getSuma1());    ////
//                    row.createCell(7).setCellValue(dato.getMes());  ///mes
//                    row.createCell(8).setCellValue(dato.getDia());  ///dia
//                    row.createCell(9).setCellValue(dato.getDiasi());  ///diasi
//                    row.createCell(10).setCellValue(dato.getDiagnostico_ing());  ///diagnostico_ing
//                    row.createCell(11).setCellValue(dato.getFec_ingreso());  ///fec_ingreso
//                    row.createCell(12).setCellValue(dato.getFec_egreso());  ///fec_egreso
//                }
//            }
//
//            String strNombreArchivo = "C:/libro1.xls";
//            File objFile = new File(strNombreArchivo);
//            FileOutputStream archivoSalida = new FileOutputStream(objFile);
//            objWB.write(archivoSalida);
//            archivoSalida.close();
//
//        }
//    }

}
