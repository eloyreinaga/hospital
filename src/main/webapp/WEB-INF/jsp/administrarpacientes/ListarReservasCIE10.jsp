<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<center>
    <table class="table table-striped table-bordered table-condensed table-responsive">
        <center>
            <form name="forma" method="POST" action='<c:url value="/ListarReservasCIE10.do"/>' >  
                <td>
                    <table class="table table-striped table-bordered table-condensed table-responsive">
                        <tr>
                            <th>BUSQUEDA DE DATOS</th>
                        </tr>
                        <tr>
                            <td>
                                <fieldset> 
                                    <table class="table table-striped table-bordered table-condensed table-responsive">   
                                        <tr>  
                                            <td>Fecha inicio  </td>
                                            <td><input type="text" name="valor_1" size="10" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>' ></td>
                                            <td><small><a href="javascript:showCal('valor_1')"><img src="./imagenes/formularios/calendario.jpeg" border="0" ></a></small></td>
                                        </tr>
                                        <tr>
                                            <td>Fecha final  </td>
                                            <td><input type="text" name="valor_2" size="10" value='<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>' readonly></td>
                                            <td><small><a href="javascript:showCal('valor_2')"><img src="./imagenes/formularios/calendario.jpeg" border="0" ></a></small></td>
                                        </tr>
                                    </table>
                                </fieldset>
                        <center>
                            <input type="submit" name="boton" class="btn btn-primary" value="Buscar">
                        </center>
                </td>
                </tr>
                </table></td>
            </form>
        </center>
        <form name="listarreservascie10" method="POST" action='<c:url value="/ListarReservasCIE10.do"/>' >
            <td>
                <table class="table table-striped table-bordered table-condensed table-responsive">
                    <tr>
                        <th colspan="3" style="font-size:12pt">RESUMEN PACIENTES CON CIE10 EN COSNULTORIOS</th>
                    </tr>
                    <tr>
                        <td width="100%" valign="top">
                            <table class="formulario" width="100%">
                                <tr>
                                    <td>Consultorio </td>	      
                                    <td>
                                        <SELECT NAME="id_consultorio" onchange="javascript:document.listarreservascie10.submit();">
                                            <option value="0">-- seleccione --
                                                <c:forEach var="estado" items="${listarCargos}">
                                                <OPTION value="<c:out value="${estado.id_consultorio}"/>" <c:if test="${estado.id_consultorio == id_consultorio}">selected</c:if>> 
                                                    <c:out value="${estado.consultorio}"/>
                                                </option>
                                            </c:forEach>
                                        </SELECT>	
                                    </td>
                                </tr> 
                                <tr>
                                    <td>Medico  </td>
                                    <td>
                                        <SELECT NAME="id_persona"  onchange="javascript:document.listarreservascie10.submit();">
                                            <c:forEach var="perso" items="${listaPersonas}">
                                                <option value="<c:out value="${perso.id_persona}"/>"<c:if test="${perso.id_persona == id_persona}">selected</c:if>> 
                                                    <c:out value="${perso.nombres}"/>
                                                </option>
                                            </c:forEach>
                                        </SELECT>	      
                                    </td>
                                </tr> 
                            </table>
                        </td>
                    </tr>
                </table></td>
        </tr></table>
