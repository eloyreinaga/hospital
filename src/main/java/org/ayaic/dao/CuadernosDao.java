package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Cuadernos;

@Mapper
public interface CuadernosDao {

    void setRegistrarOdonMensual(Cuadernos dato);

    void setRegistrarOdonPersonal(Cuadernos dato);

    void setRegistrarSnis301(Cuadernos dato);

    List getListarSnis301();

    List getListarFaltaSnis301();

    List getConfigurarImpresion(Cuadernos dato);

    List getImpresionSerologia(Cuadernos dato);///1

    List getImpresionHemograma(Cuadernos dato);///2

    List getImpresionOrina(Cuadernos dato);///3

    List getImpresionOtros(Cuadernos dato);///4

    List getImpresionEmbarazo(Cuadernos dato);///5

    List getImpresionQuimicas(Cuadernos dato);///6

    List getImpresionEcografia(Cuadernos dato);///7

    List getConfigurarImpresionGral(Cuadernos dato);

    List getConfigurarImpresionGralHcl(Cuadernos dato);///11

    List getConfigurarImpresionGralPerinatal(Cuadernos dato);///12

    List getConfigurarImpresionCarnetInf(Cuadernos dato);///13

    List getConfigurarImpresionGinecologica(Cuadernos dato);///14

    List getConfigurarImpresionFormulario(Cuadernos dato);///15

    List getConfigurarImpresionCarnet(Cuadernos dato);///16

    List getConfigurarImpresionRiesgo(Cuadernos dato);///

    List getCuadernosImpresion(Cuadernos dato);///20

    List getCodigoControl(Cuadernos dato);

    List getListarOdonMensual();

    List getListarFaltaOdonMensual();

    Cuadernos getDatosTallaPesoF02(Cuadernos dato);

    Cuadernos getDatosTallaPesoF25(Cuadernos dato);

    Cuadernos getDatosTallaPesoM02(Cuadernos dato);

    Cuadernos getDatosTallaPesoM25(Cuadernos dato);

    Cuadernos getDatosTallaEdadF02(Cuadernos dato);

    Cuadernos getDatosTallaEdadF25(Cuadernos dato);

    Cuadernos getDatosTallaEdadM02(Cuadernos dato);

    Cuadernos getDatosTallaEdadM25(Cuadernos dato);

    Cuadernos getDatosPesoEdadF02(Cuadernos dato);

    Cuadernos getDatosPesoEdadM02(Cuadernos dato);

    Cuadernos getHistorialPacientesEco(int id_paciente);

    List getListarPacientesEco();

    List getPacienteResultadoLab(Cuadernos dato);

    List getPacienteResultadoLabEco(Cuadernos dato);///E

    List getPacienteResultadoLabX(Cuadernos dato);///X

    List getPacienteResultadoLabLab(Cuadernos dato);///L

    List getPacienteResultadoLabEndo(Cuadernos dato);///D

    List getDetalleFotosEndo(Cuadernos dato);///F

    List getPacienteLaboratorio(Cuadernos dato);

    List getDatosLaboratorios(Cuadernos dato);

    Cuadernos getDatosLaboratorioC(Cuadernos dato);

    List getLabPacPendiente(Cuadernos dato);

    List getLabPacPendientePago(Cuadernos dato);///A

    List getLabPacPasados(Cuadernos dato);///P

    List getLabPacPendienteRx(Cuadernos dato);///R

    List getLabPacPendienteEco(Cuadernos dato);///E

    List getLabPendienteAmb(Cuadernos dato);

    List getLabPendienteAmbFecha(Cuadernos dato);///F

    List getLabSSPAM(Cuadernos dato);

    List getLabPendienteInter(Cuadernos dato);

    List getLabPendienteInterFecha(Cuadernos dato);///F

    List getLabPendienteInterNum(Cuadernos dato);///P

    List getLabPendienteInterHcl(Cuadernos dato);///H

    List getLabPacPendienteInter(Cuadernos dato);

    List getLabPacPendienteRxInter(Cuadernos dato);

    List getLabPacPendienteEcoInter(Cuadernos dato);

    List getLabPendiente(Cuadernos dato);

