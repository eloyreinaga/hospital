package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Salas;

@Mapper
public interface SalasDao {

    List getListarSalas();

    List getListarPisos(Salas sala);

    List getListarPisosTotal(Salas sala);///P

    List getListarSalasLibres(Salas sala);

    List getListarSalaPiso(Salas sala);///P

    List getListarSalaPisoTotal(Salas sala);///T

    Salas getDatosSala(int id_sala);

    void setCrearSala(Salas sala);

    void setModificarSala(Salas sala);

    void setEliminarSala(Salas sala);
}
