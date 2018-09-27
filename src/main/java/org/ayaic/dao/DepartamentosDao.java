package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Departamentos;

@Mapper
public interface DepartamentosDao {

    List getListarDepartamentos();

    Departamentos getDatosDepartamento(int id_departamento);

    void setCrearDepartamento(Departamentos departamento);

    void setModificarDepartamento(Departamentos departamento);

    void setEliminarDepartamento(Departamentos departamento);

    List getListarPaisDep(int id_pais);
}