    List getLabPendienteMed(Cuadernos dato);

    List getLabPendienteRx(Cuadernos dato);

    List getLabPendienteImp(Cuadernos dato);

    List getLabPendienteEco(Cuadernos dato);

    List getLabPendienteEpi(Cuadernos dato);

    List getLabPendienteCNS(Cuadernos dato);

    List getLabPendienteCNSUlt(Cuadernos dato);

    List getHemograma(Cuadernos dato);

    List getOrina(Cuadernos dato);

    List getDetalleEcos(Cuadernos dato);

    List getDetalleEcosI(Cuadernos dato);

    List getLabRealizadoInter(Cuadernos dato);

    List getLabRealizadoInterFecha(Cuadernos dato);///F

    List getLabRealizadoInterNombre(Cuadernos dato);///N

    List getLabRealizadoInterHcl(Cuadernos dato);///H

    List getLabRealizadoInterNum(Cuadernos dato);///P

    List getLabRealizadoAmb(Cuadernos dato);

    List getLabRealizadoAmbFecha(Cuadernos dato);

    List getLabRealizadoAmbNombre(Cuadernos dato);

    List getLabRealizadoTotal(Cuadernos dato);

    List getLabRealizadoSumi(Cuadernos dato);

    List getPedidoLab(Cuadernos dato);

    List getDatoPedidoLab(Cuadernos dato);///I

    List getLabMedico(Cuadernos dato);

    List getLabMedicoEco(Cuadernos dato);///E

    List getLabMedicoX(Cuadernos dato);///X

    List getLabMedicoLab(Cuadernos dato);///L

    Cuadernos getDatos302_5(Cuadernos dato);

    Cuadernos getDatos302imc(Cuadernos dato);

    void setEliminarLaboratorioC(Cuadernos dato);

    void setCrearLaboratorioC(Cuadernos dato);

    void setCrearProduccion(Cuadernos dato);

    void setCrearProduccionEmerg(Cuadernos dato);

    void setCrearPedidoLab(Cuadernos dato);

    void setModificarPedidoLab(Cuadernos dato);

    void setModificarEcoDetalle(Cuadernos dato); ///G 

    void setModificarPedidoLabo(Cuadernos dato);  ///L

    void setModificarLaboratorioC(Cuadernos dato);

    void setModificarLaboratorioCobrar(Cuadernos dato);///N

    void setModificarHemograma(Cuadernos dato);

    void setModificarOrina(Cuadernos dato);

    void setCrearDetalleSangre(Cuadernos dato);

    void setCrearDetalleOrina(Cuadernos dato);

    void setReservaLabEcografia(Cuadernos dato);

    List getListarEstadisticas(Cuadernos dato);

    List getListarEstadisticasHospi(Cuadernos dato);

    void setModificarImpOrina(Cuadernos dato);

    void setModificarImpRiesgo(Cuadernos dato);

    List getReporteCIE10(Cuadernos dato);

    List getReporteCIE10edadPersonal(Cuadernos dato);///N

    List getReporteCIE10xMedico(Cuadernos dato);///Q

    List getReporteCIE10Urgencia(Cuadernos dato);///M

    List getReporteCIE10edad(Cuadernos dato);///E

    List getReporteCIE10IngInter(Cuadernos dato);///I

    List getReporteCIE10EgrInter(Cuadernos dato);///G

    List getReporteProduccionG1(Cuadernos dato);///9

    List getReporteProduccion(Cuadernos dato);///P

    List getReporteProduccion2(Cuadernos dato);///R

    List getReporteCIE10_C1(Cuadernos dato);///1

    List getReporteCIE10_C2(Cuadernos dato);///2

    List getReporteCIE10_C3(Cuadernos dato);///3

    List getReporteCIE10_C4(Cuadernos dato);///4

    List getReporteCIE10_C7(Cuadernos dato);///7

    List getReporteCIE10_CEmerg(Cuadernos dato);///0

    List getPacienteCuaderno7(int id_historial);

    void setEliminarCuaderno7(Cuadernos dato);

    void setCrearCuaderno7(Cuadernos dato);

    Cuadernos getSnis301Odontologia(Cuadernos dato);

