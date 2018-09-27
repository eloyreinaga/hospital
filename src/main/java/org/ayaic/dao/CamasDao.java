package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Camas;

@Mapper
public interface CamasDao {

    List getListarCamas(Camas cama);

    List getListarCamasTotal(Camas cama);

    List getListarCamaUnit(Camas cama);

    List getListarCamaPiso(Camas cama);

    Camas getDatosCama(int id_cama);

    void setCrearCama(Camas cama);

    void setModificarCama(Camas cama);

    void setModificarCamaVacia(Camas cama);

    void setEliminarCama(Camas cama);

    List getListarCamasSala(Camas cama);

    List getListarCamasEstado(Camas cama);

}
