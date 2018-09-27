package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Connection;
import java.sql.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExportarSNSControlador {

    private final MiFacade mi;

    public ExportarSNSControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ExportarSNS.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion = request.getParameter("accion");
        int iAnio1 = 0;
        int iMes1 = 0;
        int iDia1 = 0;
        int iAnio2 = 0;
        int iMes2 = 0;
        int iDia2 = 0;
        Date fecha1 = new Date();
        int num = 10;
        int nummov = 10;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        if ("Exportar SNS".equals(accion)) {
            ///return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            ///ConexionBDControlador conn  = new ConexionBDControlador();
            ///Connection c=conn.conectarSyBase();
        }

        if ("Borrar SalmiSybase".equals(accion)) {
            ///ConexionBDControlador conn  = new ConexionBDControlador();
            ///Connection c=conn.conectarSyBase();
            ///Statement state=c.createStatement();

            ///String sql="select * from dba_cot_ctas_rio";
            ///ResultSet result=state.executeQuery(sql);
        }

        if ("Borrar Salmi".equals(accion)) {

            ConexionBDDControlador conn = new ConexionBDDControlador();
            Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
            PreparedStatement borrarStmt1 = c.prepareStatement("DELETE * FROM TblIMM");
            borrarStmt1.execute();
            borrarStmt1.close();
            PreparedStatement borrarStmt21 = c.prepareStatement("DELETE * FROM TblIMP");
            borrarStmt21.execute();
            borrarStmt21.close();
            PreparedStatement borrarStmt2 = c.prepareStatement("DELETE * FROM TblCPT");
            borrarStmt2.execute();
            borrarStmt2.close();
            PreparedStatement borrarStmt3 = c.prepareStatement("DELETE * FROM TblFOPO");
            borrarStmt3.execute();
            borrarStmt3.close();
            PreparedStatement borrarStmt4 = c.prepareStatement("DELETE * FROM TblFOPOs");
            borrarStmt4.execute();
            borrarStmt4.close();
            PreparedStatement borrarStmt22 = c.prepareStatement("DELETE * FROM TblIMPs");
            borrarStmt22.execute();
            borrarStmt22.close();
            PreparedStatement borrarStmt5 = c.prepareStatement("DELETE * FROM TblIMMs");
            borrarStmt5.execute();
            borrarStmt5.close();
            PreparedStatement borrarStmt6 = c.prepareStatement("DELETE * FROM TblCPTs");
            borrarStmt6.execute();
            borrarStmt6.close();
            PreparedStatement borrarStmt8 = c.prepareStatement("DELETE * FROM TblPrestacion WHERE Preid>0");
            borrarStmt8.execute();
            borrarStmt8.close();
            PreparedStatement borrarStmt9 = c.prepareStatement("DELETE * FROM TblMovimiento");
            borrarStmt9.execute();
            borrarStmt9.close();
            PreparedStatement borrarStmt10 = c.prepareStatement("DELETE * FROM TblRelSalPre");
            borrarStmt10.execute();
            borrarStmt10.close();
            PreparedStatement borrarStmt11 = c.prepareStatement("DELETE * FROM TblRelSalDia");
            borrarStmt11.execute();
            borrarStmt11.close();
            PreparedStatement borrarStmt12 = c.prepareStatement("DELETE * FROM TblDiagnosticos WHERE DiaId>0");
            borrarStmt12.execute();
            borrarStmt12.close();
            PreparedStatement borrarStmt13 = c.prepareStatement("DELETE * FROM TblLotes WHERE Lotid>0");
            borrarStmt13.execute();
            borrarStmt13.close();
            PreparedStatement borrarStmt7 = c.prepareStatement("DELETE * FROM TblEntradas WHERE EntId>1");
            borrarStmt7.execute();
            borrarStmt7.close();
            PreparedStatement borrarStmt15 = c.prepareStatement("DELETE * FROM TblSalidas WHERE Salid>0");
            borrarStmt15.execute();
            borrarStmt15.close();
            PreparedStatement borrarStmt14 = c.prepareStatement("DELETE * FROM TblSeleccion WHERE SelId>0");
            borrarStmt14.execute();
            borrarStmt14.close();
            PreparedStatement borrarStmt16 = c.prepareStatement("DELETE * FROM TblPacientes WHERE Pacid>0");
            borrarStmt16.execute();
            borrarStmt16.close();
            PreparedStatement borrarStmt17 = c.prepareStatement("DELETE * FROM TblPrescriptores WHERE Psrid>0");
            borrarStmt17.execute();
            borrarStmt17.close();
            PreparedStatement borrarStmt18 = c.prepareStatement("DELETE * FROM TblMedicamentosNoe");
            borrarStmt18.execute();
            borrarStmt18.close();
            PreparedStatement borrarStmt19 = c.prepareStatement("DELETE * FROM TblInsumosNoe");
            borrarStmt19.execute();
            borrarStmt19.close();
            PreparedStatement borrarStmt20 = c.prepareStatement("DELETE * FROM TblReactivosNoe");
            borrarStmt20.execute();
            borrarStmt20.close();
            return new ModelAndView("Aviso", "mensaje", "SALMI borrado correctamente");
        }

        if ("P6 Inv. Inicial".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            Date fecha22 = new Date();
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                int Idmes = 73;
                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);
                String fechaM = sFechaini[1];
                if ("01".equals(sFechaini[1])) {
                    fechaM = sFechaini[2] + '-' + fechaM + '-' + sFechaini[0];////fffffffff
                } else {
                    fechaM = sFechaini[2] + '-' + sFechaini[1] + '-' + sFechaini[0];
                }

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                PreparedStatement guardarStmt2 = c.prepareStatement("INSERT INTO tblIMMs(IMMId,IMMNro,IMMMes,GesId) VALUES(?,?,?,?)");
                if ("01".equals(sFechaini[1])) {
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 12);///IMMNro
                    guardarStmt2.setString(3, "diciembre");///IMMMes
                    guardarStmt2.setInt(4, 4);///GesId
                }
                if ("02".equals(sFechaini[1])) {
                    Idmes = 74;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 1);///IMMNro
                    guardarStmt2.setString(3, "enero");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("03".equals(sFechaini[1])) {
                    Idmes = 75;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 2);///IMMNro
                    guardarStmt2.setString(3, "febrero");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("04".equals(sFechaini[1])) {
                    Idmes = 76;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 3);///IMMNro
                    guardarStmt2.setString(3, "marzo");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("05".equals(sFechaini[1])) {
                    Idmes = 77;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 4);///IMMNro
                    guardarStmt2.setString(3, "abril");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("06".equals(sFechaini[1])) {
                    Idmes = 78;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 5);///IMMNro
                    guardarStmt2.setString(3, "mayo");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("07".equals(sFechaini[1])) {
                    Idmes = 79;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 6);///IMMNro
                    guardarStmt2.setString(3, "junio");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("08".equals(sFechaini[1])) {
                    Idmes = 80;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 7);///IMMNro
                    guardarStmt2.setString(3, "julio");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("09".equals(sFechaini[1])) {
                    Idmes = 81;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 8);///IMMNro
                    guardarStmt2.setString(3, "agosto");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("10".equals(sFechaini[1])) {
                    Idmes = 82;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 9);///IMMNro
                    guardarStmt2.setString(3, "septiembre");///IMMMes   
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("11".equals(sFechaini[1])) {
                    Idmes = 83;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 10);///IMMNro
                    guardarStmt2.setString(3, "octubre");///IMMMes
                    guardarStmt2.setInt(4, 5);///GesId
                }
                if ("12".equals(sFechaini[1])) {
                    Idmes = 84;
                    guardarStmt2.setInt(1, Idmes);//IMMid
                    guardarStmt2.setInt(2, 11);///IMMNro
                    guardarStmt2.setString(3, "noviembre");///IMMMes   
                    guardarStmt2.setInt(4, 5);///GesId
                }

                guardarStmt2.execute();
                guardarStmt2.close();

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("19");   /////getInvMed
                List pressalmi = this.mi.getSalmi(dato);
                //ConexionBDControlador conn  = new ConexionBDControlador();
                //Connection c=conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                nummov = 10;
                /*
       for (int i = 0; i < pressalmi.size(); i++) {
          Cuadernos mov = (Cuadernos) pressalmi.get(i);
          if(mov.getSuma2()!=0){
               PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblMovimiento(MovId,MvsId,SelId,FinId,PrgId,EntId,PosId,SalId,NegId,MovEnt,MovPos,MovSal,MovNeg,MovSdo,MovCos,MovVal,LotId,MovRec,MovFch,UsuId) VALUES(?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");
                   guardarStmt.setInt(1, nummov);//MovId
                   guardarStmt.setInt(2, 10);//MvsId  10 son entradas 20 = inventario
                   guardarStmt.setInt(3, mov.getId_persona());//SelId
                   guardarStmt.setInt(4, 2);//FinId
                   guardarStmt.setInt(5, 1);//PrgId
                   guardarStmt.setInt(6, 0);///EntId
                   guardarStmt.setInt(7, 0);//PosId
                   guardarStmt.setInt(8, 0);///SalId
                   guardarStmt.setInt(9, 0);///NegId  
                   guardarStmt.setInt(10, 0);///MovPos
                   guardarStmt.setInt(11, 0);///MovPos
                   guardarStmt.setInt(12, 0);//MovSal
                   guardarStmt.setInt(13, 0);//MovNeg
                   guardarStmt.setInt(14, 0);//MovNeg
                   guardarStmt.setString(15,"0");//MovCos deberia ser double
                   guardarStmt.setString(16, "0");//MovVal
                   guardarStmt.setInt(17, 0);//LotId
                   guardarStmt.setInt(18, 0);//MovRec
                   guardarStmt.setString(19, fechaM);///fecha
                   guardarStmt.setInt(20, 1);///usuario
                   guardarStmt.execute();
                   guardarStmt.close();
                 nummov=nummov+1;  
            }       
       }
                 */
                for (int i = 0; i < pressalmi.size(); i++) {
                    Cuadernos mov = (Cuadernos) pressalmi.get(i);
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblIMM(IMMId,SelId,IMMSMS,IMMSMP,IMMSMV,IMMRMS,IMMRMP,IMMRMV,IMMCMS,IMMCMP,IMMCMV,IMMAPS,IMMAPP,IMMAPV,IMMANS,IMMANP,IMMANV) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)");
                    guardarStmt.setInt(1, Idmes);//IMMId
                    guardarStmt.setInt(2, mov.getId_persona());//SelId
                    guardarStmt.setInt(3, 0);//IMMSMS
                    guardarStmt.setInt(4, 0);//IMMSMP
                    guardarStmt.setInt(5, 0);//IMMSMV
                    guardarStmt.setInt(6, mov.getSuma1());///IMMRMS
                    guardarStmt.setInt(7, mov.getSuma2());//IMMRMP
                    if (mov.getSuma4() < 0) {
                        guardarStmt.setInt(8, (-1) * mov.getSuma4() + mov.getSuma3());//IMMRMV
                    } else {
                        guardarStmt.setInt(8, mov.getSuma3());//IMMRMV   
                    }
                    guardarStmt.setInt(9, 0);///IMMCMS
                    guardarStmt.setInt(10, 0);//IMMCMP 
                    guardarStmt.setInt(11, 0);//IMMCMV 
                    guardarStmt.setInt(12, 0);///IMMAPS
                    guardarStmt.setInt(13, 0);//IMMAPP
                    guardarStmt.setInt(14, 0);//IMMAPV
                    guardarStmt.setInt(15, 0);///IMMANS
                    guardarStmt.setInt(16, 0);//IMMANP
                    guardarStmt.setInt(17, 0);//IMMANV
                    guardarStmt.execute();
                    guardarStmt.close();
                }   ///////////7fin del inventario sumi

                try {
                    return new ModelAndView("Aviso", "mensaje", "Inventario Guardado Correctamente al SALMI");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Inventarios");
                }
            }
        }
        if ("P2 Sel. Prestacion".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setTipo("13");   ////getPrestacion
            List pressalmi = this.mi.getSalmi(dato);
            ConexionBDDControlador conn = new ConexionBDDControlador();
            Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

            num = 1;
            for (int i = 0; i < pressalmi.size(); i++) {
                Cuadernos persalmi2 = (Cuadernos) pressalmi.get(i);
                PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblPrestacion (PreId,PreCod,PreNom,FinId,PrgId,PreCla,PreAct) VALUES(?,?,?,?,?,?,?)");
                guardarStmt.setInt(1, num);//PreId
                guardarStmt.setString(2, persalmi2.getEstado());//PreCod
                guardarStmt.setString(3, persalmi2.getAspecto());//PreNom
                guardarStmt.setInt(4, 1);//FinId
                guardarStmt.setInt(5, 0);//PrgId
                guardarStmt.setInt(6, 0);//PreCla
                guardarStmt.setInt(7, 1);//PreAct
                guardarStmt.execute();
                guardarStmt.close();
                num = num + 1;
            }//fin del for
            try {
                return new ModelAndView("Aviso", "mensaje", "Prestaciones Guardado Correctamente al SALMI");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Prestaciones");
            }
        }

        if ("P3 MedInsReacNoe".equals(accion)) {////deben tener Tipo=M,I,R en SUSI y CodSumi igual al del SIAL
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setTipo("14");
            List salmi = this.mi.getSalmi(dato); ////getMedNoe
            ConexionBDDControlador conn = new ConexionBDDControlador();
            Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
            for (int i = 0; i < salmi.size(); i++) {
                Cuadernos persalmi2 = (Cuadernos) salmi.get(i);
                if ("M".equals(persalmi2.getTipo())) {
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblMedicamentosNoe (MedCod,MedNom,MedFof,MedCon) VALUES(?,?,?,?)");
                    guardarStmt.setString(1, persalmi2.getColor());
                    guardarStmt.setString(2, persalmi2.getReferido());
                    guardarStmt.setString(3, persalmi2.getContraref());
                    guardarStmt.setString(4, persalmi2.getTipodent());
                    guardarStmt.execute();
                    guardarStmt.close();
                }

            }//fin del for
            for (int i = 0; i < salmi.size(); i++) {
                Cuadernos persalmi2 = (Cuadernos) salmi.get(i);
                if ("I".equals(persalmi2.getTipo())) {
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblInsumosNoe (InsCod,InsNom,InsFof,InsCon) VALUES(?,?,?,?)");
                    guardarStmt.setString(1, persalmi2.getColor());
                    guardarStmt.setString(2, persalmi2.getReferido());
                    guardarStmt.setString(3, persalmi2.getContraref());
                    guardarStmt.setString(4, persalmi2.getTipodent());
                    guardarStmt.execute();
                    guardarStmt.close();
                }

            }//fin del for
            for (int i = 0; i < salmi.size(); i++) {
                Cuadernos persalmi2 = (Cuadernos) salmi.get(i);
                if ("R".equals(persalmi2.getTipo())) {
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblReactivosNoe (ReaCod,ReaNom,ReaFof,ReaCon) VALUES(?,?,?,?)");
                    guardarStmt.setString(1, persalmi2.getColor());
                    guardarStmt.setString(2, persalmi2.getReferido());
                    guardarStmt.setString(3, persalmi2.getContraref());
                    guardarStmt.setString(4, persalmi2.getTipodent());
                    guardarStmt.execute();
                    guardarStmt.close();
                }
            }//fin del for
            try {
                return new ModelAndView("Aviso", "mensaje", "Medicamentos, Insumos y Reactivos no escenciales, Guardado Correctamente al SALMI");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Prestaciones");
            }
        }

        if ("P1 Personas".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setTipo("1");  ///getPersonasSalmi
            List persalmi = this.mi.getSalmi(dato);
            ConexionBDDControlador conn = new ConexionBDDControlador();
            Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

            for (int i = 0; i < persalmi.size(); i++) {
                Cuadernos persalmi2 = (Cuadernos) persalmi.get(i);
                PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblPrescriptores (PsrId,PsrNom,PsrApe,PsrApm,PsrIde,DepCod,PrfId,CarId,PsrCel,PsrCor,PsrSts,PsrAct) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                guardarStmt.setInt(1, persalmi2.getId_cuaderno());////PsrId
                guardarStmt.setString(2, persalmi2.getAnti());///PsrNom
                guardarStmt.setString(3, persalmi2.getBacterias());////PsrApe
                guardarStmt.setString(4, persalmi2.getAspecto());///PsrApm
                guardarStmt.setString(5, persalmi2.getAccion());////PsrIde
                guardarStmt.setString(6, Integer.toString(datoestab.getId_departamento()));////DepCod
                guardarStmt.setString(7, "1");///DepCod
                guardarStmt.setString(8, "12");///PrfId
                guardarStmt.setString(9, "0");///PsrCel
                guardarStmt.setString(10, persalmi2.getCodigo());////PsrCor
                guardarStmt.setInt(11, 1);///PsrSts
                guardarStmt.setInt(12, 1);///PsrAct
                guardarStmt.execute();
                guardarStmt.close();
            }//fin del for
            try {
                return new ModelAndView("Aviso", "mensaje", "Personas Guardado Correctamente al SALMI");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Prescriptores");
            }
        }

        if ("P7 Pacientes".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                String fechaM = sFechaini[1];
                if ("01".equals(sFechaini[1])) {
                    fechaM = sFechaini[2] + '-' + fechaM + '-' + sFechaini[0];////fffffffff
                } else {
                    fechaM = sFechaini[2] + '-' + Integer.toString(Integer.parseInt(sFechaini[1]) - 1) + '-' + sFechaini[0];
                }
                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("2");   /////getPacientesSalmi
                List pacsalmi = this.mi.getSalmi(dato);

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

                for (int i = 0; i < pacsalmi.size(); i++) {
                    Cuadernos pacsalmi2 = (Cuadernos) pacsalmi.get(i);
                    iAnio1 = pacsalmi2.getFec_ingreso().getYear() + 1900;
                    iMes1 = pacsalmi2.getFec_ingreso().getMonth() + 1;
                    iDia1 = pacsalmi2.getFec_ingreso().getDate();
                    iAnio2 = pacsalmi2.getFec_egreso().getYear() + 1900;
                    iMes2 = pacsalmi2.getFec_egreso().getMonth() + 1;
                    iDia2 = pacsalmi2.getFec_egreso().getDate();

                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblPacientes (PacId,PacAfi,PacOtr,PacNom,PacApe,PacApm,PacSex,PacIde,DepCod,PacFch,PacDom,PacReg,PacSts,PacAct) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    guardarStmt.setInt(1, pacsalmi2.getId_cuaderno());
                    guardarStmt.setString(2, pacsalmi2.getAccion());
                    guardarStmt.setString(3, pacsalmi2.getColor());
                    guardarStmt.setString(4, pacsalmi2.getAspecto());
                    guardarStmt.setString(5, pacsalmi2.getAnti());
                    guardarStmt.setString(6, pacsalmi2.getBacterias());
                    guardarStmt.setString(7, pacsalmi2.getCama());
                    guardarStmt.setString(8, pacsalmi2.getCarnet());
                    guardarStmt.setString(9, Integer.toString(datoestab.getId_departamento()));
                    guardarStmt.setString(10, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(11, pacsalmi2.getCetonas());
                    guardarStmt.setString(12, Integer.toString(iAnio2) + "-" + Integer.toString(iMes2) + "-" + Integer.toString(iDia2));
                    guardarStmt.setInt(13, 1);
                    guardarStmt.setInt(14, 1);
                    guardarStmt.execute();
                    guardarStmt.close();
                    //guardarStmt.commit();
                }//fin del for
                try {
                    return new ModelAndView("Aviso", "mensaje", "Pacientes Guardado Correctamente al SALMI");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Pacientes");
                }
            }//fin del else de Pacientes SALMI
        }

        if ("P4 SeleccionMed".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("18");   //////getSelecionMed
                List pacsalmi = this.mi.getSalmi(dato);

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

                for (int i = 0; i < pacsalmi.size(); i++) {
                    Cuadernos pacsalmi2 = (Cuadernos) pacsalmi.get(i);
                    if ("".equals(pacsalmi2.getTipodent())) {
                        pacsalmi2.setTipodent("SC");
                    }
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblSeleccion(SelId,SelTip,SelCod,SelNom,SelFof,SelCon,SelCos,SelPor,SelSal,SelDis,SelCla,SelAct) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                    guardarStmt.setInt(1, pacsalmi2.getId_persona());///SelId
                    guardarStmt.setInt(2, pacsalmi2.getAqv());///SelTip
                    guardarStmt.setString(3, pacsalmi2.getColor());///SelCod
                    guardarStmt.setString(4, pacsalmi2.getReferido());///SelNom
                    guardarStmt.setString(5, pacsalmi2.getContraref());///SelFof
                    guardarStmt.setString(6, pacsalmi2.getTipodent());///SelCon
                    guardarStmt.setString(7, pacsalmi2.getBacterias());///SelCos
                    guardarStmt.setInt(8, 10);////SelPor
                    guardarStmt.setInt(9, 0);///SelSal
                    guardarStmt.setInt(10, 0);///SelDis
                    guardarStmt.setInt(11, 0);///SelCla
                    guardarStmt.setInt(12, 1);///SelAct
                    guardarStmt.execute();
                    guardarStmt.close();
                    //guardarStmt.commit();
                }//fin del for
                try {
                    return new ModelAndView("Aviso", "mensaje", " La Seleccion de Medicamentos e Insumos se ha Guardado Correctamente al SALMI");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Seleccion Medicamentos");
                }
            }//fin del else de Pacientes SALMI
        }

        if ("P8 Salidas".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("3");    /////getSalidasSalmiSumi
                List salsalmi = this.mi.getSalmi(dato);

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

                for (int i = 0; i < salsalmi.size(); i++) {
                    Cuadernos salsalmi2 = (Cuadernos) salsalmi.get(i);
                    int his = salsalmi2.getId_cuaderno();
                    int pac = salsalmi2.getId_laboratorio();
                    iAnio1 = salsalmi2.getFec_ingreso().getYear() + 1900;
                    iMes1 = salsalmi2.getFec_ingreso().getMonth() + 1;
                    iDia1 = salsalmi2.getFec_ingreso().getDate();
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblSalidas(SalId,SalTip,FinId,PrgId,BqsId,PacId,DsrId,PsrId,RepId,SalAno,SalMes,SalDia,SalTot,SalCos,SalDoc,SalRre,SalNro,SalAte,SalAlt,SalFch,ObsId) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");

                    guardarStmt.setInt(1, salsalmi2.getId_cuaderno());
                    guardarStmt.setInt(2, 1);
                    guardarStmt.setInt(3, salsalmi2.getAqv());
                    guardarStmt.setInt(4, salsalmi2.getArterial());
                    guardarStmt.setInt(5, 0);
                    guardarStmt.setInt(6, salsalmi2.getId_laboratorio());
                    guardarStmt.setInt(7, 1);
                    guardarStmt.setInt(8, salsalmi2.getId_persona());
                    guardarStmt.setInt(9, 0);
                    guardarStmt.setInt(10, salsalmi2.getAlcohol());
                    guardarStmt.setInt(11, salsalmi2.getAnastecia());
                    guardarStmt.setInt(12, salsalmi2.getAnestesia());
                    guardarStmt.setString(13, "1");
                    guardarStmt.setString(14, "0");
                    guardarStmt.setInt(15, salsalmi2.getId_cuaderno());
                    guardarStmt.setInt(16, salsalmi2.getIngreso());
                    guardarStmt.setInt(17, 1);
                    guardarStmt.setString(18, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(19, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(20, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setInt(21, 0);
                    guardarStmt.execute();
                    guardarStmt.close();
                }

                dato.setTipo("15");////getSalidasSalmiNutri
                List salsalminutri = mi.getSalmi(dato);
                for (int i = 0; i < salsalminutri.size(); i++) {
                    Cuadernos salsalmi2 = (Cuadernos) salsalminutri.get(i);
                    iAnio1 = salsalmi2.getFec_ingreso().getYear() + 1900;
                    iMes1 = salsalmi2.getFec_ingreso().getMonth() + 1;
                    iDia1 = salsalmi2.getFec_ingreso().getDate();
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblSalidas(SalId,SalTip,FinId,PrgId,BqsId,PacId,DsrId,PsrId,RepId,SalAno,SalMes,SalDia,SalTot,SalCos,SalDoc,SalRre,SalNro,SalAte,SalAlt,SalFch,ObsId) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");

                    guardarStmt.setInt(1, salsalmi2.getId_cuaderno());
                    guardarStmt.setInt(2, 1);
                    guardarStmt.setInt(3, salsalmi2.getAqv());
                    guardarStmt.setInt(4, salsalmi2.getArterial());
                    guardarStmt.setInt(5, 0);
                    guardarStmt.setInt(6, salsalmi2.getId_laboratorio());
                    guardarStmt.setInt(7, 1);
                    guardarStmt.setInt(8, salsalmi2.getId_persona());
                    guardarStmt.setInt(9, 0);
                    guardarStmt.setInt(10, salsalmi2.getAlcohol());
                    guardarStmt.setInt(11, salsalmi2.getAnastecia());
                    guardarStmt.setInt(12, salsalmi2.getAnestesia());
                    guardarStmt.setString(13, "1");
                    guardarStmt.setString(14, "0");
                    guardarStmt.setInt(15, salsalmi2.getId_cuaderno());
                    guardarStmt.setInt(16, salsalmi2.getIngreso());
                    guardarStmt.setInt(17, 1);
                    guardarStmt.setString(18, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(19, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(20, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setInt(21, 0);
                    guardarStmt.execute();
                    guardarStmt.close();
                }
                /*FALLA MUCHO
        dato.setTipo("16");///getSalidasSalmiOtro
        List salsalmiotro = mi.getSalmi(dato);
        for(int i = 0; i < salsalmiotro.size(); i++)
        {
            Cuadernos salsalmi2 = (Cuadernos)salsalmiotro.get(i);
            iAnio1 = salsalmi2.getFec_ingreso().getYear() + 1900;
            iMes1 = salsalmi2.getFec_ingreso().getMonth() + 1;
            iDia1 = salsalmi2.getFec_ingreso().getDate();
            PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblSalidas(SalId,SalTip,FinId,PrgId,BqsId,PacId,DsrId,PsrId,RepId,SalAno,SalMes,SalDia,SalTot,SalCos,SalDoc,SalRre,SalNro,SalAte,SalAlt,SalFch,ObsId) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");
            
            guardarStmt.setInt(1, salsalmi2.getId_cuaderno());
            guardarStmt.setInt(2, 1);
            guardarStmt.setInt(3, salsalmi2.getAqv());
            guardarStmt.setInt(4, salsalmi2.getArterial());
            guardarStmt.setInt(5, 0);
            guardarStmt.setInt(6, salsalmi2.getId_laboratorio());
            guardarStmt.setInt(7, 1);
            guardarStmt.setInt(8, salsalmi2.getId_persona());
            guardarStmt.setInt(9, 0);
            guardarStmt.setInt(10, salsalmi2.getAlcohol());
            guardarStmt.setInt(11, salsalmi2.getAnastecia());
            guardarStmt.setInt(12, salsalmi2.getAnestesia());
            guardarStmt.setString(13, "1");
            guardarStmt.setString(14, "0");
            guardarStmt.setInt(15, salsalmi2.getId_cuaderno());
            guardarStmt.setInt(16, salsalmi2.getIngreso());
            guardarStmt.setInt(17, 1);
            guardarStmt.setString(18, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
            guardarStmt.setString(19, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
            guardarStmt.setString(20, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
            guardarStmt.setInt(21, 0);
            guardarStmt.execute();
            guardarStmt.close();
        }

      
      ////////////////////////////////para ventas externaas
       dato.setTipo("17");////getSalidasSalmiExt
       List salsalmiext = this.mi.getSalmi(dato);    
              
       for (int i = 0; i < salsalmiext.size(); i++) {
           Cuadernos salsalmi2 = (Cuadernos) salsalmiext.get(i);
           iAnio1 = salsalmi2.getFec_ingreso().getYear()+1900;
           iMes1 = salsalmi2.getFec_ingreso().getMonth()+1;
           iDia1 = salsalmi2.getFec_ingreso().getDate();
          
           PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblSalidas(SalId,SalTip,FinId,PrgId,BqsId,PacId,DsrId,PsrId,RepId,SalAno,SalMes,SalDia,SalTot,SalCos,SalDoc,SalRre,SalNro,SalAte,SalAlt,SalFch,ObsId) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");
           guardarStmt.setInt(1, salsalmi2.getId_cuaderno());//Sald
           guardarStmt.setInt(2, 1);//SalTip
           guardarStmt.setInt(3, salsalmi2.getAqv());//FinId
           guardarStmt.setInt(4, salsalmi2.getArterial());//PrgId
           guardarStmt.setInt(5, 0);///BqsId
           guardarStmt.setInt(6, 0);//PacId
           guardarStmt.setInt(7, 1);///DsrId
           guardarStmt.setInt(8, 0);///PsrId
           guardarStmt.setInt(9, 0);///RepId
           guardarStmt.setInt(10, salsalmi2.getAlcohol());//SalAno
           guardarStmt.setInt(11, salsalmi2.getAnastecia());//SalMes
           guardarStmt.setInt(12, salsalmi2.getAnestesia());//SalDia
           guardarStmt.setString(13, "0.00");//SalTot
           guardarStmt.setString(14, "0.00");//SalCos
           guardarStmt.setInt(15,  i+1);//SalDoc   numero correlativo del salmi
           guardarStmt.setInt(16, 1);//SalRe    ambulatorio=1; internado=2; odontologia=3; especialista=4   ----el sistema salmi cuenta las cantidad de atenciones para generar su numero correlativo
           guardarStmt.setInt(17, i+1);//SAlNro   numeracion segun internados, ambulatorios
           guardarStmt.setString(18, Integer.toString(iAnio1)+"-"+Integer.toString(iMes1)+"-"+Integer.toString(iDia1));
           guardarStmt.setString(19, Integer.toString(iAnio1)+"-"+Integer.toString(iMes1)+"-"+Integer.toString(iDia1));
           guardarStmt.setString(20, Integer.toString(iAnio1)+"-"+Integer.toString(iMes1)+"-"+Integer.toString(iDia1));
           guardarStmt.setInt(21, 0); //ObsId    para correlativo observaciones en la tabla observaciones
           guardarStmt.execute();
           guardarStmt.close();
         }//fin del for
                 */
                try {
                    return new ModelAndView("Aviso", "mensaje", "Salidas Guardado Correctamente al SALMI");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Salidas");
                }
            }//fin del else de Pacientes SALMI
        }

        if ("P5 Lotes".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("4");
                List lotesalmi = this.mi.getSalmi(dato); ///getLotesSalmi
                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                Statement st = c.createStatement();

                for (int i = 0; i < lotesalmi.size(); i++) {
                    Cuadernos lote = (Cuadernos) lotesalmi.get(i);
                    iAnio1 = lote.getFec_ingreso().getYear() + 1900;
                    iMes1 = lote.getFec_ingreso().getMonth() + 1;
                    iDia1 = lote.getFec_ingreso().getDate();
                    /////////busca los codgos de medcamentos existentes en SALMI y coloca respectivos lotes 
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblLotes (LotId,LotNro,LotVto,LotCos,LotGen,LotNac,LotAct) VALUES(?,?,?,?,?,?,?)");
                    guardarStmt.setInt(1, lote.getId_persona());
                    guardarStmt.setString(2, lote.getTipoconsulta());
                    guardarStmt.setString(3, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));
                    guardarStmt.setString(4, lote.getTipodent());
                    guardarStmt.setInt(5, 1);
                    guardarStmt.setInt(6, 1);
                    guardarStmt.setInt(7, 1);
                    guardarStmt.execute();
                    guardarStmt.close();
                }//fin del for
            }
            try {
                return new ModelAndView("Aviso", "mensaje", "Lotes Guardado Correctamente al SALMI");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Lotes");
            }
        }

        if ("P9 Compras Medica".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                String fechaM = sFechaini[1];
                if ("01".equals(sFechaini[1])) {
                    fechaM = sFechaini[2] + '-' + fechaM + '-' + sFechaini[0];////fffffffff
                } else {
                    fechaM = sFechaini[2] + '-' + sFechaini[1] + '-' + sFechaini[0];
                }
                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("9");
                List Movsalmi = this.mi.getSalmi(dato); ////getMovSalmi

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                Statement st = c.createStatement();

                for (int i = 0; i < Movsalmi.size(); i++) {
                    Cuadernos mov = (Cuadernos) Movsalmi.get(i);
                    //iAnio1 = mov.getFec_ingreso().getYear()+1900;  iMes1 = mov.getFec_ingreso().getMonth()+1;   iDia1 = mov.getFec_ingreso().getDate();
                    nummov = nummov + 1;

                    PreparedStatement preparedStatement = c.prepareStatement("SELECT SelCod,SelId FROM TblSeleccion where selid>0 and SelCod=?");
                    preparedStatement.setString(1, mov.getTipo());  ///pasamos el parametro del codigo medicamento              
                    ResultSet rs = preparedStatement.executeQuery();
                    String cod = "0", nume = "0";
                    //if(num==2092){
                    // dato.setAnti(cod); 
                    //}
                    if (rs != null) {
                        while (rs.next()) {
                            cod = rs.getString(1);
                            nume = rs.getString(2);
                        }
                    }
                    rs.close();
                    preparedStatement.close();
                    ///Saca el id_lote para el medcamento
                    dato.setTipo("10");
                    dato.setAnti(cod);
                    List Lotesalmi = this.mi.getSalmi(dato);   /////getLotesSalmiUni
                    ///if (!Lotesalmi.isEmpty()){
                    Cuadernos lotesal = (Cuadernos) Lotesalmi.get(0);
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblMovimiento(MovId,MvsId,SelId,FinId,PrgId,EntId,PosId,SalId,NegId,MovEnt,MovPos,MovSal,MovNeg,MovSdo,MovCos,MovVal,LotId,MovRec,MovFch,UsuId) VALUES(?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");
                    guardarStmt.setInt(1, nummov);//MovId
                    guardarStmt.setInt(2, 10);//MvsId  10 son entradas
                    guardarStmt.setInt(3, mov.getId_persona());//SelId
                    if ("S".equals(mov.getAspecto())) {
                        guardarStmt.setInt(4, 1);//FinId
                        guardarStmt.setInt(5, 0);//PrgId    
                    }
                    if ("V".equals(mov.getAspecto())) {
                        guardarStmt.setInt(4, 3);//FinId
                        guardarStmt.setInt(5, 0);//PrgId    
                    }
                    if ("P".equals(mov.getAspecto())) {
                        guardarStmt.setInt(4, 2);//FinId
                        guardarStmt.setInt(5, mov.getSuma1());//PrgId    
                    }
                    guardarStmt.setInt(6, 1);///EntId
                    guardarStmt.setInt(7, 0);//PosId
                    guardarStmt.setInt(8, 0);///SalId
                    guardarStmt.setInt(9, 0);///NegId  
                    guardarStmt.setInt(10, mov.getAuto());//MovEnt    todas las entradas o compras
                    guardarStmt.setInt(11, 0);///MovPos
                    guardarStmt.setInt(12, 0);//MovSal
                    guardarStmt.setInt(13, 0);//MovNeg
                    guardarStmt.setInt(14, mov.getAuto());//MovPos  
                    guardarStmt.setString(15, Double.toString(mov.getCm()));//MovCos deberia ser double
                    guardarStmt.setString(16, Double.toString(mov.getCm() * mov.getAuto()));//MovVal
                    guardarStmt.setInt(17, mov.getId_persona());//LotId
                    guardarStmt.setInt(18, 0);//MovRec
                    guardarStmt.setString(19, fechaM);
                    guardarStmt.setInt(20, 1);
                    guardarStmt.execute();
                    guardarStmt.close();
                    /// }    
                }//fin del for

                try {
                    return new ModelAndView("Aviso", "mensaje", "Compras Guardado Salmi Correctamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Pacientes");
                }
            }//fin del else de Pacientes SALMI
        }

        if ("P12 Salidas Medica".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                String fechaM = sFechaini[1];
                if ("01".equals(sFechaini[1])) {
                    fechaM = sFechaini[2] + '-' + fechaM + '-' + sFechaini[0];////fffffffff
                } else {
                    fechaM = sFechaini[2] + '-' + Integer.toString(Integer.parseInt(sFechaini[1]) - 1) + '-' + sFechaini[0];
                }
                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("6");    ///////getMovimientoSalmi
                List movsalmi = this.mi.getSalmi(dato);

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                Statement st = c.createStatement();

                num = 2000;
                for (int i = 0; i < movsalmi.size(); i++) {
                    Cuadernos mov = (Cuadernos) movsalmi.get(i);
                    iAnio1 = mov.getFec_ingreso().getYear() + 1900;
                    iMes1 = mov.getFec_ingreso().getMonth() + 1;
                    iDia1 = mov.getFec_ingreso().getDate();
                    // if(mov.getSuma2()==738391){
                    //       num=9000;     
                    //}
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblMovimiento(MovId,MvsId,SelId,FinId,PrgId,EntId,PosId,SalId,NegId,MovEnt,MovPos,MovSal,MovNeg,MovSdo,MovCos,MovVal,LotId,MovRec,MovFch,UsuId) VALUES(?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?)");
                    guardarStmt.setInt(1, i + 2000);//MovId
                    guardarStmt.setInt(2, 30);//MvsId
                    guardarStmt.setInt(3, mov.getSuma1());//SelId  Integer.parseInt(nume)
                    if ("S".equals(mov.getReferido())) {
                        guardarStmt.setInt(4, mov.getSuma4());//FinId
                        guardarStmt.setInt(5, mov.getSuma5());//PrgId    
                    }
                    if ("E".equals(mov.getReferido()) || "A".equals(mov.getReferido()) || "V".equals(mov.getReferido()) || "X".equals(mov.getReferido()) || "E".equals(mov.getReferido()) || "M".equals(mov.getReferido())) {
                        guardarStmt.setInt(4, mov.getSuma4());//FinId
                        guardarStmt.setInt(5, mov.getSuma5());//PrgId   
                    }
                    if ("P".equals(mov.getReferido())) {
                        guardarStmt.setInt(4, mov.getSuma4());//FinId
                        guardarStmt.setInt(5, mov.getSuma5());//PrgId   
                    }
                    if (mov.getSuma1() == 574) {
                        guardarStmt.setInt(4, 2);//FinId
                        guardarStmt.setInt(5, 103);//PrgId   
                    }
                    guardarStmt.setInt(6, 0);///EntId
                    guardarStmt.setInt(7, 0);//PosId
                    guardarStmt.setInt(8, mov.getSuma2());///SalId
                    guardarStmt.setInt(9, 0);///NegId
                    guardarStmt.setInt(10, 0);///MovEnt
                    guardarStmt.setInt(11, 0);//MovPos
                    guardarStmt.setInt(12, mov.getSuma3());//MovSal
                    guardarStmt.setInt(13, 0);//MovNeg
                    guardarStmt.setInt(14, 99);//MovSdo
                    guardarStmt.setString(15, mov.getTipoconsulta());//MovCos
                    guardarStmt.setString(16, "99");//MovVal
                    guardarStmt.setInt(17, mov.getSuma1());//LotId
                    guardarStmt.setInt(18, mov.getSuma3());//MovRec
                    guardarStmt.setString(19, Integer.toString(iAnio1) + "-" + Integer.toString(iMes1) + "-" + Integer.toString(iDia1));//MovFec
                    guardarStmt.setInt(20, 1);//UsuId
                    guardarStmt.execute();
                    guardarStmt.close();
                    num = num + 1;
                }//fin del for
                /////*/
                try {
                    return new ModelAndView("Aviso", "mensaje", "Salidas Guardado Salmi Correctamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Pacientes");
                }

            }//fin del else de Pacientes SALMI
        }

        if ("P10 Diagnosticos".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("7");
                List Diagsalmi = this.mi.getSalmi(dato);
                dato.setTipo("8");
                List Ciesalmi = this.mi.getSalmi(dato);
                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();

                for (int i = 0; i < Diagsalmi.size(); i++) {  ////Diagnosticos
                    Cuadernos Diag = (Cuadernos) Diagsalmi.get(i);
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblDiagnosticos (DiaId,DiaCod,DiaNom,DiaCos,DiaTip,FinId,PrgId,DiaCla,DiaAct) VALUES(?,?,?,?,?, ?,?,?,?)");
                    guardarStmt.setInt(1, Diag.getSuma1());///DiaId
                    guardarStmt.setString(2, Diag.getTipo());///DiaCod
                    guardarStmt.setString(3, Diag.getTipodent());///DiaNom
                    guardarStmt.setInt(4, 1);///CuaId 
                    guardarStmt.setInt(5, 1);///DiaCos
                    guardarStmt.setInt(6, 3);///DiaCla
                    guardarStmt.setInt(7, 0);///CuaId 
                    guardarStmt.setInt(8, 0);///CuaId 
                    guardarStmt.setInt(9, 1);///DiaAct
                    guardarStmt.execute();
                    guardarStmt.close();
                }//fin del for
                for (int i = 0; i < Ciesalmi.size(); i++) {////RelSalDia
                    Cuadernos Cie = (Cuadernos) Ciesalmi.get(i);
                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblRelSalDia (SalId,DiaId,RelNue,RelRep,RelNro,RelCos) VALUES(?,?,?,?,?,?)");
                    guardarStmt.setInt(1, Cie.getSuma2());///SalId
                    guardarStmt.setInt(2, Cie.getSuma1());///DiaId
                    guardarStmt.setInt(3, 1);///RelNue
                    guardarStmt.setInt(4, 0);///RelRep
                    guardarStmt.setInt(5, 1);///RelNro
                    guardarStmt.setString(6, "0");///RelCos
                    guardarStmt.execute();
                    guardarStmt.close();
                }//fin del for
                try {
                    return new ModelAndView("Aviso", "mensaje", "Diagnosticos Guardado Salmi Correctamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Diagnosticos");
                }
            }
        }

        if ("P11 Prestaciones".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("11");  ////getFopo
                List Fopo = this.mi.getSalmi(dato);

                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                num = 0;
                /*
       for (int i = 0; i < Fopo.size(); i++) {  ////Diagnosticos
          Cuadernos fopo = (Cuadernos) Fopo.get(i);
          num=num+1;
          PreparedStatement preparedStatement = c.prepareStatement("select PreCod,PreNom from Tblprestaciones where precod=?");
           preparedStatement.setString(1, fopo.getTipo());  ///pasamos el parametro del codigo medicamento              
           ResultSet rs = preparedStatement.executeQuery();
           String cod="0", nomb="0";
           if(rs!=null){
              while(rs.next()){
                 cod = rs.getString(1);
                 nomb = rs.getString(2);
            } }
           rs.close();
           preparedStatement.close();
           
          PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblPrestacion (PreId,PreCod,PreNom,PreCla,PreAct) VALUES(?,?,?,?,?)");
          guardarStmt.setInt(1, num);///PreId
          guardarStmt.setString(2, cod);///PreCod
          guardarStmt.setString(3, nomb);///PreNom
          guardarStmt.setInt(4, 0);///PreCla
          guardarStmt.setInt(5, 1);///PreAct
          guardarStmt.execute(); guardarStmt.close();
        }//fin del for
                 */

                dato.setTipo("5");
                List PrestaSalmi = this.mi.getSalmi(dato); ////getRelSalPreSalmi
                for (int i = 0; i < PrestaSalmi.size(); i++) {  ////Diagnosticos
                    Cuadernos prestacion = (Cuadernos) PrestaSalmi.get(i);

                    PreparedStatement preparedStatement = c.prepareStatement("select PreId,PreCod from Tblprestacion where precod=?");
                    preparedStatement.setString(1, prestacion.getTipo());  ///pasamos el parametro del codigo medicamento              
                    ResultSet rs = preparedStatement.executeQuery();
                    String cod = "0", nume = "0";
                    if (rs != null) {
                        while (rs.next()) {
                            cod = rs.getString(1);
                            nume = rs.getString(2);
                        }
                    }
                    rs.close();
                    preparedStatement.close();

                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblRelSalPre(SalId,PreId,RelNue,RelRep,RelNro,RelCos) VALUES(?,?,?,?,?,?)");
                    guardarStmt.setInt(1, prestacion.getId_historial());///SalId
                    guardarStmt.setInt(2, Integer.parseInt(cod));///PreId
                    guardarStmt.setInt(3, 1);///RelNue
                    guardarStmt.setInt(4, 0);///RelRep
                    guardarStmt.setInt(5, prestacion.getCancer());///RelNro
                    guardarStmt.setInt(6, 10);///RelCos
                    guardarStmt.execute();
                    guardarStmt.close();
                }//fin del for
                try {
                    return new ModelAndView("Aviso", "mensaje", "Prestaciones Guardado Salmi Correctamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Prestaciones");
                }
            }
        }

        if ("P13 IMM".equals(accion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_farmacia(cliente.getId_farmacia());
                dato.setTipo("12");
                List Diagsalmi = this.mi.getSalmi(dato);
                ConexionBDDControlador conn = new ConexionBDDControlador();
                Connection c = conn.conectarSalmi();   ////Connection c=conn.ConexionSalmiOdbc();
                Statement st = c.createStatement();

                dato.setTipo("12");
                List movsalmi = this.mi.getSalmi(dato);

                for (int i = 0; i < movsalmi.size(); i++) {
                    Cuadernos mov = (Cuadernos) movsalmi.get(i);

                    PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO tblIMM(IMMId,SelId,IMMSMS,IMMSMV,IMMSMP,IMMRMS,IMMRMV,IMMRMP,IMMCMS,IMMCMV,IMMCMP,IMMAPP,IMMAPV,IMMAPS,IMMANP,IMMANV,IMMANS) VALUES(?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?)");
                    guardarStmt.setInt(1, 2);//IMMId
                    guardarStmt.setInt(2, mov.getId_persona());//SelId
                    guardarStmt.setInt(3, mov.getSuma1());//SelId  Integer.parseInt(nume)
                    guardarStmt.setInt(4, 2);//FinId  mov.getSuma4()
                    guardarStmt.setInt(5, 2);//PrgId  mov.getSuma5()
                    guardarStmt.setInt(6, 0);///EntId
                    guardarStmt.setInt(7, 0);//PosId
                    guardarStmt.setInt(8, mov.getSuma2());///SalId
                    guardarStmt.setInt(9, 0);///NegId
                    guardarStmt.setInt(10, 0);///MovEnt
                    guardarStmt.setInt(11, 0);//MovPos
                    guardarStmt.setInt(12, mov.getSuma3());//MovSal
                    guardarStmt.setInt(13, 0);//MovNeg
                    guardarStmt.setInt(14, 99);//MovSdo
                    guardarStmt.setString(15, mov.getTipoconsulta());//MovCos
                    guardarStmt.setString(16, "99");//MovVal
                    guardarStmt.setInt(17, mov.getId_persona());//LotId
                    guardarStmt.execute();
                    guardarStmt.close();

                }//fin del for

                try {
                    return new ModelAndView("Aviso", "mensaje", "Diagnosticos Guardado Salmi Correctamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Ocurrio un error al Exportar Diagnosticos");
                }
            }
        }

        if ("Borrar SNIS".equals(accion)) { ///Borrar SNIS
            ConexionBDDControlador conn = new ConexionBDDControlador();
            Connection c = conn.conectarSnis();
            PreparedStatement borrarStmt1 = c.prepareStatement("DELETE * FROM 301G01_DAT");
            borrarStmt1.execute();
            borrarStmt1.close();
            PreparedStatement borrarStmt2 = c.prepareStatement("DELETE * FROM 301G02_DAT");
            borrarStmt2.execute();
            borrarStmt2.close();
            PreparedStatement borrarStmt3 = c.prepareStatement("DELETE * FROM 301G03_DAT");
            borrarStmt3.execute();
            borrarStmt3.close();
            PreparedStatement borrarStmt4 = c.prepareStatement("DELETE * FROM 301G04_DAT");
            borrarStmt4.execute();
            borrarStmt4.close();
            PreparedStatement borrarStmt5 = c.prepareStatement("DELETE * FROM 301G05_DAT");
            borrarStmt5.execute();
            borrarStmt5.close();
            PreparedStatement borrarStmt6 = c.prepareStatement("DELETE * FROM 301G06_DAT");
            borrarStmt6.execute();
            borrarStmt6.close();
            PreparedStatement borrarStmt7 = c.prepareStatement("DELETE * FROM 301G07_DAT");
            borrarStmt7.execute();
            borrarStmt7.close();
            PreparedStatement borrarStmt8 = c.prepareStatement("DELETE * FROM 301G08_DAT");
            borrarStmt8.execute();
            borrarStmt8.close();
            PreparedStatement borrarStmt9 = c.prepareStatement("DELETE * FROM 301G09_DAT");
            borrarStmt9.execute();
            borrarStmt9.close();
            PreparedStatement borrarStmt10 = c.prepareStatement("DELETE * FROM 301G10_DAT");
            borrarStmt10.execute();
            borrarStmt10.close();
            PreparedStatement borrarStmt11 = c.prepareStatement("DELETE * FROM 301G11_DAT");
            borrarStmt11.execute();
            borrarStmt11.close();
            PreparedStatement borrarStmt12 = c.prepareStatement("DELETE * FROM 301G12_DAT");
            borrarStmt12.execute();
            borrarStmt12.close();
            PreparedStatement borrarStmt13 = c.prepareStatement("DELETE * FROM 301G13_DAT");
            borrarStmt13.execute();
            borrarStmt13.close();
            PreparedStatement borrarStmt14 = c.prepareStatement("DELETE * FROM 301G14_DAT");
            borrarStmt14.execute();
            borrarStmt14.close();
            PreparedStatement borrarStmt15 = c.prepareStatement("DELETE * FROM 301G15_DAT");
            borrarStmt15.execute();
            borrarStmt15.close();
            PreparedStatement borrarStmt16 = c.prepareStatement("DELETE * FROM 301G16_DAT");
            borrarStmt16.execute();
            borrarStmt16.close();
            PreparedStatement borrarStmt17 = c.prepareStatement("DELETE * FROM 301G17_DAT");
            borrarStmt17.execute();
            borrarStmt17.close();
            PreparedStatement borrarStmt18 = c.prepareStatement("DELETE * FROM 301G18_DAT");
            borrarStmt18.execute();
            borrarStmt18.close();
            PreparedStatement borrarStmt19 = c.prepareStatement("DELETE * FROM 301G19_DAT");
            borrarStmt19.execute();
            borrarStmt19.close();
            PreparedStatement borrarStmt20 = c.prepareStatement("DELETE * FROM 301G20_DAT");
            borrarStmt20.execute();
            borrarStmt20.close();
            PreparedStatement borrarStmt21 = c.prepareStatement("DELETE * FROM 301G21_DAT");
            borrarStmt21.execute();
            borrarStmt21.close();
            PreparedStatement borrarStmt22 = c.prepareStatement("DELETE * FROM 301G22_DAT");
            borrarStmt22.execute();
            borrarStmt22.close();
            PreparedStatement borrarStmt23 = c.prepareStatement("DELETE * FROM 301G23_DAT");
            borrarStmt23.execute();
            borrarStmt23.close();
            PreparedStatement borrarStmt24 = c.prepareStatement("DELETE * FROM 301G24_DAT");
            borrarStmt24.execute();
            borrarStmt24.close();
            PreparedStatement borrarStmt25 = c.prepareStatement("DELETE * FROM 301G25_DAT");
            borrarStmt25.execute();
            borrarStmt25.close();
            PreparedStatement borrarStmt26 = c.prepareStatement("DELETE * FROM 301G26_DAT");
            borrarStmt26.execute();
            borrarStmt26.close();
            PreparedStatement borrarStmt27 = c.prepareStatement("DELETE * FROM 301G27_DAT");
            borrarStmt27.execute();
            borrarStmt27.close();
            PreparedStatement borrarStmt28 = c.prepareStatement("DELETE * FROM 301G28_DAT");
            borrarStmt28.execute();
            borrarStmt28.close();
            PreparedStatement borrarStmt29 = c.prepareStatement("DELETE * FROM 301G29_DAT");
            borrarStmt29.execute();
            borrarStmt29.close();
            PreparedStatement borrarStmt30 = c.prepareStatement("DELETE * FROM 301G30_DAT");
            borrarStmt30.execute();
            borrarStmt30.close();
            return new ModelAndView("Aviso", "mensaje", "SNIS borrado correctamente");

        } ////Fin de Borrar SNIS

        if ("Borrar SOAPS".equals(accion)) { ///Borrar SOAPS
            ConexionBDDControlador connn = new ConexionBDDControlador();
            Connection c = connn.ConexionSqlSoaps();
            Statement stat = c.createStatement();

            PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO dbo.AAA(sss) VALUES(?)");
            guardarStmt.setString(1, "ELOY");//MvsId
            guardarStmt.execute();
            guardarStmt.close();

            return new ModelAndView("Aviso", "mensaje", "Se conecto Correctamente");
        }

        return new ModelAndView("administrarcuadernos/ExportarSNS", modelo);
    }
}