    Cuadernos getSnis301Nutricion(Cuadernos dato);

    List getPacienteCemergencia(int id_historial);

    List getPacienteCuaderno1(int id_historial);

    void setCrearCuaderno1(Cuadernos dato);

    void setCrearTransferencia(Cuadernos dato);///T

    void setCrearAdmision(Cuadernos dato);///A

    void setCrearEpicrisis(Cuadernos dato);///E

    void setCrearProtocolo(Cuadernos dato);///P

    void setCrearSolSangre(Cuadernos dato);///S

    void setCrearOftalmologia(Cuadernos dato);///O

    void setModificarCuaderno1(Cuadernos dato);

    void setModificarCodigo(Cuadernos dato);///2

    void setModificarTranferencia(Cuadernos dato);///M

    void setModificarAdmision(Cuadernos dato);///A

    void setModificarEpicrisis(Cuadernos dato);///E

    void setModificarPrococolos(Cuadernos dato);///P

    void setModificarSangre(Cuadernos dato);///S

    void setEliminarCuaderno1(Cuadernos dato);

    Cuadernos getSnis301Externa(Cuadernos dato);

    List getPacienteCuadernof(Cuadernos dato);

    void setCrearCuadernof(Cuadernos dato);

    void setEliminarCuadernof(Cuadernos dato);

    List getPacienteCuaderno3(int id_historial);

    void setEliminarCuaderno3(Cuadernos dato);

    void setCrearCuaderno3(Cuadernos dato);

    Cuadernos getSnis301Prevencion(Cuadernos dato);

    void setEliminarCuaderno2(Cuadernos dato);

    void setCrearCuaderno2(Cuadernos dato);

    Cuadernos getSnis301ControlPre(Cuadernos dato);

    List getPacienteCuaderno2(Cuadernos dato);

    List getIndicadores();

    void setEliminarCuaderno4(Cuadernos dato);

    void setCrearCuaderno4(Cuadernos dato);

    void setModificarCuaderno4(Cuadernos dato);

    void setModificarCuaderno4A(Cuadernos dato);

    void setModificarCuaderno4B(Cuadernos dato);

    void setModificarCuaderno4C(Cuadernos dato);

    void setModificarCuaderno4D(Cuadernos dato);

    void setModificarCuaderno4E(Cuadernos dato);

    void setModificarCuaderno4F(Cuadernos dato);

    void setModificarCuaderno4G(Cuadernos dato);

    void setModificarCuaderno24(Cuadernos dato);

    List getPacienteCuaderno4(Cuadernos dato);

    Cuadernos getPacienteCuaderno4A(Cuadernos dato);

    void setEliminarVacunas(Cuadernos dato);

    void setCrearVacunas(Cuadernos dato);

    List getPacienteVacunas(Cuadernos dato);

    Cuadernos getSnis301Vacunas(Cuadernos dato);

    List getOdonMensual(Cuadernos dato);

    void setEliminarCuaderno5(Cuadernos dato);

    void setCrearCuaderno5(Cuadernos dato);

    List getPacienteCuaderno5(Cuadernos dato);

    void setModificarCuaderno5(Cuadernos dato);

    void setModificarAdmision2(Cuadernos dato);

    void setEliminarCuaderno6(Cuadernos dato);

    void setCrearCuaderno6(Cuadernos dato);

    List getPacienteCuaderno6(Cuadernos dato);

    List getPacienteCuaderno6H(Cuadernos dato);

    List getListaPacientesCuaderno6(Cuadernos dato);

    List getListaPacientesCuaderno6Total(Cuadernos dato);

    Cuadernos getSnis301Enfermeria(Cuadernos dato);

    void setModificarC2(Cuadernos dato);

    List getCuadernoC1(Cuadernos dato);

    List getVerTransferencia(Cuadernos dato);///T

    List getListarTransferencia(Cuadernos dato);///L

    List getDatoTransferencia(Cuadernos dato);///D

    List getVerAdmisiones(Cuadernos dato);///A

    List getVerEpicrisis(Cuadernos dato);///E

    List getVerProtocolos(Cuadernos dato);///I

    List getListarAdmision(Cuadernos dato);///M

