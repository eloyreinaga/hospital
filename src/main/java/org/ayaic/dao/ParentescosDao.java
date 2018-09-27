package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Parentescos;

@Mapper
public interface ParentescosDao {

    List getListarParentescos();

    Parentescos getDatosParentesco(int id_parentesco);

    void setCrearParentesco(Parentescos parentesco);

    void setModificarParentesco(Parentescos parentesco);

    void setEliminarParentesco(Parentescos parentesco);

}
