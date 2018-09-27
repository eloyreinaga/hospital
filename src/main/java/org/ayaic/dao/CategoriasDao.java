package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Categorias;

@Mapper
public interface CategoriasDao {

    List getListarCategorias(Categorias categoria);

}
