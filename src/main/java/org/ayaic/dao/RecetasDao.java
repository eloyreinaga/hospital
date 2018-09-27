package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.ayaic.domain.Recetas;


@Mapper
public interface RecetasDao {

    List getListarMedPaquete(Recetas dato);

    void setCrearRecetaPaque(Recetas dato);

    void setEliminarRecetaPaque(Recetas dato);

    void setModificarRecetaPaquete(Recetas dato);

    List getListarRecetasPres(Recetas dato);

    List getListarRecetas(Recetas dato);

    List getListarRecetasPrivado(Recetas dato);///P

    List getListarRecetasHistorial(Recetas dato);///H

    List getListarRecetasCNS(Recetas dato);///C

    List getListarRecetasUlt(Recetas dato);

    List getListarUltRecetaI(Recetas dato);///I

    List getListarUltReceta(Recetas dato);///U

    List getListarRecetaAnterior(Recetas dato);

    List getListarRecetaAnteriorCarmelo(Recetas dato);

    List getListarRecetasYa(Recetas dato);

    List getListarRecetasInt(Recetas dato);

    List getListarRecetasTotal(Recetas dato);

    List getListarRecetasTotalMed(Recetas dato);///M

    List getListarRecetasCaros(Recetas dato);///C

    List getListarRecetasMedico(Recetas dato);

    List getListarRecetasMedicoCNS(Recetas dato);///C

    List getListarRepRecetaCNS(Recetas dato);///H

    List getListarKardexPac(Recetas dato);///K
    
    List getListarKardexProf(Recetas dato);

    List getListarRecetasInter(Recetas dato);

    List getListarRecetasIndi(Recetas dato);

    List getListarRecetasMedicoI(Recetas dato);

    List getListarRecetasMedicoImp(Recetas dato);

    List getListaRecetaGen(Recetas dato);

    List getResumenXmedico(Recetas dato);

    List getResumenXespecialidad(Recetas dato);

    List getVencimientos(Recetas dato);

    List getResumenXservicio(Recetas dato);

    List getKardexResumenXMedica(Recetas dato);

    List getKardexResumenXMedicaSaldo(Recetas dato);

    int setRegistrarKardex(Recetas dato);

    void setCrearReceta(Recetas dato);

    void setEliminarReceta(Recetas dato);

    void setModificarEstadoReceta(Recetas dato);

    void setModificarRecetaNumera(Recetas dato);///N

    void setModificarRecetaDosifi(Recetas dato);///D

    void setModificarKardex(Recetas dato);

    void setModificarKardexV(Recetas dato);///E

    void setModificarKardexR(Recetas dato);///R

    void setModificarKardexVenta(Recetas dato);///V

    void setModificarKardexPaciente(Recetas dato);///K

    void setModificarEstadoInter(Recetas dato);

    void setCrearRecetaMedSumi(Recetas dato);

    List getListaMedKardexEntregados(Recetas dato);

    List getKardexFarmaciaCNSDet(Recetas dato);///D

    List getKardexFarmaciaCNSDetFar(Recetas dato);///F

    List getListaKardexPacienteIndi(Recetas dato);///I

    List getKardexRemision(Recetas dato);///Q

    List getKardexResumenDispensa(Recetas dato);///A

    List getKardexResumenDispensaDia(Recetas dato);///P

    List getKardexExport(Recetas dato);///X

    List getRevercionExcel(Recetas dato);///B

    List getKardexRestringido(Recetas dato);///R

    List getKardexFarmaciaCNS(Recetas dato);///C

    List getKardexUnitario(Recetas dato);///H

    List getListaMedKardexXPedido(Recetas dato);///T

    List getListaKardexPaciente(Recetas dato);///K

    List getKardexUsuario(Recetas dato);///U

    List getKardexRestringido3(Recetas dato);///3

    List getKardexRestringido4(Recetas dato);///4

    List getKardexRestringido5(Recetas dato);///5

    List getKardexRestringido6(Recetas dato);///6

    List getKardexRestringido7(Recetas dato);///7

    List getKardexFarmaciaCNS_SC(Recetas dato);///1

    List getKardexFarmaciaCNSDet_SC(Recetas dato);///2

    List getListarRecetasS(Recetas dato);

    List getListarKardexPago(int id_historial);

    List getListarKardex(Recetas dato);

    List getListarKardexInterFact(Recetas dato);///I

    List getListarKardexFactura(Recetas dato);///F

    List getListarKardexPerfilFar(Recetas dato);///P

    List getListarKardexPerfilFarGen(Recetas dato);///G

    List getListarFechaPerfil(Recetas dato);///D

    List getListarFechaPerfil2(Recetas dato);///2

    List getListaKardexPacienteDesg(Recetas dato);///3

    List getListarFechaPerfildat(Recetas dato);///E

    List getListarPerfilReversion(Recetas dato);///L

    List getListarKardexPerfilFarDet(Recetas dato);///R

    List getListarKardexComprueba(Recetas dato);///C

    List getListarKardexCodsumi(Recetas dato);///S

    List getListarKardexAjus(Recetas dato);///A

    List getListarKardexI(Recetas dato);

    List getListarKardexIImpRec(Recetas dato);

    List getListarKardexInter(Recetas dato);
	
	List getListarKardexProve(Recetas dato);

    List getListarKardexTotal(Recetas dato);

    void setCrearKardex(Recetas dato);
    
    void setCrearKardexProf(Recetas dato);
    
    void setCrearKardexProf2(Recetas dato);

    void setCrearKardexPaciente(Recetas dato);///I

    void setCrearKardexPacInsert(Recetas dato);///K

    void setEliminarKardex(Recetas dato);
    
    void setEliminarKardexProf(Recetas dato);

    void setEliminarKardexReceta(Recetas dato);///K

    List getListarKardexMedicamento(Recetas dato);

    List getListarKardexMedicamentoSin0(Recetas dato);///S

    List getListarKardexMedicamentoSolo0(Recetas dato);///C

    List getListarKardexMedicamentoSoloE(Recetas dato);///E

    List getListarKardexMedicamentoSoloA(Recetas dato);///A

    List getListarKardexMedicamentoSoloS(Recetas dato);///D

    List getListarKardexProg(Recetas dato);///P
    
    List getListarKardexControl(Recetas dato);///P

    List getListarKardexIndi(Recetas dato);///I

    List getListarKardexMicro(Recetas dato);
}
