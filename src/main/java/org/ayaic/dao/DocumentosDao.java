package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Documentos;

@Mapper
public interface DocumentosDao {

    List getListarDocumentos();

    Documentos getDatosDocumento(int id_documento);

    void setCrearDocumento(Documentos documento);

    void setModificarDocumento(Documentos documento);

    void setEliminarDocumento(Documentos documento);

}
