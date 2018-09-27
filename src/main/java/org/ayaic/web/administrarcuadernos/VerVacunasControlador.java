package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Controller
public class VerVacunasControlador {

    private final MiFacade mi;

    public VerVacunasControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerVacunas.do")
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
        modelo.put("dato", cliente);

        if ("Vacunas a Excel".equals(sAccion) || "VacunasDentro".equals(sAccion) || "VacunasFuera".equals(sAccion) || "Vacunas2".equals(sAccion)) {
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

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);

                if ("VacunasDentro".equals(sAccion)) {
                    dato.setBcg(0);
                }
                if ("VacunasFuera".equals(sAccion)) {
                    dato.setBcg(1);
                }
                dato.setTipo("U");
                modelo.put("estab", Estab1);

                if (datosconsul.getId_cargo() == 7) {
                    List listaVac1 = this.mi.getVerVacunasTodos(dato);
                    modelo.put("listaCobros", listaVac1);
                } else {
                    List listaVac = this.mi.getVerVacunas(dato);
                    modelo.put("listaCobros", listaVac);
                }
                if("Vacunas a Excel".equals(sAccion)){
                    return new ModelAndView(new excel2(), modelo);
                }
                if ("VacunasDentro".equals(sAccion) || "VacunasFuera".equals(sAccion)) {
                    return new ModelAndView(new VerVacunasPDF(), modelo);
                } else {
                    return new ModelAndView(new VerVacunas1HojaPDF(), modelo);
                }

            }
        }
        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
    
    private static class excel2 extends AbstractXlsView {
        
        @Override
        protected void buildExcelDocument(Map<String, Object> map, Workbook wrkbk, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            
            Sheet sheet = wrkbk.createSheet("vacunas");

            Row fila = sheet.createRow(0);
            Cell celda;

        String[] titulos = {"No", "HCL", "NumRegistro", "fechaAten", "Nombres", "fechaNac", "sexo", "edad", "mes", "dia", 
                "bcg", "polio", "penta", "antirota", "neumococica", "DT", "H1N1", "FA", "HepatitisB", "SRP", 
                "SR", "VPH", "IdPor"};
        int i;

        for (i  = 0; i< titulos.length ; i++) {
                celda = fila.createCell(i);
            celda.setCellValue(titulos[i]);
        }
        // Nueva fila
        fila  = sheet.createRow(1);
        List listarVac = (List) map.get("listaCobros");
        for (i  = 0; i< listarVac.size (); i++) {
                Cuadernos dato = (Cuadernos) listarVac.get(i);
            Row filas = sheet.createRow(i + 5);
            for (int j = 0; j < 18; j++) {
                HSSFRow row = (HSSFRow)sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(dato.getId_historial());
                    row.createCell(2).setCellValue(dato.getRegistro());
                    row.createCell(3).setCellValue((new StringBuilder()).append(dato.getFechap().getDate()).append("/").append(dato.getFechap().getMonth() + 1).append("/").append(dato.getFechap().getYear() + 1900).toString());
                    row.createCell(4).setCellValue(dato.getNombres());
                    row.createCell(5).setCellValue((new StringBuilder()).append(dato.getFec_nacimiento().getDate()).append("/").append(dato.getFec_nacimiento().getMonth() + 1).append("/").append(dato.getFec_nacimiento().getYear() + 1900).toString());
                    if(dato.getId_tipo_sexo() == 1)
                        row.createCell(6).setCellValue("F");
                    else
                        row.createCell(6).setCellValue("M");
                    row.createCell(7).setCellValue(dato.getEdad());
                    row.createCell(8).setCellValue(dato.getMes());
                    row.createCell(9).setCellValue(dato.getDia());
                    if(dato.getBcg() == 0)  row.createCell(10).setCellValue(" ");
                    if(dato.getBcg() == 1)  row.createCell(10).setCellValue("Si");
                    if("N".equals(dato.getPolio()))  row.createCell(11).setCellValue(" ");
                    if("P".equals(dato.getPolio()))  row.createCell(11).setCellValue("1ra");
                    if("S".equals(dato.getPolio()))  row.createCell(11).setCellValue("2da");
                    if("T".equals(dato.getPolio()))  row.createCell(11).setCellValue("3ra");
                    if("C".equals(dato.getPolio()))  row.createCell(11).setCellValue("4ta");
                    if("Q".equals(dato.getPolio()))  row.createCell(11).setCellValue("5ta");
                    if("N".equals(dato.getPenta()))  row.createCell(12).setCellValue(" ");
                    if("P".equals(dato.getPenta()))  row.createCell(12).setCellValue("1ra");
                    if("S".equals(dato.getPenta()))  row.createCell(12).setCellValue("2da");
                    if("T".equals(dato.getPenta()))  row.createCell(12).setCellValue("3ra");
                    if("C".equals(dato.getPenta()))  row.createCell(12).setCellValue("4ta");
                    if("Q".equals(dato.getPenta()))  row.createCell(12).setCellValue("5ta");
                    if("N".equals(dato.getAnti()))   row.createCell(13).setCellValue(" ");
                    if("P".equals(dato.getAnti()))   row.createCell(13).setCellValue("1ra");
                    if("S".equals(dato.getAnti()))   row.createCell(13).setCellValue("2da");
                    if("T".equals(dato.getAnti()))   row.createCell(13).setCellValue("3ra");
                    if("N".equals(dato.getBacterias()))  row.createCell(14).setCellValue(" ");
                    if("P".equals(dato.getBacterias()))  row.createCell(14).setCellValue("1ra");
                    if("S".equals(dato.getBacterias()))  row.createCell(14).setCellValue("2da");
                    if("T".equals(dato.getBacterias()))  row.createCell(14).setCellValue("3ra");
                    if("N".equals(dato.getMujerdt()))    row.createCell(15).setCellValue(" ");
                    if("P".equals(dato.getMujerdt()))    row.createCell(15).setCellValue("1ra");
                    if("S".equals(dato.getMujerdt()))    row.createCell(15).setCellValue("2da");
                    if("T".equals(dato.getMujerdt()))    row.createCell(15).setCellValue("3ra");
                    if("C".equals(dato.getMujerdt()))    row.createCell(15).setCellValue("4ta");
                    if("Q".equals(dato.getMujerdt()))    row.createCell(15).setCellValue("5ta");
                    if(dato.getCancer() == 0)   row.createCell(16).setCellValue(" ");
                    if(dato.getCancer() == 1)   row.createCell(16).setCellValue("Pers.Salud");
                    if(dato.getCancer() == 2)   row.createCell(16).setCellValue("Enf.Cron.");
                    if(dato.getCancer() == 3)   row.createCell(16).setCellValue("MujEmbar");
                    if(dato.getCancer() == 4)   row.createCell(16).setCellValue("Mayor60Anios");
                    if(dato.getCancer() == 5)   row.createCell(16).setCellValue("OtrosGruo");
                    if(dato.getCancer() == 6)   row.createCell(16).setCellValue("6a11m 1ra");
                    if(dato.getCancer() == 7)   row.createCell(16).setCellValue("7a11m 2da");
                    if(dato.getCancer() == 8)   row.createCell(16).setCellValue("12a23m DU");
                    if(dato.getCancer() == 9)   row.createCell(16).setCellValue("2a4anios");
                    row.createCell(17).setCellValue(dato.getFama());
                    if(dato.getAuto() == 0)     row.createCell(18).setCellValue(" ");
                    if(dato.getAuto() == 1)     row.createCell(18).setCellValue("1ra");
                    if(dato.getAuto() == 2)     row.createCell(18).setCellValue("2da");
                    if(dato.getAuto() == 3)     row.createCell(18).setCellValue("3ra");
                    if("0".equals(dato.getSrp()))   row.createCell(19).setCellValue(" ");
                    if("1".equals(dato.getSrp()))   row.createCell(19).setCellValue("1ra");
                    if("2".equals(dato.getSrp()))   row.createCell(19).setCellValue("2da");
                    if(dato.getSr() == 0)    row.createCell(20).setCellValue(" ");
                    if(dato.getSr() == 1)    row.createCell(20).setCellValue("1ra");
                    if(dato.getVph() == 0)   row.createCell(21).setCellValue(" ");
                    if(dato.getVph() == 1)   row.createCell(21).setCellValue("1ra");
                    if(dato.getVph() == 2)   row.createCell(21).setCellValue("2da");
                    row.createCell(22).setCellValue(dato.getId_persona());

            }
        }
    }  

    }

}
