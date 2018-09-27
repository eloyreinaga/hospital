<%@ include file="../Superior.jsp" %>


<div style="font-size:15pt"> Pacientes Atendidos el dia de Ayer</div>
<br>

<table class="table table-striped table-bordered table-condensed table-responsive"> 
    <tr style="font-size:9pt">
        <th bgcolor="#F2F2F2"> No </th>
        <th bgcolor="#F2F2F2"> FECHA </th>
        <th bgcolor="#F2F2F2"> PACIENTE </th>
        <th bgcolor="#F2F2F2"> EDAD </th>
        <th bgcolor="#F2F2F2"> TIPO </th>    
        <th bgcolor="#F2F2F2"> RECETA </th>    
        <th bgcolor="#F2F2F2"> Ley475 </th>    
        <th bgcolor="#F2F2F2"> C-1 </th>    
        <th bgcolor="#F2F2F2"> C-2 </th>    
        <th bgcolor="#F2F2F2"> C-3 </th>    
        <th bgcolor="#F2F2F2"> C-4 </th>
        <th bgcolor="#F2F2F2"> C-5 </th>    
        <th bgcolor="#F2F2F2"> C-6 </th>    
        <th bgcolor="#F2F2F2"> C-7 </th>        
        <th bgcolor="#F2F2F2"> C-V </th>
        <th bgcolor="#F2F2F2"> C-F </th>
        <th colspan="3"> IMPRIMIR </th>
        <th> MODIFICAR </th>
    </tr>  
    <c:forEach var="lista" items="${milistaAten}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><fmt:formatDate value="${lista.fecha}" pattern='dd/MM/yy'/>&nbsp;&nbsp;<b><fmt:formatDate value="${lista.fecha}" pattern='HH:mm'/></b></td>    
            <td><c:out value="${lista.nombres}"/></td>   
        <form name=formaH<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarHistorial.do"/>'>
            <td>     
                <div><center><a href="javascript:document.formaH<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.edad}"/></a></center></div>
                <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                <input type="hidden" name="nombres"    value=<c:out value="${lista.nombres}"/> >
                <input type="hidden" name="accion"         value='Historial' >
                <input type="hidden" name="sw"             value='1' >
            </td>
        </form>
        <c:if test="${lista.expedido=='S'}">
            <form name=formaHA<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAmbulatorioGen.do"/>'>
                <td align="center" style="color:red">     
                    <div><center><a href="javascript:document.formaHA<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.expedido}"/></a></center></div>
                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                    <input type="hidden" name="nombres"    value=<c:out value="${lista.nombres}"/> >
                    <input type="hidden" name="accion"         value='Historial' >
                    <input type="hidden" name="sw"             value='1' >
                </td>
            </form>
        </c:if>

        <c:if test="${lista.expedido=='E'}"> 
            <td align="center">  Externo   </td>
        </c:if>
        <c:if test="${lista.expedido=='P'}"> 
            <td align="center" style="color:blue"><c:out value="${lista.seguro}"/> </td>
        </c:if>

        <form name=formaReceta<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarReceta.do"/>'>
            <td align="center">     
                <div><center><a href="javascript:document.formaReceta<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.edad_ini}"/></a></center></div>
                <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                <input type="hidden" name="nombres"        value=<c:out value="${lista.nombres}"/> >
                <input type="hidden" name="accion"         value='RecetaT' >
                <input type="hidden" name="sw"             value='1' >
            </td>
        </form>     
        <c:if test="${lista.expedido=='S' and lista.edad_fin>0}"> 
            <td align="center"><b><c:out value="${lista.edad_fin}"/><b></td>
                    </c:if>

                    <c:if test="${lista.expedido=='S' and lista.edad_fin<=0}"> 
                        <td align="center" style="color:red"><b><c:out value="Falta"/></b></td>
                            </c:if>

                    <c:if test="${lista.expedido=='E' and lista.edad_fin<=0}"> 
                        <td align="center"><b><c:out value="${lista.edad_fin}"/><b></td>
                                </c:if>

                                <c:if test="${lista.expedido=='P' }"> 
                                    <td align="center"><b><c:out value="${lista.edad_fin}"/><b></td>
                                            </c:if>  
                                            <form name=forma1P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno1.do"/>'>
                                                <th align="center">     
                                                    <div><center><a href="javascript:document.forma1P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_tipo_documento}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno1' >
                                                </th>
                                            </form><!--Fin de cuaderno1 individual  -->
                                            <form name=forma2P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno2.do"/>'>
                                                <td align="center">     
                                                    <div><center><a href="javascript:document.forma2P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_tipo_parentesco}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno2' >
                                                </td>
                                            </form><!--Fin de cuaderno2 individual  -->
                                            <form name=forma3P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno3.do"/>'>
                                                <td align="center">     
                                                    <div><center><a href="javascript:document.forma3P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_pais}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno3' >
                                                </td>
                                            </form><!--Fin de cuaderno3 individual  -->
                                            <form name=forma4P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno4.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.forma4P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_departamento}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno4' >
                                                </td>
                                            </form><!--Fin de cuaderno4 individual  -->
                                            <form name=forma5P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno5.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.forma5P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.num}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno5' >
                                                </td>
                                            </form><!--Fin de cuaderno5 individual  -->
                                            <form name=forma6P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno6.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.forma6P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_provincia}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno6' >
                                                </td>
                                            </form><!--Fin de cuaderno6 individual  -->    
                                            <form name=forma7P<c:out value="${contador.count}"/> method=post action='<c:url value="/Cuaderno7.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.forma7P<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_localidad}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Cuaderno7' >
                                                </td>
                                            </form><!--Fin de cuaderno7 individual  -->
                                            <form name=formaV<c:out value="${contador.count}"/> method=post action='<c:url value="/Vacunas.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.formaV<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.id_reservacion}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Vacunas1' >
                                                </td>
                                            </form><!--Fin de cuaderno vacunas individual  -->
                                            <form name=formaF<c:out value="${contador.count}"/> method=post action='<c:url value="/Fisioterapia.do"/>'>
                                                <td align="center" style="color:blue">     
                                                    <div><center><a href="javascript:document.formaF<c:out value="${contador.count}"/>.submit();"><c:out value="${lista.embarazo}"/></a></center></div>
                                                    <input type="hidden" name="id_paciente"    value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="accion"         value='Vacunas1' >
                                                </td>
                                            </form><!--Fin de cuaderno fisioterapia individual  -->   
                                            <td >   
                                                <form name=formaMip1<c:out value="${contador.count}"/> method=post action='<c:url value="/HistorialAtendidos.do"/>'>
                                                    <a href="javascript:document.formaMip1<c:out value="${contador.count}"/>.submit();"><font size="2">Imp1</font></a>
                                                    <input type="hidden" name='id_historial'    value='<c:out value="${lista.id_historial}"/>'> 
                                                    <input type="hidden" name='id_seguro'       value='<c:out value="${id_seguro}"/>'> 
                                                    <input type="hidden" name='id_persona'      value='<c:out value="${lista.id_persona}"/>'>
                                                    <input type="hidden" name='id_consultorio'  value='<c:out value="${lista.id_consultorio}"/>'>
                                                    <input type="hidden" name='id_paciente'     value='<c:out value="${lista.id_paciente}"/>'>
                                                    <input type="hidden" name='expedido'        value='<c:out value="${lista.expedido}"/>' >            
                                                    <input type="hidden" name="accionc"         value='imprimeHCLBasica1'>
                                                </form>  
                                            </td>
                                            <td>
                                                <form name=formaMip2<c:out value="${contador.count}"/> method=post action='<c:url value="/HistorialAtendidos.do"/>'>
                                                    <a href="javascript:document.formaMip2<c:out value="${contador.count}"/>.submit();"><font size="2">Imp2</font></a>
                                                    <input type="hidden" name='id_historial'    value='<c:out value="${lista.id_historial}"/>'> 
                                                    <input type="hidden" name='id_seguro'       value='<c:out value="${id_seguro}"/>'> 
                                                    <input type="hidden" name='id_persona'      value='<c:out value="${lista.id_persona}"/>'>
                                                    <input type="hidden" name='id_consultorio'  value='<c:out value="${lista.id_consultorio}"/>'>
                                                    <input type="hidden" name='id_paciente'     value='<c:out value="${lista.id_paciente}"/>'>
                                                    <input type="hidden" name='expedido'        value='<c:out value="${lista.expedido}"/>' >            
                                                    <input type="hidden" name="accionc"         value='imprimeHCLBasica2'>
                                                </form>
                                            </td>
                                            <td>
                                                <form name=formaMip3<c:out value="${contador.count}"/> method=post action='<c:url value="/HistorialAtendidos.do"/>'>
                                                    <a href="javascript:document.formaMip3<c:out value="${contador.count}"/>.submit();"><font size="2">Imp3</font></a>
                                                    <input type="hidden" name='id_historial'    value='<c:out value="${lista.id_historial}"/>'> 
                                                    <input type="hidden" name='id_seguro'       value='<c:out value="${id_seguro}"/>'> 
                                                    <input type="hidden" name='id_persona'      value='<c:out value="${lista.id_persona}"/>'>
                                                    <input type="hidden" name='id_consultorio'  value='<c:out value="${lista.id_consultorio}"/>'>
                                                    <input type="hidden" name='id_paciente'     value='<c:out value="${lista.id_paciente}"/>'>
                                                    <input type="hidden" name='expedido'        value='<c:out value="${lista.expedido}"/>' >            
                                                    <input type="hidden" name="accionc"         value='imprimeHCLBasica3'>
                                                </form>
                                            </td>
                                            <form name=formaMaa<c:out value="${contador.count}"/> method=post action='<c:url value="/AtenderPaciente.do"/>'>
                                                <td >     
                                                    <div><a class="btn btn-warning btn-xs" href="javascript:document.formaMaa<c:out value="${contador.count}"/>.submit();">Consultar</a></div>
                                                    <input type="hidden" name="id_reservacion" value=<c:out value="${lista.id_historial}"/> >
                                                    <input type="hidden" name="id_persona" value=<c:out value="${id_persona}"/> >         
                                                    <input type="hidden" name="id_consultorio" value=<c:out value="${lista.id_consultorio}"/> >         
                                                    <input type="hidden" name="id_paciente" value=<c:out value="${lista.id_paciente}"/> >
                                                    <input type="hidden" name="expedido" value=<c:out value="${lista.expedido}"/> >         
                                                    <input type="hidden" name="tipo_medico" value=<c:out value="${tipo_medico}"/> >         
                                                    <input type="hidden" name="accion" value='Consultar' >
                                                    <input type="hidden" name="sw1" value='actualiza' >
                                                </td>
                                            </form>
                                            </tr> 
                                        </c:forEach>
                                        </table>