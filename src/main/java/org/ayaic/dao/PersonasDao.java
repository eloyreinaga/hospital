package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.ayaic.domain.Personas;

@Mapper
public interface PersonasDao {

    List getListarPersonas(Personas persona);
    
    List getListarPersonasInter(Personas persona);

    List getListarPersonasFacturas(Personas persona);///F

    List getListarPersonasSoloAten(Personas persona);///S

    List getListarPersonasNombre(Personas persona);///N

    List getListarPersonasNombreMat(Personas persona);///1

    List getListarPersonasUsua(Personas persona);///U

    List getListarPersonasUsuaLocal(Personas persona);///O

    List getListarPersonaUnico(Personas persona);///B

    List getListarPersonasNombreLocal(Personas persona);///M

    List getListarPersonasLocal(Personas persona);///L

    List getListarPersonasEstab(Personas persona);///E

    List getListarPersonasFarma(Personas persona);///A
    
    List getListarPersonasFichas(Personas persona);

    Personas getDatosPersona(int id_persona);

    Personas getDatosPersonaInt(int id_persona);

    List getDatosPersonaInter(Personas persona);
    
    List getDatosPersonaConsul(Personas persona);

    List getDatosPersonaConsulUrgen(Personas persona);///U

    List getDatosPersonaConsulEmerg(Personas persona);///E

    List getDatosPersonaConsulLabos(Personas persona);///L

    List getDatosPersonaConsulCIE(Personas persona);///C

    List getListaMedicoAsig(Personas persona);///A

    Personas getBuscarPersona(int id_persona);

    void setCrearPersona(Personas persona);

    void setCrearPersonaEnfer(Personas persona);
    
    void setCrearPersonaInter(Personas persona);

    void setModificarPersona(Personas persona);

    void setModificarPersonaFic(Personas persona);

    void setEliminarPersona(Personas persona);
    
    void setEliminarPersonaInter(Personas persona);

    void setEliminarPersonaAsig(Personas persona);

    List getListarFichadas(Personas persona);
}
