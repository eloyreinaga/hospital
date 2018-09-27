<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div style="font-size:15pt"> Entrega de Medicamentos solo PROGRAMAS</div>
<table>
 <tr>
   <td>
    <table>
      <tr>
      <form name=formap method=post action='<c:url value="/ListarAtendidosProg.do"/>'>
        <td colspan="2">
          <div><a class="btn btn-primary" href="javascript:document.formap.submit();" >Nueva Receta Programas</a>
           <input type="hidden" name="sw" value='SSPAM'>
           <input type=hidden name=accion value='adicionar'>
        </div></td>
        </form>
      </tr>
    </table></td>
    <td><table>
      <tr>
      <form name=formaxyb method=post action='<c:url value="/ListarAtendidos.do"/>'>
        <td colspan="2">
          <div>
           <a class="btn btn-info" href="javascript:document.formaxyb.submit();" >Descargar Botiquin de Servicio</a>
           <input type="hidden" name='sw' value='Botiquin' >       
           <input type=hidden name='accion' value='adicionar'>
        </div></td>
        </form>
      <tr>
    </table>
   </td>
</tr> 
</table> 
<div style="font-size:15pt"> Pagar y Entregar Items (Enviado por el Medico)</div>
<br>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> NOMBRE COMPLETO </th>    
    <th bgcolor="#F2F2F2"> Edad </th> 
    <th bgcolor="#F2F2F2"> Tipo </th>
    <th bgcolor="#F2F2F2"> CONSULTORIO </th> 
    <th bgcolor="#F2F2F2"> MEDICO </th>
    <th bgcolor="#F2F2F2"> RECETA </th>
    <th bgcolor="#F2F2F2"> ENTREGAR </th>  
    </tr>  
   <c:forEach var="lista" items="${listarAtendidos}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${lista.fecha}" pattern='dd/MM/yy HH:mm'/></td>    
       <td style="color:blue"><c:out value="${lista.nombres}"/></td>    
       <td align="center"><c:out value="${lista.edad}"/></td>
       <td><c:out value="${lista.seguro}"/></td>    
       <td><c:out value="${lista.accion}"/></td>
       <td><c:out value="${lista.nombre}"/></td>   
    <form name=formaPre<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaPre<c:out value="${contador.count}"/>.submit();">Previa</a></div>
  	 <input type="hidden" name="id_historial" value='<c:out value="${lista.id_historial}"/>'>
         <input type="hidden" name="id_persona"   value='<c:out value="${lista.id_persona}"/>' > 
         <input type="hidden" name="expedido"     value='<c:out value="${lista.expedido}"/>'> 
         <input type="hidden" name="id_paciente"  value='<c:out value="${lista.id_paciente}"/>'> 
         <input type="hidden" name="nombres"      value='<c:out value="${lista.nombres}"/>'> 
         <input type="hidden" name="nombre"       value='<c:out value="${lista.nombre}"/>'>
	 <input type="hidden" name="accion"       value='previa' >
	 <input type="hidden" name="sw"           value='1' >
       </td>
     </form>     
    <form name=formaMa<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
       <td>     
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaMa<c:out value="${contador.count}"/>.submit();">Entregar</a></div>
  	 <input type="hidden" name="id_historial"  value='<c:out value="${lista.id_historial}"/>' >
         <input type="hidden" name="id_paciente"   value='<c:out value="${lista.id_paciente}"/>' >  
         <input type="hidden" name="expedido"      value='<c:out value="${lista.expedido}"/>' > 
	 <input type="hidden" name="accion"        value='entregar' >
	 <input type="hidden" name="sw"            value='1' >
       </td>
     </form>  
    </tr>
   </c:forEach>
</table>

<div style="font-size:15pt"> Pacientes Programas que estan Internados</div>
<br>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> FECHA </th>
    <th bgcolor="#F2F2F2"> NOMBRE PACIENTE </th>    
    <th bgcolor="#F2F2F2"> Edad </th> 
    <th bgcolor="#F2F2F2"> Tipo </th>
    <th bgcolor="#F2F2F2"> CONSULTORIO </th> 
    <th bgcolor="#F2F2F2"> MEDICO </th>  
    <th bgcolor="#F2F2F2"> RECETA </th>
    <th bgcolor="#F2F2F2"> ENTREGAR </th>  
    </tr>  
   <c:forEach var="listaI" items="${listarAtendidosI}" varStatus="contador">
    <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><fmt:formatDate value="${listaI.fecha}" pattern='dd/MM/yy HH:mm'/></td>    
       <td style="color:blue"><c:out value="${listaI.nombres}"/></td>
       <td align="center"><c:out value="${listaI.edad}"/></td>
       <c:forEach var="listaInt" items="${listaPacAfi}">
            <c:if test="${listaI.expedido == 'P' and listaI.id_seguro==listaInt.id_seguro}">
                <td><c:out value="${listaInt.seguro}"/></td>
            </c:if>
        </c:forEach>     
       <td><c:out value="${listaI.accion}"/></td>
       <td><c:out value="${listaI.nombre}"/></td>      
    <form name=formaPrei<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaPrei<c:out value="${contador.count}"/>.submit();">Previa</a></div>
  	 <input type="hidden" name="id_historial"   value='<c:out value="${listaI.id_historial}"/>' >
         <input type="hidden" name="id_historia"    value='<c:out value="${listaI.id_historia}"/>' >
         <input type="hidden" name="id_paciente"    value='<c:out value="${listaI.id_paciente}"/>' > 
         <input type="hidden" name="expedido"      value='<c:out value="${listaI.expedido}"/>' > 
         <input type="hidden" name="nombres"        value='<c:out value="${listaI.nombres}"/> '> 
         <input type="hidden" name="id_persona"     value='<c:out value="${listaI.id_persona}"/>' >
         <input type="hidden" name="nombre"         value='<c:out value="${listaI.nombre}"/>' >
	 <input type="hidden" name="accion"         value='previaint' >
	 <input type="hidden" name="sw"             value='1' >
       </td>
     </form>     
    <form name=formaMai<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarAtendidos.do"/>'>
       <td>     
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaMai<c:out value="${contador.count}"/>.submit();">Entregar</a></div>
  	 <input type="hidden" name="id_historial" value='<c:out value="${listaI.id_historial}"/>' >
         <input type="hidden" name="id_historia" value='<c:out value="${listaI.id_historia}"/>' >
         <input type="hidden" name="expedido"      value='<c:out value="${listaI.expedido}"/>' > 
         <input type="hidden" name="id_paciente"  value='<c:out value="${listaI.id_paciente}"/>' >
         <input type="hidden" name="id_persona"  value='<c:out value="${listaI.id_persona}"/>' >
	 <input type="hidden" name="accion"       value='entregarint' >
	 <input type="hidden" name="sw"           value='1' >
       </td>
     </form>
    </tr>  
   </c:forEach>
</table>

