package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Consultorios;

@Mapper
public interface ConsultoriosDao {

    List getListarConsultorios(Consultorios a);
    
    List getListarConsultoriosInter(Consultorios a);

    List getListarConsultoriosEmerg(Consultorios a);

    List getListarConsultoriosTransf(Consultorios a);

    List getListarConsultoriosUrgen(Consultorios a);

    List getListarTipoRecetaCNS(Consultorios a);

    List getListarCentroCNS(Consultorios a);

    List getListarCentroCNSFar(Consultorios a);

    List getListarServicioCNS(Consultorios a);

    List getListarServicioCNSFar(Consultorios a);

    List getListarServicioCNS1(Consultorios a);

    List getListarServicioCNS2(Consultorios a);

    List getListarCodCNS(Consultorios a);

    List getListarConsultoriosGen(Consultorios a);

    Consultorios getDatosConsultorio(int id_cargo);

    void setCrearConsultorio(Consultorios a);

    void setModificarConsultorio(Consultorios a);

    void setEliminarConsultorio(Consultorios a);

    List getListarCargos();

    Consultorios getDatosCargo(int id_cargo);

    void setCrearCargo(Consultorios a);

    void setModificarCargo(Consultorios a);

    void setEliminarCargo(Consultorios a);

}
