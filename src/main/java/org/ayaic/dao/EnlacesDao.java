package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Enlaces;

@Mapper
public interface EnlacesDao {

    List getListarEnlaces(Enlaces enlace);

    Enlaces getBuscarEnlace(Enlaces enlace);
    // INICIO Combustible \\

    Enlaces getEnlBuscarEnlace(Enlaces enlace);
    // FIN Combustible \\

}
