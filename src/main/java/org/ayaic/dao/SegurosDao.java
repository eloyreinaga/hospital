package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Seguros;

@Mapper
public interface SegurosDao {

    List getListarSeguros(String id_estado);

    Seguros getDatosSeguro(int id_seguro);

    void setCrearSeguro(Seguros seguro);

    void setModificarSeguro(Seguros seguro);

    void setEliminarSeguro(Seguros seguro);

}
