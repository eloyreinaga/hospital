package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Paises;

@Mapper
public interface PaisesDao {

    List getListarPaises();

    Paises getDatosPais(int id_pais);

    void setCrearPais(Paises pais);

    void setModificarPais(Paises pais);

    void setEliminarPais(Paises pais);
}
