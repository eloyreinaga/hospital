package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Tableros;

@Mapper
public interface TablerosDao {

    List getListarNoticias();

    List getListarTiposTableros();

    List getListarTiposAvisos();

    int setRegistrarTablero(Tableros tablero);

    Tableros getBuscarTablero(Tableros tablero);

    int setEliminarTablero(Tableros tablero);

}
