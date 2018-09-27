package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Escolaridades;

@Mapper
public interface EscolaridadesDao {

    List getListarEscolaridades();

    Escolaridades getDatosEscolaridad(int id_escolaridad);

    void setCrearEscolaridad(Escolaridades escolaridad);

    void setModificarEscolaridad(Escolaridades escolaridad);

    void setEliminarEscolaridad(Escolaridades escolaridad);

}
