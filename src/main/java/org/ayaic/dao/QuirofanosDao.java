package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Quirofanos;

@Mapper
public interface QuirofanosDao {

    List getListarQuirofanos(Quirofanos quirofano);

    List getListarQuirofanosLibres(Quirofanos quirofano);///L

    List getListarQuirofanosLibres1(Quirofanos quirofano);///Q

    List getListarQuirofanosLibres2(Quirofanos quirofano);///I

    Quirofanos getDatosQuirofano(Quirofanos quirofano);

    void setCrearQuirofano(Quirofanos quirofano);

    void setCrearReservaQuirofano(Quirofanos quirofano);///Q

    void setModificarQuirofano(Quirofanos quirofano);

    void setEliminarQuirofano(Quirofanos quirofano);
}