    List getListarEpicrisis(Cuadernos dato);///P

    List getListarProtocolos(Cuadernos dato);///R

    List getDatoAdmision(Cuadernos dato);///N

    List getDatoEpicrisis(Cuadernos dato);///Q

    List getDatoProtocolo(Cuadernos dato);///S

    List getListarAdmisionTot(Cuadernos dato);///G

    List getListarFechas(Cuadernos dato);///1

    List getListarFechasCount(Cuadernos dato);///2

    List getVerSolSangre(Cuadernos dato);///3

    List getListarSolSangres(Cuadernos dato);///4

    List getDatoSolSangre(Cuadernos dato);///5

    List getVerOftalmologia(Cuadernos dato);///6

    List getCuadernoC2(Cuadernos dato);

    List getCuadernoC3(Cuadernos dato);

    List getCuadernoC4(Cuadernos dato);

    List getCuadernoC5(Cuadernos dato);

    List getCuadernoC6(Cuadernos dato);

    List getCuadernoC7(Cuadernos dato);

    List getCuadernoV(Cuadernos dato);

    List getLabGen(Cuadernos dato);

    List getVerCuaderno7(Cuadernos dato);

    List getVerCuaderno7Paci(Cuadernos dato);///P

    List getVerCuaderno7Todos(Cuadernos dato);///U

    List getReporteDental(Cuadernos dato);///S

    List getReporteDentalT(Cuadernos dato);///D

    List getVerCuaderno7Uni(Cuadernos dato);

    List getVerVacunas(Cuadernos dato);

    List getVerVacunasTodos(Cuadernos dato);

    List getVerVacunasUni(Cuadernos dato);

    List getVerCemergencias(Cuadernos dato);

    List getVerCemergenciasTodos(Cuadernos dato);

    List getVerCuaderno1(Cuadernos dato);

    List getVerCuaderno1Sspam(Cuadernos dato);

    List getVerCuaderno1CNS(Cuadernos dato);

    List getVerCuaderno1CNSInter(Cuadernos dato);

    List getVerCuaderno1SspamTodos(Cuadernos dato);

    List getVerCuaderno1Uni(Cuadernos dato);

    List getVerCuaderno1T(Cuadernos dato);

    List getVerCuaderno1Todos(Cuadernos dato);

    List getVerCuaderno1Cie(Cuadernos dato);

    List getVerCuaderno1CieUni(Cuadernos dato);

    List getVerCuaderno1CieMorbi(Cuadernos dato);

    List getReporteSNISConsulta(Cuadernos dato);

    List getReporteSNISConsulta2(Cuadernos dato);

    List getVerCuadernoFisio(Cuadernos dato);

    List getVerCuadernofTodos(Cuadernos dato);

    List getVerCuadernoFisioTodos(Cuadernos dato);

    List getVerCuadernof(Cuadernos dato);

    List getVerCuaderno6(Cuadernos dato);

    List getVerCuaderno6Todos(Cuadernos dato);

    List getVerCuaderno6C1(Cuadernos dato);

    List getVerCuaderno6C4(Cuadernos dato);

    List getVerCuaderno6Uni(Cuadernos dato);

    List getVerEcografias(Cuadernos dato);

    List getVerEcografiasAdmi(Cuadernos dato);

    List getVerCuaderno2(Cuadernos dato);

    List getVerCuaderno2Todos(Cuadernos dato);

    List getVerCuaderno2C3(Cuadernos dato);

    List getVerCuaderno2C3Uni(Cuadernos dato);

    List getReporteProduccionPrenatal(Cuadernos dato);

    List getVerCuaderno2Parto(Cuadernos dato);

    List getVerCuaderno2Uni(Cuadernos dato);

    List getVerCuaderno3(Cuadernos dato);

    List getVerCuaderno3Todos(Cuadernos dato);

    List getVerCuaderno3Prod1(Cuadernos dato);

    List getVerCuaderno3Prod2(Cuadernos dato);

    List getVerCuaderno3Uni(Cuadernos dato);

    List getDatosUni(Cuadernos dato);

    List getVerCuaderno4(Cuadernos dato);

    List getVerCuaderno4Paci(Cuadernos dato);