</center>
<input type="hidden" name='accion'          value='<c:out value="${accion}"/>'>
</form> 

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr style="font-size:9pt">
        <th bgcolor="#F2F2F2"> NRO </th>
        <th bgcolor="#F2F2F2"> FECHA </th>
        <th bgcolor="#F2F2F2"> HCL </th>
        <th bgcolor="#F2F2F2"> PACIENTE </th>
        <th bgcolor="#F2F2F2"> Edad </th>
        <th bgcolor="#F2F2F2"> CONSULTORIO </th> 
        <th bgcolor="#F2F2F2"> TIPO </th> 
        <th bgcolor="#F2F2F2"> MEDICO </th> 
        <th bgcolor="#F2F2F2"> CIE10 </th> 
        <th bgcolor="#F2F2F2"> MODIFICAR </th>
    </tr>  
    <c:forEach var="lista" items="${milista}" varStatus="contador">
        <tr style="font-size:9pt">
        <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarPaciente.do"/>'>
            <td>     
                <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"><c:out value="${contador.count}"/></a></div>
                <input type="hidden" name="id_paciente" value=<c:out value="${lista.id_paciente}"/> >         
                <input type="hidden" name="id_reservacion" value=<c:out value="${lista.id_reservacion}"/> >                  
                <input type="hidden" name="id_consultorio" value=<c:out value="${lista.id_consultorio}"/> >                  
                <input type="hidden" name="accion" value='EliminarReserva' >
                <input type="hidden" name="sw1" value='1' >
            </td>
        </form>
        <form name=formaEstg<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarHistorial.do"/>'>
            <td>     
                <div><center><a href="javascript:document.formaEstg<c:out value="${contador.count}"/>.submit();"><fmt:formatDate value="${lista.fecha}" pattern='dd/MM/yyyy HH:mm'/></a></center></div>
                <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                <input type="hidden" name="id_historial"   value=<c:out value="${lista.id_reservacion}"/> >          
                <input type="hidden" name="accion"         value='Estado' >
                <input type="hidden" name="sw"             value='1' >
            </td>
        </form>

        <td style="color:green"><b><c:out value="${lista.hcl}"/></b></td> 
        <td><c:out value="${lista.nombres}"/></td> 
        <td align="center"><c:out value="${lista.edad_ini}"/></td>
        <td style="font-size:10pt;color:blue"><c:out value="${lista.consultorio}"/></td>    
        <c:if test="${lista.expedido == 'E' }">
            <td style="color:blue">Externo</td>
        </c:if>
        <c:if test="${lista.expedido == 'S' }">
            <td style="color:red">SIIS</td>
        </c:if>
        <c:if test="${lista.expedido == 'P' }">
            <td align="center"><c:out value="${lista.seguro}"/></td>
        </c:if>

        <td><c:out value="${lista.nombre}"/></td>
        <c:if test="${lista.resultado != 'Estadistica Morbilidad' }">
            <td align="center" style="font-size:12pt"><b><c:out value="${lista.codigo}"/></b></td> 
                </c:if>
                <c:if test="${lista.resultado == 'Estadistica Morbilidad' }">
            <td align="center" style="font-size:10pt;color:red"><b><c:out value="${lista.codigo}"/></b></td> 
                </c:if>
                <c:if test="${fn:length(lista.codigo)==0 }">
            <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarReservasCIE10.do"/>'>
                <td>     
                    <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Reportar</a></div>
                    <input type="hidden" name="id_paciente" value=<c:out value="${lista.id_paciente}"/> >         
                    <input type="hidden" name="id_reservacion" value=<c:out value="${lista.id_reservacion}"/> >         
                    <input type="hidden" name="id_consultorio" value=<c:out value="${lista.id_consultorio}"/> >   
                    <input type="hidden" name="expedido" value=<c:out value="${lista.expedido}"/> > 
                    <input type="hidden" name="fecha" value=<c:out value="${lista.fecha}"/> >  
                    <input type="hidden" name="tipo_medico" value=<c:out value="${tipo_medico}"/> >   
                    <input type="hidden" name="accion" value='Reportar' >
                    <input type="hidden" name="sw" value='1' >
                </td>
            </form>
        </c:if>
        <c:if test="${fn:length(lista.codigo)!=0 }">
            <form name=formaMaM<c:out value="${contador.count}"/> method=post action='<c:url value="/AtenderPaciente.do"/>'>
                <td >     
                    <div><a class="btn btn-warning btn-xs" href="javascript:document.formaMaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
                    <input type="hidden" name="id_reservacion" value=<c:out value="${lista.id_reservacion}"/> >
                    <input type="hidden" name="id_persona" value=<c:out value="${lista.id_persona}"/> >         
                    <input type="hidden" name="id_consultorio" value=<c:out value="${lista.id_consultorio}"/> >         
                    <input type="hidden" name="id_paciente" value=<c:out value="${lista.id_paciente}"/> >
                    <input type="hidden" name="expedido" value=<c:out value="${lista.expedido}"/> > 
                    <input type="hidden" name="fecha" value=<c:out value="${lista.fecha}"/> >  
                    <input type="hidden" name="tipo_medico" value=<c:out value="${tipo_medico}"/> >         
                    <input type="hidden" name="accionm" value='Modificar' >
                    <input type="hidden" name="sw1" value='actualiza' >
                </td>
            </form> 
        </c:if>
    </c:forEach>
</table>