package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Empresas;

@Mapper
public interface EmpresasDao {

    List getListarEmpresas(String id_estado);

    Empresas getDatosEmpresa(int id_empresa);

    List getListarEmpresa2(Empresas empresa);

    List getListarEmpresa3(Empresas empresa);////L

    List getListarEmpresaCaja(Empresas empresa);////C

    List getListaEmpresa(Empresas empresa);

    List getListaEmpresaCod(Empresas empresa);////L

    List getListaEmpEmpresa(Empresas empresa);

    List getListaEmpEmpresaCod(Empresas empresa); ////L

    void setCrearEmpresa(Empresas empresa);

    void setModificarEmpresa(Empresas empresa);

    void setEliminarEmpresa(Empresas empresa);

}
