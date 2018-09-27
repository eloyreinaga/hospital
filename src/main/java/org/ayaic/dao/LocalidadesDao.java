package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Localidades;

@Mapper
public interface LocalidadesDao {

    List getListarPaisDepProvLoc(Localidades dato);

    List getListarMuniRed(Localidades dato);

    List getListarEstab(Localidades dato);

    List getListarEstabRef(Localidades dato);///R

    List getEstabTransCns(Localidades dato);////C

    List getEstabHabiles(Localidades dato);///M

    List getListarEstabGen(Localidades dato);///G

    List getListarEsta(Localidades dato);

    List getListarEstaUsua(Localidades dato); ////U

    List getListarRed(Localidades dato);

    Localidades getDatosLocalidad(Localidades dato);

    Localidades getDatosEstable(Localidades dato); ////E

    void setModificarEstab(Localidades dato);
}
