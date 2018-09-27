package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.ayaic.domain.Pacientes;

@Mapper
public interface PacientesDao {

    List getReporteAfiliadosSumi(Pacientes dato);

    List getReporteAfiliados(Pacientes dato); ///T

    List getOtrosAfiliados(Pacientes dato);  ////O 

    List getDatosAfiliados(Pacientes dato);

    List getDatosAfiliadosAct(Pacientes dato);///A

    List getDatosAfiliadosNada(Pacientes dato);///T

    void setModificarPacienteConsul(Pacientes paciente);
    
    void setModificarPacienteSumi(Pacientes paciente);

    void setModificarPacienteSumiEmpresa(Pacientes paciente); ////E

    void setModificarPedidos(Pacientes paciente);

    void setModificarPedidoFactura(Pacientes paciente); ///E

    void setUnirHCL(Pacientes paciente);

    void setCrearPacienteSumi(Pacientes paciente);
    
    void setCrearPacienteCaja(Pacientes paciente);

    void setCrearPacienteTicket(Pacientes paciente);////T

    String getNumRegistro(int id_paciente);
    
    String getNumProforma(Pacientes paciente);        
    
    String getNumAsegurado(int id_paciente);

    String getNumSumi(String id_estado, int cod_esta);

    List getDatosFaltaHcl(Pacientes paciente);

    List getDatosFaltaHcl2(Pacientes paciente);///A

    List getListarPacientes(Pacientes paciente);
    
    List getListarPacientesFichas(Pacientes paciente);

    List getListarPacientesPPagos(Pacientes paciente);///P

    List getListarPacientesHisto(Pacientes paciente);///H

    List getListarPacientesHistoNombre(Pacientes paciente);///N

    List getListarPacientesHistoCie10(Pacientes paciente); ///M

    List getListarPacientesNombreCns(Pacientes paciente);///C

    List getListarPacientesCnsUnico(Pacientes paciente);///U

    List getListarPacientesSeguro(Pacientes paciente);///S

    List getListarPacientesPrivNom(Pacientes paciente);///1

    List getListarPacientesBio(Pacientes paciente);

    List getListarPacientesAfi(Pacientes paciente);

    List getListarPacientesCI(Pacientes paciente);

    List getListarPacientesGrafica(Pacientes paciente);

    List getListarPacientesAfiR(Pacientes paciente);

    List getListarPacientesFN(Pacientes paciente);

    List getListarPacientesFNCns(Pacientes paciente);///C
    
    List getListarPacAfiliados(Pacientes paciente);///C

    List getListarPacientesHC(Pacientes paciente);

    List getListarPacientesEmp(Pacientes paciente);///F

    List getListarPacientesFarma(Pacientes paciente);///P

    List getListarPacienteFar(Pacientes paciente);///M

    List getListarPacientesCaja(Pacientes paciente);///C

    List getListarPacientesVigencia(Pacientes paciente);///V

    List getListarPacientesVigenciaBuscar(Pacientes paciente);///B

    List getListarPacientesVigenciaSolo(Pacientes paciente);///S

    List getListarPacientesHCCns(Pacientes paciente);///H

    Pacientes getDatosPaciente(int id_paciente);

    Pacientes getDatosPacienteJefe(int id_paciente);

    Pacientes getDatosPacienteCI(int id_paciente);

    List getDatosPacienteInt(Pacientes paciente);

    void setCrearPaciente(Pacientes paciente);

    void setCrearVigencia(Pacientes paciente);///V

    void setModificarPaciente(Pacientes paciente);

    void setModificarVigencia(Pacientes paciente); ///V

    void setModificaPacienteEmb(Pacientes paciente);

    void setModificaPacienteSangre(Pacientes paciente); ////G

    void setModificaPacienteFact(Pacientes paciente); ////F

    void setModificaPacienteNit(Pacientes paciente); ////F

    void setEliminarPaciente(Pacientes paciente);

    void setEliminarPacienteSeguro(Pacientes paciente);///E
    
    void setEliminarPacienteSscp(Pacientes paciente);

    int getNumHcl();

    void setCrearPeedido(Pacientes paciente);
    
    void setCrearProforma(Pacientes paciente);
    
    void setCrearFactura(Pacientes paciente);

    void setCrearPedidoNutri(Pacientes paciente);

