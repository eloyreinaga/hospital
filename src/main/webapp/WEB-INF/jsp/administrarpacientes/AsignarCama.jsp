<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>

<div><a class="volver" href='ListarPacientes.do'>Volver</a></div>
<br>

<form name="adicionacat" method="POST" action='<c:url value="/ConfirmarPaciente.do"/>' >     
    <table class="formulario">
        <tr>
            <th colspan="3">INTERNAR AL PACIENTE VIGENCIA </th>
        </tr>
        <tr>    
            <td>Nombres</td>    
            <td>::</td>
            <td><c:out value = "${nombres}"/></td>
        </tr>
        <tr>
            <td>Especialidad  </td>
            <td>::</td>	      
            <td>
                <SELECT NAME="id_consultorio" onchange="javascript:document.adicionacat.submit();">
                    <option value="">-- seleccione --</option>
                    <c:forEach var="estado" items="${listarCargos}">
                        <c:if test="${estado.id_cargo!=3 and estado.id_especialidad!=99 and estado.id_cargo!=15 and estado.id_cargo!=7 and estado.id_cargo!=34 and estado.id_cargo!=33 and estado.id_cargo!=1 and estado.id_cargo!=11 and estado.id_cargo!=99}">   
                            <option value="<c:out value="${estado.id_consultorio}"/>" <c:if test="${estado.id_consultorio == id_consultorio}">selected</c:if>>
                                <c:out value="${estado.consultorio}"/>
                            </option>
                        </c:if>    
                    </c:forEach>
                </SELECT>	
                <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
                <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
                <input type="hidden" name='cod_esta'        value='<c:out value="${cod_esta}"/>'>
            </td>
        </tr>
        <tr>
            <td>Medico  </td>
            <td>::</td>
            <td>
                <SELECT NAME="id_persona" >
                    <option value="">-- seleccione --</option>
                    <c:forEach var="perso" items="${listaPersonas}">
                        <option value="<c:out value="${perso.id_persona}"/>"<c:if test="${perso.id_persona == id_persona}">selected</c:if>> 
                            <c:out value="${perso.nombres}"/>
                        </option>
                    </c:forEach>
                </SELECT>	      
                <input type="hidden" name='id_persona'  value='<c:out value="${id_persona}"/>'>
            </td>
        </tr>
        <tr>
            <td>Listar Pisos  </td>
            <td>::</td>	      
            <td>
                <SELECT NAME="id_piso" onchange="javascript:document.adicionacat.submit();">
                    <option value="0">-- Sin piso --</option>
                </SELECT>	
            </td>       
        </tr>      
        <tr>
            <td>Listar Salas  </td>
            <td>::</td>	      
            <td>
                <SELECT NAME="id_sala" onchange="javascript:document.adicionacat.submit();">
                    <option value="0">-- Sin sala --</option>
                </SELECT>	
            </td>       
        </tr>      
        <tr>
            <td>Buscar Cama  </td>
            <td>::</td>	      
            <td>
                <SELECT NAME="id_cama">
                    <option value="0">-- Sin cama --</option>   
                </SELECT>	
            </td>        
        </tr> 

    </table>
    <center>
        <input type="submit" class="siguiente" value='InternarPaciente' onclick="document.adicionacat.accion.value = 'InternarPaciente';
                document.adicionacat.action = '<c:url value="/ConfirmarPaciente.do"/>'">                                       
        <!--dd -->                                         

    </center>
    <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
    <input type="hidden" name='id_consultorio'   value='<c:out value="${id_consultorio}"/>'>
    <input type="hidden" name='id_paciente'      value='<c:out value="${id_paciente}"/>'>
    <input type="hidden" name='id_reservacion'   value='<c:out value="${id_reservacion}"/>'>
    <input type="hidden" name='accion'           value='InternarPaciente'>
</form>
