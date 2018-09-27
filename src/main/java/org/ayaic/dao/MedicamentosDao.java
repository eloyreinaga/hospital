package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Medicamentos;

@Mapper
public interface MedicamentosDao {

    List getListarMedicamentosGestion(Medicamentos dato);

    List getListarMedicamentosCot(Medicamentos dato);

    List getListarMedicamentosCotFar(Medicamentos dato);///F

    List getListarMedicamentosCotb1(Medicamentos dato);

    List getListarMedicamentos(Medicamentos dato);

    List getListarMedicamentosTotal(Medicamentos dato);///T

    List getListarMedicamentosAsignados(Medicamentos dato);///S

    List getListarMedicamentosPorAsig(Medicamentos dato);///P

    List getListarMedicamentosAlma(Medicamentos dato);///A

    List getListarInvAlma(Medicamentos dato);///I

    List getListarMedicamentosAlmaGral(Medicamentos dato);///G

    List getListarMedicamentosVacio(Medicamentos dato);///C

    List getListarMedicamentoSolo(Medicamentos dato);///C

    List getListarInventa(Medicamentos dato);

    List getListarInventaGrupo(Medicamentos dato);

    List getListarInventaGrupoSub(Medicamentos dato);

    List getListarInventaSubGrupo(Medicamentos dato);

    List getListarInventaPartida(Medicamentos dato);

    List getListarTipos(Medicamentos dato);
    
    List getListarGrupos(Medicamentos dato);

    List getListarSubGrupos(Medicamentos dato);///S

    List getListarSubGrupos2(Medicamentos dato);///2

    List getListarPartidas(Medicamentos dato);///P

    List getListarMedicamentosb1(Medicamentos dato);

    List getListarMedicamentosRe(Medicamentos dato);

    List getListarMedicamentosMicro(Medicamentos dato);

    List getListarCarmelosExel(Medicamentos dato);

    List getActualizarMedicamentos(Medicamentos dato);

    List getActualizarMedicamentos_med(Medicamentos dato);

    Medicamentos getDatosMedicamento(Medicamentos dato);

    Medicamentos getDatoGrupo(Medicamentos dato);

    Medicamentos getDatoSubGrupo(Medicamentos dato);

    Medicamentos getDatoPartida(Medicamentos dato);

    Medicamentos getDatoSubPartida(Medicamentos dato);

    Medicamentos getDatosMedicamentoB(Medicamentos dato); ///B

    Medicamentos getDatosItem(Medicamentos dato); ///I

    Medicamentos getDatosItemAlmacen(Medicamentos dato); ///A

    Medicamentos getDatosSubItem(Medicamentos dato); ///T

    Medicamentos getDatosMedicamentoAsig(Medicamentos dato); ///S

    Medicamentos getDatosMedicamentoPasado(Medicamentos dato); ///H

    Medicamentos getDatosMedicamentoUnico(Medicamentos dato); ///U

    Medicamentos getDatosMedicamentoExiste(Medicamentos dato); ///Y

    Medicamentos getDatoPrograma(Medicamentos dato); ///D

    void setEliminarMedicamento(Medicamentos medicamento);

    void setEliminarMedicamentoLocal(Medicamentos medicamento); ///L  

    void setEliminarMedicamentoAsignado(Medicamentos medicamento);///S   

    void setEliminarGrupo(Medicamentos medicamento);///G   

    void setEliminarPartida(Medicamentos medicamento);///P   

    void setEliminarSubGrupo(Medicamentos medicamento);///2   

    void setCrearMedicamento(Medicamentos medicamento);

    void setCrearMedicamentoLocal(Medicamentos medicamento);///L

    void setCrearMedicamentoLocal2(Medicamentos medicamento);///2

    void setCrearMedicamentoAsignacion(Medicamentos medicamento);///S

    void setCrearGrupo(Medicamentos medicamento);///G

    void setCrearPartida(Medicamentos medicamento);///P

    void setCrearSubGrupo(Medicamentos medicamento);///I

    void setModificarMedicamento(Medicamentos medicamento);

    void setModificarMedicamentoStock(Medicamentos medicamento);

    void setModificarMedicamentoTotal(Medicamentos medicamento);///T

    void setModificarGrupo(Medicamentos medicamento);///G

    void setModificarPartida(Medicamentos medicamento);///P

    void setModificarSubGrupo(Medicamentos medicamento);///S

    void setModificarCie10(Medicamentos medicamento);

    List getListarEnfermedades(String nombres);

    List getListarEnfermedadesOtra(String nombres);

    List getListarEnfermedadesCod(String nombres);

    List getListarEnfermedadesCot(Medicamentos medicamento);

    List getListarProgramas(Medicamentos medicamento);

    List getListarImm(Medicamentos medicamento);

    List getListarImmFarma(Medicamentos medicamento);///F

    List getListarImmCNS(Medicamentos medicamento);///C

    List getListarImmCNS2(Medicamentos medicamento);///D

    List getListarImmCNSsaldo(Medicamentos medicamento);///S

    List getListarImmCNSsaldo2(Medicamentos medicamento);///M

    List getListarFaltanImm(Medicamentos medicamento);

    List getListarDatosImm(Medicamentos medicamento);

    List getListarDatosImmCNS2(Medicamentos medicamento);

    List getListarActImm(Medicamentos medicamento);

    List getListarImmFechaDada(Medicamentos medicamento);

    List getListarImmFechaDadaProg(Medicamentos medicamento);///P

    List getListarImmFechaDadaUsua(Medicamentos medicamento);///U

    List getListarImmFechaDadaCNS(Medicamentos medicamento);///C

    List getListarImmFechaDet(Medicamentos medicamento);///D

    List getListarActCpt(Medicamentos medicamento);

    List getListarActCptII(Medicamentos medicamento);///P

    List getListarCptPsico(Medicamentos medicamento);///S

}