    void setCrearFacturaInter(Pacientes paciente);///I

    void setCrearFacturaInter2(Pacientes paciente);///W

    void setEliminarPedido(Pacientes paciente);
    
    void setEliminarPedidoProf(Pacientes paciente);

    int getNumReceta(Pacientes paciente);

    int getNumPedido(Pacientes paciente);
    
    int getNumPedidoProf(Pacientes paciente);

    List getDatosPedidoRubro(Pacientes paciente);

    List getDatosPedidoRubrosDet(Pacientes paciente);///T

    List getDatosPedidoRubrosDetDeu(Pacientes paciente);///O

    List getDatosPedidoDetRubro(Pacientes paciente);///C

    List getDatosPedidoRubroDental(Pacientes paciente);///D

    List getDatosPedidoRubroGeneral(Pacientes paciente);///G

    List getDatosPedidoRubroGeneralSf(Pacientes paciente);///N

    List getDatosPedidoRubrosDetSf(Pacientes paciente);///F

    List getDatosPedidoRubrosDetSfPag(Pacientes paciente);//P

    Pacientes getDatosPedido(Pacientes paciente);
    
    Pacientes getDatosPedidoProf(Pacientes paciente);

    Pacientes getDatosPedidoHisto(Pacientes paciente);

    Pacientes getDatosPedidoHis(Pacientes paciente);

    Pacientes getDatosFactura(Pacientes paciente);////debe estar el codgo estab mas 

    Pacientes getDatosPedidoKardex(Pacientes paciente);

    Pacientes getDatosPedidoI(Pacientes paciente);

    void setModificarPedido(Pacientes paciente);///M

    void setModificarPedidoPPago(Pacientes paciente);///P

    void setModificarPedidoElimPP(Pacientes paciente);///O

    void setModificarPedidoSinFecha(Pacientes paciente);

    void setModificarPedidoAnt(Pacientes paciente);
    
    void setModificarPedidoProf(Pacientes paciente);

    List getListarCobroRubro(Pacientes paciente);

    List getListarCobroRubroEnfer(Pacientes paciente);

    List getListarCobroRubroOdon(Pacientes paciente);///rubro=3

    List getListarCobroEnfer(Pacientes paciente);///rubro=3

    List getListarCobroRubroFar(Pacientes paciente);
    
    List getListarProformas(Pacientes paciente);
    
    List getListarProformasNom(Pacientes paciente);

    List getReporteCobroFarm(Pacientes dato);

    List getReporteCobroFarmGen(Pacientes dato);///G

    List getListaMedEntregados(Pacientes dato);

    List getListaMedEntregadosI(Pacientes dato);

    List getListaMedKEntregados(Pacientes dato);

    List getListaMedKEntregadosEnfer(Pacientes dato);///T

    List getListaMedKEntregadosSPAM(Pacientes dato);///S

    List getListaMedKEntregadosFecha(Pacientes dato);///F

    List getListaMedKEntregadosUni(Pacientes dato);///U

    List getListaCobroTotal(Pacientes dato);

    List getListaMedRecibidos(Pacientes dato);

    List getListaMedRecibidosHcl(Pacientes dato);///H

    List getListaMedRecibidosTipo(Pacientes dato);///T

    List getListaMedRecibidos2(Pacientes dato);///2

    List getListaMedRecibidosAlmaGen(Pacientes dato);///3 ///busqueda solo para almacen de cualquier paciente en todo hospital modificacion  

    List getReporteEconomico(Pacientes dato);

    List getReporteEconomicoFac(Pacientes dato);

    List getReporteEconomicoGenFac(Pacientes dato);///D

    List getReporteEconomicoPPago(Pacientes dato);///P

    List getReporteEconomicoGenFacRub(Pacientes dato);///G

    List getReporteFacturasEmitidasHcl(Pacientes dato);///E

    List getReporteEconomicoRubro(Pacientes dato);

    List getReporteEconomicoGenRubro(Pacientes dato);

    List getReporteEconomicoGenRubroDental(Pacientes dato);

    long getNumClaDoc(Pacientes dato);
    
    long getNumClaDocProf(Pacientes dato);

    List getAtencionFicha(Pacientes dato);

    void setCrearFicha(Pacientes paciente);
}