    List getVerCuaderno4Todos(Cuadernos dato);

    List getVerCuaderno4_C2(Cuadernos dato);

    List getVerCuaderno4_C2Consul(Cuadernos dato);

    List getVerCuaderno4_C2Creci(Cuadernos dato);

    List getVerCuaderno4_C22014D(Cuadernos dato);

    List getVerCuaderno4_C22014F(Cuadernos dato);

    List getVerCuaderno4_C22014DIndi(Cuadernos dato);

    List getVerCuaderno4_C22014FIndi(Cuadernos dato);

    List getVerCuaderno4Uni(Cuadernos dato);

    List getDesarrolloSimple(Cuadernos dato);

    List getVerCuaderno4Creci(Cuadernos dato);

    List getVerCuaderno4CreciDA(Cuadernos dato);

    List getVerCuaderno4CreciNuevoDA(Cuadernos dato);

    List getVerCuaderno4Enfer(Cuadernos dato);

    List getVerCuaderno5(Cuadernos dato);

    List getVerCuaderno5Todos(Cuadernos dato);

    List getVerCuadernoInterServicio(Cuadernos dato);

    List getVerCuaderno5Piso(Cuadernos dato);

    List getVerCuaderno5Uni(Cuadernos dato);

    Cuadernos getVerCuaderno2Ult(Cuadernos dato);

    Cuadernos getVerCuaderno3Count(Cuadernos dato);///3

    Cuadernos getVerCuaderno4PaciUlt(Cuadernos dato);///4

    Cuadernos getVerCuaderno5Ult(Cuadernos dato);///5

    List getVerDesarrollo(Cuadernos dato);

    List getVerDesarrolloSimple(Cuadernos dato);

    List getVerDesarrollo2(Cuadernos dato);

    List getVerSnis302(Cuadernos dato);

    //Control de Calidad
    List getO80_C2(Cuadernos dato);

    List getPC42_C2(Cuadernos dato);

    List getPC64_C2(Cuadernos dato);

    List getZ34_C2(Cuadernos dato);

    List getZ39_C2(Cuadernos dato);

    List getZ301_C3(Cuadernos dato);

    List getZ305_C3(Cuadernos dato);

    List getZ124_C3(Cuadernos dato);

    List getC2_O80(Cuadernos dato);

    List getC2_PC42(Cuadernos dato);

    List getC2_PC64(Cuadernos dato);

    List getC2_Z34(Cuadernos dato);

    List getC2_Z39(Cuadernos dato);

    List getC3_Z301(Cuadernos dato);

    List getC3_Z305(Cuadernos dato);

    List getC3_Z124(Cuadernos dato);

    List getZ301_DIU(Cuadernos dato);

    List getPC23_C3(Cuadernos dato);

    List getC3_PC23(Cuadernos dato);

    List getPC23_DEPO(Cuadernos dato);

    List getCensoDiario(Cuadernos dato);

    List getSalmi(Cuadernos dato);

    List getPersonasSalmi(Cuadernos dato);

    List getPacientesSalmi(Cuadernos dato);

    List getSalidasSalmiSumi(Cuadernos dato);

    List getLotesSalmi(Cuadernos dato);

    List getRelSalPreSalmi(Cuadernos dato);

    List getMovimientoSalmi(Cuadernos dato);

    List getDiagnosticoSalmi(Cuadernos dato);

    List getCie10Salmi(Cuadernos dato);

    List getMovSalmi(Cuadernos dato);

    List getLotesSalmiUni(Cuadernos dato);

    List getFopo(Cuadernos dato);

    List getIMMSalmi(Cuadernos dato);

    List getPrestacion(Cuadernos dato);

    List getMedNoe(Cuadernos dato);

    List getSalidasSalmiNutri(Cuadernos dato);

    List getSalidasSalmiOtro(Cuadernos dato);

    List getSalidasSalmiExt(Cuadernos dato);

    List getSelecionMed(Cuadernos dato);

    List getInvMed(Cuadernos dato);

    List getGrafica1(Cuadernos dato);

    List getGrafica2(Cuadernos dato);

    List getGrafica3(Cuadernos dato);

    List getGrafica4(Cuadernos dato);

}
