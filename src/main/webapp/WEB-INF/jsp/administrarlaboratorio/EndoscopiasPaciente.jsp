<%@ include file="../Superior.jsp" %>

<div style="font-size:15pt"> Laboratorios e Imagenologiia</div>
<br>
<table class="formulario">
    <tr>
        <th colspan="3">DATOS PERSONALES</th>
    </tr>
    <tr>
        <td width="100%" valign="top">
            <table class="formulario" width="100%">

                <tr>
                    <td>HCL</td>
                    <td>::</td>
                    <td><c:out value = "${datos.hcl}"/></td>
                </tr>
                <tr>    
                    <td>Nombres</td>    
                    <td>::</td>
                    <td><c:out value = "${datos.nombres}"/></td>
                </tr>
                <tr>
                    <td>Sexo</td>
                    <td>::</td>	      
                    <td> <c:out value="${buscarSexo.sexo}"/></td>
                </tr> 
                <tr>
                    <td>Fecha de nac.</td>    
                    <td>::</td>
                    <td><c:out value="${fec_nacimiento}"/></td>	                 
                </tr>
                <tr>    
                    <td>Direcci&oacute;n</td>    
                    <td>::</td>	      
                    <td><c:out value = "${datos.direccion}"/></td>
                </tr>  
                <tr>    
                    <td>Edad</td>    
                    <td>::</td>	      
                    <td style="color:blue"><b><c:out value = "${datos.edad}"/> años <c:out value = "${datos.mes}"/> meses <c:out value = "${datos.dia}"/> dias</b></td>
                </tr>  
                <c:if test="${fn:length(datos.factor_riesgo)>2 }">
                    <tr>    
                        <td>Factores de Riesgo</td>    
                        <td>::</td>	      
                        <td style="font-size:20pt;color:red"><c:out value = "${datos.factor_riesgo}"/></td>
                    </tr>  
                </c:if>
            </table>
        </td>
    </tr>

</table>
<!--
<form name=formaM method=post action='<c:url value="/AtenderPaciente.do"/>'>
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM.submit();">Retornar</a></div>
          <input type="hidden" name='id_reservacion'  value='<c:out value="${id_reservacion}"/>'>  
          <input type="hidden" name='id_persona'      value='<c:out value="${id_persona}"/>'>
          <input type="hidden" name='id_consultorio'  value='<c:out value="${id_consultorio}"/>'>
          <input type="hidden" name='id_paciente'     value='<c:out value="${id_paciente}"/>'>
          <input type="hidden" name="expedido"        value='<c:out value="${expedido}"/>' >                   
          <input type="hidden" name='accion'          value='Ninguno'>
  </form>
-->



<table class="tabla">
    <tr>
        <th> NRO </th>
        <th> FECHA </th>
        <th> ENDOSCOPIAS </th>
        <th> INDICACIONES </th>    
        <th> RESULTADO </th>        
    </tr>  
    <c:forEach var="lista" items="${milista}" varStatus="contador">
        <tr style="font-size:9pt">
            <td align="center"><c:out value="${contador.count}"/></td>
            <td><fmt:formatDate value="${lista.fechap}" pattern='dd/MM/yyyy'/></td>
            <td><c:out value="${lista.laboratorio}"/></td>    
            <td><c:out value="${lista.tipoconsulta}"/></td>         
            <td> <c:out value = "${lista.resultado}" escapeXml="False"/></td>   
            <td ><img src="../../../assets/img/imageneshistorial/97233/14/142011_12122015125435.png"> </td>   
            </c:forEach>
</table>
